package com.app.pickamovie;

public class Rating {

    private String Source;
    private String Value;

    public Rating(String Source, String Value)
    {
        this.Source = Source;
        this.Value = Value;
    }

    public String getSource() {
        return Source;
    }

    public String getValue() {
        return Value;
    }
}