package dungeon;

import java.util.*;

public class Trap extends Room
{
	public Trap(int id)
	{
		super(id);
		this.name = "Trap";
	}

	public Trap(int id, Room previousRoom)
	{
		super(id, previousRoom);
		this.name = "Trap";
	}

	public int trapped(int evade)
	{
		int r = new Random().nextInt(11);

		if (evade + r > 7)
		{
			System.out.println("You succesfully evaded the trap!\n");
			return 0;
		}
		else
		{
			int i = new Random().nextInt(21);
			System.out.println("Too bad, you took " + i + " damages!");
			return i;
		}
	}
}