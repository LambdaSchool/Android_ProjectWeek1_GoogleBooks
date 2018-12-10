package com.thadocizn.bookapplication.classes;

public class Bookshelf {
    private int shelfId;
    private String shelfName;

    public Bookshelf(int shelfId, String shelfName) {
        this.shelfId = shelfId;
        this.shelfName = shelfName;
    }

    public Bookshelf(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }
}
