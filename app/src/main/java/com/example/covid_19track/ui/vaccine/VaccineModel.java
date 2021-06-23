package com.example.covid_19track.ui.vaccine;

public class VaccineModel {
    // string variable for center name.
    String centerName;

    // string variable for center address.
    String centerAddress;

    // string variable for center opening time.
    String centerFromTime;

    // string variable for center closing time.
    String centerToTime;

    // string variable for center fee type
    String fee_type;

    // int variable for age limit.
    int ageLimit;

    // string variable for vaccination name.
    String vaccineName;

    // int variable for vaccine availability.
    int availableCapacity;

    public VaccineModel(String centerName, String centerAddress, String centerFromTime, String centerToTime, String fee_type, int ageLimit, String vaccineName, int availableCapacity) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.centerFromTime = centerFromTime;
        this.centerToTime = centerToTime;
        this.fee_type = fee_type;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.availableCapacity = availableCapacity;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getCenterFromTime() {
        return centerFromTime;
    }

    public void setCenterFromTime(String centerFromTime) {
        this.centerFromTime = centerFromTime;
    }

    public String getCenterToTime() {
        return centerToTime;
    }

    public void setCenterToTime(String centerToTime) {
        this.centerToTime = centerToTime;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
