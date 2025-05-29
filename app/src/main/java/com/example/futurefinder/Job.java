package com.example.futurefinder;

public class Job {
    private String title = "";
    private String company = "";
    private String url = "";

    public Job(String title,String company, String location, String url)
    {
        this.title = title;
        this.company = company;
        this.url = url;
    }
    public Job()
    {
        this.title = "";
        this.company = "";
        this.url = "";
    }

    public String getTitle()
    {
        return  this.title;
    }

    public String getCompany() {
        return company;
    }

    public String getUrl(){
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUrl(String url){
        this.url = url;
    }


}
