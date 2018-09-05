package com.spiralg.chatrestful.model;

public class Token {
    private int id;
    private String key;
    private String expectDate;
    public Token(){
    }
    public Token(int id, String key, String expectDate) {
        this.id = id;
        this.key = key;
        this.expectDate = expectDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getExpectDate() {
        return expectDate;
    }
    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }
    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", expectDate='" + expectDate + '\'' +
                '}';
    }
}
