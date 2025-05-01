package perpustakaan.Service;

import perpustakaan.Model.Book;
import perpustakaan.datastructure.BookBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas BookService - layanan untuk mengelola data buku perpustakaan.
 *
 * Kelas ini menyediakan antarmuka untuk operasi CRUD pada data buku dan pencarian
 * berdasarkan judul atau kategori menggunakan Binary Search Tree.
 */
public class BookService {
    private BookBinarySearchTree bookTree;
    private int nextBookId;

    /**
     * Konstruktor untuk BookService.
     */
    public BookService() {
        bookTree = new BookBinarySearchTree();
        nextBookId = 1; // Mulai dari ID 1
    }

    /**
     * Menambahkan buku baru ke perpustakaan.
     *
     * @param title Judul buku
     * @param category Kategori buku
     * @return Book yang baru ditambahkan
     */
    public Book addBook(String title, String category) {
        Book newBook = new Book(nextBookId, title, category);
        bookTree.addBook(newBook);
        nextBookId++;
        return newBook;
    }

    /**
     * Mendapatkan buku berdasarkan ID.
     *
     * @param id ID buku
     * @return Book yang ditemukan atau null jika tidak ada
     */
    public Book getBookById(int id) {
        return bookTree.findBook(id);
    }

    /**
     * Memperbarui data buku.
     *
     * @param id ID buku
     * @param title Judul baru (null jika tidak diubah)
     * @param category Kategori baru (null jika tidak diubah)
     * @return true jika berhasil diperbarui, false jika gagal
     */
    public boolean updateBook(int id, String title, String category) {
        Book book = getBookById(id);
        if (book == null) {
            return false;
        }

        if (title != null) {
            book.setTitle(title);
        }

        if (category != null) {
            book.setCategory(category);
        }

        return bookTree.updateBook(book);
    }

    /**
     * Menghapus buku.
     *
     * @param id ID buku
     * @return true jika berhasil dihapus, false jika gagal
     */
    public boolean removeBook(int id) {
        return bookTree.removeBook(id);
    }

    /**
     * Mendapatkan semua buku.
     *
     * @return Daftar semua buku
     */
    public List<Book> getAllBooks() {
        return bookTree.getAllBooks();
    }

    /**
     * Mencari buku berdasarkan judul.
     * Menggunakan pendekatan case-insensitive dan pencarian sebagian.
     *
     * @param title Judul yang dicari
     * @return Daftar buku yang judulnya mengandung kata kunci
     */
    public List<Book> searchBooksByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return new ArrayList<>();
        }

        List<Book> allBooks = getAllBooks();
        List<Book> result = new ArrayList<>();
        String lowercaseTitle = title.toLowerCase();

        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(lowercaseTitle)) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Mencari buku berdasarkan kategori.
     * Menggunakan pendekatan case-insensitive dan pencarian sebagian.
     *
     * @param category Kategori yang dicari
     * @return Daftar buku yang kategorinya mengandung kata kunci
     */
    public List<Book> searchBooksByCategory(String category) {
        if (category == null || category.isEmpty()) {
            return new ArrayList<>();
        }

        List<Book> allBooks = getAllBooks();
        List<Book> result = new ArrayList<>();
        String lowercaseCategory = category.toLowerCase();

        for (Book book : allBooks) {
            if (book.getCategory().toLowerCase().contains(lowercaseCategory)) {
                result.add(book);
            }
        }

        return result;
    }
}