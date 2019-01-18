package com.example.tyzzer.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class BookRepo {
    public class BooksRepo {
        private ArrayList<Book> booksList;

        public void addBook(final Book book) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BookDbDao.addBook(book);
                }
            }).start();
        }

        public ArrayList<Book> listBooks() {
            booksList = BookDbDao.listBooks();
            return booksList;
        }

        public void updateBookRead(Book book) {
            BookDbDao.updateBookRead(book);
        }

        public void updateBookReview(Book book) {
            BookDbDao.updateBookReview(book);
        }

        public void deleteBook(Book book) {
            BookDbDao.deleteBook(book);
        }

        public void addBookshelf(Bookshelf bookshelf) {
            BookDbDao.addBookshelf(bookshelf);
        }

        public ArrayList<Bookshelf> listBookshelves() {
            return BookDbDao.listBookshelves();
        }

        public void deleteBookshelf(Bookshelf bookshelf) {
            BookDbDao.deleteBookshelf(bookshelf);
        }

        public void addBookInShelf(Bookshelf bookshelf, Book book) {
            BookDbDao.addBookInShelf(bookshelf, book);
        }

        public void deleteBookInShelf(Bookshelf bookshelf, Book book) {
            BookDbDao.deleteBookInShelf(bookshelf, book);
        }

        public ArrayList<Book> listBookInShelf(Bookshelf bookshelf) {
            return BookDbDao.listBookInShelf(bookshelf);
        }

    }
}
