package bm.game.tile.model;

public class Tile {
	private int x;
	private boolean isBlack = false;
	private int tileWidth;

	/**
	 * Class constructor.
	 * 
	 * @param x
	 *            the x coordinate of the tile
	 * @param tileWidth
	 *            the width of the tile
	 */
	public Tile(int x, int tileWidth) {
		this.x = x;
		this.tileWidth = tileWidth;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setBlack() {
		isBlack = true;
	}

	public void setWhite() {
		isBlack = false;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Returns true if the tile contains the given coordinate, false if not.
	 * 
	 * @param mouseXCoordinate
	 *            - x coordinate of mouse
	 */
	public boolean containsPoint(double mouseYCoordinate) {
		return x <= mouseYCoordinate && x + tileWidth > mouseYCoordinate;
	}
}
