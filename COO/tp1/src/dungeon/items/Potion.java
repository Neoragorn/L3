package items;


public class Potion extends Item
{
	private String name = "Potion";
	private int 	healthpower = 35;

	public String description()
	{
		return ("Potion : Healthpower " + healthpower);
	}

	public String getName()
	{
		return this.name;
	}

	public int getAtkOrHealth()
	{
		return this.healthpower;
	}
}