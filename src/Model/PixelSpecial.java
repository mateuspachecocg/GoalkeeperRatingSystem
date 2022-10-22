package Model;

public class PixelSpecial extends Pixel{

	private int defensesHere;
	private int goalsHere;
	
	public PixelSpecial(Pixel pixel) {
		super(pixel.getPx(), pixel.getPy());
		defensesHere = 0;
		goalsHere = 0;
	}
	
	public PixelSpecial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PixelSpecial(int px, int py) {
		super(px, py);
		defensesHere = 0;
		goalsHere = 0;
		// TODO Auto-generated constructor stub
	}

	public int getDefensesHere() {
		return defensesHere;
	}

	public int getGoalsHere() {
		return goalsHere;
	}
	
	public void plusDefenseHere() {
		defensesHere++;
	}
	
	public void plusGoalHere() {
		goalsHere++;
	}
	
	
	
	
	
}
