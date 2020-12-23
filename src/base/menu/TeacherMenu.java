package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.DAO.TeacherDAO;
import base.controller.TeacherController;
import base.model.Teacher;
import base.util.InputUtil;

public class TeacherMenu {
	public static void printMenu() {
		System.out.println();
		System.out.println("------ MENU DE DOCENTES ------");
		System.out.println("1. Listar docentes");
		System.out.println("2. Buscar docente por ID");
		System.out.println("3. Buscar docente por nombre y apellido");
		System.out.println("4. Agregar docente");
		System.out.println("5. Modificar docente");
		System.out.println("6. Eliminar docente");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			TeacherController.listAll(connection);
			break;
		case 2:
			int id = InputUtil.inputInt(sc, "Ingrese ID del docente:");
			TeacherController.getById(connection, id);
			break;
		case 3:
			String name = InputUtil.inputString(sc, "Ingrese nombre:");
			String lastname = InputUtil.inputLine(sc, "Ingrese apellido:");
			TeacherController.getByLastname(connection, name, lastname);
			break;
		case 4:
			Teacher teacher = createTeacher(sc);
			TeacherController.insert(connection, teacher);
			break;
		case 5:
			Teacher editTeacher = editTeacher(sc, connection);
			TeacherController.edit(connection, editTeacher);
			break;
		case 6:
			int deleteId = InputUtil.inputInt(sc, "Ingrese ID del docente:");
			TeacherController.delete(connection, deleteId);
			break;
		}
	}
	
	private static Teacher createTeacher(Scanner sc) {
		String name = InputUtil.inputStringNotBlank(sc, "Ingrese nombre");
		String lastname = InputUtil.inputLine(sc, "Ingrese apellido:");
		String alternative_name1 = null;
		if(Util.confirmOptionalField(sc, "primer nombre alternativo")) {
			alternative_name1 = InputUtil.inputStringNotBlank(sc, "Ingrese primer nombre alternativo:");
		}
		String alternative_name2 = null;
		if(Util.confirmOptionalField(sc, "segundo nombre alternativo")) {
			alternative_name2 = InputUtil.inputStringNotBlank(sc, "Ingrese segundo nombre alternativo:");
		}
		String description = null;
		if(Util.confirmOptionalField(sc, "descripcion")) {
			description = InputUtil.inputStringNotBlank(sc, "Ingrese descripcion:");
		}
		String image = null;
		if(Util.confirmOptionalField(sc, "url de imagen")) {
			image = InputUtil.inputStringNotBlank(sc, "Ingrese url de la imagen:");
		}
		return new Teacher(name, lastname, alternative_name1, alternative_name2, description, image);
	}
	
	private static Teacher editTeacher(Scanner sc, Connection connection) throws SQLException {
		int id = InputUtil.inputInt(sc, "Ingrese ID del docente a editar:");
		Teacher teacher = TeacherDAO.findById(connection, id);
		if(Util.confirmEditMessage(sc, "nombre", teacher.getName())) {
			String name = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			teacher.setName(name);
		}
		if(Util.confirmEditMessage(sc, "apellido", teacher.getLastname())) {
			String lastname = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			teacher.setLastname(lastname);
		}
		if(Util.confirmEditMessage(sc, "primer nombre alternativo", teacher.getAlternative_name1())) {
			String alternative_name1 = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			teacher.setAlternative_name1(alternative_name1);
		}
		if(Util.confirmEditMessage(sc, "nombre", teacher.getAlternative_name2())) {
			String alternative_name2 = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			teacher.setAlternative_name2(alternative_name2);
		}
		if(Util.confirmEditMessage(sc, "descripcion", teacher.getDescription())) {
			String description = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			teacher.setDescription(description);
		}
		if(Util.confirmEditMessage(sc, "url de imagen", teacher.getImage())) {
			String image = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			teacher.setImage(image);
		}
		return teacher;
	}
}