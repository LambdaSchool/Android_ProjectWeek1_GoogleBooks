package com.thadocizn.googlebooks.bookshelfInfo;

public class Bookshelf {

    private String name;
    private long bookshelfId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBookshelfId() {
        return bookshelfId;
    }

    public void setBookshelfId(long bookshelfId) {
        this.bookshelfId = bookshelfId;
    }

    public Bookshelf(long bookshelfId, String name) {

        this.name = name;
        this.bookshelfId = bookshelfId;

    }
}
