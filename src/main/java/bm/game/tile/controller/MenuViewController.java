package bm.game.tile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MenuViewController extends ViewController {
	@FXML
	private TextField playerName;
	
	/**
	 * Shows difficulty menu window.
	 */
	@FXML
	public void showDifficultyMenu() {
		game.setPlayerName(playerName.getText());
		game.getMainApp().showDifficultyMenu();
		
	}

	/**
	 * Shows achievement menu window.
	 */
	@FXML
	public void showAchievementMenu() {
		
		game.getMainApp().showAchievementMenu();
	}

	/**
	 * Exits game.
	 */
	@FXML
	public void exit() {
		Platform.exit();
	}
}
