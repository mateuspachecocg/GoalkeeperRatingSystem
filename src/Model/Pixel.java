package Model;

public class Pixel {
	private int px;
	private int py;
	private boolean isMargin;
	
	public Pixel() {
		super();
	}
	
	public Pixel(int px, int py, boolean isMargin) {
		super();
		this.px = px;
		this.py = py;
		this.isMargin = isMargin;
	}

	public Pixel(int px, int py) {
		super();
		this.px = px;
		this.py = py;
		this.isMargin = false;
	}
	public int getPx() {
		return px;
	}
	public int getPy() {
		return py;
	}

	public boolean isMargin() {
		return isMargin;
	}
	
	
	
	
}
