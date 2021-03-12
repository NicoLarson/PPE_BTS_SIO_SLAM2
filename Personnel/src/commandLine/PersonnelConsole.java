package commandLine;

import personnel.*;
import commandLineMenus.*;
import static commandLineMenus.rendering.examples.util.InOut.*;

public class PersonnelConsole
{
	private GestionPersonnel gestionPersonnel;
	LigueConsole ligueConsole;
	EmployeConsole employeConsole;
	
	public PersonnelConsole(GestionPersonnel gestionPersonnel)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = new EmployeConsole();
		this.ligueConsole = new LigueConsole(gestionPersonnel, employeConsole);
		
	}
	
	public void start()
	{
		menuPrincipal().start();
	}
	
	private Menu menuPrincipal()
	{
		Menu menu = new Menu("Gestion du personnel des ligues");
		menu.add(employeConsole.editerEmploye(gestionPersonnel.getRoot()));
		menu.add(ligueConsole.menuLigues());
		menu.add(menuQuitter());
		return menu;
	}

	private Menu menuQuitter()
	{
		Menu menu = new Menu("Quitter", "q");
		menu.add(quitterEtEnregistrer("1"));
		menu.add(quitterSansEnregistrer("2"));
		menu.addBack("q");
		return menu;
	}
	
	private Option quitterEtEnregistrer(String key)
	{
		return new Option("Quitter et enregistrer", key, 
				() -> 
				{
					try
					{
						gestionPersonnel.sauvegarder();
						Action.QUIT.optionSelected();
					} 
					catch (SauvegardeImpossible e)
					{
						System.out.println("Impossible d'effectuer la sauvegarde");
					}
				}
			);
	}
	
	private Option quitterSansEnregistrer(String key)
	{
		return new Option("Quitter sans enregistrer", key, Action.QUIT);
	}
	
	private boolean verifiePassword()
	{
		while (!gestionPersonnel.getRoot().checkPassword(getString("password : ")))
			System.out.println("Password incorrect.");
		return true;
	}
	
	public static void main(String[] args) throws SauvegardeImpossible
	{
		PersonnelConsole personnelConsole = 
				new PersonnelConsole(GestionPersonnel.getGestionPersonnel());
		personnelConsole.gestionPersonnel.rootBdd();
		if (personnelConsole.verifiePassword())
			personnelConsole.start();
	}
}
