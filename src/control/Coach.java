package control;

import java.util.ArrayList;

import model.Goalkeeper;
import model.Goalpost;
import model.Outcome;
import model.PixelDefense;
import model.Shot;
import model.Team;

public class Coach {
	
	public ArrayList<Outcome> trainingGoalkeepers(ArrayList<Team> bd_teams, ArrayList<Shot> bd_shots, Goalpost goalpost) {
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
	
	public void setGoalAndDefense(ArrayList<Outcome> results, ArrayList<Goalkeeper> bd_goalkeepers) {
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
}
