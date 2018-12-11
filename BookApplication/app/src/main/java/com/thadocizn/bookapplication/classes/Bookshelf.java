package com.thadocizn.bookapplication.classes;

public class Bookshelf {
    private int shelfId;
    private int tagId;
    private int bookId;
    private String shelfName;

    public Bookshelf(int shelfId, String shelfName, int tagId, int bookId) {
        this.shelfId   = shelfId;
        this.shelfName = shelfName;
        this.tagId     = tagId;
        this.bookId    = bookId;
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
