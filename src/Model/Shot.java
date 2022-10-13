package Model;

public class Shot {
	private int id;
	private int strength;
	private int quadrant;
	private int px, py;

	public Shot(int id, int strength, int quadrant, int px, int py) {
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

	public int getQuadrant() {
		return quadrant;
	}

	public int getPx() {
		return px;
	}

	public int getPy() {
		return py;
	}

}
