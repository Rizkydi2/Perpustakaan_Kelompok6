package perpustakaan.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas User - model untuk pengguna perpustakaan.
 *
 * Kelas ini merepresentasikan pengguna perpustakaan dengan data pribadi
 * dan daftar buku yang sedang dipinjam.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private List<BorrowRequest> borrowedBooks;

    /**
     * Konstruktor untuk User.
     *
     * @param id ID pengguna
     * @param name Nama pengguna
     * @param email Email pengguna
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Mendapatkan ID pengguna.
     *
     * @return ID pengguna
     */
    public int getId() {
        return id;
    }

    /**
     * Mendapatkan nama pengguna.
     *
     * @return Nama pengguna
     */
    public String getName() {
        return name;
    }

    /**
     * Mengatur nama pengguna.
     *
     * @param name Nama pengguna baru
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mendapatkan email pengguna.
     *
     * @return Email pengguna
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mengatur email pengguna.
     *
     * @param email Email pengguna baru
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mendapatkan daftar buku yang sedang dipinjam.
     *
     * @return Daftar peminjaman buku
     */
    public List<BorrowRequest> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Menambahkan buku ke daftar pinjaman.
     *
     * @param borrowRequest Data peminjaman
     */
    public void addBorrowedBook(BorrowRequest borrowRequest) {
        borrowedBooks.add(borrowRequest);
    }

    /**
     * Menghapus buku dari daftar pinjaman.
     *
     * @param bookId ID buku yang dikembalikan
     * @return BorrowRequest yang dihapus, atau null jika tidak ditemukan
     */
    public BorrowRequest removeBorrowedBook(int bookId) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i).getBook().getId() == bookId) {
                return borrowedBooks.remove(i);
            }
        }
        return null;
    }

    /**
     * Memeriksa apakah pengguna sedang meminjam buku tertentu.
     *
     * @param bookId ID buku
     * @return true jika sedang meminjam, false jika tidak
     */
    public boolean isBookBorrowed(int bookId) {
        for (BorrowRequest request : borrowedBooks) {
            if (request.getBook().getId() == bookId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mendapatkan jumlah buku yang sedang dipinjam.
     *
     * @return Jumlah buku yang dipinjam
     */
    public int getBorrowedBooksCount() {
        return borrowedBooks.size();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email +
                ", borrowedBooks=" + getBorrowedBooksCount() + "]";
    }
}