package com.tuan.vocabulary.review;

/**
 * Created by tuanl on 20-Feb-17.
 */

public class Word {
    private int ID;
    private String Key;
    private String Value;
    private boolean IsCorrect;

    public Word(){

    }
    public Word(int id, String key, String value){
        this.ID = id;
        this.Key = key;
        this.Value = value;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public boolean isCorrect() {
        return IsCorrect;
    }

    public void setCorrect(boolean correct) {
        IsCorrect = correct;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
