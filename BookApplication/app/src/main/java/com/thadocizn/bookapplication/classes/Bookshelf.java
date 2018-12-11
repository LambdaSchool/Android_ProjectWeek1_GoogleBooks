package com.thadocizn.bookapplication.classes;

public class Bookshelf {
    private int shelfId;
    private int tagId;
    private int bookId;
    private String shelfName;

    public Bookshelf(int shelfId, int tagId, int bookId) {
        this.shelfId   = shelfId;
        this.tagId     = tagId;
        this.bookId    = bookId;
    }

    public Bookshelf(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

}
