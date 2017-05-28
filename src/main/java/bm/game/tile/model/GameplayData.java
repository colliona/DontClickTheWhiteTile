package bm.game.tile.model;

/**
 * Stores the data of a game.
 * 
 * @author collion
 *
 */
public class GameplayData {
	/**
	 * The name of the current player.
	 */
	private String playerName;
	/**
	 * Difficulty level.
	 */
	private String difficulty;
	/**
	 * Final score.
	 */
	private int finalScore;
	/**
	 * Average of click speed.
	 */
	private double averageOfClickSpeed;

	/**
	 * Class constructor.
	 */
	public GameplayData() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param playerName
	 *            - the name of the player
	 */
	public GameplayData(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * 
	 * @return - the name of the player
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * 
	 * @param playerName
	 *            - the name of the player
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * 
	 * @return - the difficulty of the game
	 */
	public String getDifficulty() {
		return this.difficulty;
	}

	/**
	 * 
	 * @param difficulty
	 *            - the difficulty of the game
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * 
	 * @return - final score of the player
	 */
	public int getFinalScore() {
		return finalScore;
	}

	/**
	 * 
	 * @param finalScore
	 *            - final score of the player
	 */
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	/**
	 * 
	 * @return - the average of elapsed time between clicking two black tiles,
	 *         in seconds
	 */
	public double getAverageOfClickSpeed() {
		return averageOfClickSpeed;
	}

	/**
	 * 
	 * @param averageOfClickSpeed
	 *            - the average of elapsed time between clicking two black
	 *            tiles, in seconds
	 */
	public void setAverageOfClickSpeed(double averageOfClickSpeed) {
		this.averageOfClickSpeed = averageOfClickSpeed;
	}
}
