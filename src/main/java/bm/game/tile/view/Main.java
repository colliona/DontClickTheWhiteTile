package bm.game.tile.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import bm.game.tile.controller.AchievementViewController;
import bm.game.tile.controller.DifficultyViewController;
import bm.game.tile.controller.GameController;
import bm.game.tile.controller.GameOverViewController;
import bm.game.tile.controller.MenuViewController;
import bm.game.tile.model.GameWindow;
import bm.game.tile.model.GameplayData;
import bm.game.tile.model.Row;
import bm.game.tile.model.Tile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private GameController game;
	private int tileWidth = 150;
	private List<Row> rows;
	private GameplayData lastGameplayData = new GameplayData("Anonymous");

	/**
	 * 
	 * @return - the game controller of the game
	 */
	public GameController getGame() {
		return game;
	}
	
	public GameplayData getGameplayData(){
		return this.lastGameplayData;
	}

	/**
	 * 
	 * @param primaryStage
	 *            - the primary stage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 
	 * @return - the list of the game's rows
	 */
	public List<Row> getRows() {
		return rows;
	}

	/**
	 * JavaFX start method.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Don't Click The White Tiles!");

		showGameMenu();

	}

	/**
	 * Shows the game menu window.
	 */
	public void showGameMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/TileMenuLayout.fxml"));
			GridPane menuLayout = (GridPane) loader.load();

			MenuViewController controller = loader.getController();
			game = new GameController(this);
			controller.setGameController(game);

			Scene scene = new Scene(menuLayout);

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the difficulty menu window.
	 */
	public void showDifficultyMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/TileDifficultyLayout.fxml"));
			GridPane difficultyLayout = (GridPane) loader.load();

			DifficultyViewController controller = loader.getController();
			controller.setGameController(game);

			Scene scene = new Scene(difficultyLayout);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the game over window.
	 */
	public void showGameOverWindow(GameplayData lastGameplayData) {
		try {

			this.lastGameplayData = lastGameplayData;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/TileGameOverLayout.fxml"));
			Pane gameOverLayout = (Pane) loader.load();

			GameOverViewController controller = loader.getController();
			controller.setGameController(game);
			controller.setGameScores();

			Stage stage = new Stage();
			Scene scene = new Scene(gameOverLayout);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(event -> controller.closeButtonFunction());
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the achievement menu window.
	 */
	public void showAchievementMenu() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/TileAchievementLayout.fxml"));
			ScrollPane achievementLayout = (ScrollPane) loader.load();
			
			AchievementViewController controller = loader.getController();
			controller.setGameController(game);
			controller.setAchievements();

			achievementLayout.setVbarPolicy(ScrollBarPolicy.NEVER);
			achievementLayout.setHbarPolicy(ScrollBarPolicy.NEVER);
			Scene scene = new Scene(achievementLayout);
			

			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Shows the game window.
	 */
	public void showGame(String difficulty) {

		primaryStage.setWidth(600);
		primaryStage.setHeight(600);
		primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				primaryStage.setMaximized(false);
			}
		});

		GameplayData gameplayData = new GameplayData();
		game.setGameplayData(gameplayData);

		List<Tile> tilesOfRow1 = Arrays.asList(new Tile(0, tileWidth), new Tile(150, tileWidth),
				new Tile(300, tileWidth), new Tile(450, tileWidth));
		List<Tile> tilesOfRow2 = Arrays.asList(new Tile(0, tileWidth), new Tile(150, tileWidth),
				new Tile(300, tileWidth), new Tile(450, tileWidth));
		List<Tile> tilesOfRow3 = Arrays.asList(new Tile(0, tileWidth), new Tile(150, tileWidth),
				new Tile(300, tileWidth), new Tile(450, tileWidth));
		List<Tile> tilesOfRow4 = Arrays.asList(new Tile(0, tileWidth), new Tile(150, tileWidth),
				new Tile(300, tileWidth), new Tile(450, tileWidth));

		Row row1 = new Row(-600, tilesOfRow1, 200);
		Row row2 = new Row(-400, tilesOfRow2, 200);
		Row row3 = new Row(-200, tilesOfRow3, 200);
		Row row4 = new Row(0, tilesOfRow4, 200);

		rows = Arrays.asList(row1, row2, row3, row4);

		GameWindow gameWindow = new GameWindow(rows);

		GameView gameView = new GameView(game);

		game.setGameView(gameView, gameWindow);
		Scene scene = new Scene(gameView, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		game.startGame(difficulty);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
