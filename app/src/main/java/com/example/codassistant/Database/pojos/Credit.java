package com.example.codassistant.Database.pojos;

public class Credit {
    private String creditFor;
    private String creditTo;

    public Credit(String creditFor, String creditTo) {
        this.creditFor = creditFor;
        this.creditTo = creditTo;
    }

    public Credit() {

    }

    public String getCreditFor() {
        return creditFor;
    }

    public void setCreditFor(String creditFor) {
        this.creditFor = creditFor;
    }

    public String getCreditTo() { return creditTo; }

    public void setCreditTo(String creditTo) { this.creditTo = creditTo; }
}
