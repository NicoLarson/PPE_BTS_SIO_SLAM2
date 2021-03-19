package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.DateImpossible;
import personnel.Employe;

public class EmployeConsole 
{
	private Option afficher(final Employe employe, String key)
	{
		return new Option("Afficher l'employé", key, () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "1");
			menu.add(afficher(employe, "1"));
			menu.add(changerNom(employe, "2"));
			menu.add(changerPrenom(employe, "3"));
			menu.add(changerMail(employe, "4"));
			menu.add(changerPassword(employe, "5"));
			menu.add(changerDateArrive(employe, "6"));
			menu.add(changerDateDepart(employe, "7"));
			menu.addBack("q");
			
			return menu;
	}

	private Option changerNom(final Employe employe, String key)
	{
		return new Option("Changer le nom", key, () -> 
		{
			employe.setNom(isRequired("Nouveau nom : "));
			
		});
	}
	
	private Option changerPrenom(final Employe employe, String key)
	{
		return new Option("Changer le prénom", key, () -> 
		{	employe.setPrenom(isRequired("Nouveau prénom : "));
			
		});
	}
	
	private Option changerMail(final Employe employe, String key)
	{
		return new Option("Changer le mail", key, () -> 
		{
			employe.setMail(isRequired("Nouveau mail : "));
			
		});
	}
	
	private Option changerPassword(final Employe employe, String key)
	{
		return new Option("Changer le password", key, () -> 
		{
			employe.setPassword(isRequired("Nouveau password : "));
			
		});
	}

	private Option changerDateArrive(final Employe employe, String key)
	{
		return new Option("Changer la date d'arrivée", key, () -> {
			boolean flag = false;
			
			while (!flag) {
				try {
					String date = getString("Date d'arrivée (format yyyy-mm-dd): ");
					
					LocalDate dateArrive = date.equals("") ? null : LocalDate.parse(date);
					employe.setDateArrive(dateArrive);
					flag = true;
				}
				catch (DateTimeParseException | DateImpossible e) {
					System.out.println("Date incorrect");
				}
			}
		});
	}

	private Option changerDateDepart(final Employe employe, String key)
	{
		return new Option("Changer la date de depart", key, () -> {
			boolean flag = false;
			
			while (!flag) {
				try {
					String date = getString("Date de départ (format yyyy-mm-dd): ");
					
					LocalDate dateDepart = date.equals("") ? null : LocalDate.parse(date);
					employe.setDateDepart(dateDepart);
					flag = true;
				}
				catch (DateTimeParseException | DateImpossible e) {
					System.out.println("Date incorrect");
				}
			}
		});
	}
	
	public static String isRequired(String message)
	{
		String value = "";
		
		while (value.length() == 0)
			value = getString(message);
		return value;
	}
}
