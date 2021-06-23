package com.example.covid_19track.ui.more;

public class MoreModel {
    private int imageId;
    private String symptom_name;


    public MoreModel(int imageId, String symptom_name) {
        this.imageId = imageId;
        this.symptom_name = symptom_name;
    }


    public String getSymptom_name() {
        return symptom_name;
    }

    public void setSymptom_name(String symptom_name) {
        this.symptom_name = symptom_name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
