package rooms;

import items.Item;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Room
{
	protected  Map<Integer, Room> doors = new HashMap<Integer, Room>();
	protected  boolean hasroom = false;
	protected  String name;
	protected  int		id;
	protected  String  desc;
	protected String   doordesc;
	protected ArrayList<Item> treasure;
	protected int 	  gold;
	protected boolean searched = false;

	public Room(int id)
	{
		this.id = id;
		this.desc = "Id " + id;
		this.treasure = new ArrayList<Item>();
	}

	public Room(int id, Room previousRoom)
	{
		this.doors.put(previousRoom.getId(), previousRoom);
		this.desc = "This is the Room " + id;
		this.id = id;
		this.treasure = new ArrayList<Item>();
	}

	public Room(Room copy)
	{
		this.id = copy.id;
		this.desc = copy.desc;
		this.treasure = copy.treasure;
		this.searched = copy.searched;
		this.name = copy.name;
		this.doors = copy.doors;
		this.gold = copy.gold;
		this.hasroom = copy.hasroom;
		this.doordesc = copy.doordesc;
	}

	public String	  showDesc()
	{
		return this.desc;
	}

	public Map<Integer, Room> getNeighbour()
	{
		return this.doors;
	}

	public void showNeighbour()
	{
		System.out.print("This door will lead to Room : ");
		for (Map.Entry<Integer, Room> e : doors.entrySet())
			System.out.print(e.getKey() + ", ");
		System.out.println();
	}

	public void noMoreGold()
	{
		this.gold = 0;
	}

	public int getGold()
	{
		return gold;
	}

	public ArrayList<Item> getTreasure()
	{
		return treasure;
	}

	public void setSearched()
	{
		this.searched = true;
	}

	public boolean checkRoomExist()
	{
		return this.hasroom;
	}

	public void setHasRoom()
	{
		this.hasroom = true;
	}

	public void	 addNeighbour(Room neightbor)
	{
		this.doors.put(neightbor.getId(), neightbor);
	}

	public String getName()
	{
		return this.name;
	}	

	public int	getId()
	{
		return this.id;
	}
}