package bm.game.tile.DAO;

import java.util.Collection;

import bm.game.tile.model.GameplayData;

/**
 * Interface of gameplay data access object.
 * 
 * @author collion
 *
 */
public interface IGameplayDataDAO {
	/**
	 * Gets all previous gameplay data.
	 * 
	 * @return - collection of gameplay data
	 */
	public Collection<GameplayData> getAllGamePlayData();

	/**
	 * Saves a gameplay data.
	 * 
	 * @param gameplayData
	 *            - the gameplay data to save
	 */
	public void saveGamePlayData(GameplayData gameplayData);

}
