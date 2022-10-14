package Model;

public class Outcome {
	
	private int id;
	private Team team;
	private Goalkeeper goalkeeper;
	private Pixel pivotDefenseArea;
	private Shot shot;
	private Goalpost goalpost;
	
	public Outcome(int id, Team team, Goalkeeper goalkeeper, Pixel pivotDefenseArea, Shot shot, Goalpost goalpost) {
		super();
		this.id = id;
		this.team = team;
		this.goalkeeper = goalkeeper;
		this.pivotDefenseArea = pivotDefenseArea;
		this.shot = shot;
		this.goalpost = goalpost;
	}
	
	public boolean wasGoal() {
		
		boolean rtn = true;
		
		if(shot.getPx())
		
		return rtn;
	}
	
	
	
	
	
	
}
