package perpustakaan.Model;

import java.util.UUID;

/**
 * Kelas Book - mewakili sebuah buku dalam sistem perpustakaan.
 *
 * Kelas ini menyimpan informasi dasar tentang buku seperti ID, judul,
 * penulis, kategori, dan status ketersediaan buku.
 *
 * @author Developer Sistem Perpustakaan
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;

    /**
     * Konstruktor untuk membuat buku baru dengan ID yang digenerate secara otomatis.
     *
     * @param title Judul buku
     * @param author Penulis buku
     * @param category Kategori buku
     */
    public Book(String title, String author, String category) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true; // Buku baru selalu tersedia
    }

    /**
     * Konstruktor untuk membuat buku dengan ID yang sudah ditentukan.
     * Biasanya digunakan saat memuat data dari penyimpanan.
     *
     * @param id ID buku
     * @param title Judul buku
     * @param author Penulis buku
     * @param category Kategori buku
     * @param isAvailable Status ketersediaan buku
     */
    public Book(String id, String title, String author, String category, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    /**
     * Mendapatkan ID buku.
     *
     * @return ID buku
     */
    public String getId() {
        return id;
    }

    /**
     * Mendapatkan judul buku.
     *
     * @return Judul buku
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mengatur judul buku.
     *
     * @param title Judul buku yang baru
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Mendapatkan penulis buku.
     *
     * @return Penulis buku
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Mengatur penulis buku.
     *
     * @param author Penulis buku yang baru
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Mendapatkan kategori buku.
     *
     * @return Kategori buku
     */
    public String getCategory() {
        return category;
    }

    /**
     * Mengatur kategori buku.
     *
     * @param category Kategori buku yang baru
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Memeriksa apakah buku tersedia untuk dipinjam.
     *
     * @return true jika buku tersedia, false jika tidak
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Mengatur status ketersediaan buku.
     *
     * @param available Status ketersediaan buku yang baru
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Representasi string dari objek buku.
     *
     * @return String yang berisi informasi buku
     */
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}