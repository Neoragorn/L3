package items;

public class Fists extends Item
{
	protected int atk;
	protected int durability;
	protected String name;

	public Fists(int atk, int durability)
	{
		this.atk = atk;
		this.durability = durability;
		this.name = "Fists";
	}

	public int	  getAtkOrHealth()
	{
		return atk;
	}

	public String description()
	{
		return ("Fists : Atk " + atk + " durability " + durability);
	}

	public String getName()
	{
		return this.name;
	}
}