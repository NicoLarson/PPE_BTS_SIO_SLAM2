package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import personnel.*;

class testLigue
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();

	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}

	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty"); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	@Test
	void getNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		assertEquals("ffsc", ligue.getNom());
	}
	
	@Test
	void setNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		ligue.setNom("ffe");
		assertEquals("ffe", ligue.getNom());
	}
	
	@Test
	void toStringLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		assertEquals("ffsc", ligue.toString());
	}
	
	@Test
	void compareTo() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Ligue ligue2 = gestionPersonnel.addLigue("ffe");
		assertEquals(14, ligue.compareTo(ligue2));
	}
	
	@Test
	void getAdmin() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		assertEquals("root   (super-utilisateur)", ligue.getAdministrateur().toString());
	}
	
	@Test
	void setAdmin() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		ligue.setAdministrateur(employe);
		assertEquals(employe, ligue.getAdministrateur());
	}
	
	@Test
	void remove() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		int initSize = gestionPersonnel.getLigues().size();
		ligue.remove();
		assertEquals(initSize - 1, gestionPersonnel.getLigues().size());
	}
}