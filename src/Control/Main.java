package Control;

import java.util.*;
import Model.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	
	
	Quadrant firstQuadrant = new Quadrant(1, new Pixel(0,0), new Pixel (4,8));
	Quadrant secondQuadrant = new Quadrant(2, new Pixel(0,9), new Pixel (4,17));
	Quadrant thirdQuadrant = new Quadrant(3, new Pixel(5,0), new Pixel (7,8));
	Quadrant fourQuadrant = new Quadrant(4, new Pixel(5,9), new Pixel (7,17));
	
	public static void main(String[] args) {
		ArrayList<Shot> bd_shots = loadShots();
		ArrayList<Goalkeeper> bd_goalkeepers = loadGoalkeepers();
		
		
	}

	public static ArrayList<Shot> loadShots() {
		int amountShot, shotId, shotStrength, shotQuadrant, shotPx, shotPy;
		ArrayList<Shot> shots = new ArrayList<Shot>();
		File fileObject;
		Scanner fileReader;

		try {
			fileObject = new File("bd_shots.txt");
			fileReader = new Scanner(fileObject);
			amountShot = fileReader.nextInt();

			for (int i = 0; i < amountShot; i++) {
				shotId = fileReader.nextInt();
				shotStrength = fileReader.nextInt();
				shotQuadrant = fileReader.nextInt();
				shotPx = fileReader.nextInt();
				shotPy = fileReader.nextInt();
				shots.add(new Shot(shotId, shotStrength, shotQuadrant, shotPx, shotPy));
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return shots;
	}

	public static ArrayList<Goalkeeper> loadGoalkeepers() {
		int amountGoalpeekers, goalpeekerId, goalkeeperSpeed, goalkeeperFlexibility, goalkeeperAgility,
				goalkeeperCoordination, goalkeeperStrength, goalkeeperBalance;
		String goalkeeperName;
		ArrayList<Goalkeeper> goalkeepers = new ArrayList<Goalkeeper>();
		File fileObject;
		Scanner fileReader;

		try {
			fileObject = new File("bd_goalpeekers.txt");
			fileReader = new Scanner(fileObject);
			amountGoalpeekers = fileReader.nextInt();

			for (int i = 0; i < amountGoalpeekers; i++) {
				goalpeekerId = fileReader.nextInt();
				goalkeeperName = fileReader.nextLine();
				goalkeeperSpeed = fileReader.nextInt();
				goalkeeperFlexibility = fileReader.nextInt();
				goalkeeperAgility = fileReader.nextInt();
				goalkeeperCoordination = fileReader.nextInt();
				goalkeeperStrength = fileReader.nextInt();
				goalkeeperBalance = fileReader.nextInt();
				goalkeepers.add(new Goalkeeper(goalpeekerId, goalkeeperName, goalkeeperSpeed, goalkeeperFlexibility,
						goalkeeperAgility, goalkeeperCoordination, goalkeeperStrength, goalkeeperBalance));
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return goalkeepers;

	}

	
}
