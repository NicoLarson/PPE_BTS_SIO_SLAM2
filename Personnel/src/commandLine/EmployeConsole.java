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
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employé", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateArrive(employe));
			menu.add(changerDateDepart(employe));
			menu.addBack("q");
			return menu;
	}

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	private Option changerDateArrive(final Employe employe)
	{
		return new Option("Changer la date d'arrivée", "y", () -> {
			LocalDate dateArrive = null;
			
			while (dateArrive == null) {
				try {
					dateArrive = LocalDate.parse(getString("Date arrive :"));
					employe.setDateArrive(dateArrive);
				}
				catch (DateImpossible e) {
					e.printStackTrace();
				}
				catch (DateTimeParseException e) {
					System.out.println("Format incorrect");
				}
			}
		});
	}
	private Option changerDateDepart(final Employe employe)
	{
		return new Option("Changer la date de depart", "u", () -> {
			LocalDate dateDepart = null;
			
			while (dateDepart == null) {
				try {
					dateDepart = LocalDate.parse(getString("Date de départ :"));
					employe.setDateArrive(dateDepart);
				}
				catch (DateImpossible e) {
					e.printStackTrace();
				}
				catch (DateTimeParseException e) {
					System.out.println("Format incorrect");
				}
			}
		});
	}
	

}
