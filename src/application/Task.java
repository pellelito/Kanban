package application;

public class Task {
	
	String title;
	String text;
	String list;
	String id;
	
	public Task(String title, String text, String list) { 
		this.title = title; 
		this.text = text; 
		this.list = list; 
		this.id = title;
		}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 

}
