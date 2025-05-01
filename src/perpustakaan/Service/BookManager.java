package perpustakaan.Service;

import perpustakaan.Model.Book;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Kelas BookManager - mengelola koleksi buku dalam sistem perpustakaan.
 *
 * Kelas ini mengimplementasikan operasi CRUD (Create, Read, Update, Delete)
 * untuk mengelola buku menggunakan struktur data LinkedList.
 *
 * @author Developer Sistem Perpustakaan
 * @version 1.0
 */
public class BookManager {
    // Menggunakan LinkedList sebagai struktur data untuk menyimpan buku
    private LinkedList<Book> books;

    /**
     * Konstruktor untuk membuat objek BookManager baru.
     * Inisialisasi LinkedList kosong untuk menyimpan buku.
     */
    public BookManager() {
        this.books = new LinkedList<>();
    }

    /**
     * Menambahkan buku baru ke dalam koleksi.
     *
     * @param title Judul buku
     * @param author Nama penulis
     * @param category Kategori buku
     * @return Objek buku yang baru ditambahkan
     */
    public Book addBook(String title, String author, String category) {
        // Membuat ID unik untuk buku baru
        String id = generateUniqueId();

        // Membuat objek buku baru
        Book newBook = new Book(id, title, author, category);

        // Menambahkan buku ke dalam LinkedList
        books.add(newBook);

        return newBook;
    }

    /**
     * Mencari buku berdasarkan ID.
     *
     * @param id ID buku yang dicari
     * @return Objek buku jika ditemukan, null jika tidak ditemukan
     */
    public Book findBookById(String id) {
        // Mencari buku dalam LinkedList berdasarkan ID
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }

        // Mengembalikan null jika buku tidak ditemukan
        return null;
    }

    /**
     * Menampilkan semua buku dalam koleksi.
     *
     * @return LinkedList berisi semua buku
     */
    public LinkedList<Book> getAllBooks() {
        return books;
    }

    /**
     * Memperbarui informasi buku yang sudah ada.
     *
     * @param id ID buku yang akan diperbarui
     * @param title Judul buku baru (null jika tidak diubah)
     * @param author Penulis baru (null jika tidak diubah)
     * @param category Kategori baru (null jika tidak diubah)
     * @return true jika berhasil diperbarui, false jika buku tidak ditemukan
     */
    public boolean updateBook(String id, String title, String author, String category) {
        // Mencari buku yang akan diperbarui
        Book bookToUpdate = findBookById(id);

        // Jika buku tidak ditemukan, kembalikan false
        if (bookToUpdate == null) {
            return false;
        }

        // Perbarui atribut yang diberikan
        if (title != null) {
            bookToUpdate.setTitle(title);
        }

        if (author != null) {
            bookToUpdate.setAuthor(author);
        }

        if (category != null) {
            bookToUpdate.setCategory(category);
        }

        return true;
    }

    /**
     * Menghapus buku dari koleksi.
     *
     * @param id ID buku yang akan dihapus
     * @return true jika berhasil dihapus, false jika buku tidak ditemukan
     */
    public boolean deleteBook(String id) {
        // Mencari buku yang akan dihapus
        Book bookToRemove = findBookById(id);

        // Jika buku tidak ditemukan, kembalikan false
        if (bookToRemove == null) {
            return false;
        }

        // Hapus buku dari LinkedList
        return books.remove(bookToRemove);
    }

    /**
     * Mencari buku berdasarkan judul.
     *
     * @param title Judul buku yang dicari
     * @return LinkedList berisi buku dengan judul yang sesuai
     */
    public LinkedList<Book> findBooksByTitle(String title) {
        LinkedList<Book> result = new LinkedList<>();

        // Mencari buku dengan judul yang sesuai
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Mencari buku berdasarkan kategori.
     *
     * @param category Kategori buku yang dicari
     * @return LinkedList berisi buku dengan kategori yang sesuai
     */
    public LinkedList<Book> findBooksByCategory(String category) {
        LinkedList<Book> result = new LinkedList<>();

        // Mencari buku dengan kategori yang sesuai
        for (Book book : books) {
            if (book.getCategory().toLowerCase().equals(category.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Membuat ID unik untuk buku baru.
     *
     * @return ID unik dalam format String
     */
    private String generateUniqueId() {
        // Menggunakan UUID untuk menghasilkan ID unik
        return "BK-" + UUID.randomUUID().toString().substring(0, 8);
    }
}