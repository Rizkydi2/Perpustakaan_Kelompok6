package perpustakaan.Service;

import perpustakaan.Model.Book;
import java.util.LinkedList;

/**
 * Kelas BookManager - mengelola koleksi buku dalam sistem perpustakaan.
 *
 * Kelas ini mengimplementasikan operasi CRUD (Create, Read, Update, Delete)
 * untuk buku dalam koleksi perpustakaan.
 *
 * @author Developer Sistem Perpustakaan
 */
public class BookManager {
    private LinkedList<Book> books;

    /**
     * Konstruktor untuk membuat objek BookManager baru.
     */
    public BookManager() {
        this.books = new LinkedList<>();
    }

    /**
     * Menambahkan buku baru ke dalam koleksi.
     *
     * @param book Buku yang akan ditambahkan
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Mendapatkan semua buku dalam koleksi.
     *
     * @return LinkedList berisi semua buku
     */
    public LinkedList<Book> getAllBooks() {
        return books;
    }

    /**
     * Mencari buku berdasarkan ID.
     *
     * @param id ID buku yang dicari
     * @return Objek buku jika ditemukan, null jika tidak ditemukan
     */
    public Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Mencari buku berdasarkan judul.
     * Metode ini akan mencocokkan sebagian judul (case-insensitive).
     *
     * @param title Judul buku yang dicari
     * @return LinkedList berisi buku-buku yang judulnya cocok
     */
    public LinkedList<Book> findBooksByTitle(String title) {
        LinkedList<Book> result = new LinkedList<>();
        String titleLower = title.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(titleLower)) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Mencari buku berdasarkan kategori.
     * Metode ini akan mencocokkan kategori secara tepat (case-insensitive).
     *
     * @param category Kategori buku yang dicari
     * @return LinkedList berisi buku-buku yang kategorinya cocok
     */
    public LinkedList<Book> findBooksByCategory(String category) {
        LinkedList<Book> result = new LinkedList<>();
        String categoryLower = category.toLowerCase();

        for (Book book : books) {
            if (book.getCategory().toLowerCase().contains(categoryLower)) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Memperbarui informasi buku dalam koleksi.
     *
     * @param updatedBook Buku yang sudah diperbarui informasinya
     * @return true jika berhasil diperbarui, false jika buku tidak ditemukan
     */
    public boolean updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                return true;
            }
        }
        return false;
    }

    /**
     * Menghapus buku dari koleksi berdasarkan ID.
     *
     * @param id ID buku yang akan dihapus
     * @return true jika berhasil dihapus, false jika buku tidak ditemukan
     */
    public boolean deleteBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId().equals(id)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}