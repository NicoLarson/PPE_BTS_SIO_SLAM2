package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import personnel.*;

class testEmploye
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();

	@Test
	void isAdmin() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertFalse(employe.estAdmin(ligue));
		ligue.setAdministrateur(employe);
		assertTrue(employe.estAdmin(ligue));
	}
	
	@Test
	void isRoot() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = gestionPersonnel.getRoot();
		Employe employe2 = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertTrue(employe.estRoot());
		assertFalse(employe2.estRoot());
	}
	
	@Test
	void getNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertEquals("Richards", employe.getNom());
	}

	@Test
	void setNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		employe.setNom("John");
		assertEquals("John", employe.getNom());
	}
	
	@Test
	void getPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertEquals("Nigel", employe.getPrenom());
	}
	
	@Test
	void setPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		employe.setPrenom("Doe");
		assertEquals("Doe", employe.getPrenom());
	}
	
	@Test
	void getMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertEquals("n.richards@mail.ru", employe.getMail());
		
	}

	@Test
	void setMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		employe.setMail("n.richards@qq.com");
		assertEquals("n.richards@qq.com", employe.getMail());
	}
	
	@Test
	void getLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertEquals(ligue, employe.getLigue());
	}
	
	
	@Test
	void getDateDepart() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", LocalDate.parse("2018-12-28"), LocalDate.parse("2018-12-30"));
		assertEquals(LocalDate.parse("2018-12-30"), employe.getDateDepart());
	}
	
	@Test
	void setDateDepart() throws SauvegardeImpossible, DateImpossible
	{
		try {
			Ligue ligue = gestionPersonnel.addLigue("ffsc");
			Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", LocalDate.parse("2018-12-28"), LocalDate.parse("2018-12-30"));
			employe.setDateDepart(LocalDate.parse("2018-12-31"));
			assertEquals(LocalDate.parse("2018-12-31"), employe.getDateDepart());
			employe.setDateDepart(null);
			assertEquals(null, employe.getDateDepart());
			employe.setDateDepart(LocalDate.parse("2018-12-27"));
			fail("L'execption DateImpossible devrait être lance");
		} catch (DateImpossible err) {
			assertTrue(err instanceof DateImpossible);
		}
	}
	
	@Test
	void getDateArrive() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", LocalDate.parse("2018-12-28"), LocalDate.parse("2018-12-30"));
		assertEquals(LocalDate.parse("2018-12-28"), employe.getDateArrive());
	}
	
	@Test
	void setDateArrive() throws SauvegardeImpossible, DateImpossible
	{
		try {
			Ligue ligue = gestionPersonnel.addLigue("ffsc");
			Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", LocalDate.parse("2018-12-28"), LocalDate.parse("2018-12-30"));
			employe.setDateArrive(LocalDate.parse("2018-12-29"));
			assertEquals(LocalDate.parse("2018-12-29"), employe.getDateArrive());
			employe.setDateArrive(null);
			assertEquals(null, employe.getDateArrive());
			employe.setDateArrive(LocalDate.parse("2018-12-31"));
			fail("L'execption DateImpossible devrait être lance");
		} catch (DateImpossible err) {
			assertTrue(err instanceof DateImpossible);
		}
	}

	@Test
	void checkPassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		assertFalse(employe.checkPassword("admiN"));
		assertTrue(employe.checkPassword("admin"));
	}
	
	@Test
	void setPassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		employe.setPassword("ytreza");
		assertFalse(employe.checkPassword("admin"));
		assertTrue(employe.checkPassword("ytreza"));
	}
	
	@Test
	void remove() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		employe.remove();
		assertEquals(0, ligue.getEmployes().size());
	}
	
	@Test 
	void compareTo() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe firstEmploye = ligue.addEmploye("meme", "Nigel", "n.richards@mail.ru", "admin", null, null);
		Employe secondEmploye = ligue.addEmploye("meme", "Nigels", "n.richards@mail.ru", "admin2", null, null);
		Employe thirdEmploye = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin3", null, null);
		assertEquals(1, secondEmploye.compareTo(firstEmploye));
		assertEquals(27, secondEmploye.compareTo(thirdEmploye));
	}
	
	@Test
	void toStringEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ffsc");
		Employe employe = ligue.addEmploye("Richards", "Nigel", "n.richards@mail.ru", "admin", null, null);
		Employe rootEmploye = gestionPersonnel.getRoot();
		assertEquals("Richards Nigel n.richards@mail.ru (ffsc)", employe.toString());
		assertEquals("root   (super-utilisateur)", rootEmploye.toString());
	}
}
