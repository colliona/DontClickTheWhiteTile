package bm.game.tile.model;

import java.util.List;
import java.util.Random;

public class GameWindow {
	private List<Row> rows;
	private Random random = new Random();

	public GameWindow(List<Row> rows) {
		this.rows = rows;
	}

	public List<Row> getRows() {
		return rows;
	}
	
	public void nextFrame() {
		for (Row row: rows) {
			if (row.getY() >= 600) {
				row.setY(-200);
				row.setBlackTile(random.nextInt(4));
			} else {
				row.setY(row.getY() + 4);
			}
			
			System.out.println(row.getBlackTileIndex());
		}
	}

}
