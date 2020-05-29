package bkp.com.hospitalmanagementsystem.model;

public class Bill {
    private String aid, bill,medicinerates, status;

    public Bill() {
    }

    public Bill(String aid, String bill, String medicinerates, String status) {
        this.aid = aid;
        this.bill = bill;
        this.medicinerates = medicinerates;
        this.status = status;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getMedicinerates() {
        return medicinerates;
    }

    public void setMedicinerates(String medicinerates) {
        this.medicinerates = medicinerates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
