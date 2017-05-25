package bm.game.tile.controller;

import java.util.Date;
import java.util.Random;
import java.util.Vector;

import bm.game.tile.model.GameWindow;
import bm.game.tile.model.GameWindowDelegate;
import bm.game.tile.model.Row;
import bm.game.tile.model.Tile;
import bm.game.tile.service.GameService;
import bm.game.tile.view.GameView;
import bm.game.tile.view.GameViewDelegate;
import bm.game.tile.view.Main;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The main controller class. This holds everything.
 */

public class GameController implements GameViewDelegate, GameWindowDelegate {
	private Main mainApp;
	private GameView gameView;
	private GameWindow gameWindow;
	public static AnimationTimer timer;
	private boolean isGameOver = false;
	private int difficultyMultiplier;
	private int score = 0;
	private int finalScore;
	private double averageOfClickSpeed;
	private Vector<Double> timeDifferenceBetweenBlackTilesClickedInSeconds = new Vector<Double>();
	private Date previousClickTime = null;

	public int getFinalScore() {
		return this.finalScore;
	}

	public double getAverageOfClickSpeed() {
		return this.averageOfClickSpeed;
	}

	/**
	 * Class constructor.
	 * 
	 * @param gameView
	 *            - the view to display
	 * @param gameWindow
	 *            - the window of display
	 */
	public GameController(GameView gameView, GameWindow gameWindow) {
		gameView.setDelegate(this);
		this.gameView = gameView;
		this.gameWindow = gameWindow;
	}

	/**
	 * Sets the details of the handling of the mouse click.
	 */
	@Override
	public EventHandler<? super MouseEvent> getClickHandler() {
		return mouseEvent -> {
			double maxY = -200;
			if (isGameOver) {
				return;
			}
			
			for (Row row : mainApp.getRows()) {
				if (!(row.getY() > maxY)) {
					continue;
				}
				
				if (row.wasItClicked() == false) {
					maxY = row.getY();
				}
			}
			for (Row row : mainApp.getRows()) {
				if (row.getY() == maxY) {
					if (row.containsPoint(mouseEvent.getY())) {
						for (Tile tile : row.getTiles()) {
							if (tile.containsPoint(mouseEvent.getX())) {
								if (tile.isBlack()) {
									actionOnBlackTile(row, tile);
									return;
								} else {
									actionOnWhiteTile();
									return;
								}
							}
						}
					}
				}
			}
			actionOnWhiteTile();
		};
	}

	/**
	 * Executes action for black tiles.
	 */
	private void actionOnBlackTile(Row row, Tile tile) {
		row.setClicked(true);
		tile.setWhite();
		score++;

		if (previousClickTime == null) {
			previousClickTime = new Date();
		} else {
			Date now = new Date();

			Double differenceInSeconds = (now.getTime() - previousClickTime.getTime()) / 1000.0;

			timeDifferenceBetweenBlackTilesClickedInSeconds.addElement(differenceInSeconds);

			this.previousClickTime = now;
		}

	}

	/**
	 * Executes action for white tiles.
	 */
	public void actionOnWhiteTile() {
		isGameOver = true;

		timer.stop();

		finalScore = score;

		averageOfClickSpeed = GameService.calculateAverageClickTime(timeDifferenceBetweenBlackTilesClickedInSeconds);

		mainApp.showGameOverWindow();

	}

	/**
	 * Generates tiles for a row.
	 */
	private void generateTiles() {
		Random random = new Random();

		for (Row row : gameWindow.getRows()) {
			int blackIndex = random.nextInt(4);

			row.setBlackTile(blackIndex);
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param difficulty
	 *            - the difficulty of the game
	 */
	public void startGame(String difficulty) {
		generateTiles();

		switch (difficulty) {
		case "easy":
			difficultyMultiplier = 3;
			break;
		case "medium":
			difficultyMultiplier = 6;
			break;
		case "hard":
			difficultyMultiplier = 10;
			break;
		case "insane":
			difficultyMultiplier = 25;
		}
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gameView.draw(gameWindow);
				gameWindow.nextFrame(difficultyMultiplier);
			}
		};

		timer.start();

	}

	/**
	 * Sets the gameview and gamewindow for this controller.
	 * 
	 * @param gameView
	 *            - the view to display
	 * @param gameWindow
	 *            - the window of the display
	 */
	public void setGameView(GameView gameView, GameWindow gameWindow) {
		gameView.setDelegate(this);
		gameWindow.setDelegate(this);
		this.gameView = gameView;
		this.gameWindow = gameWindow;
	}

	/**
	 * 
	 * @param mainApp
	 *            - reference to {@link bm.game.tile.view.Main}
	 */
	public GameController(Main mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * @return mainApp
	 */
	public Main getMainApp() {
		return this.mainApp;
	}

}
