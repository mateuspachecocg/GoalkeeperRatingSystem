package Model;

public class Coach {

	public Pixel getPivotDefenseArea(Quadrant quadrant, Goalpost goalpost) {
		int positionX = 0, positionY = 0;

		switch (quadrant.getQuadrantNumber()) {
		case 1:
			positionX = this.getRandomIntegerBetweenRange(goalpost.getTopLeftCorner().getPx() + 1,
					quadrant.getBottomRigthCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(goalpost.getTopLeftCorner().getPy() + 1,
					quadrant.getBottomRigthCorner().getPx());
			break;
		case 2:
			positionX = this.getRandomIntegerBetweenRange(goalpost.getTopRigthCorner().getPx() + 1,
					quadrant.getBottomRigthCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPy(),
					goalpost.getTopRigthCorner().getPy() - 1);
			break;
		case 3:
			positionX = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPx(),
					quadrant.getBottomRigthCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(goalpost.getBottomLeftCorner().getPy() + 1,
					quadrant.getBottomRigthCorner().getPy());
			break;
		case 4:
			positionX = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPx(),
					goalpost.getBottomRightCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPy(),
					goalpost.getBottomRightCorner().getPy() - 1);
			break;
		}

		return new Pixel(positionX, positionY);
	}

	public int getUpperLimit(Quadrant quadrant, Goalpost goalpost) {
		switch (quadrant.getQuadrantNumber()) {
		case 1:
			return goalpost.getTopLeftCorner().getPx();
		case 2:
			return goalpost.getTopRigthCorner().getPx();
		case 3:
			return quadrant.getTopLeftCorner().getPx() + 1;
		case 4:
			return quadrant.getTopLeftCorner().getPx() + 1;
		default:
			return -1;
		}
	}

	public int getLeftLimit(Quadrant quadrant, Goalpost goalpost) {
		switch (quadrant.getQuadrantNumber()) {
		case 1:
			return quadrant.getTopLeftCorner().getPy() + 1;
		case 2:
			return goalpost.getTopRigthCorner().getPy();
		case 3:
			return quadrant.getBottomRigthCorner().getPy() + 1;
		case 4:
			return goalpost.getBottomRightCorner().getPy();
		default:
			return -1;
		}
	}

	private int getRandomIntegerBetweenRange(double min, double max) {
		int x = (int) ((int) (Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}
