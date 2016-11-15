package cs414.a4;

public class Dice {

	private int die1FaceValue;
	private int die2FaceValue;
	private int totalFaceValue;
	
	// Constructor, roll is called so the dice start at a random value
	public Dice() {
		roll();
	}

	public int roll() {
		die1FaceValue = (int) (Math.random() * 6 + 1);
		die2FaceValue = (int) (Math.random() * 6 + 1);
		totalFaceValue = die1FaceValue + die2FaceValue;
		return totalFaceValue;
	}

	public int getDie1() {
		// Return the number on the first die.
		return die1FaceValue;
	}

	public int getDie2() {
		// Return the number on the second die.
		return die2FaceValue;
	}


}
