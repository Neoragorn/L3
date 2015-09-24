package monster;

import java.util.Random;
import items.Item;

public class Monster
{
	protected int health;
	protected int attack;
	protected int gold;
	protected Item treasure;
	protected String name;
	protected String type;
	protected String[] nameChoice = {"Quentin", "Nicolas", "Amelie", "Ayleen", "Damien", "Julien", "Julian", "Laurent"};

	public Monster()
	{

	}

	public int 	getAttack()
	{
		return this.attack;
	}

	public int 	getHealth()
	{
		return this.health;
	}

	public void loseHealth(int atk)
	{
		this.health -= atk;
		if (this.health < 0)
			this.health = 0;
	}

	public String getType()
	{
		return this.type;
	}

	public String getName()
	{
	return this.name;		
	}

	public void chooseName()
	{
		int r = new Random().nextInt(8);;

		this.name = nameChoice[r];
	}
}
