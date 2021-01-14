package personnel;

public class DateImpossible extends Exception
{
	public DateImpossible()
	{
		System.err.println("La date d'arrivée doit être inférieur à la date de départ");
	}
}

