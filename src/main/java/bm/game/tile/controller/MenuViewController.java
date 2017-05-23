package bm.game.tile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MenuViewController extends ViewController {
	/**
	 * Shows difficulty menu window.
	 */
	@FXML
	public void showDifficultyMenu() {
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
