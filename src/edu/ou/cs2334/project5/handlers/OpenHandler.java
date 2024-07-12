package edu.ou.cs2334.project5.handlers;



import java.io.File;

import edu.ou.cs2334.project5.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {

	private Openable opener;
	
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		File file = super.fileChooser.showOpenDialog(super.window);
		if (file != null) {
			opener.open(file);
		}
		
	}
	
}