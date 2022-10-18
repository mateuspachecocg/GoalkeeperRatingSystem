package Model;

import java.util.ArrayList;

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

	public boolean wasDefense() {

		boolean notWasDefense = true;

		for (Pixel pixel : this.getDefenseArea()) {
			
		}

		return false;
	}

	public ArrayList<Pixel> getDefenseArea() {

		ArrayList<Pixel> defenseArea = new ArrayList<Pixel>();

		int positionX = pivotDefenseArea.getPx();
		int positionY = pivotDefenseArea.getPy();
		int upperLimit = this.getUpperLimit();
		int leftLimit = this.getLeftLimit();

		defenseArea.add(pivotDefenseArea);

		for (int i = 1; i < this.goalkeeper.getGPA(); i++) {
			if (positionX + 1 < upperLimit) {
				positionX++;
			} else if (positionY + 1 < leftLimit) {
				positionY++;
				positionX = pivotDefenseArea.getPx();
			} else {
				break;
			}
			// Check if is Margin
			

		}

		return defenseArea;
	}

	public int getUpperLimit() {
		switch (shot.getQuadrant().getQuadrantNumber()) {
		case 1:
			return goalpost.getTopLeftCorner().getPx();
		case 2:
			return goalpost.getTopRigthCorner().getPx();
		case 3:
			return shot.getQuadrant().getTopLeftCorner().getPx() + 1;
		case 4:
			return shot.getQuadrant().getTopLeftCorner().getPx() + 1;
		default:
			return -1;
		}
	}

	public int getLeftLimit() {
		switch (shot.getQuadrant().getQuadrantNumber()) {
		case 1:
			return shot.getQuadrant().getTopLeftCorner().getPy() + 1;
		case 2:
			return goalpost.getTopRigthCorner().getPy();
		case 3:
			return shot.getQuadrant().getBottomRigthCorner().getPy() + 1;
		case 4:
			return goalpost.getBottomRightCorner().getPy();
		default:
			return -1;
		}
	}

}
