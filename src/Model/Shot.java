package Model;

public class Shot {
	private int id;
	private int strength;
	private Quadrant quadrant;
	private Pixel pixel;

	public Shot(int id, int strength, Quadrant quadrant, Pixel pixel) {
		super();
		this.id = id;
		this.strength = strength;
		this.quadrant = quadrant;
		this.pixel = pixel;
	}

	public int getId() {
		return id;
	}

	public Quadrant getQuadrant() {
		return quadrant;
	}
	
	public Pixel getPixel() {
		return pixel;
	}
	
	public int getStrength() {
		return strength;
	}

}
