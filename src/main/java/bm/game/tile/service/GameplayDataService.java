package bm.game.tile.service;

import java.util.Collection;
import java.util.stream.Collectors;

import bm.game.tile.DAO.GamePlayDataDAO;
import bm.game.tile.DAO.IGameplayDataDAO;
import bm.game.tile.model.GameplayData;

public class GameplayDataService {
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

		return listOfGames.stream().mapToInt(GameplayData::getFinalScore).min().getAsInt() <= 1;
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

		return listOfGames.stream().mapToInt(GameplayData::getFinalScore).max().getAsInt() >= 100;
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

		return listOfGames.stream().filter(gameplayData -> gameplayData.getFinalScore() >= 10)
				.mapToDouble(GameplayData::getAverageOfClickSpeed).min().getAsDouble() <= 0.3;
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
		return listOfGames.stream().filter(gameplayData -> gameplayData.getDifficulty().equals("hard"))
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
		return listOfGames.stream().mapToInt(GameplayData::getFinalScore).max().getAsInt() >= 1000;

	}

}
