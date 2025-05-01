package perpustakaan.datastructure;

import perpustakaan.Model.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas BookBinarySearchTree - implementasi Binary Search Tree untuk penyimpanan data buku.
 *
 * Struktur data ini digunakan untuk menyimpan data buku perpustakaan berdasarkan
 * ID buku untuk pencarian yang efisien. Implementasi ini mendukung pencarian
 * buku berdasarkan ID dengan kompleksitas waktu O(log n) dalam kasus terbaik.
 */
public class BookBinarySearchTree {
    private Node root;

    private class Node {
        Book book;
        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Menambahkan buku ke dalam binary search tree.
     *
     * @param book Buku yang akan ditambahkan
     */
    public void addBook(Book book) {
        if (book == null) {
            return;
        }

        root = addBookRecursive(root, book);
    }

    private Node addBookRecursive(Node current, Book book) {
        if (current == null) {
            return new Node(book);
        }

        // Bandingkan berdasarkan ID buku
        if (book.getId() < current.book.getId()) {
            current.left = addBookRecursive(current.left, book);
        } else if (book.getId() > current.book.getId()) {
            current.right = addBookRecursive(current.right, book);
        } else {
            // Jika ID sudah ada, perbarui data buku
            current.book = book;
        }

        return current;
    }

    /**
     * Mencari buku berdasarkan ID.
     *
     * @param id ID buku yang dicari
     * @return Book yang ditemukan, atau null jika tidak ditemukan
     */
    public Book findBook(int id) {
        Node result = findBookRecursive(root, id);
        return result == null ? null : result.book;
    }

    private Node findBookRecursive(Node current, int id) {
        if (current == null) {
            return null;
        }

        if (id == current.book.getId()) {
            return current;
        }

        if (id < current.book.getId()) {
            return findBookRecursive(current.left, id);
        } else {
            return findBookRecursive(current.right, id);
        }
    }

    /**
     * Menghapus buku berdasarkan ID.
     *
     * @param id ID buku yang akan dihapus
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeBook(int id) {
        if (findBook(id) == null) {
            return false;
        }

        root = removeBookRecursive(root, id);
        return true;
    }

    private Node removeBookRecursive(Node current, int id) {
        if (current == null) {
            return null;
        }

        if (id == current.book.getId()) {
            // Node tanpa anak
            if (current.left == null && current.right == null) {
                return null;
            }

            // Node dengan satu anak
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            // Node dengan dua anak
            Book smallestValue = findSmallestValue(current.right);
            current.book = smallestValue;
            current.right = removeBookRecursive(current.right, smallestValue.getId());
            return current;
        }

        if (id < current.book.getId()) {
            current.left = removeBookRecursive(current.left, id);
        } else {
            current.right = removeBookRecursive(current.right, id);
        }

        return current;
    }

    private Book findSmallestValue(Node root) {
        return root.left == null ? root.book : findSmallestValue(root.left);
    }

    /**
     * Mendapatkan semua buku dalam bentuk daftar.
     * Menggunakan traversal inorder untuk mendapatkan buku dalam urutan ID.
     *
     * @return List dari semua buku
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        inOrderTraversal(root, books);
        return books;
    }

    private void inOrderTraversal(Node node, List<Book> books) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, books);
        books.add(node.book);
        inOrderTraversal(node.right, books);
    }

    /**
     * Memperbarui data buku.
     *
     * @param book Buku dengan data terbaru
     * @return true jika berhasil diperbarui, false jika tidak ditemukan
     */
    public boolean updateBook(Book book) {
        if (findBook(book.getId()) == null) {
            return false;
        }

        // Hapus buku lama dan tambahkan yang baru
        removeBook(book.getId());
        addBook(book);
        return true;
    }
}