package bm.game.tile.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bm.game.tile.DAO.GamePlayDataDAO;
import bm.game.tile.DAO.IGameplayDataDAO;
import bm.game.tile.model.GameplayData;

/**
 * Class for essential business logic of achievements and other statistics.
 * 
 * @author collion
 *
 */
public class GameplayDataService {
	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger("GameplayDataService.class");
	/**
	 * Data access object for the class.
	 */
	private IGameplayDataDAO gameplayDataDAO = new GamePlayDataDAO();

	/**
	 * 
	 * @param gameplayDataDAO
	 *            - data access object of gameplay data
	 */
	public void setGameplayDataDAO(IGameplayDataDAO gameplayDataDAO) {
		this.gameplayDataDAO = gameplayDataDAO;
	}

	/**
	 * Gets all the previously stored gameplay data of a certain player.
	 * 
	 * @param playerNameToGet
	 *            - the name of player
	 * @return - the list of the player's previous gameplay data
	 */
	public Collection<GameplayData> getGamePlayDataForPlayer(String playerNameToGet) {

		logger.info("Player data loaded.");
		return gameplayDataDAO.getAllGamePlayData().stream()
				.filter(gameplayDataObject -> gameplayDataObject.getPlayerName().equals(playerNameToGet))
				.collect(Collectors.toList());

	}

	/**
	 * Gets the highest scoring game's gameplay data.
	 * 
	 * @param listOfGames
	 *            - list of a player's game
	 * @return - gameplay data of highest score's game
	 */
	public GameplayData highestScoringGame(Collection<GameplayData> listOfGames) {
		logger.info("Highest scoring game of player is loaded.");
		return listOfGames.stream().max((gameData1, gameData2) -> gameData1.getFinalScore() - gameData2.getFinalScore())
				.get();
	}

	/**
	 * Decides whether the player deserves the 'Unlucky' achievement.
	 * 
	 * @param listOfGames
	 *            - collection of gameplay datas
	 * @return - whether the given list of gameplay data is eligible for
	 *         unlocking the 'Unlucky' achievement
	 */
	public boolean isUnlucky(Collection<GameplayData> listOfGames) {
		logger.info("Achievement Unlucky is checked.");
		return listOfGames.stream().mapToInt(GameplayData::getFinalScore).min().orElse(5) <= 1;
	}

	/**
	 * Decides whether the player deserves the 'Hundred Percent' achievement.
	 * 
	 * @param listOfGames
	 *            - collection of gameplay datas
	 * @return - whether the given list of gameplay data is eligible for
	 *         unlocking the 'Hundred Percent' achievement
	 */
	public boolean isOneHundredApproved(Collection<GameplayData> listOfGames) {
		logger.info("Achievement One Hundred is checked.");
		return listOfGames.stream().mapToInt(GameplayData::getFinalScore).max().orElse(5) >= 100;
	}

	/**
	 * Decides whether the player deserves the 'Are you iNsANe ?' achievement.
	 * 
	 * @param listOfGames
	 *            - collection of gameplay datas
	 * @return - whether the given list of gameplay data is eligible for
	 *         unlocking the 'Are you iNsANe ?' achievement
	 */
	public boolean isInsane(Collection<GameplayData> listOfGames) {
		logger.info("Achievement Are You Insane is checked.");
		return listOfGames.stream().filter(gameplayData -> gameplayData.getDifficulty().equals("insane"))
				.anyMatch(gameplayData -> gameplayData.getFinalScore() >= 10);

	}

	/**
	 * Decides whether the player deserves the 'Fast Fingers' achievement.
	 * 
	 * @param listOfGames
	 *            - collection of gameplay datas
	 * @return - whether the given list of gameplay data is eligible for
	 *         unlocking the 'Fast Fingers' achievement
	 */
	public boolean hasFastFingers(Collection<GameplayData> listOfGames) {
		logger.info("Achievement Fast Fingers is checked.");
		return listOfGames.stream().filter(gameplayData -> gameplayData.getFinalScore() >= 10)
				.mapToDouble(GameplayData::getAverageOfClickSpeed).min().orElse(1.0) <= 0.3;
	}

	/**
	 * Decides whether the player deserves the 'Rock Solid' achievement.
	 * 
	 * @param listOfGames
	 *            - collection of gameplay datas
	 * @return - whether the given list of gameplay data is eligible for
	 *         unlocking the 'Rock Solid' achievement
	 */
	public boolean isRockSolid(Collection<GameplayData> listOfGames) {
		logger.info("Achievement Rock Solid is checked.");
		return listOfGames.stream()
				.filter(gameplayData -> gameplayData.getDifficulty().equals("hard")
						|| gameplayData.getDifficulty().equals("insane"))
				.anyMatch(gameplayData -> gameplayData.getFinalScore() >= 100);
	}

	/**
	 * Decides whether the player deserves the 'Loyal Gamer' achievement.
	 * 
	 * @param listOfGames
	 *            - collection of gameplay datas
	 * @return - whether the given list of gameplay data is eligible for
	 *         unlocking the 'Loyal Gamer' achievement
	 */
	public boolean isLoyal(Collection<GameplayData> listOfGames) {
		logger.info("Achievement Loyal Gamer is checked.");
		return listOfGames.stream().mapToInt(GameplayData::getFinalScore).max().orElse(5) >= 1000;

	}

}
