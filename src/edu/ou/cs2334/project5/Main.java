package edu.ou.cs2334.project5;

import edu.ou.cs2334.project5.presenters.NonogramMakerPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;
	private static final int IDX_NUM_SIZE = 2;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		Parameters p = getParameters();
		
		int numRows = Integer.parseInt(p.getRaw().get(IDX_NUM_ROWS));
		int numCols = Integer.parseInt(p.getRaw().get(IDX_NUM_COLS));
		int cellSize = Integer.parseInt(p.getRaw().get(IDX_NUM_SIZE));
		
		NonogramMakerPresenter present = new NonogramMakerPresenter(numRows, numCols, cellSize);
		Scene scene = new Scene(present.getPane());
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Nonograms!");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}
	
}