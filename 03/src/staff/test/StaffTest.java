package staff.test;
import staff.StaffMember;
import staff.Lecturer;


public class StaffTest{
    public static void printStaffDetails(StaffMember s) {
        String newString = s.toString();
        System.out.println(newString);
    }

    public static void main(String[] args) {
        StaffMember J = new StaffMember("Jay", 6666, "2008-5-1", "2020-8-1");
        StaffMember J2 = new StaffMember("Jay", 6666, "2008-5-1", "2020-8-1");
        Lecturer M = new Lecturer("Mercy", 12345, "2010-6-1", "2020-6-1", "CSE", "A");
        Lecturer M2 = new Lecturer("Mercy", 12345, "2010-6-1", "2020-6-1", "CSE", "A");

        printStaffDetails(J);
        printStaffDetails(M);

        // Test the equals method
        // reflexive: x.equals(x) should return true
        assert J.equals(J);
        assert M.equals(M);

        // symmetric: x.equals(y) return true if and only if y.equals(x) return true
        assert J.equals(J2);
        assert J2.equals(J);
        assert M.equals(M2);
        assert M2.equals(M);

        // test consistency
        // change the name of J2 to Tom, now should be different to J
        J2.setName("Tom");
        assert J.equals(J2) == false;
        // change the name of J to Tom as well, now should be the same
        J.setName("Tom");
        assert J.equals(J2);

        // test transitive: x.equals(y) true and y.equals(z) true then x.equals(z) true
        StaffMember A1 = new StaffMember("Sam", 12345, "2000-6-1", "2030-8-1");
        StaffMember A2 = new StaffMember("Sam", 12345, "2000-6-1", "2030-8-1");
        StaffMember A3 = new StaffMember("Sam", 12345, "2000-6-1", "2030-8-1");

        assert A1.equals(A2);
        assert A2.equals(A3);
        assert A1.equals(A3);

        // x.equals(null) should return false
        assert J.equals(null) == false;
        assert M.equals(null) == false;
    }
}