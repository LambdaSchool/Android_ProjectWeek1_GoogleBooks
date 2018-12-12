package com.thadocizn.bookapplication.classes;

public class Bookshelf {
    private int bookId;
    private String shelf_name;

    public Bookshelf(int bookId, String shelfName) {
        this.bookId    = bookId;
        this.shelf_name = shelfName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getShelf_name() {
        return shelf_name;
    }

    public void setShelf_name(String shelf_name) {
        this.shelf_name = shelf_name;
    }
}
