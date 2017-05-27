package bm.game.tile.DAO;

import java.util.Collection;

import bm.game.tile.model.GameplayData;

public interface IGameplayDataDAO {

	public Collection<GameplayData> getAllGamePlayData();
	
	public void saveGamePlayData(GameplayData gameplayData);
	
}
