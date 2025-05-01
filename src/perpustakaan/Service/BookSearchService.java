package perpustakaan.Service;

import perpustakaan.datastructure.BinarySearchTree;
import perpustakaan.Model.Book;
import java.util.List;

/**
 * Kelas BookSearchService - menyediakan layanan pencarian buku menggunakan BST.
 *
 * Kelas ini mengelola dua BST terpisah: satu untuk pencarian berdasarkan judul
 * dan yang lainnya untuk pencarian berdasarkan kategori.
 *
 * @author Developer Sistem Perpustakaan
 */
public class BookSearchService {
    private BinarySearchTree titleSearchTree; // BST untuk pencarian berdasarkan judul
    private BinarySearchTree categorySearchTree; // BST untuk pencarian berdasarkan kategori

    /**
     * Konstruktor untuk membuat layanan pencarian buku baru.
     */
    public BookSearchService() {
        this.titleSearchTree = new BinarySearchTree(true); // Pencarian berdasarkan judul
        this.categorySearchTree = new BinarySearchTree(false); // Pencarian berdasarkan kategori
    }

    /**
     * Memperbarui kedua BST dengan koleksi buku terbaru.
     *
     * @param books Daftar buku terbaru
     */
    public void updateSearchTrees(List<Book> books) {
        // Membangun ulang kedua BST
        titleSearchTree.rebuild(books);
        categorySearchTree.rebuild(books);
    }

    /**
     * Menambahkan satu buku ke kedua BST.
     *
     * @param book Buku yang akan ditambahkan
     */
    public void addBookToSearchTrees(Book book) {
        titleSearchTree.insert(book);
        categorySearchTree.insert(book);
    }

    /**
     * Mencari buku berdasarkan judul.
     *
     * @param title Judul buku yang dicari
     * @return Daftar buku yang judulnya cocok dengan kriteria pencarian
     */
    public List<Book> searchByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return titleSearchTree.inorderTraversal(); // Jika tidak ada kriteria, tampilkan semua
        }
        return titleSearchTree.search(title);
    }

    /**
     * Mencari buku berdasarkan kategori.
     *
     * @param category Kategori buku yang dicari
     * @return Daftar buku yang kategorinya cocok dengan kriteria pencarian
     */
    public List<Book> searchByCategory(String category) {
        if (category == null || category.isEmpty()) {
            return categorySearchTree.inorderTraversal(); // Jika tidak ada kriteria, tampilkan semua
        }
        return categorySearchTree.search(category);
    }
}