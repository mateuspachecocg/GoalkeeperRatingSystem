package Model;

public class Goalkeeper {
	private int id;
	private String name;
	private int speed;
	private int flexibility;
	private int agility;
	private int coordination;
	private int strength;
	private int balance;

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
	}

	// GPA is the goalkeeper's playing area
	public int getGPA() {
		int gpa = (this.speed * 3 + this.flexibility * 2 + this.agility * 3 + this.coordination * 2 + this.strength
				+ this.balance * 2) / 8;

		if (gpa > 0 && gpa < 17) {
			return gpa;
		} else {
			return -1;
		}
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

	
}
