package dungeon;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Player
{
	private String  name;
	private String  cmd;
	private int     health;
	private int 	evade;
	private int 	gold;
	private Room	currentRoom;
	private ArrayList<Item> inventory;
	private Item 	tmpItem;
	private final Scanner scanner = new Scanner(System.in);

	public Player(String name, Room room)
	{
		this.name = name;
		this.currentRoom = room;
		this.health = 100;
		this.evade = 2;
		this.gold = 0;
		this.inventory = new ArrayList<Item>();
	}

	public void play()
	{
			System.out.println("What do you want to do?");	
			System.out.print("> ");
			this.cmd = scanner.nextLine();			
			System.out.println("----------------------\n");	
	}

	public void situation()
	{
		System.out.println("You currently have " + health + " hp and " + this.gold + " gold");
		System.out.print("In your inventory, you have: \n'");
		for (Item o: inventory)
			if (o != null)
				System.out.println(o.description() + "'");
		System.out.println("");
	}

	public void takeItem(String[] item)
	{
		try
		{
			if (item[1].equals("gold"))
				if (currentRoom.getGold() != 0)
				{
					this.gold += currentRoom.getGold();
					System.out.println("You have taken " + currentRoom.getGold() + " of the gold");
					currentRoom.noMoreGold();
					return;
				}
				else
					{
						System.out.println("There is no gold here");
						return;
					}
				else
				{
					for (Item o: currentRoom.getTreasure())
					{
						if (o.getName().equals(item[1]))
						{
							this.inventory.add(o);
							this.tmpItem = o;
						}
					}
					if (tmpItem != null)
						this.currentRoom.getTreasure().remove(this.tmpItem);
					tmpItem = null;
				}
			System.out.println("You have taken " + item[1]);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("The item does not exist!");
		}
	}

	public void removeItem(String[] item)
	{
		try
		{
			System.out.println("in remove ! ");
			for (Item o: inventory)
				if (o.getName().equals(item[1]))
					tmpItem = o;
				if (tmpItem != null)
					this.inventory.remove(tmpItem);
				System.out.println("You dropped " + tmpItem.getName());
				tmpItem = null;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("The item does not exist!");	
		}

	}

	public void updateSituation()
	{
		if (this.currentRoom instanceof Trap)
		{
			Trap room = (Trap)(currentRoom);
			this.health -= room.trapped(this.evade);
		}
	}

	public void	  changeRoom(String[] direction)
	{
		if (direction.length != 2)
		{
			System.out.println("You cannot go there !");
			System.out.println("----------------------\n");	
			return;
		}
		try
		{
			for (Map.Entry<Integer, Room> e : currentRoom.getNeighbour().entrySet())
			{
				if (Integer.parseInt(direction[1]) == e.getKey())
				{
					this.currentRoom = e.getValue();
					updateSituation();
					return;
				}
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("You must put a number!");
		}
		System.out.println("You cannot go there !");
		System.out.println("----------------------\n");	
	}

	public void  searchRoom()
	{
		System.out.println("You decide to search throught the room...Heres' what you found:\n");
		currentRoom.setSearched();
		ArrayList<Item> treasure = currentRoom.getTreasure();
		if (treasure.size() == 0)
			System.out.println("Nothing");
		else
		{
			for (Item o: treasure)
				System.out.println(o.getName());
		}
		if (currentRoom.getGold() != 0)
			System.out.println("\nYou found " + currentRoom.getGold() + " gold");
	}

	public String getCmd()
	{
		return this.cmd;
	}

	public int getHealth()
	{
		return this.health;
	}

	public String getName()
	{
		return this.name;		
	}

	public Room getRoom()
	{
		return this.currentRoom;
	}

	public void whereIsPlayer()
	{
		System.out.println("----------------------\n");	
		System.out.println("I am currently in the Room " + currentRoom.getId());
		System.out.println("The room where you are is a " + currentRoom.getName() + " room");
		currentRoom.showNeighbour();
	}
}
