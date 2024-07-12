package edu.ou.cs2334.project5.handlers;

import edu.ou.cs2334.project5.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

public class ToggleButtonEventHandler implements EventHandler<ActionEvent>{

	private NonogramMakerModel model;
	private int rowIdx;
	private int colIdx;
	
	public ToggleButtonEventHandler(NonogramMakerModel model, int rowIdx, int colIdx) {
		this.model = model;
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		ToggleButton butt = (ToggleButton) event.getSource();
		model.setCell(rowIdx, colIdx, butt.isSelected());
		
	}
	
}
