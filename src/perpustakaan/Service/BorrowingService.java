package perpustakaan.Service;

import perpustakaan.Model.Book;
import perpustakaan.Model.BorrowRequest;
import perpustakaan.Model.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Kelas BorrowingService - layanan untuk mengelola peminjaman buku.
 *
 * Kelas ini menyediakan antarmuka untuk peminjaman, pengembalian, dan
 * antrian peminjaman buku.
 */
public class BorrowingService {
    private BookService bookService;
    private UserService userService;
    private ReturnHistoryService returnHistoryService;
    private LinkedList<BorrowRequest> borrowQueue;
    private Map<Integer, Boolean> borrowedBooks; // bookId -> borrowed status

    /**
     * Konstruktor untuk BorrowingService.
     *
     * @param bookService Layanan buku
     * @param userService Layanan pengguna
     * @param returnHistoryService Layanan riwayat pengembalian
     */
    public BorrowingService(BookService bookService, UserService userService,
                            ReturnHistoryService returnHistoryService) {
        this.bookService = bookService;
        this.userService = userService;
        this.returnHistoryService = returnHistoryService;
        this.borrowQueue = new LinkedList<>();
        this.borrowedBooks = new HashMap<>();
    }

    /**
     * Meminjam buku.
     *
     * @param userId ID pengguna
     * @param bookId ID buku
     * @return BorrowRequest jika berhasil, null jika masuk antrian
     */
    public BorrowRequest borrowBook(int userId, int bookId) {
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (user == null || book == null) {
            return null;
        }

        // Periksa apakah buku sudah dipinjam
        if (isBookBorrowed(bookId)) {
            // Masukkan ke antrian
            BorrowRequest queueRequest = new BorrowRequest(user, book, new Date());
            borrowQueue.add(queueRequest);
            return null;
        }

        // Pinjam buku
        BorrowRequest request = new BorrowRequest(user, book, new Date());
        borrowedBooks.put(bookId, true);
        userService.addBorrowedBook(userId, request);

        return request;
    }

    /**
     * Mengembalikan buku.
     *
     * @param userId ID pengguna
     * @param bookId ID buku
     * @return true jika berhasil, false jika gagal
     */
    public boolean returnBook(int userId, int bookId) {
        // Periksa apakah buku sedang dipinjam oleh pengguna
        if (!isBookBorrowedByUser(userId, bookId)) {
            return false;
        }

        // Set waktu pengembalian
        BorrowRequest request = userService.removeBorrowedBook(userId, bookId);
        request.setReturnTime(new Date());

        // Tambahkan ke riwayat pengembalian
        returnHistoryService.addReturnToHistory(request);

        // Periksa antrian peminjaman
        processQueue(bookId);

        // Jika tidak ada yang mengantri, tandai buku sebagai tersedia
        if (!isBookBorrowed(bookId)) {
            borrowedBooks.put(bookId, false);
        }

        return true;
    }

    /**
     * Memproses antrian peminjaman untuk buku yang baru dikembalikan.
     *
     * @param bookId ID buku
     */
    private void processQueue(int bookId) {
        for (int i = 0; i < borrowQueue.size(); i++) {
            BorrowRequest request = borrowQueue.get(i);
            if (request.getBook().getId() == bookId) {
                // Hapus dari antrian
                borrowQueue.remove(i);

                // Pinjamkan ke pengguna berikutnya
                User nextUser = request.getUser();
                Book book = request.getBook();

                BorrowRequest newRequest = new BorrowRequest(nextUser, book, new Date());
                borrowedBooks.put(bookId, true);
                userService.addBorrowedBook(nextUser.getId(), newRequest);

                break;
            }
        }
    }

    /**
     * Memeriksa apakah buku sedang dipinjam.
     *
     * @param bookId ID buku
     * @return true jika sedang dipinjam, false jika tidak
     */
    public boolean isBookBorrowed(int bookId) {
        Boolean status = borrowedBooks.get(bookId);
        return status != null && status;
    }

    /**
     * Memeriksa apakah buku sedang dipinjam oleh pengguna tertentu.
     *
     * @param userId ID pengguna
     * @param bookId ID buku
     * @return true jika buku dipinjam oleh pengguna, false jika tidak
     */
    public boolean isBookBorrowedByUser(int userId, int bookId) {
        User user = userService.getUserById(userId);
        return user != null && user.isBookBorrowed(bookId);
    }

    /**
     * Mendapatkan daftar antrian peminjaman.
     *
     * @return Daftar permintaan peminjaman dalam antrian
     */
    public List<BorrowRequest> getBorrowQueue() {
        return new ArrayList<>(borrowQueue);
    }
}