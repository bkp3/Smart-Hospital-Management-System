package bkp.com.hospitalmanagementsystem.model;

public class Prescriptions {

    private String aid, age, gender, fname, name, notes, phone, problem, status, medicine, precautions;

    public Prescriptions() {
    }

    public Prescriptions(String aid, String age, String gender, String fname, String name, String notes, String phone, String problem, String status, String medicine, String precautions) {
        this.aid = aid;
        this.age = age;
        this.gender = gender;
        this.fname = fname;
        this.name = name;
        this.notes = notes;
        this.phone = phone;
        this.problem = problem;
        this.status = status;
        this.medicine = medicine;
        this.precautions = precautions;
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

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }
}
