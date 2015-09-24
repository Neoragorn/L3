package rooms;

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
}