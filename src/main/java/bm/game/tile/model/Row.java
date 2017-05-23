package bm.game.tile.model;

import java.util.List;

public class Row {
	List<Tile> tilesOfRow;
	private int y;
	private int rowHeight;
	private int blackTileIndex = -1;

	public Row(int y, List<Tile> tilesOfRow, int rowHeight) {
		this.y = y;
		this.tilesOfRow = tilesOfRow;
		this.rowHeight = rowHeight;
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

	public List<Tile> getTiles() {
		return tilesOfRow;
	}

	public int getBlackTileIndex() {
		return blackTileIndex;
	}
	
	

}
