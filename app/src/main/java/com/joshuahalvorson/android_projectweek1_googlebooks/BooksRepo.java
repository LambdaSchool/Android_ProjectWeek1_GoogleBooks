package com.joshuahalvorson.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class BooksRepo {
    ArrayList<BookVolume> booksList;
    public void addBook(final BookVolume bookVolume){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BooksDbDao.createBookEntry(bookVolume);
            }
        }).start();
    }

    public ArrayList<BookVolume> readBooks(){
        booksList = BooksDbDao.readAllBookEntries();
        return booksList;
    }

    public void updateBookHasRead(BookVolume bookVolume){
        BooksDbDao.updateBookEntryHasRead(bookVolume);
    }

    public void updateBookIsFavorite(BookVolume bookVolume){
        BooksDbDao.updateBookEntryIsFavorite(bookVolume);
    }

    public void updateBookUserReview(BookVolume bookVolume){
        BooksDbDao.updateBookEntryReview(bookVolume);
    }

    public void deleteBook(BookVolume bookVolume){
        BooksDbDao.deleteBookEntry(bookVolume);
    }

    public void addBookshelf(Bookshelf bookshelf){
        BooksDbDao.createBookshelf(bookshelf);
    }

    public ArrayList<Bookshelf> readBookshelves(){
        return BooksDbDao.readAllBookshelves();
    }

    public void addBookshelfBookRelation(Bookshelf bookshelf, BookVolume bookVolume){
        BooksDbDao.createBookshelfBookRelationship(bookshelf, bookVolume);
    }

    public void removeBookshelfBookRelation(Bookshelf bookshelf, BookVolume bookVolume){
        BooksDbDao.deleteBookshelfBookRelationship(bookshelf, bookVolume);
    }

    public ArrayList<BookVolume> readBooksInBookshelf(Bookshelf bookshelf){
        return BooksDbDao.readBooksInBookshelf(bookshelf);
    }
}
