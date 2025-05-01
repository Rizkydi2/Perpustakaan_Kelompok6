package perpustakaan.datastructure;

import perpustakaan.Model.Book;

/**
 * Kelas TreeNode - merepresentasikan sebuah node dalam struktur data Binary Search Tree.
 *
 * Kelas ini digunakan untuk menyimpan data buku dan referensi ke node kiri dan kanan
 * dalam struktur pohon. Memudahkan penerapan operasi pencarian biner untuk buku.
 *
 * @author Developer Sistem Perpustakaan
 */
public class TreeNode {
    private Book data; // Buku yang disimpan dalam node
    private TreeNode left; // Node anak di sebelah kiri (nilai lebih kecil)
    private TreeNode right; // Node anak di sebelah kanan (nilai lebih besar)
    private String searchKey; // Kunci pencarian (judul atau kategori buku)

    /**
     * Konstruktor untuk membuat node baru dengan buku dan kunci pencarian tertentu.
     *
     * @param book Buku yang akan disimpan di node
     * @param searchKey Kunci pencarian (judul atau kategori)
     */
    public TreeNode(Book book, String searchKey) {
        this.data = book;
        this.searchKey = searchKey.toLowerCase(); // Menyimpan kunci dalam huruf kecil untuk pencarian yang tidak peka huruf besar/kecil
        this.left = null;
        this.right = null;
    }

    /**
     * Mengambil data buku yang disimpan dalam node.
     *
     * @return Data buku
     */
    public Book getData() {
        return data;
    }

    /**
     * Mengambil node anak di sebelah kiri.
     *
     * @return Node anak kiri
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Mengatur node anak di sebelah kiri.
     *
     * @param left Node anak kiri baru
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Mengambil node anak di sebelah kanan.
     *
     * @return Node anak kanan
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Mengatur node anak di sebelah kanan.
     *
     * @param right Node anak kanan baru
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Mengambil kunci pencarian yang digunakan untuk node ini.
     *
     * @return Kunci pencarian
     */
    public String getSearchKey() {
        return searchKey;
    }
}