package base.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import base.model.Course;
import base.model.Student;

public class ExportData {
	public static void generateGradesBooklet(List<Map<String, Object>> gradesList) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("boletin.txt", true));
		
		Student student = (Student)gradesList.get(0).get("student");
		writer.append("Nombre: " + student.getFullName());
		writer.newLine();
		
		List<String> subjects = new ArrayList<String>();
		for(Map<String, Object> map: gradesList) {
			Course course = (Course) map.get("course");
			subjects.add(course.getName());
		}
		String subjectsList = "";
		for(String subject: subjects) {
			subjectsList += (subject + " | ");
		}
		writer.append(subjectsList);
		
	    writer.newLine();
	    writer.close();
	    System.out.println("Data successfully stored");
	}
}
