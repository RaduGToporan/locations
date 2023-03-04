package com.wdev.locations.beans;

public class MyLocation {
    int id;
    String xcoord;
    String ycoord;
    String shortNote;
    String comments;

    public MyLocation(int id, String xcoord, String ycoord, String shortNote, String comments) {
        this.id = id;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.shortNote = shortNote;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXcoord() {
        return xcoord;
    }

    public void setXcoord(String xcoord) {
        this.xcoord = xcoord;
    }

    public String getYcoord() {
        return ycoord;
    }

    public void setYcoord(String ycoord) {
        this.ycoord = ycoord;
    }

    public String getShortNote() {
        return shortNote;
    }

    public void setShortNote(String shortNote) {
        this.shortNote = shortNote;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

// JavaBeans are classes that encapsulate many objects into a single object (the bean). It is a java class that should follow following conventions:
//
// Must implement Serializable.
// It should have a public no-arg constructor.
// All properties in java bean must be private with public getters and setter methods.

// Syntax for setter methods:
// It should be public in nature.
// The return-type should be void.
// The setter method should be prefixed with set.
// It should take some argument i.e. it should not be no-arg method.

// Syntax for getter methods:
// It should be public in nature.
// The return-type should not be void i.e. according to our requirement we have to give return-type.
// The getter method should be prefixed with get.
// It should not take any argument.
// For Boolean properties getter method name can be prefixed with either “get” or “is”. But recommended to use “is”.
// References: https://www.geeksforgeeks.org/javabean-class-java/
