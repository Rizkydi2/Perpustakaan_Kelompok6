package perpustakaan;

import perpustakaan.Model.Book;
import perpustakaan.Service.BookManager;
import perpustakaan.Service.BookSearchService;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Kelas LibrarySystem - kelas utama yang menjalankan aplikasi sistem perpustakaan.
 *
 * Kelas ini menyediakan antarmuka pengguna berbasis CLI (Command Line Interface)
 * untuk berinteraksi dengan sistem perpustakaan digital.
 *
 * @author Developer Sistem Perpustakaan
 */
public class LibrarySystem {
    private static BookManager bookManager;
    private static BookSearchService searchService;
    private static Scanner scanner;

    /**
     * Metode utama untuk menjalankan aplikasi sistem perpustakaan.
     *
     * @param args Argumen baris perintah (tidak digunakan)
     */
    public static void main(String[] args) {
        // Inisialisasi komponen sistem
        bookManager = new BookManager();
        searchService = new BookSearchService();
        scanner = new Scanner(System.in);

        // Tambahkan beberapa buku contoh
        initSampleBooks();

        boolean running = true;

        System.out.println("=== SISTEM MANAJEMEN PERPUSTAKAAN DIGITAL ===");

        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 0:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan Sistem Perpustakaan Digital.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    /**
     * Menampilkan menu utama sistem.
     */
    private static void displayMainMenu() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Manajemen Buku");
        System.out.println("2. Pencarian Buku");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    /**
     * Menampilkan submenu manajemen buku.
     */
    private static void displayBookManagementMenu() {
        System.out.println("\n=== MANAJEMEN BUKU ===");
        System.out.println("1. Tambah Buku Baru");
        System.out.println("2. Tampilkan Semua Buku");
        System.out.println("3. Perbarui Informasi Buku");
        System.out.println("4. Hapus Buku");
        System.out.println("0. Kembali ke Menu Utama");
        System.out.print("Pilih menu: ");
    }

    /**
     * Menampilkan submenu pencarian buku.
     */
    private static void displayBookSearchMenu() {
        System.out.println("\n=== PENCARIAN BUKU ===");
        System.out.println("1. Cari Berdasarkan Judul");
        System.out.println("2. Cari Berdasarkan Kategori");
        System.out.println("0. Kembali ke Menu Utama");
        System.out.print("Pilih menu: ");
    }

    /**
     * Mendapatkan pilihan pengguna dari input.
     *
     * @return Pilihan pengguna sebagai integer
     */
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Nilai tidak valid
        }
    }

    /**
     * Menangani operasi manajemen buku.
     */
    private static void manageBooks() {
        boolean managingBooks = true;

        while (managingBooks) {
            displayBookManagementMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 0:
                    managingBooks = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    /**
     * Menangani operasi pencarian buku.
     */
    private static void searchBooks() {
        boolean searchingBooks = true;

        while (searchingBooks) {
            displayBookSearchMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    searchBooksByCategory();
                    break;
                case 0:
                    searchingBooks = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    /**
     * Menambahkan buku baru ke dalam sistem.
     */
    private static void addNewBook() {
        System.out.println("\n=== TAMBAH BUKU BARU ===");

        System.out.print("Judul Buku: ");
        String title = scanner.nextLine();

        System.out.print("Penulis: ");
        String author = scanner.nextLine();

        System.out.print("Kategori: ");
        String category = scanner.nextLine();

        Book newBook = new Book(title, author, category);
        bookManager.addBook(newBook);
        searchService.addBookToSearchTrees(newBook);

        System.out.println("Buku berhasil ditambahkan dengan ID: " + newBook.getId());
    }

    /**
     * Menampilkan semua buku dalam koleksi.
     */
    private static void displayAllBooks() {
        System.out.println("\n=== DAFTAR SEMUA BUKU ===");

        LinkedList<Book> books = bookManager.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("Tidak ada buku dalam koleksi.");
            return;
        }

        for (Book book : books) {
            displayBookDetails(book);
        }
    }

    /**
     * Memperbarui informasi buku yang sudah ada.
     */
    private static void updateBook() {
        System.out.println("\n=== PERBARUI INFORMASI BUKU ===");

        System.out.print("Masukkan ID buku yang akan diperbarui: ");
        String id = scanner.nextLine();

        Book book = bookManager.findBookById(id);

        if (book == null) {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
            return;
        }

        System.out.println("Buku yang akan diperbarui:");
        displayBookDetails(book);

        System.out.println("\nMasukkan informasi baru (kosongkan jika tidak ingin mengubah):");

        System.out.print("Judul Buku [" + book.getTitle() + "]: ");
        String title = scanner.nextLine();

        System.out.print("Penulis [" + book.getAuthor() + "]: ");
        String author = scanner.nextLine();

        System.out.print("Kategori [" + book.getCategory() + "]: ");
        String category = scanner.nextLine();

        // Perbarui hanya jika input tidak kosong
        if (!title.isEmpty()) {
            book.setTitle(title);
        }

        if (!author.isEmpty()) {
            book.setAuthor(author);
        }

        if (!category.isEmpty()) {
            book.setCategory(category);
        }

        bookManager.updateBook(book);
        searchService.updateSearchTrees(bookManager.getAllBooks());

        System.out.println("Informasi buku berhasil diperbarui.");
    }

    /**
     * Menghapus buku dari koleksi.
     */
    private static void deleteBook() {
        System.out.println("\n=== HAPUS BUKU ===");

        System.out.print("Masukkan ID buku yang akan dihapus: ");
        String id = scanner.nextLine();

        Book book = bookManager.findBookById(id);

        if (book == null) {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
            return;
        }

        System.out.println("Buku yang akan dihapus:");
        displayBookDetails(book);

        System.out.print("Apakah Anda yakin ingin menghapus buku ini? (y/n): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            bookManager.deleteBook(id);
            searchService.updateSearchTrees(bookManager.getAllBooks());
            System.out.println("Buku berhasil dihapus dari koleksi.");
        } else {
            System.out.println("Penghapusan buku dibatalkan.");
        }
    }

    /**
     * Mencari buku berdasarkan judul.
     */
    private static void searchBooksByTitle() {
        System.out.println("\n=== CARI BUKU BERDASARKAN JUDUL ===");

        System.out.print("Masukkan judul buku (atau bagian dari judul): ");
        String title = scanner.nextLine();

        List<Book> foundBooks = searchService.searchByTitle(title);

        displaySearchResults(foundBooks, "judul");
    }

    /**
     * Mencari buku berdasarkan kategori.
     */
    private static void searchBooksByCategory() {
        System.out.println("\n=== CARI BUKU BERDASARKAN KATEGORI ===");

        System.out.print("Masukkan kategori buku: ");
        String category = scanner.nextLine();

        List<Book> foundBooks = searchService.searchByCategory(category);

        displaySearchResults(foundBooks, "kategori");
    }

    /**
     * Menampilkan hasil pencarian buku.
     *
     * @param books Daftar buku hasil pencarian
     * @param searchType Jenis pencarian (judul atau kategori)
     */
    private static void displaySearchResults(List<Book> books, String searchType) {
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang cocok dengan " + searchType + " tersebut.");
            return;
        }

        System.out.println("\nDitemukan " + books.size() + " buku:");

        for (Book book : books) {
            displayBookDetails(book);
        }
    }

    /**
     * Menampilkan detail buku.
     *
     * @param book Buku yang detailnya akan ditampilkan
     */
    private static void displayBookDetails(Book book) {
        System.out.println("\nID: " + book.getId());
        System.out.println("Judul: " + book.getTitle());
        System.out.println("Penulis: " + book.getAuthor());
        System.out.println("Kategori: " + book.getCategory());
        System.out.println("Status: " + (book.isAvailable() ? "Tersedia" : "Dipinjam"));
        System.out.println("--------------------------");
    }

    /**
     * Menginisialisasi beberapa buku contoh untuk demonstrasi.
     */
    private static void initSampleBooks() {
        // Buat beberapa buku contoh
        Book book1 = new Book("Pemrograman Java untuk Pemula", "Budi Santoso", "Teknologi");
        Book book2 = new Book("Struktur Data dan Algoritma", "Dewi Wijaya", "Teknologi");
        Book book3 = new Book("Database Management Systems", "Rini Putri", "Teknologi");
        Book book4 = new Book("Manajemen Proyek IT", "Agus Pratama", "Bisnis");
        Book book5 = new Book("Machine Learning dengan Python", "Hendra Gunawan", "Teknologi");

        // Tambahkan buku ke sistem
        bookManager.addBook(book1);
        bookManager.addBook(book2);
        bookManager.addBook(book3);
        bookManager.addBook(book4);
        bookManager.addBook(book5);

        // Perbarui pohon pencarian
        searchService.updateSearchTrees(bookManager.getAllBooks());
    }
}