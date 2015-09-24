package items;

public class Sword extends Item
{
	protected int atk;
	protected int durability;
	protected String name;

	public Sword(int atk, int durability)
	{
		this.atk = atk;
		this.durability = durability;
		this.name = "Sword";
	}

	public int	  getAtkOrHealth()
	{
		return atk;
	}

	public String description()
	{
		return ("Sword : Atk " + atk + " durability " + durability);
	}

	public String getName()
	{
		return this.name;
	}
}