package bm.game.tile.controller;

import bm.game.tile.service.GameplayDataService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Controls the achievement window.
 * @author collion
 *
 */
public class AchievementViewController extends ViewController {
	/**
	 * Button for getting back to the main menu.
	 */
	@FXML
	private Button backToMainMenuButton;
	/**
	 * Label of unlucky achievement.
	 */
	@FXML
	private Label unlucky;
	/**
	 * Label of hundredPercent achievement.
	 */
	@FXML
	private Label hundredPercent;
	/**
	 * Label of areYouInsane achievement.
	 */
	@FXML
	private Label areYouInsane;
	/**
	 * Label of fastFingers achievement.
	 */
	@FXML
	private Label fastFingers;
	/**
	 * Label of rockSolid achievement.
	 */
	@FXML
	private Label rockSolid;
	/**
	 * Label of loyalGamer achievement.
	 */
	@FXML
	private Label loyalGamer;


	/**
	 * Sets the status of the achievements.
	 */
	@FXML
	public void setAchievements() {
		
		GameplayDataService gameplayDataService = new GameplayDataService();
		game.setPlayerName("Anonymous");
		
		backToMainMenuButton.setTextFill(Color.WHITE);
		unlucky.setText("Unlucky  : Get a score of 0 or 1.");
		hundredPercent.setText("Hundred Percent : Get a score of 100.");
		areYouInsane.setText("Are you iNsANe ?  : Get a score of at least 10 on Insane difficulty.");
		fastFingers.setText("Fast Fingers  : Achieve an average of 0.3 seconds of click speed.");
		rockSolid.setText("Rock Solid : Get a score of 100 on Hard difficulty.");
		loyalGamer.setText("Loyal Gamer: Get a score of 1000.");

		if (gameplayDataService.isUnlucky(gameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			unlucky.setTextFill(Color.GREEN);
		} else {
			unlucky.setTextFill(Color.RED);
		}

		if (gameplayDataService.isOneHundredApproved(
				gameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			hundredPercent.setTextFill(Color.GREEN);
		} else {
			hundredPercent.setTextFill(Color.RED);
		}

		if (gameplayDataService.isInsane(
				gameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			areYouInsane.setTextFill(Color.GREEN);
		} else {
			areYouInsane.setTextFill(Color.RED);
		}

		if (gameplayDataService.isInsane(
				gameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			fastFingers.setTextFill(Color.GREEN);
		} else {
			fastFingers.setTextFill(Color.RED);
		}

		if (gameplayDataService.isRockSolid(
				gameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			rockSolid.setTextFill(Color.GREEN);
		} else {
			rockSolid.setTextFill(Color.RED);
		}

		if (gameplayDataService.isLoyal(
				gameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			loyalGamer.setTextFill(Color.GREEN);
		} else {
			loyalGamer.setTextFill(Color.RED);
		}
	}

	/**
	 * Gets us back to the main menu.
	 */
	@FXML
	public void backToMainMenuProcedure() {
		game.getMainApp().showGameMenu();

	}
	
}
