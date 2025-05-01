package perpustakaan.Service;

import perpustakaan.Model.Book;
import perpustakaan.Model.BorrowRequest;
import perpustakaan.Model.User;
import perpustakaan.datastructure.UserBinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas UserService - layanan untuk mengelola data pengguna perpustakaan.
 *
 * Kelas ini menyediakan antarmuka untuk operasi CRUD pada data pengguna dan mengelola
 * hubungan antara pengguna dan buku yang dipinjam.
 */
public class UserService {
    private UserBinaryTree userTree;
    private int nextUserId;

    /**
     * Konstruktor untuk UserService.
     */
    public UserService() {
        userTree = new UserBinaryTree();
        nextUserId = 1; // Mulai dari ID 1
    }

    /**
     * Mendaftarkan pengguna baru.
     *
     * @param name Nama pengguna
     * @param email Email pengguna
     * @return User yang baru dibuat
     */
    public User registerUser(String name, String email) {
        User newUser = new User(nextUserId, name, email);
        userTree.addUser(newUser);
        nextUserId++;
        return newUser;
    }

    /**
     * Mendapatkan pengguna berdasarkan ID.
     *
     * @param id ID pengguna
     * @return User yang ditemukan atau null jika tidak ada
     */
    public User getUserById(int id) {
        return userTree.findUser(id);
    }

    /**
     * Memperbarui data pengguna.
     *
     * @param id ID pengguna
     * @param name Nama baru (null jika tidak diubah)
     * @param email Email baru (null jika tidak diubah)
     * @return true jika berhasil diperbarui, false jika gagal
     */
    public boolean updateUser(int id, String name, String email) {
        User user = getUserById(id);
        if (user == null) {
            return false;
        }

        if (name != null) {
            user.setName(name);
        }

        if (email != null) {
            user.setEmail(email);
        }

        return userTree.updateUser(user);
    }

    /**
     * Menghapus pengguna.
     *
     * @param id ID pengguna
     * @return true jika berhasil dihapus, false jika gagal
     */
    public boolean removeUser(int id) {
        // Periksa apakah pengguna masih memiliki buku yang dipinjam
        User user = getUserById(id);
        if (user != null && !user.getBorrowedBooks().isEmpty()) {
            return false; // Tidak bisa menghapus pengguna yang masih memiliki buku
        }

        return userTree.removeUser(id);
    }

    /**
     * Mendapatkan semua pengguna.
     *
     * @return Daftar semua pengguna
     */
    public List<User> getAllUsers() {
        return userTree.getAllUsers();
    }

    /**
     * Mencatat buku yang dipinjam oleh pengguna.
     *
     * @param userId ID pengguna
     * @param borrowRequest Data peminjaman
     * @return true jika berhasil, false jika gagal
     */
    public boolean addBorrowedBook(int userId, BorrowRequest borrowRequest) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }

        user.addBorrowedBook(borrowRequest);
        return userTree.updateUser(user);
    }

    /**
     * Menghapus catatan buku yang dipinjam saat pengembalian.
     *
     * @param userId ID pengguna
     * @param bookId ID buku
     * @return BorrowRequest yang dihapus, atau null jika gagal
     */
    public BorrowRequest removeBorrowedBook(int userId, int bookId) {
        User user = getUserById(userId);
        if (user == null) {
            return null;
        }

        BorrowRequest request = user.removeBorrowedBook(bookId);
        if (request != null) {
            userTree.updateUser(user);
        }

        return request;
    }

    /**
     * Mendapatkan daftar buku yang sedang dipinjam oleh pengguna.
     *
     * @param userId ID pengguna
     * @return Daftar peminjaman buku oleh pengguna
     */
    public List<BorrowRequest> getUserBorrowedBooks(int userId) {
        User user = getUserById(userId);
        if (user == null) {
            return new ArrayList<>();
        }

        return user.getBorrowedBooks();
    }
}