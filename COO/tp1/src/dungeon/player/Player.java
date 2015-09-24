package player;

import items.*;
import java.io.*;
import rooms.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Player
{
	private int 	atk;
	private String  name;
	private String  cmd;
	private int     health;
	private int 	evade;
	private int 	gold;
	private Room	currentRoom;
	private ArrayList<Item> inventory;
	private Item 	weapon;
	private Item 	tmpItem;
	private final Scanner scanner = new Scanner(System.in);

	public Player(String name, Room room)
	{
		this.weapon = new Fists(5, 500);
		this.name = name;
		this.currentRoom = room;
		this.health = 100;
		this.evade = 2;
		this.gold = 0;
		this.atk = 5;
		this.inventory = new ArrayList<Item>();
		this.inventory.add(new Potion());
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
		System.out.println("Your attack is " + this.atk + " and you're equipped with ");
		if (this.weapon == null)
			System.out.println("nothing");
		else
			System.out.println(this.weapon.getName());
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
			if (item[1].equalsIgnoreCase("gold"))
			{
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
			}
			else
				{
					for (Item o: currentRoom.getTreasure())
					{
						if (o.getName().equalsIgnoreCase(item[1]))
						{
							this.inventory.add(o);
							System.out.println("You have taken " + item[1]);
							this.tmpItem = o;
						}
					}
					if (tmpItem != null)
					{
						this.currentRoom.getTreasure().remove(this.tmpItem);
						tmpItem = null;
						return;
					}
					System.out.println("You cannot take this!");
					return;
				}
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
			for (Item o: inventory)
			{
				if (o.getName().equalsIgnoreCase(item[1]))
					tmpItem = o;
			}
			if (tmpItem != null)
			{
				if (tmpItem.getName().equals(this.weapon.getName()))
					{
						this.atk = 5;
						this.weapon = new Fists(5, 500);
					}
				this.inventory.remove(tmpItem);
				System.out.println("You dropped " + tmpItem.getName());
				tmpItem = null;
				return;
			}
			System.out.println("The item does not exist!");
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("The item does not exist!");	
		}

	}

	public void equipItem(String[] item)
	{
		try
		{
			for (Item o: inventory)
				if (o.getName().equalsIgnoreCase(item[1]))
				{
					this.weapon = o;
					if (this.atk == 5)
						this.atk += this.weapon.getAtkOrHealth();
					else
						this.atk = 5 + this.weapon.getAtkOrHealth();
					System.out.println("You equipped " + this.weapon.getName());
				}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("The item does not exist!");	
		}		
	}

	public void use(String[] command)
	{
		try
		{
			if (command[1].equalsIgnoreCase("Potion"))
			{
			 	for (Item o: inventory)
					if (o.getName().equalsIgnoreCase(command[1]))
					{
						System.out.println("You used a potion and recovered " + o.getAtkOrHealth() + " hp");
						this.health += o.getAtkOrHealth();
						if (this.health > 100)
							this.health = 100;
						inventory.remove(o);
						break;
					}
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("You cannot use this!");	
		}
	}

	public void fightSystem()
	{
		try
		{
			boolean fight = false;
			while (!fight)
			{
				Thread.sleep(500);
				System.out.println("You have " + this.health + " hp");
				System.out.println("The monster has " + this.currentRoom.getMonster().getHealth() + " hp");
				System.out.println("You attack with your " + this.weapon.getName());
				Thread.sleep(500);
				this.currentRoom.getMonster().loseHealth(this.weapon.getAtkOrHealth());
				System.out.println("You inflicted " + this.weapon.getAtkOrHealth() + " damages");
				if (this.currentRoom.getMonster().getHealth() <= 0)
				{
					System.out.println("You won the battle !");
					break;
				}
				Thread.sleep(2000);
				System.out.println("---------------------\nNow, it's my turn human!");
				Thread.sleep(1000);
				this.health -= this.currentRoom.getMonster().getAttack();
				System.out.println("You have taken " + this.currentRoom.getMonster().getAttack() + " damages\n--------------------------");
				if (this.health <= 0)
				{
					System.out.println("You lost the battle !");
					break;
				}
			}
		}
		catch (InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void updateSituation()
	{
		if (this.currentRoom instanceof Trap)
		{
			Trap room = (Trap)(currentRoom);
			this.health -= room.trapped(this.evade);
		}
		if (this.currentRoom.getMonster() != null)
		{
			System.out.println("A monster ! It's a " + this.currentRoom.getMonster().getType() + " called "
				+ this.currentRoom.getMonster().getName() + ". Time to fight!");
			fightSystem();
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
