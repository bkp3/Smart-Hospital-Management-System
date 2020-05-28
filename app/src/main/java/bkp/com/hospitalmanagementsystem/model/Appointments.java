package bkp.com.hospitalmanagementsystem.model;

public class Appointments {

    private String aid, age, gender, fname, name, notes, phone, problem, status, department, ward, floor;

    public Appointments() {
    }

    public Appointments(String aid, String age, String gender, String fname, String name, String notes, String phone, String problem, String status, String department, String ward, String floor) {
        this.aid = aid;
        this.age = age;
        this.gender = gender;
        this.fname = fname;
        this.name = name;
        this.notes = notes;
        this.phone = phone;
        this.problem = problem;
        this.status = status;
        this.department = department;
        this.ward = ward;
        this.floor = floor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
