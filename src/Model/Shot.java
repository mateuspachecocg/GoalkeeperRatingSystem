package Model;

public class Shot {
	private int id;
	private int strength;
	private Quadrant quadrant;
	private int px, py;

	public Shot(int id, int strength, Quadrant quadrant, int px, int py) {
		super();
		this.id = id;
		this.strength = strength;
		this.quadrant = quadrant;
		this.px = px;
		this.py = py;
	}

	public int getId() {
		return id;
	}

	public Quadrant getQuadrant() {
		return quadrant;
	}

	public int getPx() {
		return px;
	}

	public int getPy() {
		return py;
	}
	
	public int getStrength() {
		return strength;
	}

}
