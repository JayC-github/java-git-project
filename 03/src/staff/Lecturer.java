package staff;
public class Lecturer extends StaffMember {
    public String school;
    public String academic_status;
    /**
     *
     *  @param school
     *  @param academic_status
     */

    /**
     *
     * @param name name of the lecturer
     * @param salary salary of the lecturer
     * @param hire_date hire date of the lecturer
     * @param end_date end date of the lecturer
     * @param school school the lecturer belongs to
     * @param academic_status academic status of the lecturer
     */

    public Lecturer(String name, int salary, String hire_date, String end_date, String school, String academic_status) {
        super(name, salary, hire_date, end_date);
        this.school = school;
        this.academic_status = academic_status;
    }

    /**
     *
     * @return school of the lecturer
     */
    public String getSchool() {
        return school;
    }

    /**
     *
     * @param school set the school of the lecturer
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     *
     * @return the academic status of the lecturer
     */
    public String getStatus() {
        return academic_status;
    }

    /**
     *
     * @param academic_status set the academic status of the lecturer
     */
    public void setStatus(String academic_status) {
        this.academic_status = academic_status;
    }

    @Override
    public String toString(){
        return super.toString() + ", school name=" + school + ", academic status=" + academic_status;
    }
    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        }

        Lecturer l = (Lecturer) object;
        return  school.equals(l.school) && academic_status.equals(l.academic_status) && salary==l.salary;
    }

}
