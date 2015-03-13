package at.zachner.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	private Part file;
	private String fileContent;

	public void upload() {
	  try {
	    fileContent = new Scanner(file.getInputStream()).useDelimiter("A").next();
	  } catch (IOException e) {
	  	// Error handling
	  }
	}

	public Part getFile() {
	  return file;
	}

	public void setFile(Part file) {
	  this.file = file;
	}
	

}