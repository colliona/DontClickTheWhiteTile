package bm.game.tile.controller;

import bm.game.tile.controller.GameController;

/**
 * JavaFX controllers for different windows are derived from this class.
 * 
 * @author collion
 *
 */
public abstract class ViewController {
	/**
	 * Controller of the game.
	 */
	protected GameController game;

	/**
	 * This method is crucial in order for the controllers to communicate with
	 * each other.
	 * 
	 * @param game
	 *            - the game controller to set
	 */
	public void setGameController(GameController game) {
		this.game = game;
	}

}
