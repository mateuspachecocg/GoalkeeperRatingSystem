package Model;

import java.util.ArrayList;

public class Outcome {

	private int id;
	private Team team;
	private Goalkeeper goalkeeper;
	private Pixel pivotDefenseArea;
	private Shot shot;
	private Goalpost goalpost;
	private ArrayList<Pixel> defenseArea;
	private boolean wasDefense;

	public Outcome(int id, Team team, Goalkeeper goalkeeper, Pixel pivotDefenseArea, Shot shot, Goalpost goalpost) {
		super();
		this.id = id;
		this.team = team;
		this.goalkeeper = goalkeeper;
		this.pivotDefenseArea = pivotDefenseArea;
		this.shot = shot;
		this.goalpost = goalpost;
		this.defenseArea = this.calcDefenseArea();
		this.wasDefense = this.verifyWasDefense();
	}

	private boolean verifyWasDefense() {

		boolean wasDefense = false;
		for (Pixel pixel : this.getDefenseArea()) {
			if (shot.getPixel().getPx() == pixel.getPx() && shot.getPixel().getPy() == pixel.getPy()) {
				// Checking if is a Margin Pixel
				if (pixel.isMargin()) {
					// Checking the Strengths
					if (goalkeeper.getStrength() >= shot.getStrength()) {
						wasDefense = true;
					} else {
						wasDefense = false;
					}
				} else {
					wasDefense = true;
				}
				break;
			}
		}

		return wasDefense;
	}

	private ArrayList<Pixel> calcDefenseArea() {

		ArrayList<Pixel> defenseArea = new ArrayList<Pixel>();

		int positionX = pivotDefenseArea.getPx();
		int positionY = pivotDefenseArea.getPy();

		int upperLimit = this.getGoalAreaUpperLimit();
		int leftLimit = this.getGoalAreaLeftLimit();

		defenseArea.add(pivotDefenseArea);

		for (int i = 1; i < this.goalkeeper.getGPA(); i++) {
			if (positionX - 1 > upperLimit) {
				positionX--;
				defenseArea.add(new Pixel(positionX, positionY));
			} else if (positionY + 1 < leftLimit) {
				positionY++;
				positionX = pivotDefenseArea.getPx();
				defenseArea.add(new Pixel(positionX, positionY, true));
			} else {
				break;
			}
		}

		// Marking the maginal pixels in the left
		int indexOf;
		for (int i = 0; i < defenseArea.size(); i++) {
			indexOf = defenseArea.size() - 1 - i;
			if (defenseArea.get(indexOf).getPy() == positionY) {
				defenseArea.get(indexOf).setIsMargin(true);
			}
		}

		return defenseArea;
	}

	private int getGoalAreaUpperLimit() {
		switch (shot.getQuadrant().getQuadrantNumber()) {
		case 1:
			return goalpost.getTopLeftCorner().getPx();
		case 2:
			return goalpost.getTopRigthCorner().getPx();
		case 3:
			return shot.getQuadrant().getTopLeftCorner().getPx() - 1;
		case 4:
			return shot.getQuadrant().getTopLeftCorner().getPx() - 1;
		default:
			return -1;
		}
	}

	private int getGoalAreaLeftLimit() {
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
	
	private ArrayList<Pixel> getDefenseArea() {
		return defenseArea;
	}
	
	public boolean wasDefense() {
		return wasDefense;
	}
	
	public Team getTeam() {
		return team;
	}

	public Goalkeeper getGoalkeeper() {
		return goalkeeper;
	}

	public Shot getShot() {
		return shot;
	}

}
