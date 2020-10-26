package application;
	
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	VBox meny = new VBox(5);
	static VBox backlog = new VBox(5);
	static VBox todo = new VBox(5);
	static VBox doing = new VBox(5);
	static VBox done = new VBox(5);
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		Pane root = new Pane();
		Scene scene = new Scene(root, width, height);
		Button addNew = new Button("Add new");
		Button close = new Button("Close");
		root.setStyle("-fx-background-size: cover;");
		
		
		try {
				//create form___________________________________________________________________________________________________________________________
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			HBox frame = new HBox(50);
			
			
			meny.setMinHeight(height);
			meny.setMinWidth(width/6);
			meny.setBackground(new Background(new BackgroundFill(Color.rgb(120, 120, 120), CornerRadii.EMPTY, null)));
			
			
			backlog.setMinHeight(height);
			backlog.setMinWidth(width/6);
			backlog.setBackground(new Background(new BackgroundFill(Color.rgb(255, 204, 204), new CornerRadii(20), null)));
			
			
			todo.setMinHeight(height);
			todo.setMinWidth(width/6);
			todo.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 204), new CornerRadii(20), null)));
			
			
			
			doing.setMinHeight(height);
			doing.setMinWidth(width/6);
			doing.setBackground(new Background(new BackgroundFill(Color.rgb(204, 229, 255), new CornerRadii(20), null)));
			
			
			done.setMinHeight(height);
			done.setMinWidth(width/6);
			done.setBackground(new Background(new BackgroundFill(Color.rgb(204, 204, 255), new CornerRadii(20), null)));
			
			Label lblBacklog = new Label("Backlog");
			Label lblTodo = new Label("Todo");
			Label lblDoing = new Label("Doing");
			Label lblDone = new Label("Done");
			
			backlog.getChildren().add(lblBacklog);
			backlog.setAlignment(Pos.TOP_CENTER);
			backlog.setId("Backlog");
			todo.getChildren().add(lblTodo);
			todo.setAlignment(Pos.TOP_CENTER);
			todo.setId("Todo");
			doing.getChildren().add(lblDoing);
			doing.setAlignment(Pos.TOP_CENTER);
			doing.setId("Doing");
			done.getChildren().add(lblDone);
			done.setAlignment(Pos.TOP_CENTER);
			done.setId("Done");
		
			meny.getChildren().addAll(addNew, close);
			meny.setAlignment(Pos.CENTER);
			frame.getChildren().addAll(meny, backlog, todo, doing, done);
			root.getChildren().add(frame);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
			//end form__________________________________________________________________________________________________________________
			
			//get tasks from file
		try {
			FileHandler.readFromFile();
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
			//add handlers to vboxes
		backlog.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	DragEventHandler.setOnDragOver(event);
		    }
		});

		backlog.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	try {
					DragEventHandler.setOnDragDropped(event,backlog);
				} catch (IOException e) {
					e.printStackTrace();
				}
		     }
		});
		todo.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	DragEventHandler.setOnDragOver(event);
		    }
		});

		todo.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	try {
					DragEventHandler.setOnDragDropped(event,todo);
				} catch (IOException e) {
					e.printStackTrace();
				}
		     }
		});
		doing.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	DragEventHandler.setOnDragOver(event);
		    }
		});

		doing.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	try {
					DragEventHandler.setOnDragDropped(event,doing);
				} catch (IOException e) {
					e.printStackTrace();
				}
		     }
		});
		done.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	DragEventHandler.setOnDragOver(event);
		    }
		});

		done.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {

		    	try {
					DragEventHandler.setOnDragDropped(event,done);
				} catch (IOException e) {
					e.printStackTrace();
				}
		     }
		});
			// vbox handlers done______________________________________________________________________________________________
		
			//lets me create new task
		addNew.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>()
	    {
	        public void handle(ActionEvent e)
	        {
	        	CreateTask.addNewTask("","","");
	        }
	    });   
		
			//closes my app
		close.setOnAction(e -> Platform.exit());
	}
		
			//adds new task to list
		public static void addNewTask(String title, String text, String list) throws IOException {
        
			//Creates new label and adds click event
		Label lblTask = new Label(title + "\n" + text);
    	
    	lblTask.setOnDragDetected(new EventHandler<MouseEvent>() {
    	    public void handle(MouseEvent event) {
    	        	// drag detected
    	        Dragboard db = lblTask.startDragAndDrop(TransferMode.ANY);

    	        	// add string to dragboard, must have something for drag to start
    	        ClipboardContent content = new ClipboardContent();
    	        content.putString(lblTask.getText());
    	        db.setContent(content);

    	        event.consume();
    	    }
    	});
    	
    		//adds handlers for mouse events
    	lblTask.setOnMouseClicked(new EventHandler<MouseEvent>() {

    	    @Override
    	    public void handle(MouseEvent click) {
    	
    	    		//removes clicked item if user accepts
    	        if (click.getClickCount() == 2) {
    	        	if( MouseClickHandler.doubleClick()) {
          		
    	        		try {
							FileHandler.removeTask(click);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    	                
    	        	}
    	        }
    	        if(click.getButton() == MouseButton.SECONDARY) {
    	        		
    	        		//lets user update task        
    	        	try {
						MouseClickHandler.rightClick(click);
					} catch (IOException e) {
						e.printStackTrace();
					}
 	                               
    	        }
    	    }
    	});
    		//sets an id to task
    	lblTask.setId(title);
    	
    		//puts new task in correct list
    	if(list.equals("Backlog")) backlog.getChildren().add(lblTask);
    	if(list.equals("Todo")) todo.getChildren().add(lblTask);
    	if(list.equals("Doing")) doing.getChildren().add(lblTask);
    	if(list.equals("Done")) done.getChildren().add(lblTask);
    
    	}
			//removes task
		public static void removeTask(Label lbl) {
				backlog.getChildren().remove(lbl);
				todo.getChildren().remove(lbl);
				doing.getChildren().remove(lbl);
				done.getChildren().remove(lbl);
			
		}
			//runs the app
		public static void main(String[] args) {
		launch(args);
	}
}
