package perpustakaan.Model;

import java.util.Date;

/**
 * Kelas BorrowRequest - model untuk permintaan peminjaman buku.
 *
 * Kelas ini merepresentasikan permintaan peminjaman buku oleh pengguna,
 * termasuk informasi tentang pengguna, buku, waktu peminjaman dan pengembalian.
 */
public class BorrowRequest {
    private User user;
    private Book book;
    private Date borrowTime;
    private Date returnTime;

    /**
     * Konstruktor untuk BorrowRequest.
     *
     * @param user Pengguna yang meminjam
     * @param book Buku yang dipinjam
     * @param borrowTime Waktu peminjaman
     */
    public BorrowRequest(User user, Book book, Date borrowTime) {
        this.user = user;
        this.book = book;
        this.borrowTime = borrowTime;
        this.returnTime = null; // Diatur saat buku dikembalikan
    }

    /**
     * Mendapatkan pengguna yang meminjam.
     *
     * @return Pengguna
     */
    public User getUser() {
        return user;
    }

    /**
     * Mendapatkan buku yang dipinjam.
     *
     * @return Buku
     */
    public Book getBook() {
        return book;
    }

    /**
     * Mendapatkan waktu peminjaman.
     *
     * @return Waktu peminjaman
     */
    public Date getBorrowTime() {
        return borrowTime;
    }

    /**
     * Mendapatkan waktu pengembalian.
     *
     * @return Waktu pengembalian, atau null jika belum dikembalikan
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * Mengatur waktu pengembalian.
     *
     * @param returnTime Waktu pengembalian
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * Memeriksa apakah buku sudah dikembalikan.
     *
     * @return true jika sudah dikembalikan, false jika belum
     */
    public boolean isReturned() {
        return returnTime != null;
    }

    @Override
    public String toString() {
        return "BorrowRequest [user=" + user.getName() + ", book=" + book.getTitle() +
                ", borrowTime=" + borrowTime + ", returnTime=" + returnTime + "]";
    }
}