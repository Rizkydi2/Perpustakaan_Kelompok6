package perpustakaan.datastructure;

import perpustakaan.Model.BorrowRequest;
import java.util.LinkedList;

/**
 * Implementasi struktur data Queue (Antrian) untuk mengelola permintaan peminjaman buku
 * berdasarkan urutan kedatangan (FIFO - First In First Out).
 */
public class BorrowingQueue {
    private LinkedList<BorrowRequest> queue;

    /**
     * Konstruktor untuk membuat antrian baru
     */
    public BorrowingQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Menambahkan permintaan peminjaman ke dalam antrian
     *
     * @param request Permintaan peminjaman yang akan ditambahkan
     */
    public void enqueue(BorrowRequest request) {
        queue.addLast(request);
    }

    /**
     * Mengambil dan menghapus permintaan peminjaman dari depan antrian
     *
     * @return Permintaan peminjaman, atau null jika antrian kosong
     */
    public BorrowRequest dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    /**
     * Melihat permintaan peminjaman di depan antrian tanpa menghapusnya
     *
     * @return Permintaan peminjaman, atau null jika antrian kosong
     */
    public BorrowRequest peek() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.getFirst();
    }

    /**
     * Memeriksa apakah antrian kosong
     *
     * @return true jika antrian kosong, false jika tidak
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Mendapatkan jumlah permintaan dalam antrian
     *
     * @return Jumlah permintaan
     */
    public int size() {
        return queue.size();
    }

    /**
     * Mendapatkan semua permintaan dalam antrian
     *
     * @return Daftar permintaan peminjaman
     */
    public LinkedList<BorrowRequest> getAllRequests() {
        // Membuat salinan untuk mencegah modifikasi langsung pada antrian internal
        return new LinkedList<>(queue);
    }
}