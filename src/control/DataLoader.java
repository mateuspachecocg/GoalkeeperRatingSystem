package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Goalkeeper;
import model.Pixel;
import model.Quadrant;
import model.Shot;
import model.Team;

public class DataLoader {

	public ArrayList<Shot> loadShots(String DBName) {
		int amountShot, shotId, shotStrength, numberOfQuadrant, shotPx, shotPy;
		File fileObject;
		Scanner fileReader;
		Quadrant shotQuadrant;
		ArrayList<Shot> bd_shots = new ArrayList<Shot>();

		try {
			fileObject = new File(DBName);
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
					shotQuadrant = Main.firstQuadrant;
					break;
				case 2:
					shotQuadrant = Main.secondQuadrant;
					break;
				case 3:
					shotQuadrant = Main.thirdQuadrant;
					break;
				case 4:
					shotQuadrant = Main.forthQuadrant;
					break;
				default:
					shotQuadrant = null;
				}

				bd_shots.add(new Shot(shotId, shotStrength, shotQuadrant, new Pixel(shotPx, shotPy)));
			}
			fileReader.close();
			return bd_shots;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Goalkeeper> loadGoalkeepers(String DBName) {
		int amountGoalpeekers, goalpeekerId, goalkeeperSpeed, goalkeeperFlexibility, goalkeeperAgility,
				goalkeeperCoordination, goalkeeperStrength, goalkeeperBalance;
		String goalkeeperName;
		File fileObject;
		Scanner fileReader;
		ArrayList<Goalkeeper> bd_goalkeepers = new ArrayList<Goalkeeper>();
		try {
			fileObject = new File(DBName);
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
			return bd_goalkeepers;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Team> loadTeams(ArrayList<Goalkeeper> bd_goalkeepers) {
		ArrayList<Team> bd_teams = new ArrayList<Team>();
		Team brazil = new Team(1, "Brazil");
		Team belgium = new Team(2, "Belgium");
		Team argentina = new Team(3, "Argentina");
		Team france = new Team(4, "France");
		Team italy = new Team(5, "Italy");

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

		return bd_teams;
	}

}
