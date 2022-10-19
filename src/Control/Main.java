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
	public static ArrayList<Team> bd_teams;

	public static void main(String[] args) {
		// Loading Data
		loadShots();
		loadGoalkeepers();
		loadTeams();

		Goalpost goalpost = new Goalpost(new Pixel(7, 1), new Pixel(1, 1), new Pixel(1, 16), new Pixel(7, 16));
		Coach coach = new Coach();

		// Training Session
		ArrayList<Outcome> trainingResults = trainingGoalkeepers(coach, goalpost);
		
		// Solutions for Questions
		showTeamsAverageDefense(trainingResults);
		
		
		
		System.out.println();
	}

	// Functions for Question 01, 02 e 03.
	public static void showTeamsAverageDefense(ArrayList<Outcome> results) {
		int defenseCount = 0, outCount = 0, goalTakenCount = 0;
		double average, bestAverage = 0.0;
		Goalkeeper teamBetterGPK;
		for (Team team : bd_teams) {
			teamBetterGPK = team.getGoalkeepers().get(0);
			System.out.println("Team " + team.getName() + ": ");
			for (Goalkeeper gpk : team.getGoalkeepers()) {
				for (Outcome otc : results) {
					if (otc.getGoalkeeper().getId() == gpk.getId()) {
						if (otc.wasDefense()) {
							defenseCount++;
						} else {
							if(otc.wasGoal()) {
								goalTakenCount++;
							} else {
								outCount++;
							}
							
						}
					}
				}
				average = (double) defenseCount / (1.0*(defenseCount + goalTakenCount));
				if(average >= bestAverage) {
					if(gpk.getStrength() >= teamBetterGPK.getStrength()) {
						teamBetterGPK = gpk;
					}
					bestAverage = average;
				}
				
				System.out.printf("    Goalkeeper: %s\t\tDEFENSE COUNT: %2.0f defenses per 100 shot and %d goals taked.\n", gpk.getName(),
						average*100, goalTakenCount);
				goalTakenCount = 0;
				defenseCount = 0;
				outCount = 0;
			}
			
			System.out.println("\n    RECOMMEDATION FOR THE FIRST-STRING GOALKEEPER: "+ teamBetterGPK.getName());

		}

	}

	public static ArrayList<Outcome> trainingGoalkeepers(Coach coach, Goalpost goalpost) {
		ArrayList<Outcome> outcomes = new ArrayList<Outcome>();
		int idCount = 1;
		Pixel pivotDefense;
		for (Team team : bd_teams) {
			for (Goalkeeper gpk : team.getGoalkeepers()) {
				for (Shot shot : bd_shots) {
					// Generating a PixelPivotDefenseArea
					pivotDefense = coach.getPivotDefenseArea(shot.getQuadrant(), goalpost);
					outcomes.add(new Outcome(idCount, team, gpk, pivotDefense, shot, goalpost));
					idCount++;
				}
			}
		}

		return outcomes;
	}

	public static void loadTeams() {

		Team brazil = new Team(1, "Brazil");
		Team belgium = new Team(2, "Belgium");
		Team argentina = new Team(3, "Argentina");
		Team france = new Team(4, "France");
		Team italy = new Team(5, "Italy");
		Team spain = new Team(6, "Spain");
		bd_teams = new ArrayList<Team>();
		bd_teams.add(brazil);
		bd_teams.add(belgium);
		bd_teams.add(argentina);
		bd_teams.add(france);
		bd_teams.add(italy);

		int k = 0;

		for (Team team : bd_teams) {
			for (int i = 0; i < 5; i++) {
				team.addGoalkeeper(bd_goalkeepers.get(k));
				k++;
			}
		}

		// bd_teams.add(spain);
		// bd_teams.get(5).addGoalkeeper(bd_goalkeepers.get(k));
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
