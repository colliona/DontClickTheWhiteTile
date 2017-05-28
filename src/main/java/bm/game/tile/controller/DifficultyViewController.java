package bm.game.tile.controller;

import javafx.fxml.FXML;

/**
 * Provides functionality for the difficulty view.
 * 
 * @author collion
 *
 */
public class DifficultyViewController extends ViewController {
	/**
	 * Sets game difficulty to easy.
	 */
	@FXML
	public void easy() {
		game.getMainApp().showGame("easy");
	}

	/**
	 * Sets game difficulty to medium.
	 */
	@FXML
	public void medium() {
		game.getMainApp().showGame("medium");
	}

	/**
	 * Sets game difficulty to hard.
	 */
	@FXML
	public void hard() {
		game.getMainApp().showGame("hard");
	}

	/**
	 * Sets game difficulty to insane.
	 */
	@FXML
	public void insane() {
		game.getMainApp().showGame("insane");
	}

}
