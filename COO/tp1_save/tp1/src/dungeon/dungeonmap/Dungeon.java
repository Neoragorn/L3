package dungeonmap;

import rooms.Room;
import rooms.Exit;
import rooms.Trap;
import player.Player;

import java.util.*;

public class Dungeon
{
	protected boolean hasExit = false;
	protected boolean gameIsFinished = false;
	protected final Scanner scanner = new Scanner(System.in);
	private Map<Integer, Room> dungeon_map;
	private Player player;
	private RandomDungeon randomMap;

	public Dungeon()
	{
		this.randomMap = new RandomDungeon();
		this.dungeon_map = randomMap.getMap();
	}	

	public RandomDungeon getRandomMap()
	{
		return this.randomMap;
	}

	public void createPlayer()
	{
			System.out.println("----------------------\n");	
			System.out.println("What is your name?");	
			System.out.print("> ");
			String line = scanner.nextLine();
			for (Map.Entry<Integer, Room> e : dungeon_map.entrySet())
				{
					int i = e.getKey();
					if (i == 0)
					{
						Room r = e.getValue();
						this.player = new Player(line, r);
					}
				}
	}

	public boolean gameIsFinished()
	{
		return gameIsLost() || gameIsWon();
	}

	public boolean gameIsLost()
	{
		if (player.getHealth() <= 0)
		{
			System.out.println("You are dead!");
			return true;
		}
		return false;
	}

	public boolean gameIsWon()
	{
		Object obj = this.player.getRoom();
		if (obj instanceof Exit)
		{
			System.out.println("It's a " + player.getRoom().getName() + " room!");
			return true;
		}
		return false;
	}

	public void interpretCommand(String command)
	{
		if (command.length() == 0)
		{
			System.out.println("You can put 'go *number of the door' or 'search'");
			return;
		}
		String[] cmd = command.split(" ");
		switch (cmd[0])
		{
			case "go":
				player.changeRoom(cmd);
				break;
			case "search":
				player.searchRoom();				
				break;
			case "situation":
				player.situation();
				break;
			case "take":
				player.takeItem(cmd);
				break;
			case "remove":
				player.removeItem(cmd);
				break;
			default:
			System.out.println("I don’t know what you mean");
			break;
		}
	}

	public void start()
	{
		do
		{			
			player.whereIsPlayer();
			player.play();
			interpretCommand(player.getCmd());									
		}
		while (!gameIsFinished());
			if (gameIsWon())
			System.out.println("You win!");
			else
			System.out.println("You loose!");
	}

	public void showEvery()
	{
		for (Map.Entry<Integer, Room> e : dungeon_map.entrySet())
		{
			int i = e.getKey();
			Room r = e.getValue();
			System.out.println("Room :" + r.getId() + " : It's a " + r.getName() + " room! and is linked to " + r.getNeighbour());
		}			
	}

	public static void main(String[] args)
	{
		Dungeon dungeon = new Dungeon();

		System.out.println("\n\n\nWelcome to the Dungeon ! This first level has " + dungeon.getRandomMap().getMaxId() + " rooms to discover!");
		System.out.println("Be careful of trap rooms and don't hesitaite to explore every room, you might get a surprise !");
		dungeon.createPlayer();
		dungeon.showEvery();
		dungeon.start();
	}
}