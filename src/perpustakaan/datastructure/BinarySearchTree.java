package perpustakaan.datastructure;

import perpustakaan.Model.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas BinarySearchTree - implementasi struktur data Binary Search Tree untuk pencarian buku.
 *
 * Kelas ini memungkinkan pencarian buku berdasarkan judul atau kategori
 * dengan kompleksitas waktu O(log n) pada kasus rata-rata.
 *
 * @author Developer Sistem Perpustakaan
 */
public class BinarySearchTree {
    private TreeNode root; // Node akar dari BST
    private boolean searchByTitle; // Menentukan apakah pencarian berdasarkan judul atau kategori

    /**
     * Konstruktor untuk membuat BST baru.
     *
     * @param searchByTitle true jika pencarian berdasarkan judul, false jika berdasarkan kategori
     */
    public BinarySearchTree(boolean searchByTitle) {
        this.root = null;
        this.searchByTitle = searchByTitle;
    }

    /**
     * Menambahkan buku baru ke dalam BST.
     *
     * @param book Buku yang akan ditambahkan
     */
    public void insert(Book book) {
        String searchKey = getSearchKey(book);
        root = insertRec(root, book, searchKey);
    }

    /**
     * Metode rekursif untuk menambahkan buku ke dalam BST.
     *
     * @param root Node saat ini yang sedang diperiksa
     * @param book Buku yang akan ditambahkan
     * @param searchKey Kunci pencarian buku
     * @return Node yang telah diperbarui
     */
    private TreeNode insertRec(TreeNode root, Book book, String searchKey) {
        // Jika pohon kosong, buat node baru
        if (root == null) {
            return new TreeNode(book, searchKey);
        }

        // Lakukan penyisipan rekursif berdasarkan perbandingan kunci
        int compareResult = searchKey.compareTo(root.getSearchKey());

        if (compareResult < 0) {
            // Jika kunci lebih kecil, masukkan ke subpohon kiri
            root.setLeft(insertRec(root.getLeft(), book, searchKey));
        } else if (compareResult > 0) {
            // Jika kunci lebih besar, masukkan ke subpohon kanan
            root.setRight(insertRec(root.getRight(), book, searchKey));
        } else {
            // Kunci sudah ada (duplikat), kita biarkan saja (tidak diperbarui)
            // Dalam kasus nyata, kita mungkin ingin menangani duplikat dengan cara lain
        }

        return root;
    }

    /**
     * Mencari buku berdasarkan kunci pencarian (judul atau kategori).
     *
     * @param searchKey Kunci yang dicari
     * @return Daftar buku yang cocok dengan kunci pencarian
     */
    public List<Book> search(String searchKey) {
        List<Book> result = new ArrayList<>();
        searchRec(root, searchKey.toLowerCase(), result);
        return result;
    }

    /**
     * Metode rekursif untuk mencari buku dalam BST.
     *
     * @param root Node saat ini yang sedang diperiksa
     * @param searchKey Kunci yang dicari
     * @param result Daftar untuk menyimpan hasil pencarian
     */
    private void searchRec(TreeNode root, String searchKey, List<Book> result) {
        if (root == null) {
            return;
        }

        // Jika kunci saat ini mengandung kunci pencarian, tambahkan ke hasil
        if (root.getSearchKey().contains(searchKey)) {
            result.add(root.getData());
        }

        // Untuk pencarian lebih lengkap, kita juga perlu memeriksa kedua subpohon
        // karena kita menggunakan contains bukan exact match
        searchRec(root.getLeft(), searchKey, result);
        searchRec(root.getRight(), searchKey, result);
    }

    /**
     * Mengembalikan semua buku dalam BST dalam urutan inorder.
     *
     * @return Daftar semua buku
     */
    public List<Book> inorderTraversal() {
        List<Book> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    /**
     * Metode rekursif untuk traversal inorder.
     *
     * @param root Node saat ini yang sedang diperiksa
     * @param result Daftar untuk menyimpan hasil traversal
     */
    private void inorderRec(TreeNode root, List<Book> result) {
        if (root != null) {
            inorderRec(root.getLeft(), result);
            result.add(root.getData());
            inorderRec(root.getRight(), result);
        }
    }

    /**
     * Memperbarui BST setelah koleksi buku berubah.
     *
     * @param books Daftar buku terbaru
     */
    public void rebuild(List<Book> books) {
        this.root = null; // Reset pohon
        for (Book book : books) {
            insert(book); // Masukkan semua buku ke pohon baru
        }
    }

    /**
     * Mendapatkan kunci pencarian dari buku berdasarkan mode pencarian.
     *
     * @param book Buku yang akan diambil kuncinya
     * @return Kunci pencarian (judul atau kategori)
     */
    private String getSearchKey(Book book) {
        return searchByTitle ? book.getTitle().toLowerCase() : book.getCategory().toLowerCase();
    }
}