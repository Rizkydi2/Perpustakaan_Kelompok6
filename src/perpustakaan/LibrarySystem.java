package perpustakaan;

import perpustakaan.Model.Book;
import perpustakaan.Model.BorrowRequest;
import perpustakaan.Model.User;
import perpustakaan.Service.BookService;
import perpustakaan.Service.BorrowingService;
import perpustakaan.Service.ReturnHistoryService;
import perpustakaan.Service.UserService;
import perpustakaan.datastructure.ReturnHistoryStack;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Kelas LibrarySystem - sistem utama aplikasi perpustakaan digital.
 *
 * Kelas ini mengintegrasikan semua layanan dan antar muka pengguna
 * untuk manajemen perpustakaan digital.
 */
public class LibrarySystem {
    private BookService bookService;
    private BorrowingService borrowingService;
    private UserService userService;
    private ReturnHistoryService returnHistoryService;
    private Scanner scanner;

    /**
     * Konstruktor untuk LibrarySystem.
     */
    public LibrarySystem() {
        bookService = new BookService();
        ReturnHistoryStack returnHistoryStack = new ReturnHistoryStack();
        returnHistoryService = new ReturnHistoryService(returnHistoryStack);
        userService = new UserService();
        borrowingService = new BorrowingService(bookService, userService, returnHistoryService);
        scanner = new Scanner(System.in);

        // Data awal untuk testing
        initializeTestData();
    }

    /**
     * Menambahkan data awal untuk testing.
     */
    private void initializeTestData() {
        // Menambahkan beberapa buku
        bookService.addBook("Java Programming", "Programming");
        bookService.addBook("Data Structures", "Computer Science");
        bookService.addBook("Database Systems", "Computer Science");
        bookService.addBook("Web Development", "Programming");
        bookService.addBook("Algorithms", "Computer Science");

        // Menambahkan beberapa pengguna
        userService.registerUser("Budi Santoso", "budi@example.com");
        userService.registerUser("Ani Wijaya", "ani@example.com");
        userService.registerUser("Deni Permana", "deni@example.com");
    }

    /**
     * Memulai aplikasi perpustakaan.
     */
    public void start() {
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    viewBorrowQueue();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    manageUsers();
                    break;
                case 7:
                    displayReturnHistory();
                    break;
                case 8:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan digital!");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    /**
     * Menampilkan menu utama.
     */
    private void displayMainMenu() {
        System.out.println("\n===== SISTEM PERPUSTAKAAN DIGITAL =====");
        System.out.println("1. Manajemen Buku");
        System.out.println("2. Cari Buku");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Lihat Antrian Peminjaman");
        System.out.println("5. Kembalikan Buku");
        System.out.println("6. Manajemen Pengguna");
        System.out.println("7. Lihat Riwayat Pengembalian Terbaru");
        System.out.println("8. Keluar");
        System.out.println("======================================");
    }

    /**
     * Menampilkan menu manajemen buku.
     */
    private void manageBooks() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== MANAJEMEN BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Edit Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Kembali");
            System.out.println("=========================");

            int choice = getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    editBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    /**
     * Mengelola menu manajemen pengguna.
     */
    private void manageUsers() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== MANAJEMEN PENGGUNA =====");
            System.out.println("1. Daftar Pengguna Baru");
            System.out.println("2. Edit Data Pengguna");
            System.out.println("3. Hapus Pengguna");
            System.out.println("4. Lihat Semua Pengguna");
            System.out.println("5. Lihat Detail Pengguna");
            System.out.println("6. Lihat Buku yang Dipinjam Pengguna");
            System.out.println("7. Kembali");
            System.out.println("=============================");

            int choice = getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    editUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    displayAllUsers();
                    break;
                case 5:
                    displayUserDetails();
                    break;
                case 6:
                    displayUserBorrowedBooks();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    /**
     * Mendaftarkan pengguna baru.
     */
    private void registerUser() {
        System.out.println("\n===== DAFTAR PENGGUNA BARU =====");
        System.out.print("Masukkan nama pengguna: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan email pengguna: ");
        String email = scanner.nextLine();

        User newUser = userService.registerUser(name, email);
        System.out.println("Pengguna berhasil didaftarkan dengan ID: " + newUser.getId());
    }

    /**
     * Mengedit data pengguna.
     */
    private void editUser() {
        System.out.println("\n===== EDIT DATA PENGGUNA =====");
        int userId = getIntInput("Masukkan ID pengguna: ");

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
            return;
        }

        System.out.println("Data pengguna saat ini:");
        System.out.println(user);

        System.out.print("Masukkan nama baru (biarkan kosong jika tidak diubah): ");
        String name = scanner.nextLine();
        name = name.isEmpty() ? null : name;

        System.out.print("Masukkan email baru (biarkan kosong jika tidak diubah): ");
        String email = scanner.nextLine();
        email = email.isEmpty() ? null : email;

        boolean success = userService.updateUser(userId, name, email);
        if (success) {
            System.out.println("Data pengguna berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui data pengguna.");
        }
    }

    /**
     * Menghapus data pengguna.
     */
    private void deleteUser() {
        System.out.println("\n===== HAPUS PENGGUNA =====");
        int userId = getIntInput("Masukkan ID pengguna: ");

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
            return;
        }

        if (!user.getBorrowedBooks().isEmpty()) {
            System.out.println("Pengguna masih memiliki buku yang dipinjam. Tidak dapat dihapus.");
            return;
        }

        boolean success = userService.removeUser(userId);
        if (success) {
            System.out.println("Pengguna berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus pengguna.");
        }
    }

    /**
     * Menampilkan semua pengguna.
     */
    private void displayAllUsers() {
        System.out.println("\n===== DAFTAR SEMUA PENGGUNA =====");
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("Tidak ada pengguna terdaftar.");
            return;
        }

        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * Menampilkan detail pengguna.
     */
    private void displayUserDetails() {
        System.out.println("\n===== DETAIL PENGGUNA =====");
        int userId = getIntInput("Masukkan ID pengguna: ");

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
            return;
        }

        System.out.println("ID: " + user.getId());
        System.out.println("Nama: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Jumlah buku yang dipinjam: " + user.getBorrowedBooksCount());
    }

    /**
     * Menampilkan buku yang dipinjam oleh pengguna.
     */
    private void displayUserBorrowedBooks() {
        System.out.println("\n===== BUKU YANG DIPINJAM =====");
        int userId = getIntInput("Masukkan ID pengguna: ");

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
            return;
        }

        List<BorrowRequest> borrowedBooks = userService.getUserBorrowedBooks(userId);

        if (borrowedBooks.isEmpty()) {
            System.out.println("Pengguna tidak meminjam buku saat ini.");
            return;
        }

        System.out.println("Buku yang dipinjam oleh " + user.getName() + ":");
        for (BorrowRequest request : borrowedBooks) {
            System.out.println("- " + request.getBook().getTitle() +
                    " (Dipinjam pada: " + request.getBorrowTime() + ")");
        }
    }

    /**
     * Menambahkan buku baru.
     */
    private void addBook() {
        System.out.println("\n===== TAMBAH BUKU =====");
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();

        System.out.print("Masukkan kategori buku: ");
        String category = scanner.nextLine();

        Book newBook = bookService.addBook(title, category);
        System.out.println("Buku berhasil ditambahkan dengan ID: " + newBook.getId());
    }

    /**
     * Mengedit data buku.
     */
    private void editBook() {
        System.out.println("\n===== EDIT BUKU =====");
        int bookId = getIntInput("Masukkan ID buku: ");

        Book book = bookService.getBookById(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan.");
            return;
        }

        System.out.println("Data buku saat ini:");
        System.out.println(book);

        System.out.print("Masukkan judul baru (biarkan kosong jika tidak diubah): ");
        String title = scanner.nextLine();
        title = title.isEmpty() ? null : title;

        System.out.print("Masukkan kategori baru (biarkan kosong jika tidak diubah): ");
        String category = scanner.nextLine();
        category = category.isEmpty() ? null : category;

        boolean success = bookService.updateBook(bookId, title, category);
        if (success) {
            System.out.println("Buku berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui buku.");
        }
    }

    /**
     * Menghapus buku.
     */
    private void deleteBook() {
        System.out.println("\n===== HAPUS BUKU =====");
        int bookId = getIntInput("Masukkan ID buku: ");

        Book book = bookService.getBookById(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan.");
            return;
        }

        // Periksa apakah buku sedang dipinjam
        if (borrowingService.isBookBorrowed(bookId)) {
            System.out.println("Buku sedang dipinjam. Tidak dapat dihapus.");
            return;
        }

        boolean success = bookService.removeBook(bookId);
        if (success) {
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus buku.");
        }
    }

    /**
     * Menampilkan semua buku.
     */
    private void displayAllBooks() {
        System.out.println("\n===== DAFTAR BUKU =====");
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("Tidak ada buku tersedia.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * Mencari buku berdasarkan judul atau kategori.
     */
    private void searchBooks() {
        System.out.println("\n===== CARI BUKU =====");
        System.out.println("1. Cari berdasarkan judul");
        System.out.println("2. Cari berdasarkan kategori");
        System.out.println("3. Kembali");

        int choice = getIntInput("Pilih menu: ");

        switch (choice) {
            case 1:
                System.out.print("Masukkan judul buku: ");
                String title = scanner.nextLine();
                List<Book> booksByTitle = bookService.searchBooksByTitle(title);
                displaySearchResults(booksByTitle);
                break;
            case 2:
                System.out.print("Masukkan kategori buku: ");
                String category = scanner.nextLine();
                List<Book> booksByCategory = bookService.searchBooksByCategory(category);
                displaySearchResults(booksByCategory);
                break;
            case 3:
                return;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    /**
     * Menampilkan hasil pencarian buku.
     */
    private void displaySearchResults(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang ditemukan.");
            return;
        }

        System.out.println("\nHasil pencarian:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * Meminjam buku.
     */
    private void borrowBook() {
        System.out.println("\n===== PINJAM BUKU =====");
        int userId = getIntInput("Masukkan ID pengguna: ");

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
            return;
        }

        int bookId = getIntInput("Masukkan ID buku: ");

        Book book = bookService.getBookById(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan.");
            return;
        }

        if (user.isBookBorrowed(bookId)) {
            System.out.println("Pengguna sudah meminjam buku ini.");
            return;
        }

        BorrowRequest request = borrowingService.borrowBook(userId, bookId);
        if (request != null) {
            System.out.println("Buku berhasil dipinjam.");
        } else {
            System.out.println("Buku sedang dipinjam oleh pengguna lain. Permintaan masuk ke antrian.");
        }
    }

    /**
     * Melihat antrian peminjaman.
     */
    private void viewBorrowQueue() {
        System.out.println("\n===== ANTRIAN PEMINJAMAN =====");
        List<BorrowRequest> queue = borrowingService.getBorrowQueue();

        if (queue.isEmpty()) {
            System.out.println("Tidak ada antrian peminjaman.");
            return;
        }

        System.out.println("Antrian peminjaman:");
        for (int i = 0; i < queue.size(); i++) {
            BorrowRequest request = queue.get(i);
            System.out.println((i + 1) + ". " + request.getUser().getName() +
                    " - " + request.getBook().getTitle() +
                    " (Waktu permintaan: " + request.getBorrowTime() + ")");
        }
    }

    /**
     * Mengembalikan buku.
     */
    private void returnBook() {
        System.out.println("\n===== KEMBALIKAN BUKU =====");
        int userId = getIntInput("Masukkan ID pengguna: ");

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
            return;
        }

        List<BorrowRequest> borrowedBooks = userService.getUserBorrowedBooks(userId);
        if (borrowedBooks.isEmpty()) {
            System.out.println("Pengguna tidak meminjam buku saat ini.");
            return;
        }

        System.out.println("Buku yang dipinjam:");
        for (BorrowRequest request : borrowedBooks) {
            System.out.println(request.getBook().getId() + ". " +
                    request.getBook().getTitle() +
                    " (Dipinjam pada: " + request.getBorrowTime() + ")");
        }

        int bookId = getIntInput("Masukkan ID buku yang akan dikembalikan: ");

        boolean success = borrowingService.returnBook(userId, bookId);
        if (success) {
            System.out.println("Buku berhasil dikembalikan.");
        } else {
            System.out.println("Gagal mengembalikan buku. Pastikan buku sedang dipinjam oleh pengguna.");
        }
    }

    /**
     * Menampilkan riwayat pengembalian terbaru.
     */
    private void displayReturnHistory() {
        System.out.println("\n===== RIWAYAT PENGEMBALIAN TERBARU =====");
        List<BorrowRequest> recentReturns = returnHistoryService.getRecentReturns(5);

        if (recentReturns.isEmpty()) {
            System.out.println("Tidak ada riwayat pengembalian.");
            return;
        }

        System.out.println("5 pengembalian terbaru:");
        for (int i = 0; i < recentReturns.size(); i++) {
            BorrowRequest request = recentReturns.get(i);
            System.out.println((i + 1) + ". " + request.getUser().getName() +
                    " - " + request.getBook().getTitle() +
                    " (Waktu pengembalian: " + request.getReturnTime() + ")");
        }
    }

    /**
     * Mendapatkan input integer dari pengguna.
     *
     * @param prompt Pesan yang ditampilkan
     * @return Integer yang dimasukkan pengguna
     */
    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
    }

    /**
     * Main method untuk menjalankan aplikasi.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}