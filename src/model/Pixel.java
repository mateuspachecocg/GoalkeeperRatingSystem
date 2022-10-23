package model;

public class Pixel {
	private int px;
	private int py;
	
	
	public Pixel() {
		super();
	}
	
	public Pixel(int px, int py) {
		this.px = px;
		this.py = py;
	}
	
	public int getPx() {
		return px;
	}
	public int getPy() {
		return py;
	}
	
	public boolean sameCoordinates(Pixel px0) {
		if(this.px == px0.getPx() && this.py == px0.getPy()) {
			return true;
		} else {
			return false;
		}
	}

}
