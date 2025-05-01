package perpustakaan.Model;

/**
 * Kelas Book - model untuk buku dalam perpustakaan.
 *
 * Kelas ini merepresentasikan buku dalam sistem perpustakaan
 * dengan properti seperti id, judul, kategori, dll.
 */
public class Book {
    private int id;
    private String title;
    private String category;

    /**
     * Konstruktor untuk Book.
     *
     * @param id ID buku
     * @param title Judul buku
     * @param category Kategori buku
     */
    public Book(int id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
    }

    /**
     * Mendapatkan ID buku.
     *
     * @return ID buku
     */
    public int getId() {
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
     * @param title Judul baru
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @param category Kategori baru
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", category=" + category + "]";
    }
}