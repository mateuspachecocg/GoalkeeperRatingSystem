package Model;

public class Quadrant {
	private int quadrantNumber;
	private Pixel topLeftCorner;
	private Pixel bottomRigthCorner;
	
	public Quadrant(int id, Pixel topLeftCorner, Pixel bottomRigthCorner) {
		super();
		this.quadrantNumber = id;
		this.topLeftCorner = topLeftCorner;
		this.bottomRigthCorner = bottomRigthCorner;
	}

	public int getQuadrantNumber() {
		return quadrantNumber;
	}

	public Pixel getTopLeftCorner() {
		return topLeftCorner;
	}

	public Pixel getBottomRigthCorner() {
		return bottomRigthCorner;
	}
	
	
}
