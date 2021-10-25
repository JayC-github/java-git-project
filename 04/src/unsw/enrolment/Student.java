package unsw.enrolment;
import java.util.ArrayList;


public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<>();
    }

	public String getZID() {
		return zid;
    }

    // get the enrolment
    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }

    // add enrolment class
    public void addEnrolments(Enrolment enrolment) {
		enrolments.add(enrolment);
	}

    // set the grade for a student for a course in a paritculat term
    // assume just pass or fail pass == >=50, fail == <50
    public void setGrade(Course course, String term, int mark, String grade) {
        for (Enrolment e: enrolments) {
            if (e.getCourse().equals(course) && e.getTerm().equals(term)) {
                Grade g = new Grade(mark, grade);
                e.setGrade(g);
            }
        }
    }

    // Students receive grades (pass, fail, etc.) for courses in particular semesters
    public String receiveGrade(Course course, String term) {
        // if student receive grade for this course in this term
        for (Enrolment e: enrolments) {
            if (e.getCourse().equals(course) && e.getTerm().equals(term)) {
                return e.getGrade();
            }
        }
        // else return null
        return null;
    }

    // For a student to enrol in a course, s/he must have passed all prerequisite courses
    public boolean passPrereq(Course course, String term) {
        // if this course had no prerequisite couese
        if (course.getPrereq().size() == 0) {
            return true;
        }

        // else if student pass all the prerequisite course(s)
        for (Course prereq: course.getPrereq()) {
            boolean initial = false;
            for (Enrolment e: enrolments) {
                // if student did and passed one prereq course before this term
                // make the statement true, check the rest prereq
                if (e.getCourse().equals(prereq) && e.getGrade() == "pass" && !e.getTerm().equals(term)) {
                    initial = true;
                }
            }

            // if the student didn't pass any one of the prereq, return false
            if (!initial) {
                return false;
            }
        }

        // pass all the prerequisite courses
        return true;
    }

    //check if they offer in a particular term
    public boolean offer(Course course, String term) {
        for (CourseOffering c: course.getOfferings()) {
            if (c.getCourse().equals(course) && c.getTerm().equals(term)) {
                return true;
            }
        }
        return false;
    }

    // student try to enroll a course that are offered in particulat semester
    public void tryEnroll(Course course, String term) {
        // student passed all prerequisite courses
        // and this course is offered in this term
        if (passPrereq(course, term) && offer(course, term)) {
            for (CourseOffering c: course.getOfferings()) {
                if (c.getTerm().equals(term)) {
                    // student enrols in the course
                    // and must also enrol in some sessions of that course
                    Enrolment newE = new Enrolment(c, this);
                    Grade init = new Grade(0, null);
                    newE.setGrade(init);
                    this.addEnrolments(newE);
                    System.out.println("U enrolled in " + course.toString() + " successfully!");
                    return;
                }
            }
        } else {
            System.out.println("U do not meet the prereq requirement!");
        }
    }

}
