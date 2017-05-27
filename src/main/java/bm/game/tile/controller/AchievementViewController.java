package bm.game.tile.controller;

import bm.game.tile.service.GameplayDataService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AchievementViewController extends ViewController {
	private String playerName;
	@FXML
	private Label unlucky;
	@FXML
	private Label hundredPercent;
	@FXML
	private Label areYouInsane;
	@FXML
	private Label fastFingers;
	@FXML
	private Label rockSolid;
	@FXML
	private Label loyalGamer;

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@FXML
	public void setAchievements() {

		unlucky.setText("Unlucky  : Get a score of 0 or 1.");
		hundredPercent.setText("Hundred Percent : Get a score of 100.");
		areYouInsane.setText("Are you iNsANe ?  : Get a score of at least 10 on Insane difficulty.");
		fastFingers.setText("Fast Fingers  : Achieve an average of 0.3 seconds of click speed.");
		rockSolid.setText("Rock Solid : Get a score of 100 on Hard difficulty.");
		loyalGamer.setText("Loyal Gamer: Get a score of 1000.");

		if (GameplayDataService.isUnlucky(
				GameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			unlucky.setTextFill(Color.GREEN);
		} else {
			unlucky.setTextFill(Color.RED);
		}

		if (GameplayDataService.isOneHundredApproved(
				GameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			hundredPercent.setTextFill(Color.GREEN);
		} else {
			hundredPercent.setTextFill(Color.RED);
		}

		if (GameplayDataService.isInsane(
				GameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			areYouInsane.setTextFill(Color.GREEN);
		} else {
			areYouInsane.setTextFill(Color.RED);
		}

		if (GameplayDataService.isInsane(
				GameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			fastFingers.setTextFill(Color.GREEN);
		} else {
			fastFingers.setTextFill(Color.RED);
		}

		if (GameplayDataService.isRockSolid(
				GameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			rockSolid.setTextFill(Color.GREEN);
		} else {
			rockSolid.setTextFill(Color.RED);
		}

		if (GameplayDataService.isLoyal(
				GameplayDataService.getGamePlayDataForPlayer(game.getMainApp().getGameplayData().getPlayerName()))) {
			loyalGamer.setTextFill(Color.GREEN);
		} else {
			loyalGamer.setTextFill(Color.RED);
		}
	}

}
