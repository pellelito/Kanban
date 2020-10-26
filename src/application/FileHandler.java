package application;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class FileHandler {
	
	static List<Task> tasks = new ArrayList();
	
	public static void readFromFile() throws IOException, InterruptedException {

		Path pathToFile = Paths.get("file.txt"); 
		
			// create an instance of BufferedReader 
		try (
				BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) { 
				// read the first line from the text file 
			String line = br.readLine(); 
			
				// loops until all lines are read 
			while (line != null) { 

					//creates tasks
				String[] attributes = line.split("##");		
				addTask(attributes[0], attributes[1], attributes[2]);

				line = br.readLine(); 
				
				} 
			} catch (IOException ioe) { ioe.printStackTrace(); 
		} 
	}
	
	public static void writeToFile() throws IOException {

			//will write to file
		FileWriter writer = new FileWriter("file.txt");
		tasks.forEach((n) -> {
					
		try {
			writer.write(n.getTitle() +"##" + n.getText() + "##" + n.getList() +"\n");
			
		} catch (IOException e) {
			e.printStackTrace();
					}
		});

		writer.close(); 
	}

	public static void addTask(String title, String text, String list) throws IOException {
		boolean check = true;	
		//check if title is unique
		Iterator<Task> itr = tasks.iterator(); 
		while (itr.hasNext()) { 
			Task task = itr.next(); 
				if (task.getTitle().equals(title)) { 				
					check = false;
					new Alert(Alert.AlertType.ERROR, "Title needs to be unique!").showAndWait();
					CreateTask.addNewTask(title,text,list);
					} 
				} 
		if(check) {
			
				//create new task
			Task task = new Task(title,text,list);
				// adding task into ArrayList 
			tasks.add(task);
				//updates file
			writeToFile();
				//adds task to UI
			Main.addNewTask(title,text,list);
		}
			
	}
		//removes task i both file & UI
	public static void removeTask(MouseEvent event) throws IOException {
		Label lblTask = (Label)event.getSource();
		String lines[] = lblTask.getText().split("\\r?\\n");
		Iterator<Task> itr = tasks.iterator(); 
		Task tsk = null;
		
			//iterates through tasks to find right task
		while (itr.hasNext()) { 
			Task task = itr.next(); 
				if (task.getTitle().equals(lines[0])) { 				
					tsk = task;		
					} 
				} 
			//if correct task please delete it
		if(!(tsk == null))tasks.remove(tsk);

			//updates file
		writeToFile();
			//updates UI
		Main.removeTask(lblTask);
	}

	public static void setList(DragEvent event, Label label) throws IOException {
			//sets new list(in file) when user drags task
		Iterator<Task> itr = tasks.iterator(); 
		Label lblTask = (Label) event.getGestureSource();
		String list = lblTask.getParent().getId() ; 
	
		String lines[] = label.getText().split("\\r?\\n");
		
		while (itr.hasNext()) { 
			Task task = itr.next(); 
				if (task.getTitle().equals(lines[0])) { 				
					task.setList(list);	
					} 
				} 
			//updates file
		writeToFile();
	}
	
}
