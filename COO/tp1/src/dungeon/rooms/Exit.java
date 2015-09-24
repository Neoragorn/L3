package rooms;

import monster.*;

public class Exit extends Room
{
	public Exit(int id)
	{
		super(id);
		this.name = "Exit";
	}

	public Exit(int id, Room previousRoom)
	{
		super(id, previousRoom);
		this.name = "Exit";
	}

	public Exit(int id, Room previousRoom, Monster monster)
	{
		super(id, previousRoom);
		this.name = "Exit";
		this.monster = monster;
	}
}