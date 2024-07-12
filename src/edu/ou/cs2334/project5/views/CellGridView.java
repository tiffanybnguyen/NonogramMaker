package edu.ou.cs2334.project5.views;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CellGridView {

	private ArrayList<ToggleButton> gridButtons;
	private GridPane gridPane;
	private int numRows;
	private int numCols;
	
	public CellGridView(int numRows, int numCols, int cellLength) {
		this.numRows = numRows;
		this.numCols = numCols;
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		
		gridButtons = new ArrayList<ToggleButton>();
		
		initButtons(numRows, numCols, cellLength);
		
	}
	
	public void initButtons(int numRows, int numCols, int cellLength) {
		
		int index = 0;
		
		this.numRows = numRows;
		this.numCols = numCols;
		
		gridButtons.clear();
		gridPane.getChildren().clear(); //GridPane.clearConstraints(gridPane);
		
		for (int i = 0; i < numRows*numCols; i++) {
			ToggleButton button = new ToggleButton();
			
			button.setMaxHeight(cellLength);
			button.setMinHeight(cellLength);
			button.setPrefHeight(cellLength);
			
			button.setMaxWidth(cellLength);
			button.setMinWidth(cellLength);
			button.setPrefWidth(cellLength);
			
			gridButtons.add(button);
			
		}
		
		for (int a = 0; a<numRows; a++) {
			for (int b = 0; b<numCols; b++) {
				ToggleButton tb = gridButtons.get(index);
				gridPane.add(tb, b, a); 
				index++;
			}
		}
		
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumCols() {
		return numCols;
	}
	
	public ToggleButton getToggleButton(int row, int col) {
		return gridButtons.get(row*numCols + col);
	}
	
	public Pane getPane() {
		return gridPane;
	}
	
}