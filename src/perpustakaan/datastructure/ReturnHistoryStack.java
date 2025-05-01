package perpustakaan.datastructure;

import perpustakaan.Model.BorrowRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.EmptyStackException;

/**
 * Kelas ReturnHistoryStack - implementasi Stack untuk menyimpan riwayat pengembalian buku.
 *
 * Struktur data ini menggunakan prinsip LIFO (Last-In-First-Out) untuk menyimpan
 * riwayat pengembalian buku, sehingga pengembalian terbaru berada di atas stack.
 */
public class ReturnHistoryStack {
    private List<BorrowRequest> stack;
    private int maxSize;

    /**
     * Konstruktor untuk ReturnHistoryStack.
     *
     * @param maxSize Ukuran maksimal stack (default 10)
     */
    public ReturnHistoryStack(int maxSize) {
        this.stack = new ArrayList<>();
        this.maxSize = maxSize;
    }

    /**
     * Konstruktor default dengan ukuran maksimal 10.
     */
    public ReturnHistoryStack() {
        this(10); // Default max size 10
    }

    /**
     * Menambahkan pengembalian buku ke stack.
     *
     * @param request Data peminjaman dan pengembalian
     */
    public void push(BorrowRequest request) {
        // Jika stack sudah penuh, hapus entri terlama
        if (stack.size() >= maxSize) {
            // Hapus item terlama (paling bawah)
            stack.remove(stack.size() - 1);
        }

        // Tambahkan entri baru ke atas stack (index 0)
        stack.add(0, request);
    }

    /**
     * Mengambil dan menghapus pengembalian terbaru dari stack.
     *
     * @return BorrowRequest terbaru
     * @throws EmptyStackException jika stack kosong
     */
    public BorrowRequest pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.remove(0);
    }

    /**
     * Melihat pengembalian terbaru tanpa menghapusnya.
     *
     * @return BorrowRequest terbaru
     * @throws EmptyStackException jika stack kosong
     */
    public BorrowRequest peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.get(0);
    }

    /**
     * Memeriksa apakah stack kosong.
     *
     * @return true jika kosong, false jika tidak
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Mendapatkan ukuran stack.
     *
     * @return Jumlah entri dalam stack
     */
    public int size() {
        return stack.size();
    }

    /**
     * Mendapatkan semua entri dalam stack tanpa menghapusnya.
     *
     * @return List dari semua entri
     */
    public List<BorrowRequest> getAllReturns() {
        return new ArrayList<>(stack);
    }

    /**
     * Mendapatkan n entri teratas dari stack.
     *
     * @param n Jumlah entri yang diminta
     * @return List dari n entri teratas
     */
    public List<BorrowRequest> getRecentReturns(int n) {
        List<BorrowRequest> allReturns = getAllReturns();

        // Jika jumlah entri lebih sedikit dari yang diminta
        if (allReturns.size() <= n) {
            return allReturns;
        }

        return allReturns.subList(0, n);
    }
}