package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import personnel.*;

class testEmploye 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();

	@Test
	void isAdmin() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertEquals(false, employe.estAdmin(ligue));
		ligue.setAdministrateur(employe);
		assertEquals(true, employe.estAdmin(ligue));
	}
	
	@Test
	void isRoot() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = gestionPersonnel.getRoot();
		Employe employe2 = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertTrue(employe.estRoot());
		assertFalse(employe2.estRoot());
	}
	
	@Test
	void getNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertEquals("Richards", employe.getNom());
		
	}

	@Test
	void setNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		employe.setNom("John");
		assertEquals("John", employe.getNom());
	}
	
	@Test
	void getPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertEquals("Nigel", employe.getPrenom());
		
	}
	
	@Test
	void setPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		employe.setPrenom("Doe");
		assertEquals("Doe", employe.getPrenom());
	}
	
	@Test
	void getMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertEquals("n.richards@mail.ru", employe.getMail());
		
	}

	@Test
	void setMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		employe.setMail("n.richards@qq.com");
		assertEquals("n.richards@qq.com", employe.getMail());
	}
	
	@Test
	void getLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertEquals(ligue, employe.getLigue());
	}
	
	@Test
	void checkPassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		assertFalse(employe.checkPassword("admiN"));
		assertTrue(employe.checkPassword("admin"));
	}
	
	@Test
	void setPassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		employe.setPassword("ytreza");
		assertFalse(employe.checkPassword("admin"));
		assertTrue(employe.checkPassword("ytreza"));
	}
	
	@Test
	void remove() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		employe.remove();
		assertEquals(0, ligue.getEmployes().size());
	}
	
	@Test 
	void compareTo() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe firstEmploye = ligue.addEmploye("meme", "Nigel", "n.richards@mail.ru", "admin");
		Employe secondEmploye = ligue.addEmploye("meme", "Nigels", "n.richards@mail.ru", "admin2");
		Employe thirdEmploye = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin3");
		assertEquals(1, secondEmploye.compareTo(firstEmploye));
		assertEquals(27, secondEmploye.compareTo(thirdEmploye));
	}
	
	@Test
	void toStringEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin");
		Employe rootEmploye = gestionPersonnel.getRoot();
		assertEquals("Richards Nigel n.richards@mail.ru (ffsc)", employe.toString());
		assertEquals("root   (super-utilisateur)", rootEmploye.toString());
	}
}
