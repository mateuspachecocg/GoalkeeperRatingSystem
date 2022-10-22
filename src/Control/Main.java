package Control;

import java.util.*;
import Model.*;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

		// Adding shots in the Corner of the Goalpost
		bd_shots.add(new Shot(31, 8, firstQuadrant, new Pixel(2, 2)));
		bd_shots.add(new Shot(32, 8, secondQuadrant, new Pixel(2, 15)));

		// Instancing the Goalpost parameters.
		Goalpost goalpost = new Goalpost(new Pixel(7, 1), new Pixel(1, 1), new Pixel(1, 16), new Pixel(7, 16));

		System.out.println("WELCOME TO THE GOALKEEPERS RATING SYSTEM");
		System.out.println("STARTING THE TRAINING SERSSION...");

		// Training Session
		ArrayList<Outcome> trainingResults = trainingGoalkeepers(goalpost);
		// Counting the Defenses for Each Goalkeeper
		setGoalAndDefense(trainingResults);

		System.out.println("FINISH THE TRAINING SERSSION...");

		// Criando interface de cadastro
		Scanner input = new Scanner(System.in);

		int control = 1;
		while (control != 0) {
			mainMenu();
			control = Integer.parseInt(input.nextLine());
			switch (control) {
			case 1:
				showTeamsAverageDefense(trainingResults);
				break;
			case 2:
				classifyShots(goalpost);
				break;
			case 3:
				showGoalsInTheCornerOfCrossbar(trainingResults, goalpost);
				break;
			case 4:
				teamsWithBetterGoalkeepers(trainingResults);
				break;
			case 5:
				showInformationsAboutGoalkeepers();
				break;
			case 6:
				System.out.print("PLEASE ENTER GOALKEEPER ID: ");
				quadrantWithMoreGoals(Integer.parseInt(input.nextLine()), trainingResults);
				break;
			case 7:
				showGoalBecauseLackOfStrength(trainingResults);
				break;
			case 8:
				System.out.print("PLEASE ENTER GOALKEEPER ID: ");
				showEventsGoalkeeperByID(Integer.parseInt(input.nextLine()), trainingResults, goalpost);
				break;
			case 0:
				control = 0;
				break;
			default:
				continue;
			}
			System.out.println();
		}

		input.close();

		System.out.println();
	}

	// Method for Question10
	public static void showEventsGoalkeeperByID(int idGpk, ArrayList<Outcome> results, Goalpost goalpost) {
		Goalkeeper gpk = null;

		boolean goalkeeperDoNotExist = true;
		for (Goalkeeper g : bd_goalkeepers) {
			if (g.getId() == idGpk) {
				gpk = g;
				goalkeeperDoNotExist = false;
				break;
			}
		}

		if (goalkeeperDoNotExist) {
			System.out.println("NOT FIND GOALKEEPER WITH ID " + idGpk);
		} else {
			int x, y;
			boolean wasFound;
			ArrayList<PixelSpecial> listPixel = new ArrayList<PixelSpecial>();
			for (Outcome out : results) {
				if ((out.wasDefense() || out.wasGoal()) && out.getGoalkeeper().getId() == gpk.getId()) {
					// Verify if the pixel already was created
					wasFound = false;
					for (PixelSpecial pixel : listPixel) {
						if (pixel.sameCoordinates(out.getShot().getPixel())) {
							wasFound = true;
							if (out.wasGoal()) {
								pixel.plusGoalHere();
							} else {
								pixel.plusDefenseHere();
							}
						}
					}

					if (!wasFound) {
						PixelSpecial newPixel = new PixelSpecial(out.getShot().getPixel());
						if (out.wasDefense()) {
							newPixel.plusDefenseHere();
						} else {
							newPixel.plusGoalHere();
						}
						listPixel.add(newPixel);
					}
				}
			}
			boolean isInPixelList;
			System.out.print("      ");
			for (y = 0; y <= forthQuadrant.getBottomRigthCorner().getPy(); y++) {
				System.out.printf("  %-2d  ", y);
			}
			System.out.println();
			for (x = 0; x <= forthQuadrant.getBottomRigthCorner().getPx(); x++) {
				System.out.print("      ");
				for (y = 0; y <= forthQuadrant.getBottomRigthCorner().getPy(); y++) {
					System.out.print("------");
				}
				System.out.println();
				System.out.printf("  %-2d  ", x);
				for (y = 0; y <= forthQuadrant.getBottomRigthCorner().getPy(); y++) {
					if ((y < goalpost.getTopLeftCorner().getPy() || y > goalpost.getTopRigthCorner().getPy()
							|| x < goalpost.getTopLeftCorner().getPx())) {
						System.out.print("| OT  ");
					} else if (y == goalpost.getTopLeftCorner().getPy() || y == goalpost.getTopRigthCorner().getPy()
							|| x == goalpost.getTopLeftCorner().getPx()) {
						System.out.print("| GP  ");
					} else {
						isInPixelList = false;
						for (PixelSpecial ps : listPixel) {
							if (ps.sameCoordinates(new Pixel(x, y))) {
								if (ps.getDefensesHere() > 0 || ps.getGoalsHere() > 0) {
									if (ps.getDefensesHere() == 1) {
										System.out.printf("|  X  ");
									} else {
										System.out.printf("| %dX  ", ps.getDefensesHere());
									}
								} else {
									if (ps.getGoalsHere() == 1) {
										System.out.printf("|  *  ");
									} else {
										System.out.printf("| %d*  ", ps.getGoalsHere());
									}
								}
							}
						}
						if (!isInPixelList) {
							System.out.print("|     ");
						}

					}
				}
				System.out.println("|");
			}

//			System.out.printf(
//					"GOALS TAKEN PER QUADRANT\nTHE GOALKEEPER: %-16s\n\tFIRST : %-2d\n\tSECOND: %-2d\n\tTHIRD : %-2d\n\tFORTH : %-2d\n",
//					gpk.getName(), firstQuadCount, secondQuadCount, thirdQuadCount, forthQuadCount);
		}

	}

	// Method for Question09
	public static void quadrantWithMoreGoals(int idGpk, ArrayList<Outcome> results) {
		Goalkeeper gpk = null;

		boolean goalkeeperDoNotExist = true;
		for (Goalkeeper g : bd_goalkeepers) {
			if (g.getId() == idGpk) {
				gpk = g;
				goalkeeperDoNotExist = false;
				break;
			}
		}

		if (goalkeeperDoNotExist) {
			System.out.println("NOT FIND GOALKEEPER WITH ID " + idGpk);
		} else {
			int firstQuadCount = 0, secondQuadCount = 0, thirdQuadCount = 0, forthQuadCount = 0;
			for (Outcome out : results) {
				if (out.getGoalkeeper().getId() == gpk.getId() && out.wasGoal()) {
					switch (out.getShot().getQuadrant().getQuadrantNumber()) {
					case 1:
						firstQuadCount++;
						break;
					case 2:
						secondQuadCount++;
						break;
					case 3:
						thirdQuadCount++;
						break;
					case 4:
						forthQuadCount++;
						break;
					}
				}
			}

			System.out.printf(
					"GOALS TAKEN PER QUADRANT\nTHE GOALKEEPER: %-16s\n\tFIRST : %-2d\n\tSECOND: %-2d\n\tTHIRD : %-2d\n\tFORTH : %-2d\n",
					gpk.getName(), firstQuadCount, secondQuadCount, thirdQuadCount, forthQuadCount);
		}

	}

	// Method for Question08
	public static void showInformationsAboutGoalkeepers() {
		String teamName;
		for (Goalkeeper goalkeeper : bd_goalkeepers) {
			teamName = "";
			for (Team team : bd_teams) {
				if (team.getGoalkeepers().contains(goalkeeper)) {
					teamName = team.getName();
					break;

				}
			}
			System.out.printf("NAME: %-18s\tTEAM: %-15s \tGOALS TAKEN: %-3d\tDEFENSES: %-3d\tAGG: %-3d\n",
					goalkeeper.getName(), teamName, goalkeeper.getGoalsTaken(), goalkeeper.getNumberOfDefenses(),
					goalkeeper.getGPA());
		}
	}

	// Method for Question07
	public static void showGoalBecauseLackOfStrength(ArrayList<Outcome> results) {
		for (Outcome out : results) {
			if (out.wasGoal()) {
				if (out.getShot().getStrength() > out.getGoalkeeper().getStrength()) {
					System.out.printf("OUTCOME ID: %-5d \tGOALKEEPER: %-16s \tSHOT ID: %-3d\n", out.getId(),
							out.getGoalkeeper().getName(), out.getShot().getId());
				}
			}
		}
	}

	// Method for Question 06
	public static void teamsWithBetterGoalkeepers(ArrayList<Outcome> results) {
		String teamName;
		ArrayList<Goalkeeper> bestGoalkeeperTeam = new ArrayList<Goalkeeper>();
		for (Team team : bd_teams) {
			bestGoalkeeperTeam.add(Collections.min(team.getGoalkeepers()));
		}

		bestGoalkeeperTeam.sort(null);
		System.out.println("\n\n TEAMS WITH BETTER GOALPEEKERS ");
		for (int i = 0; i < bd_teams.size(); i++) {
			teamName = "";
			for (Team team : bd_teams) {
				if (team.getGoalkeepers().contains(bestGoalkeeperTeam.get(i))) {
					teamName = team.getName();
					break;
				}
			}
			System.out.printf("%-2d - TEAM: %-10s\tGOALKEEPER: %-16s\tGOALS TAKEN: %d\n", (i + 1), teamName,
					bestGoalkeeperTeam.get(i).getName(), bestGoalkeeperTeam.get(i).getGoalsTaken());
		}

	}

	// Method for Question 05
	public static void showGoalsInTheCornerOfCrossbar(ArrayList<Outcome> results, Goalpost goalpost) {
		boolean testResult;
		int shotPositionX, shotPositionY;
		String side;
		System.out.println("LIST OF OUTCOMES WITH GOAL IN THE CORN CROSSBAR");
		for (Outcome out : results) {

			if (out.wasGoal() && out.getShot().getQuadrant().getQuadrantNumber() < 3) {
				shotPositionX = out.getShot().getPixel().getPx();
				shotPositionY = out.getShot().getPixel().getPy();

				testResult = (shotPositionX == goalpost.getTopLeftCorner().getPx() + 1)
						|| (shotPositionX == goalpost.getTopRigthCorner().getPx() + 1);
				testResult = testResult && (shotPositionY == goalpost.getTopLeftCorner().getPy() + 1
						|| shotPositionY == goalpost.getTopRigthCorner().getPy() - 1);

				if (testResult) {
					side = out.getShot().getQuadrant().getQuadrantNumber() == 1 ? "LEFT" : "RIGTH";
					System.out.printf("OUTCOME ID	: %-5d \tGOALKEEPER: %-16s \tSHOT ID: %-3d\tSIDE: %s\n",
							out.getId(), out.getGoalkeeper().getName(), out.getShot().getId(), side);
				}
			}
		}
	}

	// Method for Question 04
	public static void classifyShots(Goalpost goalpost) {
		int outCount = 0, leftGoalpostCount = 0, RigthGoalpostCount = 0, crossbarCount = 0, insideCount = 0;

		for (Shot shot : bd_shots) {
			int shotPositionX = shot.getPixel().getPx(), shotPositionY = shot.getPixel().getPy();

			Quadrant shotQuadrant = shot.getQuadrant();

			switch (shotQuadrant.getQuadrantNumber()) {
			case 1:
				if (shotPositionY == goalpost.getTopLeftCorner().getPy()
						&& shotPositionX > shotQuadrant.getTopLeftCorner().getPx()) {
					leftGoalpostCount++;
				} else if (shotPositionX == goalpost.getTopLeftCorner().getPx()
						&& shotPositionY > goalpost.getTopLeftCorner().getPy()) {
					crossbarCount++;
				} else if (shotPositionX < goalpost.getTopLeftCorner().getPx()
						|| shotPositionY < goalpost.getTopLeftCorner().getPy()) {
					outCount++;
				} else {
					insideCount++;
				}
				break;
			case 2:
				if (shotPositionY == goalpost.getTopRigthCorner().getPy()
						&& shotPositionX > shotQuadrant.getTopLeftCorner().getPx()) {
					RigthGoalpostCount++;
				} else if (shotPositionX == goalpost.getTopRigthCorner().getPx()
						&& shotPositionY < goalpost.getTopRigthCorner().getPy()) {
					crossbarCount++;
				} else if (shotPositionX < goalpost.getTopLeftCorner().getPx()
						|| shotPositionY > goalpost.getTopLeftCorner().getPy()) {
					outCount++;
				} else {
					insideCount++;
				}
				break;
			case 3:
				if (shotPositionY == goalpost.getBottomLeftCorner().getPy()) {
					leftGoalpostCount++;
				} else if (shotPositionY < goalpost.getBottomLeftCorner().getPy()) {
					outCount++;
				} else {
					insideCount++;
				}
				break;
			case 4:
				if (shotPositionY == goalpost.getBottomRightCorner().getPy()) {
					RigthGoalpostCount++;
				} else if (shotPositionY > goalpost.getBottomRightCorner().getPy()) {
					outCount++;
				} else {
					insideCount++;
				}
				break;
			}
		}

		System.out.printf(
				"\t\tSHOT STATISTIC\n" + "\tCROSSBAR  : %-3d\n" + "\tPOST-LEFT : %-3d\n" + "\tPOST-RIGHT: %-3d\n"
						+ "\tOUT       : %-3d\n" + "\tINSIDE    : %-3d\n",
				crossbarCount, leftGoalpostCount, RigthGoalpostCount, outCount, insideCount);
	}

	// Functions for Question 01, 02 e 03.
	public static void showTeamsAverageDefense(ArrayList<Outcome> results) {
		double averageDefenses;
		for (Team team : bd_teams) {

			System.out.println("Team " + team.getName() + ": ");

			for (Goalkeeper currentGoalkeeper : team.getGoalkeepers()) {

				averageDefenses = currentGoalkeeper.getNumberOfDefenses()
						/ ((1.0) * (currentGoalkeeper.getGoalsTaken() + currentGoalkeeper.getNumberOfDefenses()));
				System.out.printf(
						"    Goalkeeper: %-17s    DEFENSE COUNT: %2.0f defenses per 100 shot and %d goals taked.\n",
						currentGoalkeeper.getName(), averageDefenses * 100, currentGoalkeeper.getGoalsTaken());
			}
			System.out.println("\n    RECOMMEDATION FOR THE FIRST-STRING GOALKEEPER: "
					+ Collections.min(team.getGoalkeepers()).getName());
		}
	}

	public static ArrayList<Outcome> trainingGoalkeepers(Goalpost goalpost) {
		ArrayList<Outcome> outcomes = new ArrayList<Outcome>();
		int idCount = 1;
		PixelDefense pivotDefense;
		for (Team team : bd_teams) {
			for (Goalkeeper gpk : team.getGoalkeepers()) {
				for (Shot shot : bd_shots) {
					// Generating a PixelPivotDefenseArea
					pivotDefense = gpk.getPivotDefenseArea(shot.getQuadrant(), goalpost);

					outcomes.add(new Outcome(idCount, team, gpk, pivotDefense, shot, goalpost));

					idCount++;
				}
			}
		}

		return outcomes;
	}

	public static void setGoalAndDefense(ArrayList<Outcome> results) {
		int defenseCount = 0, goalTakenCount = 0;

		for (Goalkeeper currentGoalkeeper : bd_goalkeepers) {

			for (Outcome otc : results) {
				if (otc.getGoalkeeper().getId() == currentGoalkeeper.getId()) {
					if (otc.wasDefense()) {
						defenseCount++;
					} else {
						if (otc.wasGoal()) {
							goalTakenCount++;
						}
					}
				}
			}
			currentGoalkeeper.setGoalsTaken(goalTakenCount);
			currentGoalkeeper.setNumberOfDefenses(defenseCount);
			goalTakenCount = 0;
			defenseCount = 0;
		}
	}

	public static void loadTeams() {

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

	public static void mainMenu() {
		System.out.println("------------------------- CHOOSE A OPTION BELOW -------------------------");
		System.out.println("1 - SHOW TEAMS AVERAGE DEFENSE PER GOALKEEPER");
		System.out.println("2 - CLASSIFY SHOTS");
		System.out.println("3 - SHOW GOALS INT THE CORNER OF THE CROSSBAR");
		System.out.println("4 - RANKING THE BETTER GOALKEEPER PER TEAM");
		System.out.println("5 - SHOW INFORMATIONS ABOUT GOALKEEPERS");
		System.out.println("6 - QUADRANT WITH MORE BY GOALKEEPER ID");
		System.out.println("7 - SHOW GOALS BECAUSE LACK OF STRENGTH");
		System.out.println("8 - MAPING GOALS AND DEFENSES MORE BY GOALKEEPER ID");
		System.out.println("0 - PARA SAIR DO PROGRAMA");
		System.out.print("ESCOLHA: ");

	}
}
