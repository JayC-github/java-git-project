package unsw.enrolment.test;

import java.time.LocalTime;

import java.sql.Date;
import java.time.DayOfWeek;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        LocalTime start = LocalTime.of(12, 0);
        LocalTime end = LocalTime.of(2, 0);
        Session comp1511Lec = new Session("CSE18", DayOfWeek.MONDAY, start, end);
        Tutorial comp1511tut = new Tutorial("CSE08", DayOfWeek.TUESDAY, start, end, "Matthew");

        comp1511Offering.addSession(comp1511Lec);
        comp1511Offering.addSession(comp1511tut);

        // TODO Create a student
        Student student = new Student("z5261536");
        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        student.tryEnroll(comp1511, "19T1");
        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        student.tryEnroll(comp1531, "19T1");

        // TODO Give the student a passing grade for COMP1511
        student.setGrade(comp1511, "19T1", 60, "pass");
        assert student.receiveGrade(comp1511, "19T1") == "pass";
        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        student.tryEnroll(comp2521, "19T2");

    }
}
