package Control;

import java.util.ArrayList;

import Model.Goalkeeper;

public class Main {

	public static void main(String[] args) {

	}

	public static ArrayList<Goalkeeper> loadGoalkeepers() {
		ArrayList<Goalkeeper> goalkeepers = new ArrayList<Goalkeeper>();
		goalkeepers.add(new Goalkeeper(1, "Patrick Skaggs", 5, 7, 8, 9, 6, 6));
		goalkeepers.add(new Goalkeeper(2, "Uehudal Hack", 9, 6, 8, 8, 7, 10));
		return null;

	}

}
