package com.example.futurefinder;

public class FieldOfStudy {
    private int total = 0;
    private String name = "";

    public FieldOfStudy ()
    {
        this.name = "";
        this.total = 0;
    }

    public FieldOfStudy (String name, int total){
        this.name = name;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
