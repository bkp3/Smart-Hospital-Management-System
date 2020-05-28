package bkp.com.hospitalmanagementsystem.model;

public class ReceptionistInfo {
    private String name, mobile, qualification, email, password, hospitalAddress;

    public ReceptionistInfo() {
    }

    public ReceptionistInfo(String name, String mobile, String qualification, String email, String password, String hospitalAddress) {
        this.name = name;
        this.mobile = mobile;
        this.qualification = qualification;
        this.email = email;
        this.password = password;
        this.hospitalAddress = hospitalAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }
}
