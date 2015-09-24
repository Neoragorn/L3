package rooms;

import items.*;
import java.util.*;

public class TreasureRoom extends Room
{
	public TreasureRoom(int id)
	{
		super(id);
		this.name = "Treasure";
		this.gold = new Random().nextInt(100);

		int t = new Random().nextInt(11);

		if (t <= 4 && t > 2)
			this.treasure.add(new Sword(4, 100));
		if (t > 8)
			this.treasure.add(new OmegaSword(12, 200, 5));
	}

	public TreasureRoom(int id, Room previousRoom)
	{
		super(id, previousRoom);
		this.name = "Treasure";
		this.gold = new Random().nextInt(100);

		int t = new Random().nextInt(11);

//		if (t <= 4 && t > 2)
			this.treasure.add(new Sword(4, 100));
		if (t > 8)
			this.treasure.add(new OmegaSword(12, 200, 5));
	}
}