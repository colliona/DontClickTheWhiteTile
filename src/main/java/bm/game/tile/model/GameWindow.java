package bm.game.tile.model;

import java.util.List;
import java.util.Random;

/**
 * Class for game window.
 * 
 * @author collion
 *
 */
public class GameWindow {
	/**
	 * List of rows of the game.
	 */
	private List<Row> rows;
	/**
	 * Random number.
	 */
	private Random random = new Random();
	/**
	 * Delegate for the game controller of the game.
	 */
	private GameWindowDelegate delegate;

	/**
	 * Class constructor.
	 * 
	 * @param rows
	 *            list of rows to be displayed
	 */
	public GameWindow(List<Row> rows) {
		this.rows = rows;
	}

	/**
	 * 
	 * @return - the rows of the game
	 */
	public List<Row> getRows() {
		return rows;
	}

	/**
	 * 
	 * @param difficultyMultiplier
	 *            - the value of Y offset for rows
	 */
	public void nextFrame(int difficultyMultiplier) {
		for (Row row : rows) {
			if (row.getY() >= 600) {
				boolean isGameOver = false;
				for (Tile tile : row.getTiles()) {
					isGameOver = tile.isBlack();
					if (isGameOver)
						break;
				}

				if (!isGameOver) {
					row.setClicked(false);
					row.setY(-200);
					row.setBlackTile(random.nextInt(row.getTiles().size()));
				} else {

					delegate.endThisGame();
				}
			} else {
				row.setY(row.getY() + difficultyMultiplier);
			}

		}
	}

	/**
	 * 
	 * @param gameWindowDelegate
	 *            - the game window delegate of the game
	 */
	public void setDelegate(GameWindowDelegate gameWindowDelegate) {
		delegate = gameWindowDelegate;

	}

}
