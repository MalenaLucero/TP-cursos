package base.model;

import java.util.Date;

public class Teacher {
	private int id;
	private String name;
	private String lastname;
	private String alternative_name1;
	private String alternative_name2;
	private String description;
	private String image;
	private Date birthdate;
	
	public Teacher(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
	
	public Teacher(String name, String lastname, String alternative_name1, String alternative_name2,
					String description, String image, Date birthdate) {
		this.name = name;
		this.lastname = lastname;
		this.alternative_name1 = alternative_name1;
		this.alternative_name2 = alternative_name2;
		this.description = description;
		this.image = image;
		this.birthdate = birthdate;
	}
	
	public Teacher(int id, String name, String lastname, String alternative_name1,
					String alternative_name2, String description, String image, Date birthdate) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.alternative_name1 = alternative_name1;
		this.alternative_name2 = alternative_name2;
		this.description = description;
		this.image = image;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAlternative_name1() {
		return alternative_name1;
	}

	public void setAlternative_name1(String alternative_name1) {
		this.alternative_name1 = alternative_name1;
	}

	public String getAlternative_name2() {
		return alternative_name2;
	}

	public void setAlternative_name2(String alternative_name2) {
		this.alternative_name2 = alternative_name2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Date getBirthdate() {
		 return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String toString() {
		return String.format("ID: %s - Docente: %s %s (%s)", id, name, lastname, alternative_name1);
	}
	
	public String toStringBirthday() {
		return String.format("Docente: %s %s - Fecha de cumpleaños: %s", name, lastname, birthdate);
	}
}
