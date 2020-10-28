package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class DragEventHandler {
	
		
		//adds handler for drag/move labels in vboxes 
	public static void setOnDragOver(DragEvent event){
		VBox vbox = (VBox) event.getSource();
		if (( event).getGestureSource() != vbox && (event).getDragboard().hasString()) {

            event.acceptTransferModes(TransferMode.MOVE);
       
        }

        event.consume();
	}
		//adds handler for drag done/dropped labels in vboxes 
	public static void setOnDragDropped(DragEvent event, VBox vbox) throws IOException {
		Dragboard db = event.getDragboard();
        Label label = (Label) event.getGestureSource();
        
        String parent = label.getParent().getId();
        boolean success = false;
        if  (!parent.equals(vbox.getId()) && db.hasString()) {
        	
			vbox.getChildren().add(label);
        	FileHandler.setList(event, label);	
        	success = true;
        	
        }else if(parent.equals(vbox.getId()) && db.hasString()) {
        	
        		//resort i released within same box
        	int source = vbox.getChildren().indexOf(label);
        	int target = vbox.getChildren().indexOf(event.getTarget());
        	
        	if(target > 0) {
        		
        		List<Node> nodes = new ArrayList<Node>(vbox.getChildren());
            	
                if (source < target) {
                    Collections.rotate(
                            nodes.subList(source, target + 1), -1);
                } else {
                    Collections.rotate(
                            nodes.subList(target, source + 1), 1);
                }
                vbox.getChildren().clear();
                vbox.getChildren().addAll(nodes);
                success = true;
        	}
        	
        	
        	
        }
        
        event.setDropCompleted(success);

        event.consume();
		
	}
}
	

