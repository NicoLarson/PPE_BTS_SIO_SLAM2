package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			instruction = connection.prepareStatement("INSERT INTO employe (nom_emp, prenom_emp, mail_emp, password_emp, id_ligue) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getHashedPass());
			instruction.setInt(5, employe.getLigue().getId());
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
			instruction = connection.prepareStatement("UPDATE ligue SET nom_ligue = ? WHERE id_ligue = ?)");
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
	public void updateEmploye(Employe emp) throws SauvegardeImpossible 
	{
		
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement(
					"UPDATE ligue SET nom_emp = ?, prenom_emp = ?, mail_emp = ?, password_emp = ?, date_arrive = ?,"
					+ "date_depart = ?,admin_ligue = ?, super_admin = ?  WHERE id_emp = ?");
			instruction.setString(1, emp.getNom());
			instruction.setString(2, emp.getPrenom());
			instruction.setString(3, emp.getMail());
			instruction.setString(4, emp.getHashedPass());
			instruction.setDate(5, Date.valueOf(emp.getDateArrive()));
			instruction.setDate(6, Date.valueOf(emp.getDateDepart()));
			instruction.setBoolean(7, emp.estAdmin(emp.getLigue()));
			instruction.setBoolean(8, emp.estRoot());
			instruction.setInt(9, emp.getId());
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
			instruction = connection.prepareStatement("DELETE FROM employe WHERE id_emp = ? LIMIT 1");
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
	public void deleteLigue(Ligue ligue) throws SauvegardeImpossible {
		
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = ? LIMIT 1");
			instruction.setInt(1, ligue.getId());
			instruction.executeUpdate();
			System.out.println("Ligue " + ligue.getNom() + " supprimé");
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
			throw new SauvegardeImpossible(e);
		}
		
	}
}
