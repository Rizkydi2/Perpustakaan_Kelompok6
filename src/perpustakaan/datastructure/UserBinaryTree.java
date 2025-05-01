package perpustakaan.datastructure;

import perpustakaan.Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas UserBinaryTree - implementasi Binary Tree untuk penyimpanan data pengguna.
 *
 * Struktur data ini digunakan untuk menyimpan data pengguna perpustakaan berdasarkan
 * ID pengguna untuk pencarian yang efisien.
 */
public class UserBinaryTree {
    private Node root;

    private class Node {
        User user;
        Node left;
        Node right;

        Node(User user) {
            this.user = user;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Menambahkan pengguna ke dalam binary tree.
     *
     * @param user Pengguna yang akan ditambahkan
     */
    public void addUser(User user) {
        if (user == null) {
            return;
        }

        root = addUserRecursive(root, user);
    }

    private Node addUserRecursive(Node current, User user) {
        if (current == null) {
            return new Node(user);
        }

        // Bandingkan berdasarkan ID pengguna
        if (user.getId() < current.user.getId()) {
            current.left = addUserRecursive(current.left, user);
        } else if (user.getId() > current.user.getId()) {
            current.right = addUserRecursive(current.right, user);
        } else {
            // Jika ID sudah ada, perbarui data pengguna
            current.user = user;
        }

        return current;
    }

    /**
     * Mencari pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang dicari
     * @return User yang ditemukan, atau null jika tidak ditemukan
     */
    public User findUser(int id) {
        Node result = findUserRecursive(root, id);
        return result == null ? null : result.user;
    }

    private Node findUserRecursive(Node current, int id) {
        if (current == null) {
            return null;
        }

        if (id == current.user.getId()) {
            return current;
        }

        if (id < current.user.getId()) {
            return findUserRecursive(current.left, id);
        } else {
            return findUserRecursive(current.right, id);
        }
    }

    /**
     * Menghapus pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan dihapus
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeUser(int id) {
        if (findUser(id) == null) {
            return false;
        }

        root = removeUserRecursive(root, id);
        return true;
    }

    private Node removeUserRecursive(Node current, int id) {
        if (current == null) {
            return null;
        }

        if (id == current.user.getId()) {
            // Node tanpa anak
            if (current.left == null && current.right == null) {
                return null;
            }

            // Node dengan satu anak
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            // Node dengan dua anak
            User smallestValue = findSmallestValue(current.right);
            current.user = smallestValue;
            current.right = removeUserRecursive(current.right, smallestValue.getId());
            return current;
        }

        if (id < current.user.getId()) {
            current.left = removeUserRecursive(current.left, id);
        } else {
            current.right = removeUserRecursive(current.right, id);
        }

        return current;
    }

    private User findSmallestValue(Node root) {
        return root.left == null ? root.user : findSmallestValue(root.left);
    }

    /**
     * Mendapatkan semua pengguna dalam bentuk daftar.
     *
     * @return List dari semua pengguna
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        inOrderTraversal(root, users);
        return users;
    }

    private void inOrderTraversal(Node node, List<User> users) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, users);
        users.add(node.user);
        inOrderTraversal(node.right, users);
    }

    /**
     * Memperbarui data pengguna.
     *
     * @param user Pengguna dengan data terbaru
     * @return true jika berhasil diperbarui, false jika tidak ditemukan
     */
    public boolean updateUser(User user) {
        if (findUser(user.getId()) == null) {
            return false;
        }

        // Hapus pengguna lama dan tambahkan yang baru
        removeUser(user.getId());
        addUser(user);
        return true;
    }
}