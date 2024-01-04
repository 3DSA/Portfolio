package com.company;
public class Type {
    private String name;
    private int number;
    private String phrase;
    public Type(String a, int b) {
        name = a;
        number = b;
    }
    public Type(String a, String b) {
        name = a;
        phrase = b;
    }
    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    public String getPhrase() {
        return phrase;
    }
    public void setNumber(int a) {
        number = a;
    }
    public void setPhrase(String a) {
        phrase = a;
    }
}
