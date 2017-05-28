package bm.game.tile.controller;

import java.text.DecimalFormat;

import bm.game.tile.service.GameplayDataService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Provides functionality for the game over view.
 * 
 * @author collion
 *
 */
public class GameOverViewController extends ViewController {
	/**
	 * Close button.
	 */
	@FXML
	private Button closeButton;
	/**
	 * Label of final score.
	 */
	@FXML
	private Label finalScore;
	/**
	 * Label of final average.
	 */
	@FXML
	private Label finalAverage;
	/**
	 * Label of high score.
	 */
	@FXML
	private Label highScoreOfPlayer;

	/**
	 * Gets us back to the main menu.
	 */
	@FXML
	public void closeButtonFunction() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();

		game.getMainApp().showGameMenu();

	}

	/**
	 * Loads the game score values into the game over scene.
	 */
	@FXML
	public void setGameScores() {

		finalScore.setText("Your final score is : " + game.getFinalScore());
		DecimalFormat df = new DecimalFormat("#.###");
		if (game.getFinalScore() != 0 && game.getFinalScore() != 1) {
			finalAverage.setText(
					"Your average of seconds between clicks is :  " + df.format(game.getAverageOfClickSpeed()));
		} else {
			finalAverage.setText("Unlucky.");
		}

		GameplayDataService gameplayDataService = new GameplayDataService();

		highScoreOfPlayer.setText("Your high score is : " + gameplayDataService
				.highestScoringGame(gameplayDataService.getGamePlayDataForPlayer(game.getPlayerName()))
				.getFinalScore());
	}
}
