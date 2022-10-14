package Model;

public class Coach {

	public Pixel pivotDefenseArea(Quadrant quadrant, Goalpost goalpost) {
		int positionX = 0, positionY = 0;

		switch (quadrant.getQuadrantNumber()) {
		case 1:
			positionX = this.getRandomIntegerBetweenRange(goalpost.getTopLeftCorner().getPx(),
					quadrant.getBottomRigthCorner().getPx() + 1);
			positionY = this.getRandomIntegerBetweenRange(goalpost.getTopLeftCorner().getPy(),
					quadrant.getBottomRigthCorner().getPx() + 1);
			break;
		case 2:
			positionX = this.getRandomIntegerBetweenRange(goalpost.getBottomRightCorner().getPx(),
					quadrant.getBottomRigthCorner().getPx() + 1);
			positionY = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPy() - 1,
					goalpost.getTopRigthCorner().getPy());
			break;
		case 3:
			positionX = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPx() - 1,
					quadrant.getBottomRigthCorner().getPx() + 1);
			positionY = this.getRandomIntegerBetweenRange(goalpost.getBottomLeftCorner().getPy(),
					quadrant.getBottomRigthCorner().getPy() + 1);
			break;
		case 4:
			positionX = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPx() + 1,
					goalpost.getBottomRightCorner().getPx());
			positionY = this.getRandomIntegerBetweenRange(quadrant.getTopLeftCorner().getPy() - 1,
					goalpost.getBottomRightCorner().getPy() + 1);
			break;
		}

		return new Pixel(positionX, positionY);
	}

	private int getRandomIntegerBetweenRange(double min, double max) {
		int x = (int) ((int) (Math.random() * ((max - min) + 1)) + min);
		return x;
	}
}
