package Model;

public class Goalkeeper {
	private int id;
	private String firstName;
	private String secondName;
	private int speed;
	private int flexibility;
	private int agility;
	private int coordination;
	private int strength;
	private int balance;
	
	// GPA is the goalkeeper's playing area
	public int getGPA() {
		int gpa = (this.speed*3+this.flexibility*2+this.agility*3+
				this.coordination*2+this.strength+this.balance*2)/8;
		
		if (gpa > 0 && gpa < 17) {
			return gpa;
		} else {
			return -1;
		}
	}
	
	
}
