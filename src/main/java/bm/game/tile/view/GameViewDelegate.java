package bm.game.tile.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Interface of the game controller's delegate for the game view.
 * 
 * @author collion
 *
 */
public interface GameViewDelegate {
	/**
	 * Handles the functionality of the click in a game.
	 * @return - the event which occurs 
	 */
	public EventHandler<? super MouseEvent> getClickHandler();

}
