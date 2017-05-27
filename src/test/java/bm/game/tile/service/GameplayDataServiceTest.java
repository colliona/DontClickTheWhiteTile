package bm.game.tile.service;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import bm.game.tile.DAO.IGameplayDataDAO;
import bm.game.tile.model.GameplayData;

@RunWith(MockitoJUnitRunner.class)
public class GameplayDataServiceTest {
	private GameplayDataService gameplayDataService = new GameplayDataService();
	@Mock
	private IGameplayDataDAO mockGameplayDataDAO;

	@Before
	public void setUp() {
		gameplayDataService = new GameplayDataService();
		gameplayDataService.setGameplayDataDAO(mockGameplayDataDAO);

	}

	@Test
	public void testGetGamePlayDataForPlayer_should_return_gameplayDataObjects_for_given_player() {
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setPlayerName("Me");
		gameplayData2.setPlayerName("Me");
		gameplayData3.setPlayerName("Myself");
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);

		when(mockGameplayDataDAO.getAllGamePlayData()).thenReturn(gameplayDataArray);

		Collection<GameplayData> gameplayDataCollection = gameplayDataService.getGamePlayDataForPlayer("Me");

		assertThat(gameplayDataCollection, hasSize(2));

	}
	
	
	@Test
	public void testIsUnlucky_should_return_false_if_unlucky_achievement_is_not_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(2);
		gameplayData2.setFinalScore(2);
		gameplayData3.setFinalScore(3);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertFalse(gameplayDataService.isUnlucky(gameplayDataArray));
	}
	
	
	@Test
	public void testIsUnlucky_should_return_true_if_unlucky_achievement_is_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(1);
		gameplayData2.setFinalScore(1);
		gameplayData3.setFinalScore(2);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertTrue(gameplayDataService.isUnlucky(gameplayDataArray));
	}

}
