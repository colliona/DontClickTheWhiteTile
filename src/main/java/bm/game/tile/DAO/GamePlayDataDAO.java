package bm.game.tile.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bm.game.tile.model.GameplayData;

public class GamePlayDataDAO {
	private static File json = new File(
			"/home/collion/eclipseworkspace/dont-click-the-white-tile/src/main/resources/json/json");
	private static JSONArray jArray;

	public static Collection<GameplayData> getAllGamePlayData() {

		List<GameplayData> returnable = new ArrayList<GameplayData>();
		JSONParser parser = new JSONParser();
		JSONArray jArray = new JSONArray();
		try {

			jArray = (JSONArray) parser.parse(new FileReader(json));

		} catch (FileNotFoundException e) {
			// logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// logger.error(e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			// logger.error(e.toString());
			e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return returnable;
	}


	@SuppressWarnings("unchecked")
	public static void saveGamePlayData(GameplayData gameplayData) {
		ObjectMapper mapper = new ObjectMapper();
		JSONParser parser = new JSONParser();

		try {

			jArray = (JSONArray) parser.parse(new FileReader(json));
			String jsonInString = mapper.writeValueAsString(gameplayData);
			jArray.add(jsonInString);
			mapper.writer().withDefaultPrettyPrinter().writeValue(json, jArray);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}