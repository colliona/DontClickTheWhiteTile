package bm.game.tile.model;

import java.util.List;

public class Row {
	List<Tile> tilesOfRow;
	private int y;
	private int rowHeight;
	private int blackTileIndex = -1;
	private boolean wasItClicked = false;

	/**
	 * Class constructor.
	 * 
	 * @param y
	 *            y coordinate of row
	 * @param tilesOfRow
	 *            list of tiles to be contained in the row
	 * @param rowHeight
	 *            height of the row
	 */
	public Row(int y, List<Tile> tilesOfRow, int rowHeight) {
		this.y = y;
		this.tilesOfRow = tilesOfRow;
		this.rowHeight = rowHeight;
	}
	
	

	public boolean wasItClicked() {
		return wasItClicked;
	}



	public void setClicked(boolean wasItClicked) {
		this.wasItClicked = wasItClicked;
	}



	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	public List<Tile> getTiles() {
		return tilesOfRow;
	}

	public int getBlackTileIndex() {
		return blackTileIndex;
	}

	/**
	 * Sets the black tile of the row.
	 * 
	 * @param indexOfBlackTile
	 *            - the index of the black tile, starts from 0 and goes from left to right.
	 */
	public void setBlackTile(int indexOfBlackTile) {
		blackTileIndex = indexOfBlackTile;
		for (int indexOfTile = 0; indexOfTile < tilesOfRow.size(); indexOfTile++) {
			if (indexOfTile == indexOfBlackTile) {
				tilesOfRow.get(indexOfTile).setBlack();
			} else {
				tilesOfRow.get(indexOfTile).setWhite();
			}
		}
	}

	/**
	 * Returns true if the row contains the given coordinate, false if not.
	 * 
	 * @param mouseYCoordinate
	 *            - y coordinate of mouse
	 */
	public boolean containsPoint(double mouseYCoordinate) {
		return y <= mouseYCoordinate && y + rowHeight > mouseYCoordinate;
	}
}
