package bm.game.tile.view;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import bm.game.tile.controller.GameController;
import bm.game.tile.model.GameWindow;
import bm.game.tile.model.Row;
import bm.game.tile.model.Tile;

public class GameView extends Group {

	GameViewDelegate delegate;
	Canvas canvas;
	GraphicsContext graphicsContext;

	/**
	 * Class constructor.
	 * 
	 * @param game - the controller of the next game 
	 */
	public GameView(GameController game) {
		this.delegate = game;
		setUp();
	}
	/**
	 * 
	 * @return - this view's reprezentative of the game controller
	 */
	public GameViewDelegate getDelegate() {
		return delegate;
	}
	/**
	 * 
	 * @param delegate - this view's reprezentative of the game controller
	 */
	public void setDelegate(GameViewDelegate delegate) {
		this.delegate = delegate;
	}
	/**
	 * Initializes the game view.
	 */
	private void setUp() {
		canvas = new Canvas(600, 600);
		graphicsContext = canvas.getGraphicsContext2D();

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, delegate.getClickHandler());
		getChildren().add(canvas);

	}
	/**
	 * Draws on the game window.
	 * @param gameWindow - main window of the game
	 */
	public void draw(GameWindow gameWindow) {

		for (Row row : gameWindow.getRows()) {
			for (Tile tile : row.getTiles()) {
				if (tile.isBlack()) {
					graphicsContext.setFill(Color.BLACK);
					graphicsContext.fillRect(tile.getX(), row.getY(), tile.getTileWidth(), row.getRowHeight());
				} else {
					graphicsContext.setFill(Color.WHITE);
					graphicsContext.fillRect(tile.getX(), row.getY(), tile.getTileWidth(), row.getRowHeight());
				}
			}
		}
	}

}
