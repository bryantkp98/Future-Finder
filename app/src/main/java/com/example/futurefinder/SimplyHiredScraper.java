package com.example.futurefinder;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

class SimplyHiredScraper extends AsyncTask<Object, String, ArrayList<Job>> {
    /**
     *
     *
     * @return ArrayList<Job> to be iterated through in the results screen
     */
    protected ArrayList<Job> doInBackground(Object... params){
        Document document;
        final String url = "https://www.simplyhired.com/search?";

        String jobTitle = (String) params[0];
        String stateFilt = (String) params[1];
        String jobType = (String) params[2];
        String searchTerm = getSearchTerm(jobTitle, stateFilt, jobType);

        ArrayList<Job> jobList = new ArrayList<Job>();

        try{
            document = Jsoup.connect(url + searchTerm).get();

            //creates a list of all job titles within the web page
            List<String> titleList = new ArrayList<String>();
            Elements titles = (document).select("a.card-link");
            titleList.addAll(titles.eachText());

            //creates a list of urls of each job card within the web page
            List<String> linksList = new ArrayList<String>();
            Elements links = (document).select("div.SerpJob-jobCard.card").select("a[href]");
            linksList.addAll(links.eachAttr("href"));

            //creates a list of the company of each job
            List<String> companyList = new ArrayList<String>();
            Elements companies = (document).select("div.SerpJob-jobCard.card").select("span.jobposting-company");
            companyList.addAll(companies.eachText());

            //goes through each list and creates a list of jobs with corresponding data
            for(int i = 0; i < titleList.size(); i++){
                Job job = new Job();
                job.setTitle(titleList.get(i));
                job.setUrl("https://www.simplyhired.com" + linksList.get(i));
                job.setCompany(companyList.get(i));
                jobList.add(job);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return jobList;
    }

    private String getSearchTerm(String jobSearch, String stateFilt, String jobType){
        String searchTerm = "q=" + jobSearch;

        if(stateFilt.length() > 0){
            searchTerm += "&l=" + stateFilt;
        }else if(jobType.length() > 0){
            searchTerm += "&jt=" + jobType;
        }
        return searchTerm;
    }

    protected void onPostExecute(ArrayList<Job> jobList) {

    }
}
