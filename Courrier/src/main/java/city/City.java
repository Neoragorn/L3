package city;


import letter.Letter;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class City
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Letter> postBox;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Inhabitant> inhabitants;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public City()
	{
		super();
		this.inhabitants = new ArrayList<Inhabitant>();
		this.postBox = new ArrayList<Letter>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public void addLetter(Letter letter)
	{
		this.postBox.add(letter);
	}

	public void removeLetter(Letter letter)
	{
		if (this.postBox.contains(letter))
			this.postBox.remove(letter);
	}

	public void sendLetter()
	{
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void distributeLetters()
	{
		// TODO implement me	
	}
	
}

