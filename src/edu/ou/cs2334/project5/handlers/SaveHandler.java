package edu.ou.cs2334.project5.handlers;

import java.io.File;

import edu.ou.cs2334.project5.interfaces.Saveable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SaveHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{

	private Saveable saver;
	
	public SaveHandler(Window window, FileChooser fileChooser, Saveable saver) {
		super(window, fileChooser);
		this.saver = saver;
	}

	@Override
	public void handle(ActionEvent event) {
		
		
		File file = super.fileChooser.showSaveDialog(super.window);
		if (file != null) {
			saver.save(file.toString());
		}
		
	}
	
}