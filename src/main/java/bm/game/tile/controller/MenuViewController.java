package bm.game.tile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Provides functionality for the game menu view.
 * @author collion
 *
 */
public class MenuViewController extends ViewController {
	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger("MenuViewController.class");
	/**
	 * Player name text field.
	 */
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
		logger.info("Game exit.");
		Platform.exit();
	}
}
