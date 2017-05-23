package bm.game.tile.controller;

import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverViewController extends ViewController {
	@FXML
	private Button closeButton;
	@FXML
	private Label finalScore;
	@FXML
	private Label finalAverage;

	@FXML
	public void closeButtonFunction() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();

		game.getMainApp().showGameMenu();

	}

	@FXML
	public void setGameScores() {

		finalScore.setText("Your final score is : " + game.getFinalScore());
		System.out.println(game.getAverageOfClickSpeed());
		DecimalFormat df = new DecimalFormat("#.###");
		if (game.getFinalScore() != 0 && game.getFinalScore() != 1) {
			finalAverage.setText("Your average of seconds between clicks is :  " + df.format(game.getAverageOfClickSpeed()));
		} else {
			finalAverage.setText("Unlucky.");
		}

	}
}
