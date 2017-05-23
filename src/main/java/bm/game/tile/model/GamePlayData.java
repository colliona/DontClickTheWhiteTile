package bm.game.tile.model;

import java.util.ArrayList;
import java.util.List;

public class GamePlayData {
	private String playerName;
	private int score;
	private double averageOfClickSpeed;
	private List<Double> timeDifferenceBetweenBlackTilesClickedInSeconds = new ArrayList<Double>();

	public void addTimeDifference(double timeDifference) {
		timeDifferenceBetweenBlackTilesClickedInSeconds.add(timeDifference);
	}

	public List<Double> getTimeDifferenceBetweenBlackTilesClickedInSeconds() {
		return timeDifferenceBetweenBlackTilesClickedInSeconds;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void addToScore(int score) {
		this.score += score;
	}

	public double getAverageOfClickSpeed() {
		return averageOfClickSpeed;
	}

	public void setAverageOfClickSpeed(double averageOfClickSpeed) {
		this.averageOfClickSpeed = averageOfClickSpeed;
	}

}
