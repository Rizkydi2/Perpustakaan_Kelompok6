package perpustakaan;

import perpustakaan.Model.Book;
import perpustakaan.Service.BookManager;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Kelas LibrarySystem - kelas utama yang menjalankan aplikasi sistem perpustakaan.
 *
 * Kelas ini mengimplementasikan antarmuka pengguna berbasis CLI (Command Line Interface)
 * untuk berinteraksi dengan sistem manajemen perpustakaan.
 *
 * @author Developer Sistem Perpustakaan
 * @version 1.0
 */
public class LibrarySystem {
    private static BookManager bookManager;
    private static Scanner scanner;

    /**
     * Metode main, entry point program.
     *
     * @param args argumen command line
     */
    public static void main(String[] args) {
        // Inisialisasi komponen sistem
        bookManager = new BookManager();
        scanner = new Scanner(System.in);

        // Tambahkan beberapa data contoh
        addSampleData();

        boolean running = true;

        // Tampilkan pesan selamat datang
        System.out.println("=======================================================");
        System.out.println("   SISTEM MANAJEMEN PERPUSTAKAAN DIGITAL BERBASIS CLI   ");
        System.out.println("=======================================================");

        // Loop utama program
        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 0:
                    System.out.println("\nTerima kasih telah menggunakan Sistem Perpustakaan Digital!");
                    running = false;
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
            }
        }

        // Tutup scanner saat program selesai
        scanner.close();
    }

    /**
     * Menampilkan menu utama sistem.
     */
    private static void displayMainMenu() {
        System.out.println("\n===== MENU UTAMA =====");
        System.out.println("1. Manajemen Buku");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu (0-1): ");
    }

    /**
     * Menampilkan submenu manajemen buku.
     */
    private static void displayBookMenu() {
        System.out.println("\n===== MANAJEMEN BUKU =====");
        System.out.println("1. Tambah Buku Baru");
        System.out.println("2. Tampilkan Semua Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Edit Informasi Buku");
        System.out.println("5. Hapus Buku");
        System.out.println("0. Kembali ke Menu Utama");
        System.out.print("Pilih menu (0-5): ");
    }

    /**
     * Mengelola operasi terkait buku.
     */
    private static void manageBooks() {
        boolean inBookMenu = true;

        while (inBookMenu) {
            displayBookMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    editBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 0:
                    inBookMenu = false;
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    /**
     * Menambahkan buku baru ke sistem.
     */
    private static void addNewBook() {
        System.out.println("\n===== TAMBAH BUKU BARU =====");

        // Minta input dari pengguna
        System.out.print("Judul: ");
        String title = scanner.nextLine();

        System.out.print("Penulis: ");
        String author = scanner.nextLine();

        System.out.print("Kategori: ");
        String category = scanner.nextLine();

        // Tambahkan buku baru
        Book newBook = bookManager.addBook(title, author, category);

        System.out.println("\nBuku berhasil ditambahkan!");
        System.out.println(newBook);
    }

    /**
     * Menampilkan semua buku dalam sistem.
     */
    private static void displayAllBooks() {
        System.out.println("\n===== DAFTAR SEMUA BUKU =====");

        LinkedList<Book> allBooks = bookManager.getAllBooks();

        if (allBooks.isEmpty()) {
            System.out.println("Tidak ada buku dalam sistem.");
            return;
        }

        int count = 1;
        for (Book book : allBooks) {
            System.out.println(count + ". " + book);
            count++;
        }
    }

    /**
     * Mencari buku berdasarkan judul atau kategori.
     */
    private static void searchBooks() {
        System.out.println("\n===== CARI BUKU =====");
        System.out.println("1. Cari berdasarkan Judul");
        System.out.println("2. Cari berdasarkan Kategori");
        System.out.print("Pilih opsi pencarian (1-2): ");

        int searchOption = getUserChoice();

        switch (searchOption) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByCategory();
                break;
            default:
                System.out.println("Opsi pencarian tidak valid.");
        }
    }

    /**
     * Mencari buku berdasarkan judul.
     */
    private static void searchByTitle() {
        System.out.print("\nMasukkan judul buku: ");
        String title = scanner.nextLine();

        LinkedList<Book> foundBooks = bookManager.findBooksByTitle(title);

        displaySearchResults(foundBooks);
    }

    /**
     * Mencari buku berdasarkan kategori.
     */
    private static void searchByCategory() {
        System.out.print("\nMasukkan kategori buku: ");
        String category = scanner.nextLine();

        LinkedList<Book> foundBooks = bookManager.findBooksByCategory(category);

        displaySearchResults(foundBooks);
    }

    /**
     * Menampilkan hasil pencarian buku.
     *
     * @param books LinkedList berisi buku hasil pencarian
     */
    private static void displaySearchResults(LinkedList<Book> books) {
        System.out.println("\n===== HASIL PENCARIAN =====");

        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang ditemukan.");
            return;
        }

        int count = 1;
        for (Book book : books) {
            System.out.println(count + ". " + book);
            count++;
        }
    }

    /**
     * Mengedit informasi buku yang sudah ada.
     */
    private static void editBook() {
        System.out.println("\n===== EDIT INFORMASI BUKU =====");

        // Tampilkan semua buku terlebih dahulu
        displayAllBooks();

        if (bookManager.getAllBooks().isEmpty()) {
            return;
        }

        System.out.print("\nMasukkan ID buku yang akan diedit: ");
        String id = scanner.nextLine();

        Book bookToEdit = bookManager.findBookById(id);

        if (bookToEdit == null) {
            System.out.println("Buku dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.println("\nBuku yang akan diedit:");
        System.out.println(bookToEdit);

        System.out.println("\nMasukkan informasi baru (kosongkan jika tidak ingin mengubah):");

        System.out.print("Judul baru: ");
        String newTitle = scanner.nextLine();
        newTitle = newTitle.isEmpty() ? null : newTitle;

        System.out.print("Penulis baru: ");
        String newAuthor = scanner.nextLine();
        newAuthor = newAuthor.isEmpty() ? null : newAuthor;

        System.out.print("Kategori baru: ");
        String newCategory = scanner.nextLine();
        newCategory = newCategory.isEmpty() ? null : newCategory;

        boolean success = bookManager.updateBook(id, newTitle, newAuthor, newCategory);

        if (success) {
            System.out.println("\nInformasi buku berhasil diperbarui!");
            System.out.println(bookManager.findBookById(id));
        } else {
            System.out.println("\nGagal memperbarui informasi buku.");
        }
    }

    /**
     * Menghapus buku dari sistem.
     */
    private static void deleteBook() {
        System.out.println("\n===== HAPUS BUKU =====");

        // Tampilkan semua buku terlebih dahulu
        displayAllBooks();

        if (bookManager.getAllBooks().isEmpty()) {
            return;
        }

        System.out.print("\nMasukkan ID buku yang akan dihapus: ");
        String id = scanner.nextLine();

        Book bookToDelete = bookManager.findBookById(id);

        if (bookToDelete == null) {
            System.out.println("Buku dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.println("\nBuku yang akan dihapus:");
        System.out.println(bookToDelete);

        System.out.print("\nApakah Anda yakin ingin menghapus buku ini? (y/n): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            boolean success = bookManager.deleteBook(id);

            if (success) {
                System.out.println("\nBuku berhasil dihapus.");
            } else {
                System.out.println("\nGagal menghapus buku.");
            }
        } else {
            System.out.println("\nPenghapusan buku dibatalkan.");
        }
    }

    /**
     * Mendapatkan pilihan menu dari pengguna.
     *
     * @return Pilihan menu dalam bentuk integer
     */
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Jika input bukan angka, return -1
        }
        return choice;
    }

    /**
     * Menambahkan data contoh ke sistem.
     */
    private static void addSampleData() {
        bookManager.addBook("Java Programming", "John Doe", "Pemrograman");
        bookManager.addBook("Data Structures and Algorithms", "Jane Smith", "Pemrograman");
        bookManager.addBook("Database Management", "Bob Johnson", "Database");
        bookManager.addBook("Web Development", "Alice Williams", "Web");
        bookManager.addBook("Artificial Intelligence", "Charlie Brown", "AI");
    }
}