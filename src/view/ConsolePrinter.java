package view;

import java.util.ArrayList;

import model.Goalpost;
import model.Pixel;
import model.PixelSpecial;
import model.Quadrant;

public class ConsolePrinter {
	
	public void welcomeMessage() {
		System.out.println("WELCOME TO THE GOALKEEPERS RATING SYSTEM");
	}
	
	public void startingTrainingMessage() {
		System.out.println("STARTING THE TRAINING SERSSION...");
	}
	
	public void finishTrainingMessage() {
		System.out.println("FINISH THE TRAINING SERSSION...");
	}
	public void mainMenu() {
		System.out.println("------------------------- CHOOSE A OPTION BELOW -------------------------");
		System.out.println("1 - SHOW TEAMS AVERAGE DEFENSE PER GOALKEEPER");
		System.out.println("2 - CLASSIFY SHOTS");
		System.out.println("3 - SHOW GOALS INT THE CORNER OF THE CROSSBAR");
		System.out.println("4 - RANKING THE BETTER GOALKEEPER PER TEAM");
		System.out.println("5 - SHOW INFORMATIONS ABOUT GOALKEEPERS");
		System.out.println("6 - QUADRANT WITH MORE BY GOALKEEPER ID");
		System.out.println("7 - SHOW GOALS BECAUSE LACK OF STRENGTH");
		System.out.println("8 - MAPING GOALS AND DEFENSES BY GOALKEEPER ID");
		System.out.println("0 - PARA SAIR DO PROGRAMA");
		System.out.print("ESCOLHA: ");

	}
	
	public void askForGoalkeeperID() {
		System.out.print("PLEASE ENTER GOALKEEPER ID: ");
	}
	
	public void printInformationByPixels(ArrayList<PixelSpecial> listPixel, Goalpost goalpost, Quadrant forthQuadrant) {
		int x, y;
		boolean isNotInPixelList;
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

					isNotInPixelList = true;
					for (PixelSpecial ps : listPixel) {
						if (ps.sameCoordinates(new Pixel(x, y))) {
							if (ps.getDefensesHere() > 0) {
								if (ps.getDefensesHere() == 1) {
									System.out.printf("|  X  ");
								} else {
									System.out.printf("| %dX  ", ps.getDefensesHere());
								}
							} else if (ps.getGoalsHere() > 0) {
								if (ps.getGoalsHere() == 1) {
									System.out.printf("|  *  ");
								} else {
									System.out.printf("| %d*  ", ps.getGoalsHere());
								}
							}
							isNotInPixelList = false;
							break;
						}
					}
					if (isNotInPixelList) {
						System.out.print("|     ");
					}

				}
			}
			System.out.println("|");
		}
	}
}
