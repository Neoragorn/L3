package dungeon;

import java.util.ArrayList;

public class Normal extends Room
{
	public Normal(int id)
	{
		super(id);
		this.name = "Normal";
	}

	public Normal(int id, Room previousRoom)
	{
		super(id, previousRoom);
		this.name = "Normal";
	}
}