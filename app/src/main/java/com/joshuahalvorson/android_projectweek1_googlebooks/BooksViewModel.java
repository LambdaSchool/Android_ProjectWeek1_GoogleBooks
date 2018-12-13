package com.joshuahalvorson.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class BooksViewModel {
    private static BooksRepo repo = new BooksRepo();;
    public static void addBook(BookVolume bookVolume){
        repo.addBook(bookVolume);
    }

    public static ArrayList<BookVolume> readBooks(){
        return repo.readBooks();
    }

    public static void updateBookHasRead(BookVolume bookVolume){
        repo.updateBookHasRead(bookVolume);
    }

    public static void updateBookIsFavorite(BookVolume bookVolume){
        repo.updateBookIsFavorite(bookVolume);
    }

    public static void updateBookUserReview(BookVolume bookVolume){
        repo.updateBookUserReview(bookVolume);
    }

    public static void deleteBook(BookVolume bookVolume){
        repo.deleteBook(bookVolume);
    }

    public static void addBookshelf(Bookshelf bookshelf){
        repo.addBookshelf(bookshelf);
    }

    public static ArrayList<Bookshelf> readBookshelves(){
        return repo.readBookshelves();
    }

    public static void deleteBookshelf(Bookshelf bookshelf, ArrayList<BookVolume> bookVolumes){
        repo.deleteBookshelf(bookshelf, bookVolumes);
    }

    public static void addBookshelfBookRelation(Bookshelf bookshelf, BookVolume bookVolume){
        repo.addBookshelfBookRelation(bookshelf, bookVolume);
    }

    public static void removeBookshelfBookRelation(Bookshelf bookshelf, BookVolume bookVolume){
        repo.removeBookshelfBookRelation(bookshelf, bookVolume);
    }

    public static ArrayList<BookVolume> readBooksInBookshelf(Bookshelf bookshelf){
        return repo.readBooksInBookshelf(bookshelf);
    }
}
