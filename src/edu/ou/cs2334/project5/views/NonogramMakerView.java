package edu.ou.cs2334.project5.views;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NonogramMakerView {

	private BorderPane borderPane;
	private MenuBar menuBar;
	private CellGridView cellGridView;
	private HashMap<String, MenuItem> menuItemsMap = new HashMap<String, MenuItem>();
	public final String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";
	public final String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";
	public final String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";
	
	public NonogramMakerView(int numRows, int numCols, int cellLength) {
		
		BorderPane borderPane = new BorderPane();
		this.borderPane = borderPane;
		cellGridView = new CellGridView(numRows, numCols, cellLength);
		
		initButtons(numRows, numCols, cellLength);
		initMenuBar();

		
		borderPane.setTop(menuBar);
		borderPane.setCenter(cellGridView.getPane());
		
	}
	
	private void initMenuBar() {
		
		Menu fileMenu = new Menu("_File");
		MenuItem open = new MenuItem("_Open");
		MenuItem save = new MenuItem("_Save");
		MenuItem exit = new MenuItem("_Exit");

		menuItemsMap.put(MENU_ITEM_OPEN, open);
		menuItemsMap.put(MENU_ITEM_SAVE, save);
		menuItemsMap.put(MENU_ITEM_EXIT, exit);

		fileMenu.getItems().addAll(open, save, exit);

		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
			
		});
		
		menuBar = new MenuBar(fileMenu);

	}
	
	public MenuItem getMenuItem(String name) {
		return menuItemsMap.get(name);
	}
	
	public Pane getPane() {
		return borderPane;
	}
	
	public void initButtons(int numRows, int numCols, int cellLength) {
		cellGridView.initButtons(numRows, numCols, cellLength);
	}
	
	public ToggleButton getToggleButton(int row, int col) {
		return cellGridView.getToggleButton(row, col);
	}
	
	public int getNumRows() {
		return cellGridView.getNumRows();
	}
	
	public int getNumCols() {
		return cellGridView.getNumCols();
	}
	
}