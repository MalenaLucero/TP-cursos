package base.model;

import base.util.StringUtil;

public class Student {
	private int id;
	private String name;
	private String lastname;
	private String alternative_name;
	
	public Student(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
	
	public Student(String name, String lastname, String alternative_name) {
		this.name = name;
		this.lastname = lastname;
		this.alternative_name = alternative_name;
	}
	
	public Student(int id, String name, String lastname, String alternative_name) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.alternative_name = alternative_name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(StringUtil.isBlank(name)) {
			System.out.println("Nombre invalido");
		} else {
			this.name = name;
		}
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		if(StringUtil.isBlank(lastname)) {
			System.out.println("Apellido invalido");
		} else {
			this.lastname = lastname;
		}
	}

	public String getAlternative_name() {
		return alternative_name;
	}

	public void setAlternative_name(String alternative_name) {
		this.alternative_name = alternative_name;
	}
	
	public String toString() {
		if(StringUtil.isBlank(alternative_name)) {
			return String.format("ID: %s - Alumno: %s %s", id, name, lastname);
		} else {
			return String.format("ID: %s - Alumno: %s %s (%s)", id, name, lastname, alternative_name);
		}
	}
}
