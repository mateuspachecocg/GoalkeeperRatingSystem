package view;

public class ConsolePrinter {
	
	
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
}
