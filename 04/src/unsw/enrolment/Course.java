package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

/**
 * A course in the enrolment system.
 * @author Robert Clifton-Everest
 *
 */
public class Course {

    private String courseCode;
    private String title;
    private int uoc;
    private List<Course> prereqs;
    private List<CourseOffering> courseOfferings;


    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
        this.prereqs = new ArrayList<Course>();
        this.courseOfferings = new ArrayList<CourseOffering>();
    }


    public void addPrereq(Course course) {
        prereqs.add(course);
    }

    // get prerequesite courses for a course
    public List<Course> getPrereq() {
        return prereqs;
    }

    // get the courseofferings for a course
    public List<CourseOffering> getOfferings() {
        return courseOfferings;
    }

    public void addOffering(CourseOffering offering) {
        courseOfferings.add(offering);
    }

    public String getCourseCode() {
        return courseCode;
    }

    // course must have credit point values
    public void setUOC(int uoc) {
        this.uoc = uoc;
    }

    public int getUOC() {
        return uoc;
    }

    @Override
    public String toString(){
        return getClass().toString()+", coursecode="+courseCode;
    }

}
