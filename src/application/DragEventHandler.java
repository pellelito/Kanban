package application;

import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class DragEventHandler {
		//adds handler for drag/move labels in vboxes 
	public static void setOnDragOver(DragEvent event){
		VBox vbox = (VBox) event.getSource();
		if (( event).getGestureSource() != vbox &&
                (event).getDragboard().hasString()) {

            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
	}
	//adds handler for drag done/dropped labels in vboxes 
	public static void setOnDragDropped(DragEvent event, VBox vbox) throws IOException {
		Dragboard db = event.getDragboard();
        Label label = (Label) event.getGestureSource();
        boolean success = false;
        if (db.hasString()) {
			vbox.getChildren().add(label);
        	FileHandler.setList(event, label);
        	success = true;
        }
        
        event.setDropCompleted(success);

        event.consume();
		
	}
}
	

