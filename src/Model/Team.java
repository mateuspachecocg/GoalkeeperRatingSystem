package Model;

import java.util.ArrayList;

public class Team {
	private int id;
	private String name;
	private ArrayList<Goalkeeper> goalkeepers;

	public Team(String name) {
		super();
		this.name = name;
		this.goalkeepers = new ArrayList<Goalkeeper>();
	}
	
	public Team(int id, String name, ArrayList<Goalkeeper> goalkeepers) {
		super();
		this.id = id;
		this.name = name;
		this.goalkeepers = new ArrayList<Goalkeeper>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Goalkeeper> getGoalkeepers() {
		return goalkeepers;
	}

	public void addGoalkeeper(Goalkeeper g) {
		this.goalkeepers.add(g);
	}

}
