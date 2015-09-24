package dungeon;

public class OmegaSword extends Sword
{

	private int magic;

	OmegaSword(int atk, int durability, int magic)
	{
		super(atk, durability);
		this.magic = magic;
		this.name = "OmegaSword";
	}
}