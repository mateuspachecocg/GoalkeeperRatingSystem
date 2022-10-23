package model;

public class Goalpost {
	private Pixel bottomLeftCorner;
	private Pixel topLeftCorner;
	private Pixel topRigthCorner;
	private Pixel bottomRightCorner;

	public Goalpost(Pixel bottomLeftCorner, Pixel topLeftCorner, Pixel topRigthCorner, Pixel bottomRightCorner) {
		super();
		this.bottomLeftCorner = bottomLeftCorner;
		this.topLeftCorner = topLeftCorner;
		this.topRigthCorner = topRigthCorner;
		this.bottomRightCorner = bottomRightCorner;
	}

	public Pixel getBottomLeftCorner() {
		return bottomLeftCorner;
	}

	public Pixel getTopLeftCorner() {
		return topLeftCorner;
	}

	public Pixel getTopRigthCorner() {
		return topRigthCorner;
	}

	public Pixel getBottomRightCorner() {
		return bottomRightCorner;
	}
}
