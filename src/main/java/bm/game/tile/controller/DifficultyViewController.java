package bm.game.tile.controller;

import javafx.fxml.FXML;

public class DifficultyViewController extends ViewController {
	@FXML
	public void easy() {
		game.getMainApp().showGame("easy");
	}

	@FXML
	public void medium() {
		game.getMainApp().showGame("medium");
	}

	@FXML
	public void hard() {
		game.getMainApp().showGame("hard");
	}

	@FXML
	public void insane() {
		game.getMainApp().showGame("insane");
	}

}
