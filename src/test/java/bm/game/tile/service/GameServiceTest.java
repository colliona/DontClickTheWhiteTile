package bm.game.tile.service;

import java.util.Vector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class GameServiceTest {
	private GameService gameService = new GameService();

	@SuppressWarnings("static-access")
	@Test
	public void calculateAverageClickTime_should_return_average_of_given_list_of_doubles(){
		Vector<Double> vectorOfDoublesToAverage = new Vector<Double>();
		vectorOfDoublesToAverage.add(new Double(1.4));
		vectorOfDoublesToAverage.add(new Double(0.6));
		
		assertThat( gameService.calculateAverageClickTime(vectorOfDoublesToAverage), equalTo(1.0) );
		
		
		
	}
	
}
