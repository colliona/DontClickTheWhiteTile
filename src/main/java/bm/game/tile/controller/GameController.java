package bm.game.tile.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bm.game.tile.DAO.GamePlayDataDAO;
import bm.game.tile.model.GameplayData;
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
 * @author collion
 *
 */
public class GameController implements GameViewDelegate, GameWindowDelegate {
	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger("GameController.class");
	/**
	 * Main application.
	 */
	private Main mainApp;
	/**
	 * Game view.
	 */
	private GameView gameView;
	/**
	 * Game window.
	 */
	private GameWindow gameWindow;
	/**
	 * Animates the view.
	 */
	public static AnimationTimer timer;
	/**
	 * True if game is over, false if not.
	 */
	private boolean isGameOver = false;
	/**
	 * Decides the measurement level of difficulty.
	 */
	private int difficultyMultiplier;
	/**
	 * Current score of player.
	 */
	private int score = 0;
	/**
	 * Final score of player.
	 */
	private int finalScore;
	/**
	 * Name of player.
	 */
	private String playerName;
	/**
	 * Player's average of click speed.
	 */
	private double averageOfClickSpeed;
	/**
	 * Vector containing time difference between two black tile clicks in seconds.
	 */
	private Vector<Double> timeDifferenceBetweenBlackTilesClickedInSeconds = new Vector<Double>();
	/**
	 * Date of click of last black tile.
	 */
	private Date previousClickTime = null;
	/**
	 * Gameplay data object.
	 */
	private GameplayData gameplayData;
	/**
	 * Decides the level of difficulty.
	 */
	private String difficulty;
	/**
	 * Data access object for gameplay data.
	 */
	private GamePlayDataDAO gameplayDataDAO;

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
	 * 
	 * @return - data access object of game controller
	 */
	public GamePlayDataDAO getGameplayDataDAO() {
		return gameplayDataDAO;
	}

	/**
	 * 
	 * @param gameplayDataDAO
	 *            - data access object of game controller
	 */
	public void setGameplayDataDAO(GamePlayDataDAO gameplayDataDAO) {
		this.gameplayDataDAO = gameplayDataDAO;
	}

	/**
	 * 
	 * @return - the final score of the player
	 */
	public int getFinalScore() {
		return this.finalScore;
	}

	/**
	 * 
	 * @return - the average of time elapsed time between clicking two black
	 *         tiles, in seconds
	 * 
	 */
	public double getAverageOfClickSpeed() {
		return this.averageOfClickSpeed;
	}

	/**
	 * 
	 * @param gameplayData
	 *            - gameplay data
	 */
	public void setGameplayData(GameplayData gameplayData) {
		this.gameplayData = gameplayData;
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
									endThisGame();
									return;
								}
							}
						}
					}
				}
			}
			endThisGame();
		};
	}

	/**
	 * Executes action for black tiles.
	 * 
	 * @param row
	 *            - the row of black tile
	 * @param tile
	 *            - the black tile
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
		
		logger.info("Black tile clicked, score added.");

	}

	/**
	 * Rounds a double value.
	 * 
	 * @param value
	 *            - the value
	 * @param places
	 *            - the place of the comma
	 * @return - the rounded value
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();

	}

	/**
	 * Ends the game in progress.
	 */
	public void endThisGame() {
		isGameOver = true;

		timer.stop();

		finalScore = score;

		averageOfClickSpeed = GameService.calculateAverageClickTime(timeDifferenceBetweenBlackTilesClickedInSeconds);

		if (!Double.isNaN(averageOfClickSpeed))
			averageOfClickSpeed = round(averageOfClickSpeed, 3);

		gameplayData.setAverageOfClickSpeed(averageOfClickSpeed);
		gameplayData.setFinalScore(finalScore);
		if (playerName.equals("")) {
			playerName = "Anonymous";
		}
		gameplayData.setPlayerName(playerName);
		gameplayData.setDifficulty(difficulty);

		this.setGameplayData(gameplayData);

		gameplayDataDAO = new GamePlayDataDAO();
		gameplayDataDAO.saveGamePlayData(gameplayData);

		logger.info("Game is ended.");
		
		mainApp.showGameOverWindow(gameplayData);

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
		logger.info("Tiles generated.");
	}

	/**
	 * Starts the game.
	 * 
	 * @param difficulty
	 *            - the difficulty of the game
	 */
	public void startGame(String difficulty) {
		
		generateTiles();
		this.difficulty = difficulty;

		switch (this.difficulty) {
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
		logger.info("Game started.");

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
	 * @return mainApp - reference to {@link bm.game.tile.view.Main}
	 */
	public Main getMainApp() {
		return this.mainApp;
	}

	/**
	 * 
	 * @param playerName
	 *            - name of the player
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;

	}

	/**
	 * 
	 * @return - name of the player
	 */
	public String getPlayerName() {
		return this.playerName;
	}

}
