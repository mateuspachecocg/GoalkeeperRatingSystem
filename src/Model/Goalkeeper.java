package Model;

public class Goalkeeper implements Comparable<Goalkeeper>{
	private int id;
	private String name;
	private int speed;
	private int flexibility;
	private int agility;
	private int coordination;
	private int strength;
	private int balance;
	private int GPA;
	private int goalsTaken;
	private int numberOfDefenses;

	public Goalkeeper(int id, String name, int speed, int flexibility, int agility, int coordination, int strength,
			int balance) {
		this.id = id;
		this.name = name;
		this.speed = speed;
		this.flexibility = flexibility;
		this.agility = agility;
		this.coordination = coordination;
		this.strength = strength;
		this.balance = balance;
		this.GPA = this.calGPA();
	}

	// GPA is the goalkeeper's playing area
	private int calGPA() {
		int gpa = (this.speed * 3 + this.flexibility * 2 + this.agility * 3 + this.coordination * 2 + this.strength
				+ this.balance * 2) / 8;

		if (gpa > 0 && gpa < 17) {
			return gpa;
		} else {
			return -1;
		}
	}

	
	public int getGPA() {
		return GPA;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStrength() {
		return strength;
	}

	@Override
	public int compareTo(Goalkeeper gpk0) {
		if (this.goalsTaken < gpk0.getGoalsTaken()) {
			return -1;
		} else if(this.goalsTaken == gpk0.getGoalsTaken()) {
			if(this.strength > gpk0.getStrength()) {
				return -1;
			} else {
				return 0;
			}
		} else {
			return 1;
		}
	}

	public int getGoalsTaken() {
		return goalsTaken;
	}

	public void setGoalsTaken(int goalsTaken) {
		this.goalsTaken = goalsTaken;
	}
	
	public int getNumberOfDefenses() {
		return numberOfDefenses;
	}

	public void setNumberOfDefenses(int numberOfDefenses) {
		this.numberOfDefenses = numberOfDefenses;
	}
	
	public PixelDefense getPivotDefenseArea(Quadrant quadrant, Goalpost goalpost) {
		int positionX = 0, positionY = 0;

		switch (quadrant.getQuadrantNumber()) {
		case 1:
			positionX = this.getRandomIntegerBetweenRange(goalpost.getTopLeftCorner().getPx() + 1,
					quadrant.getBottomRigthCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(goalpost.getTopLeftCorner().getPy() + 1,
					quadrant.getBottomRigthCorner().getPx());
			break;
		case 2:
			positionX = this.getRandomIntegerBetweenRange(goalpost.getTopRigthCorner().getPx() + 1,
					quadrant.getBottomRigthCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPy(),
					goalpost.getTopRigthCorner().getPy() - 1);
			break;
		case 3:
			positionX = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPx(),
					quadrant.getBottomRigthCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(goalpost.getBottomLeftCorner().getPy() + 1,
					quadrant.getBottomRigthCorner().getPy());
			break;
		case 4:
			positionX = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPx(),
					goalpost.getBottomRightCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPy(),
					goalpost.getBottomRightCorner().getPy() - 1);
			break;
		}

		return new PixelDefense(positionX, positionY);
	}

	public int getUpperLimit(Quadrant quadrant, Goalpost goalpost) {
		switch (quadrant.getQuadrantNumber()) {
		case 1:
			return goalpost.getTopLeftCorner().getPx();
		case 2:
			return goalpost.getTopRigthCorner().getPx();
		case 3:
			return quadrant.getTopLeftCorner().getPx() + 1;
		case 4:
			return quadrant.getTopLeftCorner().getPx() + 1;
		default:
			return -1;
		}
	}

	public int getLeftLimit(Quadrant quadrant, Goalpost goalpost) {
		switch (quadrant.getQuadrantNumber()) {
		case 1:
			return quadrant.getTopLeftCorner().getPy() + 1;
		case 2:
			return goalpost.getTopRigthCorner().getPy();
		case 3:
			return quadrant.getBottomRigthCorner().getPy() + 1;
		case 4:
			return goalpost.getBottomRightCorner().getPy();
		default:
			return -1;
		}
	}

	private int getRandomIntegerBetweenRange(double min, double max) {
		int x = (int) ((int) (Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	
}
