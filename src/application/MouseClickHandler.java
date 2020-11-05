package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class MouseClickHandler implements EventHandler<Event>{
	
	static boolean delete = false;
	
	@Override
    public void handle(Event event) {

    }

	public static boolean  doubleClick() {
			//checks if user really wants to delete task
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete Task?", ButtonType.YES, ButtonType.CANCEL);	
		 delete = false;
		
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                delete = true;
            } 
        });
		return delete;
	}
	public static void  rightClick(MouseEvent event) throws IOException {
		
			//right click means edit a task
		Label lblTask = (Label)event.getSource();
		String lines[] = lblTask.getText().split("\\r?\\n");
        String parent = lblTask.getParent().getId();

        	//updates task as new task and removes the old one
        
        if (lines.length<2) CreateTask.addNewTask(lines[0],"",parent);
        else CreateTask.addNewTask(lines[0],lines[1],parent);
        FileHandler.removeTask(event);
        
	}
}