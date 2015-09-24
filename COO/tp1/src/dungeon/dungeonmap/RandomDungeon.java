package dungeonmap;

import monster.*;
import rooms.*;
import player.Player;
import java.util.*;

public class RandomDungeon
{
	protected int idMax = 1;
	protected boolean hasExit = false;
	private Map<Integer, Room> dungeon_map = new HashMap<Integer,Room>();
	private Map<Integer, Room> tmp_map = new HashMap<Integer, Room>();

	public RandomDungeon()
	{
		createMap();
	}

	public Map<Integer, Room> getMap()
	{
		return dungeon_map;
	}

	public int getMaxId()
	{
		return idMax;
	}

	public void copyMap()
	{
		for (Map.Entry<Integer, Room> e : this.tmp_map.entrySet())
		{
			if (e.getKey() != null && e.getValue() != null)
				this.dungeon_map.put(e.getKey(), e.getValue());
		}
		tmp_map.clear();
		return;
	}

	public Room randomRoom(int id, Room oldRoom)
	{
		int r = new Random().nextInt(11);
		Monster m = new Squeleton();
		
		if (r % 2 != 0)
			m.chooseName();			
		else
			m = null;
		if (r == 10 && this.hasExit == false)
		{
			this.hasExit = true;
			return (new Exit(id, oldRoom, m));
		}
		if (r == 2 || r == 6)
			return (new Trap(id, oldRoom, m));

		if (r == 7 || r == 4)
			return (new TreasureRoom(id, oldRoom, m));
		return (new Normal(id, oldRoom, m));
	}

	public void createLinkedRoom(Room room)
	{
		int r;
		if (this.idMax == 1)
			r = 4;
		else
			r = new Random().nextInt(3);
		if (r == 0)
			return;

		int i = 0;
		if (room.checkRoomExist() == false)
		{
			while (i != r)
			{
				Room newRoom = randomRoom(this.idMax, room);
				room.getNeighbour().put(this.idMax, newRoom);
				tmp_map.put(this.idMax, newRoom);
				this.idMax++;
				i++;
			}
		}
	}

	public void		createExit()
	{
		for (Map.Entry<Integer, Room> e : dungeon_map.entrySet())
			{
					int i = e.getKey();
					if (i == this.idMax - 1)
					{
						Room r = e.getValue();
						this.idMax++;
						Room exit = new Exit(this.idMax, r);
						tmp_map.put(this.idMax, exit);
						r.getNeighbour().put(this.idMax, exit);
					}
			}
		copyMap();
	}

	public void createMap()
	{
		int i = 0;
		Room entrance = new Entrance(0);
		this.dungeon_map.put(entrance.getId(), entrance);
		while (i != 2)
		{
			Iterator<Map.Entry<Integer, Room>> it = dungeon_map.entrySet().iterator();
			while (it.hasNext())
				{
					Map.Entry<Integer, Room> elem = it.next();
					Room r = elem.getValue();
					this.createLinkedRoom(r);
					r.setHasRoom(); 
				}
			copyMap();
			i++;
		}
		if (hasExit == false)
			createExit();
	}
}
