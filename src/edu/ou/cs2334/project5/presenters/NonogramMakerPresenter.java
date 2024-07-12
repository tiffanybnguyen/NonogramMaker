package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.ToggleButtonEventHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.interfaces.Saveable;
import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.handlers.SaveHandler;
import edu.ou.cs2334.project5.models.NonogramMakerModel;
import edu.ou.cs2334.project5.views.NonogramMakerView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class NonogramMakerPresenter implements Openable, Saveable {

	private NonogramMakerView view;
	private NonogramMakerModel model;
	private int cellLength;
	
	public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
		
		model = new NonogramMakerModel(numRows, numCols);
		view = new NonogramMakerView(numRows, numCols, cellLength);
		this.cellLength = cellLength;
		init();
		
	}
	
	private Window getWindow() {
		Window window;
		
		try {
			window = view.getPane().getScene().getWindow();
			return window;
		}
		
		catch(NullPointerException e) {
			return null;
		}
	}
	
	private void init() {
		
		initToggleButtons();
		bindToggleButtons();
		configureMenuItem();
		
	}
	
	private void initToggleButtons() {
		
		view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
		
		if (this.getWindow() != null) {
			this.getWindow().sizeToScene();
		}
		
	}
	
	private void bindToggleButtons() {
		
		for (int i = 0; i < model.getNumRows(); i++) {
			for (int j = 0; j < model.getNumCols(); j++) {
				
				ToggleButton button = view.getToggleButton(i, j);
				button.setSelected(model.getCell(i, j));
				ToggleButtonEventHandler handler = new ToggleButtonEventHandler(model, i, j);
				button.setOnAction(handler);
				
				if (view.getToggleButton(i, j).isSelected() != model.getCell(i, j)) {
					model.setCell(i, j, view.getToggleButton(i, j).isSelected());
				}
			}
		}
		
	}
	
	private void configureMenuItem() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(view.MENU_ITEM_OPEN).setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		FileChooser fileChooser2 = new FileChooser();
		fileChooser2.setTitle("Save");
		fileChooser2.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser2.setInitialDirectory(new File("."));
		view.getMenuItem(view.MENU_ITEM_SAVE).setOnAction(new SaveHandler(getWindow(), fileChooser2, this));
		
		
	}
	
	public Pane getPane() {
		return view.getPane();
	}
	
	public void open(File file) {
		
		try {
			model = new NonogramMakerModel(file);
			init();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public void save(String filename) {
		
		try {
			model.saveToFile(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}