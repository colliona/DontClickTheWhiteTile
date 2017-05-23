package bm.game.tile.model;

public class Tile {
	private int x;
	private boolean isBlack = false;
	private int tileWidth;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Tile(int x, int tileWidth) {
		this.x = x;
		this.tileWidth = tileWidth;
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
	
	

}
