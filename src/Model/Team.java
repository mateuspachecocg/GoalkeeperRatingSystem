package Model;

public class Team {
	private String name;
	private Goalkeeper goalkeepers[];
	
	public Goalkeeper getGoalkeeper(int index) {
		return this.goalkeepers[index];
	}
}
