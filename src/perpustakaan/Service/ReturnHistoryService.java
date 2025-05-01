package perpustakaan.Service;

import perpustakaan.Model.BorrowRequest;
import perpustakaan.datastructure.ReturnHistoryStack;

import java.util.List;

/**
 * Kelas ReturnHistoryService - layanan untuk mengelola riwayat pengembalian buku.
 *
 * Kelas ini menyediakan antarmuka untuk operasi terkait riwayat pengembalian
 * dan berinteraksi dengan ReturnHistoryStack.
 */
public class ReturnHistoryService {
    private ReturnHistoryStack returnHistoryStack;

    /**
     * Konstruktor untuk ReturnHistoryService.
     *
     * @param returnHistoryStack Stack riwayat pengembalian
     */
    public ReturnHistoryService(ReturnHistoryStack returnHistoryStack) {
        this.returnHistoryStack = returnHistoryStack;
    }

    /**
     * Menambahkan pengembalian buku ke dalam riwayat.
     *
     * @param request Data peminjaman yang berisi informasi pengembalian
     */
    public void addReturnToHistory(BorrowRequest request) {
        // Pastikan request memiliki waktu pengembalian
        if (request.getReturnTime() == null) {
            return;
        }

        returnHistoryStack.push(request);
    }

    /**
     * Mendapatkan pengembalian terbaru.
     *
     * @return Peminjaman terbaru yang dikembalikan
     */
    public BorrowRequest getLatestReturn() {
        if (returnHistoryStack.isEmpty()) {
            return null;
        }

        return returnHistoryStack.peek();
    }

    /**
     * Mendapatkan daftar n pengembalian terbaru.
     *
     * @param n Jumlah riwayat yang diminta
     * @return Daftar n pengembalian terbaru
     */
    public List<BorrowRequest> getRecentReturns(int n) {
        return returnHistoryStack.getRecentReturns(n);
    }

    /**
     * Mendapatkan semua riwayat pengembalian.
     *
     * @return Semua riwayat pengembalian
     */
    public List<BorrowRequest> getAllReturns() {
        return returnHistoryStack.getAllReturns();
    }
}