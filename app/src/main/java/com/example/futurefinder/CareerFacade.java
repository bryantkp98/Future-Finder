package com.example.futurefinder;

public class CareerFacade
{
    private final com.example.futurefinder.College college;
    private final com.example.futurefinder.FieldOfStudy fieldOfStudy;

    public CareerFacade()
    {
        this.college = new com.example.futurefinder.College();
        this.fieldOfStudy = new com.example.futurefinder.FieldOfStudy();
    }

    public void find() {
        college.setFieldOfStudy("ART");
        college.setEnvironment("rural");
        college.setType("Public");
        college.setSize(10000);
        college.setTuition(30000);

        fieldOfStudy.setName("STEM");
    }

}

class random
{
    public static void main(String[] args)
    {
        CareerFacade yourCareer = new CareerFacade();
        yourCareer.find();

    }
}
