package com.example.futurefinder;

public class College {
    private String fieldOfStudy = "";
    private int tuition = 0;
    private String environment = "";
    private String type = "";
    private int size = 0;
    private String name = "";
    private String code = "";

    public College (String fieldOfStudy, int tuition, String environment, int size, String type, String name, String code){
        this.fieldOfStudy = fieldOfStudy;
        this.environment = environment;
        this.type = type;
        this.size = size;
        this.tuition = tuition;
        this.name = name;
        this.code = code;
    }

    public College()
    {
        this.fieldOfStudy = "";
        this.environment = "";
        this.type = "";
        this.size = 0;
        this.tuition = 0;
        this.name = "";
        this.code = "";
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public String getName() {
        return name;
    }

    public String getCode(){
        return this.code;
    }

}
