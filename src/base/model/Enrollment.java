package base.model;

import base.enums.CourseState;
import base.enums.Division;
import base.enums.EnrollmentState;
import base.util.ValidationUtil;

public class Enrollment {
	private int id;
	private int id_course;
	private int id_student;
	private EnrollmentState enrollment_state;
	private int id_teacher;
	private Division division;
	private int grade1;
	private int grade2;
	private int average_grade;
	private CourseState course_state;
	private int year;
	
	public Enrollment(int id_course, int id_student) {
		this.id_course = id_course;
		this.id_student = id_student;
		this.enrollment_state = EnrollmentState.activo;
		this.division = Division.A;
		this.course_state = CourseState.cursando;
		this.year = 2021;
	}
	
	public Enrollment(int id_course, int id_student, String enrollment_state, int id_teacher,
			String division, int grade1, int grade2, int average_grade, String course_state, int year) {
		this.id_course = id_course;
		this.id_student = id_student;
		this.enrollment_state = EnrollmentState.valueOf(enrollment_state);
		this.id_teacher = id_teacher;
		this.division = Division.valueOf(division);
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.average_grade = average_grade;
		this.course_state = CourseState.valueOf(course_state);
		this.year = year;
	}
	
	public Enrollment(int id, int id_course, int id_student, String enrollment_state, int id_teacher,
			String division, int grade1, int grade2, int average_grade, String course_state, int year) {
		this.id = id;
		this.id_course = id_course;
		this.id_student = id_student;
		this.enrollment_state = EnrollmentState.valueOf(enrollment_state);
		this.id_teacher = id_teacher;
		this.division = Division.valueOf(division);
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.average_grade = average_grade;
		this.course_state = CourseState.valueOf(course_state);
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public int getId_course() {
		return id_course;
	}

	public void setId_course(int id_course) {
		this.id_course = id_course;
	}

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	public int getAverage_grade() {
		return average_grade;
	}

	public void setAverage_grade(int average_grade) {
		if(ValidationUtil.isGradeValid(average_grade)) {
			this.average_grade = average_grade;
			if(this.average_grade > 5) {
				setCourseState(CourseState.aprobado.getValue());
			} else {
				setCourseState(CourseState.desaprobado.getValue());
			}
		}
	}

	public String getCourseState() {
		return course_state.getValue();
	}

	public void setCourseState(String course_state) {
		this.course_state = CourseState.valueOf(course_state);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getEnrollment_state() {
		return enrollment_state.getValue();
	}

	public void setEnrollment_state(String enrollment_state) {
		this.enrollment_state = EnrollmentState.valueOf(enrollment_state);
	}

	public int getId_teacher() {
		return id_teacher;
	}

	public void setId_teacher(int id_teacher) {
		this.id_teacher = id_teacher;
	}

	public String getDivision() {
		return division.getValue();
	}

	public void setDivision(String division) {
		this.division = Division.valueOf(division);
	}

	public int getGrade1() {
		return grade1;
	}

	public void setGrade1(int grade1) {
		if(ValidationUtil.isGradeValid(grade1)) {
			this.grade1 = grade1;
			setAverage_grade(calculateAverageGrade());
		}
	}

	public int getGrade2() {
		return grade2;
	}

	public void setGrade2(int grade2) {
		if(ValidationUtil.isGradeValid(grade2)) {
			this.grade2 = grade2;
			setAverage_grade(calculateAverageGrade());
		}
	}

	public String toString() {
		if(this.average_grade == 0) {
			return String.format("ID: %s - ID alumno: %s - ID curso: %s", id, id_student, id_course);
		} else {
			return String.format("ID: %s - ID alumno: %s - ID curso: %s - Estado: %s - Notas: %s, %s, %s - Año: %s",
								id, id_student, id_course, course_state, grade1, grade2, average_grade, year);
		}
	}
	
	private int calculateAverageGrade() {
		return (grade1 + grade2) / 2;
	}
}
