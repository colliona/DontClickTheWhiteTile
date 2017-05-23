package bm.game.tile.controller;

import bm.game.tile.view.Main;
import bm.game.tile.model.GameWindow;
import bm.game.tile.model.Row;
import bm.game.tile.model.Tile;
import bm.game.tile.view.GameView;
import bm.game.tile.view.GameViewDelegate;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * The main controller class. This holds everything.
 */

public class GameController implements GameViewDelegate {
	private Main mainApp;
	private Timeline mTimeline;
	private GameView gameView;
	private GameWindow gameWindow;
	private boolean isGameOver = false;
	private int difficultyMultiplier;
	private int score = 0;
	private int finalScore;
	private double averageOfClickSpeed;
	private Vector<Double> timeDifferenceBetweenBlackTilesClickedInSeconds = new Vector<Double>();
	private Date previousClickTime = null;
	private double sumOfSecondsBetweenClicks = 0;

	public int getFinalScore() {
		return this.finalScore;
	}

	public double getAverageOfClickSpeed() {
		return this.averageOfClickSpeed;
	}

	public GameController(GameView gameView, GameWindow gameWindow) {
		gameView.setDelegate(this);
		this.gameView = gameView;
		this.gameWindow = gameWindow;
	}

	public EventHandler<? super MouseEvent> getBlackClickHandler(Rectangle rectangle) {
		return mouseEvent -> {
			if (!isGameOver) {
				rectangle.setFill(Color.WHITE);
				score++;
				if (previousClickTime == null) {
					previousClickTime = new Date();
				} else {
					Date now = new Date();

					Double differenceInSeconds = (now.getTime() - previousClickTime.getTime()) / 1000.0;

					System.out.println(differenceInSeconds);

					timeDifferenceBetweenBlackTilesClickedInSeconds.addElement(differenceInSeconds);

					this.previousClickTime = now;

					rectangle.setOnMouseClicked(mouseEventAfterFirst -> {
					});
				}
			}
		};
	}

	private void calculateAverage() {

		for (int indexOfVector = 0; indexOfVector < timeDifferenceBetweenBlackTilesClickedInSeconds
				.size(); indexOfVector++) {
			sumOfSecondsBetweenClicks += timeDifferenceBetweenBlackTilesClickedInSeconds.get(indexOfVector);
		}
		System.out.println("The average of seconds passed since last click on a black tile is : "
				+ sumOfSecondsBetweenClicks / timeDifferenceBetweenBlackTilesClickedInSeconds.size());
		this.averageOfClickSpeed = sumOfSecondsBetweenClicks / timeDifferenceBetweenBlackTilesClickedInSeconds.size();

	}

	public EventHandler<? super MouseEvent> getWhiteClickHandler() {
		return mouseEvent -> {
			mTimeline.stop();
			finalScore = score;
			System.out.println("GAME OVER !");
			System.out.println("Your final score is : " + finalScore);
			this.isGameOver = true;

			calculateAverage();

			mainApp.showGameOverWindow();
		};
	}
	
	private void generateTiles() {
		Random random = new Random();
		
		for (Row row: gameWindow.getRows()) {
			int blackIndex = random.nextInt(4);
			
			row.setBlackTile(blackIndex);
		}
	}

	/**
	 * @param difficulty
	 * @throws  
	 */
	public void startGame(String difficulty) {
		generateTiles();

		switch (difficulty) {
		case "easy":
			difficultyMultiplier = 200;
			break;
		case "medium":
			difficultyMultiplier = 400;
			break;
		case "hard":
			difficultyMultiplier = 200;
			break;
		case "insane":
			difficultyMultiplier = 100;
		}
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				gameView.draw(gameWindow);
				gameWindow.nextFrame();
			}
		};
		
		timer.start();
		
	}

	public void setGameView(GameView gameView, GameWindow gameWindow) {
		gameView.setDelegate(this);
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
