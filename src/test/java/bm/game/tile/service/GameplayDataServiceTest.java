package bm.game.tile.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
	public void testHighestScoringGame_should_return_gameplayDataObject_for_highest_scoring_game() {
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(1);
		gameplayData2.setFinalScore(2);
		gameplayData3.setFinalScore(3);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);

		GameplayData gameplayDataToReturn = gameplayDataService.highestScoringGame(gameplayDataArray);
		
		assertEquals(gameplayData3, gameplayDataToReturn);
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
	
	@Test
	public void testIsOneHundredApproved_should_return_false_if_oneHundred_achievement_is_not_achieved(){
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
		
		assertFalse(gameplayDataService.isOneHundredApproved(gameplayDataArray));
	}
	
	@Test
	public void testIsOneHundredApproved_should_return_true_if_oneHundred_achievement_is_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(100);
		gameplayData2.setFinalScore(100);
		gameplayData3.setFinalScore(2);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertTrue(gameplayDataService.isOneHundredApproved(gameplayDataArray));
	}
	
	@Test
	public void testIsInsane_should_return_false_if_areYouInsane_achievement_is_not_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(1);
		gameplayData2.setFinalScore(1);
		gameplayData3.setFinalScore(2);
		gameplayData1.setDifficulty("easy");
		gameplayData2.setDifficulty("medium");
		gameplayData3.setDifficulty("hard");
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertFalse(gameplayDataService.isOneHundredApproved(gameplayDataArray));
	}
	
	@Test
	public void testIsInsane_should_return_true_if_areYouInsane_achievement_is_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(16);
		gameplayData2.setFinalScore(14);
		gameplayData3.setFinalScore(2);
		gameplayData1.setDifficulty("insane");
		gameplayData2.setDifficulty("insane");
		gameplayData3.setDifficulty("insane");
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertTrue(gameplayDataService.isInsane(gameplayDataArray));
	}
	
	
	@Test
	public void testIsInsane_should_return_false_if_hasFastFingers_achievement_is_not_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(10);
		gameplayData2.setFinalScore(10);
		gameplayData3.setFinalScore(13);
		gameplayData1.setAverageOfClickSpeed(0.4);
		gameplayData2.setAverageOfClickSpeed(0.43);
		gameplayData3.setAverageOfClickSpeed(0.45);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertFalse(gameplayDataService.hasFastFingers(gameplayDataArray));
	}
	
	@Test
	public void testIsInsane_should_return_true_if_hasFastFingers_achievement_is_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(10);
		gameplayData2.setFinalScore(10);
		gameplayData3.setFinalScore(13);
		gameplayData1.setAverageOfClickSpeed(0.4);
		gameplayData2.setAverageOfClickSpeed(0.23);
		gameplayData3.setAverageOfClickSpeed(0.2);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertTrue(gameplayDataService.hasFastFingers(gameplayDataArray));
	}
	
	@Test
	public void testIsInsane_should_return_false_if_isRockSolid_achievement_is_not_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(100);
		gameplayData2.setFinalScore(100);
		gameplayData3.setFinalScore(100);
		gameplayData1.setDifficulty("medium");
		gameplayData2.setDifficulty("medium");
		gameplayData3.setDifficulty("easy");;
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertFalse(gameplayDataService.isRockSolid(gameplayDataArray));
	}
	
	@Test
	public void testIsInsane_should_return_true_if_isRockSolid_achievement_is_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(100);
		gameplayData2.setFinalScore(100);
		gameplayData3.setFinalScore(100);
		gameplayData1.setDifficulty("medium");
		gameplayData2.setDifficulty("hard");
		gameplayData3.setDifficulty("insane");
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertTrue(gameplayDataService.isRockSolid(gameplayDataArray));
	}
	
	
	@Test
	public void testIsInsane_should_return_false_if_isLoyal_achievement_is_not_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(100);
		gameplayData2.setFinalScore(100);
		gameplayData3.setFinalScore(803);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertFalse(gameplayDataService.isLoyal(gameplayDataArray));
	}
	
	@Test
	public void testisLoyal_should_return_true_if_isLoyal_achievement_is_achieved(){
		ArrayList<GameplayData> gameplayDataArray = new ArrayList<GameplayData>();
		GameplayData gameplayData1 = new GameplayData();
		GameplayData gameplayData2 = new GameplayData();
		GameplayData gameplayData3 = new GameplayData();
		gameplayData1.setFinalScore(1000);
		gameplayData2.setFinalScore(1002);
		gameplayData3.setFinalScore(803);
		gameplayDataArray.add(gameplayData1);
		gameplayDataArray.add(gameplayData2);
		gameplayDataArray.add(gameplayData3);
		
		assertTrue(gameplayDataService.isLoyal(gameplayDataArray));
	}

}
