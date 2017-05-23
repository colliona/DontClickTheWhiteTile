package bm.game.tile.view;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import javax.swing.border.TitledBorder;

import bm.game.tile.model.GameWindow;
import bm.game.tile.model.Row;
import bm.game.tile.model.Tile;

public class GameView extends Group {
	
	GameViewDelegate delegate;
	Canvas canvas;
	GraphicsContext graphicsContext;

	public GameView(String difficulty) {

//		switch (difficulty) {
//		case "easy":
//			rows = 20;
//			break;
//		case "medium":
//			rows = 50;
//			break;
//		case "hard":
//			rows = 100;
//			break;
//		case "insane":
//			rows = 300;
//		}

		setUp();
	}

	public GameViewDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(GameViewDelegate delegate) {
		this.delegate = delegate;
	}

	private void setUp() {
		canvas = new Canvas(600, 600);
		graphicsContext = canvas.getGraphicsContext2D();
		getChildren().add(canvas);
	}
	
	public void draw(GameWindow gameWindow) {
		
		for (Row row: gameWindow.getRows()) {
			for (Tile tile: row.getTiles()) {
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
