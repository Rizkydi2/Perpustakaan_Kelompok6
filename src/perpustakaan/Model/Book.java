package perpustakaan.Model;

/**
 * Kelas Book - mewakili sebuah buku dalam sistem perpustakaan.
 *
 * Kelas ini menyimpan informasi dasar tentang buku seperti ID, judul,
 * penulis, kategori, dan status ketersediaan buku.
 *
 * @author Developer Sistem Perpustakaan
 * @version 1.0
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;

    /**
     * Konstruktor default untuk membuat objek buku baru.
     */
    public Book() {
        this.isAvailable = true;
    }

    /**
     * Konstruktor lengkap untuk membuat objek buku dengan semua atribut.
     *
     * @param id ID unik untuk buku
     * @param title Judul buku
     * @param author Nama penulis buku
     * @param category Kategori atau genre buku
     */
    public Book(String id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
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
     * Mengatur ID buku.
     *
     * @param id ID buku yang baru
     */
    public void setId(String id) {
        this.id = id;
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
     * Mendapatkan nama penulis buku.
     *
     * @return Nama penulis
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Mengatur nama penulis buku.
     *
     * @param author Nama penulis yang baru
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
     * Mengubah objek buku menjadi representasi String.
     *
     * @return Representasi String dari buku
     */
    @Override
    public String toString() {
        return "Buku [ID=" + id + ", Judul=" + title + ", Penulis=" + author +
                ", Kategori=" + category + ", Tersedia=" + (isAvailable ? "Ya" : "Tidak") + "]";
    }
}