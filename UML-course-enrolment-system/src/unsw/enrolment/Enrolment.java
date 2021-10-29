package unsw.enrolment;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    // get the string coursecode
    public String getCourseCode() {
        return offering.getCourseCode();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    // get the string grade of the student
	public String getGrade() {
		return grade.getGrade();
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
