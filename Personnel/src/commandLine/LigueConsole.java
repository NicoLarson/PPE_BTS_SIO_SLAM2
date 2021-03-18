package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;

import personnel.*;

public class LigueConsole 
{
	private GestionPersonnel gestionPersonnel;
	private EmployeConsole employeConsole;

	public LigueConsole(GestionPersonnel gestionPersonnel, EmployeConsole employeConsole)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = employeConsole;
	}

	Menu menuLigues()
	{
		Menu menu = new Menu("Gérer les ligues", "2");
		menu.add(afficherLigues("1"));
		menu.add(ajouterLigue("2"));
		menu.add(selectionnerLigue("3"));
		menu.addBack("q");
		return menu;
	}

	private Option afficherLigues(String key)
	{
		return new Option("Afficher les ligues", key, () -> {System.out.println(gestionPersonnel.getLigues());});
	}

	private Option afficher(final Ligue ligue, String key)
	{
		return new Option("Afficher la ligue", key, 
				() -> 
				{
					System.out.println(ligue);
					System.out.println("administrée par " + ligue.getAdministrateur());
				}
		);
	}

	private Option afficherEmployes(final Ligue ligue, String key)
	{
		return new Option("Afficher les employes", key, () -> { 
			if(ligue.getEmployes().size() == 0)
			{
				System.out.println("Ligue vide");
			}
			for (Employe x: ligue.getEmployes())
				System.out.println(x);
		});
	}

	private Option ajouterLigue(String key)
	{
		return new Option("Ajouter une ligue", key, () -> 
		{
			try
			{
				gestionPersonnel.addLigue(getString("nom : "));
			}
			catch(SauvegardeImpossible exception)
			{
				System.err.println("Impossible de sauvegarder cette ligue");
			}
		});
	}
	
	private Menu editerLigue(Ligue ligue)
	{
		Menu menu = new Menu("Editer " + ligue.getNom());
		menu.add(afficher(ligue, "1"));
		menu.add(gererEmployes(ligue, "2"));
		menu.add(changerNom(ligue, "3"));
		menu.add(supprimer(ligue, "4"));
		menu.addBack("q");
		return menu;
	}

	private Option changerNom(final Ligue ligue, String key)
	{
		return new Option("Renommer", key, 
				() -> {
					ligue.setNom(getString("Nouveau nom : "));
					
					});
	}

	private List<Ligue> selectionnerLigue(String key)
	{
		List<Ligue> liste = new List<Ligue>("Sélectionner une ligue", key, 
				() -> new ArrayList<>(gestionPersonnel.getLigues()),
				(element) -> editerLigue(element));
		liste.addBack("q");
		return liste;
	}
	
	private Option ajouterEmploye(final Ligue ligue, String key)
	{
		return new Option("Ajouter un employé", key,
				() -> 
				{
					String nom = EmployeConsole.isRequired("nom : "),
					prenom = EmployeConsole.isRequired("Prenom : "),
					mail = EmployeConsole.isRequired("Mail : "),
					password = EmployeConsole.isRequired("Password : ");
					LocalDate dateArrive = getDate("Date d'arrivé (format yyyy-mm-dd): ");

					ligue.addEmploye(nom, prenom, mail, password, dateArrive, null);
				}
		);
	}
	
	private Menu gererEmployes(Ligue ligue, String key)
	{
		Menu menu = new Menu("Gérer les employés de " + ligue.getNom(), key);
		menu.add(afficherEmployes(ligue, "1"));
		menu.add(ajouterEmploye(ligue, "2"));
		menu.add(modifierEmploye(ligue, "3"));
		menu.add(changerAdministrateur(ligue, "4"));
		menu.add(supprimerEmploye(ligue, "5"));
		menu.addBack("q");
		return menu;
	}


	private List<Employe> supprimerEmploye(final Ligue ligue, String key)

	{
		List<Employe> liste =  new List<>("Supprimer un employé", key, 
				() -> new ArrayList<>(ligue.getEmployes()),
				(index, element) -> {element.remove();});
		liste.addBack("q");
		return liste;
	}
	
	private List<Employe> changerAdministrateur(final Ligue ligue, String key)
	{
		List<Employe> liste = new List<>("Changer Administrateur", key, 
				() -> new ArrayList<>(ligue.getEmployes()),
				(index, element) -> {
					ligue.setAdministrateur(element);
					ligue.changeAdmin(element);
				});
		liste.addBack("q");
		return liste;
	}		

	private List<Employe> modifierEmploye(final Ligue ligue, String key)
	{
		List<Employe> liste = new List<>("Modifier un employé", key, 
				() -> new ArrayList<>(ligue.getEmployes()),
				employeConsole.editerEmploye());
		liste.addBack("q");
		return liste;
	}
	
	private Option supprimer(Ligue ligue, String key)
	{
		return new Option("Supprimer", key, () -> {ligue.remove();});
	}
	
	private LocalDate getDate(String message)
	{
		while (true) {
			try {
				String date = getString(message);
				return date.equals("") ? null : LocalDate.parse(date);
			} catch (DateTimeParseException e) {
				System.out.println("Date incorrect");
			}
		}
	}
}
 