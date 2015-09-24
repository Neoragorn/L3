package rooms;

import monster.*;
import java.util.ArrayList;
import java.util.Random;

public class Normal extends Room
{
	public Normal(int id)
	{
		super(id);
		this.name = "Normal";
		this.gold = new Random().nextInt(20);
	}

	public Normal(int id, Room previousRoom)
	{
		super(id, previousRoom);
		this.name = "Normal";
				this.gold = new Random().nextInt(20);
	}

	public Normal(int id, Room previousRoom, Monster monster)
	{
		super(id, previousRoom);
		this.name = "Normal";
		this.gold = new Random().nextInt(20);
			this.monster = monster;
	}
}