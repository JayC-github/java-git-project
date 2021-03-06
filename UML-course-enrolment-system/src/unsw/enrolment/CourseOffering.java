package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

public class CourseOffering {

    private Course course;
    private String term;
    private List<Session> sessions;
    private List<Enrolment> enrolments;

    public CourseOffering(Course course, String term) {
        this.course = course;
        this.term = term;
        this.sessions = new ArrayList<>();
        this.enrolments = new ArrayList<>();
        this.course.addOffering(this);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public Course getCourse() {
        return course;
    }

    // get the string coursecode
    public String getCourseCode() {
        return course.getCourseCode();
    }

    public String getTerm() {
        return term;
    }

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

    ///////////
	public List<Enrolment> getEnrolments() {
		return enrolments;
	}

    ////////
	public void addEnrolments(Enrolment enrolment) {
		enrolments.add(enrolment);
    }


}
