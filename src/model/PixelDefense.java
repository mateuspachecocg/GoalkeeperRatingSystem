package model;

public class PixelDefense extends Pixel{
	private boolean isMargin;
	
	public PixelDefense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PixelDefense(int px, int py) {
		super(px, py);
		// TODO Auto-generated constructor stub
	}
	
	public PixelDefense(int px, int py, boolean isMargin) {
		super(px, py);
		this.isMargin = isMargin;
		// TODO Auto-generated constructor stub
	}

	public boolean isMargin() {
		return isMargin;
	}

	public void setMargin(boolean isMargin) {
		this.isMargin = isMargin;
	}
}
