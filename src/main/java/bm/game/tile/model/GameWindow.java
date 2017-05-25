package bm.game.tile.model;

import java.util.List;
import java.util.Random;

import bm.game.tile.controller.GameController;

public class GameWindow {
	private List<Row> rows;
	private Random random = new Random();
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

	public List<Row> getRows() {
		return rows;
	}

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
					
					delegate.actionOnWhiteTile();
				}
			} else {
				row.setY(row.getY() + difficultyMultiplier);
			}

		}
	}

	public void setDelegate(GameWindowDelegate gameWindowDelegate) {
		delegate = gameWindowDelegate;
		
	}

}
