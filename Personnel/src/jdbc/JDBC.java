package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;

import personnel.*;

public class JDBC implements Passerelle
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		try 
		{
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
		
			for(Ligue ligue : gestionPersonnel.getLigues()) {
				String req = "SELECT * FROM employe WHERE id_ligue = ";
				req += ligue.getId();
				Statement instr = connection.createStatement();
				ResultSet emp = instr.executeQuery(req);
		
				
				while (emp.next())
				{
					
					int id = emp.getInt("id_emp");
					String
						nom = emp.getString("nom_emp"),
						prenom = emp.getString("prenom_emp"), 
						mail = emp.getString("mail_emp"), 
						psw = emp.getString("password_emp");
					
					LocalDate 
						arrive = LocalDate.parse(emp.getString("date_arrive")),
						depart = (emp.getString("date_depart") != null) ? LocalDate.parse(emp.getString("date_depart")) : null;
					
					boolean 
						admin = (1 == emp.getInt("admin_ligue"));
					
					Employe employe = ligue.addEmploye(nom, prenom, mail, psw, arrive, depart, id);
					
					if (admin)
						ligue.setAdministrateur(employe);
				
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (nom_ligue) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("INSERT INTO employe (nom_emp, prenom_emp, mail_emp, password_emp, date_arrive, id_ligue) VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getPass());
			instruction.setString(5, String.valueOf(employe.getDateArrive()));
			instruction.setInt(6, employe.getLigue().getId());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	/**
	 * Update a ligue
	 * @param ligue ligue to update
	 * @throws Exception If an error occurs
	 */

	@Override
	public void updateLigue(Ligue ligue) throws SauvegardeImpossible 
	{
		
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE ligue SET nom_ligue = ? WHERE id_ligue = ?");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}

	

	@Override
	public void deleteEmploye(Employe emp) throws SauvegardeImpossible {
		
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM employe WHERE id_emp = ?");
			instruction.setInt(1, emp.getId());
			instruction.executeUpdate();
			System.out.println("Employé " + emp.getNom() + " supprimé");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
		
	}

	@Override
	public void deleteLigue(Ligue ligue) throws SauvegardeImpossible 
	{	
		try
		{
			PreparedStatement tableLigue;
			tableLigue = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = ?");
			tableLigue.setInt(1, ligue.getId());
			tableLigue.executeUpdate();
			System.out.println("Ligue " + ligue.getNom() + " supprimé");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}

	@Override
	public void updateEmploye(Employe emp, String column) throws SauvegardeImpossible 
	{
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE employe SET " + column + "= ? WHERE id_emp = ?");
	
			Map <String, String> map = Map.of(
					"nom_emp", emp.getNom(),
					"prenom_emp", emp.getPrenom(),
					"mail_emp", emp.getMail(),
					"password_emp", emp.getPass(),
					"date_arrive", String.valueOf(emp.getDateArrive()),
					"date_depart", String.valueOf(emp.getDateDepart())
			);

			instruction.setString(1, map.get(column));
			instruction.setInt(2, emp.getId());
			instruction.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
	}

	@Override
	public void newAdmin(Employe employe) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement tableEmploye;
			tableEmploye = connection.prepareStatement("UPDATE employe SET admin_ligue = (CASE WHEN id_emp = ? THEN 1 WHEN id_emp <> ? THEN 0 END) WHERE id_ligue = ?");
			tableEmploye.setInt(1, employe.getId());
			tableEmploye.setInt(2, employe.getId());
			tableEmploye.setInt(3, employe.getLigue().getId());
			tableEmploye.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
	}
}

