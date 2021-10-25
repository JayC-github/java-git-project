package staff;
/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {
    public String name;
    public int salary;
    public String hire_date;
    public String end_date;


    
    /**
     *
     * @param name name of the StaffMember
     * @param salary salary of the StaffMember
     * @param hire_date hire_date of the StaffMember
     * @param end_date end_date of the StaffMember
     */

    public StaffMember(String name, int salary, String hire_date, String end_date) {
        this.name = name;
        this.salary = salary;
        this.hire_date = hire_date;
        this.end_date = end_date;
    }

    /**
     *
     * @return the name of the StaffMember
     */

    public String getName() {
        return name;
    }

    /**
     * 
     * @param name set the name of the StaffMember
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the salary of the StaffMemeber
     */
    public int getSalary() {
        return salary;
    }
    /**
     * 
     * @param salary set the salary of the StaffMember
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * 
     * @return the hide date of the StaffMemebr
     */
    public String getHire_date() {
        return hire_date;
    }
    /**
     * 
     * @param hire_date set the hire date of the StaffMmeber
     */
    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    /**
     * 
     * @return the end date of the StaffMember
     */
    public String getEnd_date() {
        return end_date;
    }
    /**
     * 
     * @param end_date set the end date of the StaffMember
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString(){
        return getClass().toString()+", name="+name+", salary="+salary+", hire date=" + hire_date + ",end date=" + end_date;
    }
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        // don't use instance, use the getClass instead
        if (getClass() != object.getClass()) {
            return false;
        }

        StaffMember s = (StaffMember) object;
        return (name.equals(s.name) && hire_date.equals(s.hire_date) && end_date.equals(s.end_date) && salary==s.salary);
    }

}