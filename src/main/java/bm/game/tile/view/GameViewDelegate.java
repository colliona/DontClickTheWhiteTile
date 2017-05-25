package bm.game.tile.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public interface GameViewDelegate {

//	public EventHandler<? super MouseEvent> getBlackClickHandler(Rectangle rectangle);
//
//	public EventHandler<? super MouseEvent> getWhiteClickHandler();

	public EventHandler<? super MouseEvent> getClickHandler();
	
}
