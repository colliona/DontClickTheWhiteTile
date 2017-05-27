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
	/**
	 * 
	 * @return - the X coordinate of this tile
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x - the X coordinate of this tile
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Sets the color of this tile to be black.
	 */
	public void setBlack() {
		isBlack = true;
	}
	/**
	 * Sets the color of this tile to be white.
	 */
	public void setWhite() {
		isBlack = false;
	}
	/**
	 * 
	 * @return - whether the color of this tile is black, or not
	 */
	public boolean isBlack() {
		return isBlack;
	}
	/**
	 * 
	 * @return - the width of this tile
	 */
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
