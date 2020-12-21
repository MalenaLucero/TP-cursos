package base.model;

import base.util.StringUtil;

public class Course {
	private int id;
	private String name;
	private int id_catedra;
	
	public Course(String name) {
		setName(name);
	}
	
	public Course(String name, int id_catedra) {
		this.id_catedra = id_catedra;
		setName(name);
	}
	
	public Course(int id, String name, int id_catedra) {
		this.id = id;
		setName(name);
		this.id_catedra = id_catedra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!StringUtil.isBlank(name)) {
			this.name = name;
		} 
	}
	
	public int getCatedra() {
		return id_catedra;
	}

	public void setCatedra(int id_catedra) {
		this.id_catedra = id_catedra;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return String.format("ID: %s - Curso: %s - ID Catedra: %s", id, name, id_catedra);
	}
}
