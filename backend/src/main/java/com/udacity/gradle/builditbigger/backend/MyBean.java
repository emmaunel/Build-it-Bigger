package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

//    private JokeTelling jokeTelling;
//
//    public MyBean() {
//        jokeTelling = new JokeTelling();
//    }
//
//    public String getJoke() {
//        return jokeTelling.getRandomJokes();
//    }

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}