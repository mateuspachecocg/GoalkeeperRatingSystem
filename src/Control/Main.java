package Control;

import java.util.*;
import Model.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static Quadrant firstQuadrant = new Quadrant(1, new Pixel(0, 0), new Pixel(4, 8));
	public static Quadrant secondQuadrant = new Quadrant(2, new Pixel(0, 9), new Pixel(4, 17));
	public static Quadrant thirdQuadrant = new Quadrant(3, new Pixel(5, 0), new Pixel(7, 8));
	public static Quadrant forthQuadrant = new Quadrant(4, new Pixel(5, 9), new Pixel(7, 17));
	public static ArrayList<Shot> bd_shots;
	public static ArrayList<Goalkeeper> bd_goalkeepers;
	public static ArrayList<Team> bd_team;
	
	public static void main(String[] args) {
		 loadShots();
		 loadGoalkeepers();
		 loadTeams(); 
		Goalpost goalpost = new Goalpost(new Pixel(7,1),new Pixel(1,1),new Pixel(1,16), new Pixel(7,16));
		 
		 // Loading Goalkeepers to Teams.
		 
		 
		 // Instance a Goalkeeper Coach
		 for(Goalkeeper gpk: bd_goalkeepers) {
			 System.out.println("Name: "+gpk.getName()+" AAG: "+gpk.getGPA());
		 }
		 
		 
	}
	
	public static void loadTeams() {
		bd_team = new ArrayList<Team>(); 
		Team brazil = new Team(1, "Brazil", new ArrayList<Goalkeeper>(bd_goalkeepers.subList(0, 4)));
		Team belgium = new Team(2, "Belgium", new ArrayList<Goalkeeper>(bd_goalkeepers.subList(5, 9)));
		Team argentina = new Team(3, "Argentina", new ArrayList<Goalkeeper>(bd_goalkeepers.subList(10, 14)));
		Team france = new Team(4, "France", new ArrayList<Goalkeeper>(bd_goalkeepers.subList(15, 19)));
		Team italy = new Team(5, "Italy", new ArrayList<Goalkeeper>(bd_goalkeepers.subList(20, 24)));
		Team spain = new Team(6, "Spain", new ArrayList<Goalkeeper>(bd_goalkeepers.subList(25, 26)));
		bd_team.add(brazil);
		bd_team.add(belgium);
		bd_team.add(argentina);
		bd_team.add(france);
		bd_team.add(italy);
		bd_team.add(spain);
	}

	public static void loadShots() {
		int amountShot, shotId, shotStrength, numberOfQuadrant, shotPx, shotPy;
		File fileObject;
		Scanner fileReader;
		Quadrant shotQuadrant;
		bd_shots = new ArrayList<Shot>();

		try {
			fileObject = new File("bd_shots.txt");
			fileReader = new Scanner(fileObject);
			amountShot = fileReader.nextInt();

			for (int i = 0; i < amountShot; i++) {
				shotId = fileReader.nextInt();
				shotStrength = fileReader.nextInt();
				numberOfQuadrant = fileReader.nextInt();
				shotPx = fileReader.nextInt();
				shotPy = fileReader.nextInt();
				switch (numberOfQuadrant) {
				case 1:
					shotQuadrant = firstQuadrant;
					break;
				case 2:
					shotQuadrant = secondQuadrant;
					break;
				case 3:
					shotQuadrant = thirdQuadrant;
					break;
				case 4:
					shotQuadrant = forthQuadrant;
					break;
				default:
					shotQuadrant = null;
				}

				bd_shots.add(new Shot(shotId, shotStrength, shotQuadrant, new Pixel(shotPx, shotPy)));
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void loadGoalkeepers() {
		int amountGoalpeekers, goalpeekerId, goalkeeperSpeed, goalkeeperFlexibility, goalkeeperAgility,
				goalkeeperCoordination, goalkeeperStrength, goalkeeperBalance;
		String goalkeeperName;
		File fileObject;
		Scanner fileReader;
		bd_goalkeepers = new ArrayList<Goalkeeper>();
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
				bd_goalkeepers.add(new Goalkeeper(goalpeekerId, goalkeeperName, goalkeeperSpeed, goalkeeperFlexibility,
						goalkeeperAgility, goalkeeperCoordination, goalkeeperStrength, goalkeeperBalance));
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

}
