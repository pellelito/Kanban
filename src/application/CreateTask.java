package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CreateTask
{
	static Stage newWindow;
	static String list = "Backlog";
	
	public CreateTask(){	
	}
	
	public static void addNewTask(String userTitle, String userText, String userList){
	
		//Create headerlabel
	Label lblCreateTask = new Label("Create new task");	
	if(!userTitle.equals(""))lblCreateTask.setText("Edit task");
	
		//adds user input buttons
	Button btnAdd = new Button("Add new");
    if(!userTitle.equals("")) btnAdd.setText("Update");
	Button btnCancel = new Button("Cancel");
	if(!userTitle.equals("")) btnCancel.setManaged(false);
	
    	// adds title	
    TextField title = new TextField(userTitle);
    if(userTitle.equals("")) title.setText("Title");
    	// adds text
    TextField text = new TextField(userText);
    if(userText.equals("")) text.setText("Text");
    	
    	//adds a list of options
    ObservableList<String> options = 
    	    FXCollections.observableArrayList(
    	    		"Backlog",
    	            "Todo",
    	            "Doing",
    	            "Done"
    	    );
    	
    ComboBox<String> comboBox = new ComboBox<String>(options);

    switch(userList) {
    case "Todo":
    	comboBox.getSelectionModel().select(1);  	
      break;
    case "Doing":
    	comboBox.getSelectionModel().select(2);
      break;
    case "Done":
    	comboBox.getSelectionModel().select(3);
        break;
    default:
    	comboBox.getSelectionModel().selectFirst();
  }
    	//create layout for input window_____________________________________________________________________________________________________
    StackPane createLayout = new StackPane();
    VBox vbox = new VBox(5);
    vbox.getChildren().addAll(lblCreateTask, title, text,comboBox, btnAdd, btnCancel);
    createLayout.getChildren().add(vbox);
    Scene secondScene = new Scene(createLayout, 230, 300);

    	// New window 
    if (newWindow == null) {
    	newWindow = new Stage();
    	newWindow.setTitle("Add/Edit task");
    	newWindow.setScene(secondScene);
    	newWindow.show();
    	} else if (newWindow.isShowing()) {
    	newWindow.toFront();
    	} else {
    	newWindow.setScene(secondScene);
    	newWindow.show();
    	}
    	//end of new window___________________________________________________________________________________________________________________
    
    	//what will happen whens user clicks add/update
    btnAdd.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>()
    {
        public void handle(ActionEvent e)
        {
        	try {	//adds/updates the new task
        		if(title.getText().matches(".*\\S.*")) {
        			list = (String) comboBox.getValue();
        			if(text.getText().matches(".*\\S.*")){
        				FileHandler.addTask(title.getText(), text.getText(), list);
        			}else FileHandler.addTask(title.getText(), "", list);
					
					newWindow.close();
					newWindow = null;
        		}else {
        				// create a alert if user not entered a title
        	        Alert a = new Alert(AlertType.ERROR); 
        	        a.setContentText("You need at least give it a title");
        	        title.setStyle("-fx-background-color: red;");
        	        a.show();
        	        
        		}
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
        }
    });
    newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
    	public void handle(WindowEvent we) {
    	newWindow.close();
    	try {
    	FileHandler.addTask(userTitle, userText, userList);
    	} catch (IOException e1) {
    	e1.printStackTrace();
    	}
    	}
    	});
    	//closes the new window
  	btnCancel.setOnAction(e -> newWindow.close());
  	
	}
	
}
