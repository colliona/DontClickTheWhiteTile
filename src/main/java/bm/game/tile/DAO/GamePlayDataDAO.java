package bm.game.tile.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bm.game.tile.model.GameplayData;

/**
 * Provides access to persistent storage.
 * 
 * @author collion
 *
 */
public class GamePlayDataDAO implements IGameplayDataDAO {
	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger("GamePlayDataDAO.class");

	/**
	 * The container array of read data.
	 */
	private JSONArray jArray;

	/**
	 * 
	 * @return - collection of all the previously stored gameplay data
	 */
	@Override
	public Collection<GameplayData> getAllGamePlayData() {
		JSONParser parser = new JSONParser();
		List<GameplayData> returnable = new ArrayList<GameplayData>();
		JSONArray jArray = new JSONArray();

		InputStream input = GamePlayDataDAO.class.getClassLoader().getResourceAsStream("json/json");
		Reader reader = new InputStreamReader(input);

		try {

			jArray = (JSONArray) parser.parse(reader);

		} catch (IOException | ParseException e) {
			logger.error(e.toString());
		}

		for (int i = 0; i < jArray.size(); i++) {

			JSONObject jsonObject;
			try {
				GameplayData gameplayData = new GameplayData();
				jsonObject = (JSONObject) parser.parse((String) jArray.get(i));

				gameplayData.setPlayerName((String) jsonObject.get("playerName"));

				Long finalScore = (Long) jsonObject.get("finalScore");
				gameplayData.setFinalScore(finalScore.intValue());

				if (jsonObject.get("averageOfClickSpeed").getClass().getName().equals("java.lang.String")) {
					gameplayData.setAverageOfClickSpeed(100.0);
				} else {
					gameplayData.setAverageOfClickSpeed((double) jsonObject.get("averageOfClickSpeed"));
				}

				gameplayData.setDifficulty((String) jsonObject.get("difficulty"));

				returnable.add(gameplayData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return returnable;
	}

	/**
	 * 
	 * @param gameplayData
	 *            - the gameplay data to be stored
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void saveGamePlayData(GameplayData gameplayData) {
		ObjectMapper mapper = new ObjectMapper();
		JSONParser parser = new JSONParser();
		FileWriter fileWriter;
		InputStream input = GamePlayDataDAO.class.getClassLoader().getResourceAsStream("json/json");
		Reader reader = new InputStreamReader(input);

		try {
			jArray = (JSONArray) parser.parse(reader);
			String jsonInString;
			jsonInString = mapper.writeValueAsString(gameplayData);
			jArray.add(jsonInString);

			URL url = GamePlayDataDAO.class.getResource("/json/json");
			File file = new File(url.toURI());
			fileWriter = new FileWriter(file);
			fileWriter.write(jArray.toJSONString());
			fileWriter.close();
		} catch (URISyntaxException | IOException | ParseException e) {
			logger.error(e.toString());
		}

	}
}
