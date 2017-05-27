package bm.game.tile.service;

import java.util.Collection;
import java.util.stream.Collectors;

import bm.game.tile.DAO.GamePlayDataDAO;
import bm.game.tile.model.GameplayData;

public class GameplayDataService {
	public static Collection<GameplayData> getGamePlayDataForPlayer(String playerNameToGet) {

		return GamePlayDataDAO.getAllGamePlayData().stream()
				.filter(gameplayDataObject -> gameplayDataObject.getPlayerName().equals(playerNameToGet))
				.collect(Collectors.toList());
	}

	public static GameplayData playersHighestScoringGame(Collection<GameplayData> listOfGamesOfChosenPerson) {
		return listOfGamesOfChosenPerson.stream()
				.max((gameData1, gameData2) -> gameData1.getFinalScore() - gameData2.getFinalScore()).get();
	}

	public static boolean isUnlucky(Collection<GameplayData> listOfGamesOfChosenPerson) {

		return listOfGamesOfChosenPerson.stream().mapToInt(GameplayData::getFinalScore).min().getAsInt() <= 1;
	}

	public static boolean isOneHundredApproved(Collection<GameplayData> listOfGamesOfChosenPerson) {

		return listOfGamesOfChosenPerson.stream().mapToInt(GameplayData::getFinalScore).max().getAsInt() >= 100;
	}

	public static boolean isInsane(Collection<GameplayData> listOfGamesOfChosenPerson) {

		return listOfGamesOfChosenPerson.stream().filter(gameplayData -> gameplayData.getDifficulty().equals("insane"))
				.anyMatch(gameplayData -> gameplayData.getFinalScore() >= 10);

	}

	public static boolean hasFastFingers(Collection<GameplayData> listOfGamesOfChosenPerson) {

		return listOfGamesOfChosenPerson.stream().filter(gameplayData -> gameplayData.getFinalScore() >= 10)
				.mapToDouble(GameplayData::getAverageOfClickSpeed).min().getAsDouble() <= 0.3;
	}

	public static boolean isRockSolid(Collection<GameplayData> listOfGamesOfChosenPerson) {
		return listOfGamesOfChosenPerson.stream().filter(gameplayData -> gameplayData.getDifficulty().equals("hard"))
				.anyMatch(gameplayData -> gameplayData.getFinalScore() >= 100);
	}

	public static boolean isLoyal(Collection<GameplayData> listOfGamesOfChosenPerson){
		return listOfGamesOfChosenPerson.stream().mapToInt(GameplayData::getFinalScore).max().getAsInt() >= 1000;
		
	}
	
}
