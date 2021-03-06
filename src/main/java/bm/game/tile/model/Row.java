package bm.game.tile.model;

import java.util.List;

/**
 * Class of a row.
 * @author collion
 *
 */
public class Row {
	/**
	 * List of the tiles of the row.
	 */
	List<Tile> tilesOfRow;
	/**
	 * Y coordinate of the row.
	 */
	private double y;
	/**
	 * Height of row.
	 */
	private int rowHeight;
	/**
	 * Infex of the black tile in the row.
	 */
	private int blackTileIndex = -1;
	/**
	 * True if the row was clicked in that lifecycle, false if not.
	 */
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

	/**
	 * 
	 * @return - whether the row was clicked in this lifecycle, or not
	 */
	public boolean wasItClicked() {
		return wasItClicked;
	}

	/**
	 * 
	 * @param wasItClicked
	 *            - whether the row was clicked in this lifecycle, or not
	 */
	public void setClicked(boolean wasItClicked) {
		this.wasItClicked = wasItClicked;
	}

	/**
	 * 
	 * @return - the Y coordinate of this row
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 *            - the Y coordinate of this row
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 * @return - the height of this row
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * 
	 * @param rowHeight
	 *            - the height of this row
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	/**
	 * 
	 * @return - the list of this row's tiles
	 */
	public List<Tile> getTiles() {
		return tilesOfRow;
	}

	/**
	 * 
	 * @return - the index of the black tile in this row, starts from 0
	 */
	public int getBlackTileIndex() {
		return blackTileIndex;
	}

	/**
	 * Sets the black tile of the row.
	 * 
	 * @param indexOfBlackTile
	 *            - the index of the black tile, starts from 0 and goes from
	 *            left to right.
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
	 * @return - true if row contains the point, false otherwise
	 */

	public boolean containsPoint(double mouseYCoordinate) {
		return y <= mouseYCoordinate && y + rowHeight > mouseYCoordinate;
	}
}
