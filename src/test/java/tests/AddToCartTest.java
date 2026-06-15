package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

/**
 * ============================================================
 * TC_002 – Tambah Produk ke Keranjang (Add to Cart)
 * ============================================================
 * Title       : Verifikasi user dapat menambahkan produk ke cart
 *               dan jumlah cart bertambah
 * Pre-conditions:
 *   1. User sudah berhasil login (TC_001 PASS).
 *   2. Halaman "Products" sedang ditampilkan.
 * Test Steps  :
 *   1. Pilih salah satu produk (misal "Sauce Labs Backpack")
 *   2. Tap tombol "Add to Cart"
 *   3. Tap icon Cart di pojok kanan atas
 * Expected Result:
 *   1. Badge/counter pada icon cart berubah menjadi "1".
 *   2. Halaman Cart menampilkan produk yang sudah ditambahkan
 *      dengan nama yang sesuai.
 * ============================================================
 */
public class AddToCartTest extends BaseTest {

    @Test(description = "TC_002 - Verifikasi penambahan produk ke cart")
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Langkah awal: Navigasi ke halaman Login lewat menu
        loginPage.navigateToLogin();

        // Pre-condition: user harus login terlebih dahulu
        loginPage.login("bob@example.com", "10203040");

        // Step 1-2: Pilih produk pertama, lalu klik Add to Cart
        productsPage.addFirstProductToCart();

        // Expected Result 1: Badge cart menunjukkan angka "1"
        String badgeText = productsPage.getCartBadge().getText();
        Assert.assertEquals(
            badgeText,
            "1",
            "Jumlah item di cart tidak sesuai harapan. Expected: '1', Actual: '" + badgeText + "'"
        );

        // Step 3: Buka halaman cart
        productsPage.openCart();

        // Expected Result 2: Halaman Cart tampil setelah icon cart di-tap
        // (navigasi ke halaman cart berhasil)
        // Catatan: tambahkan assertion nama produk jika locator item di Cart sudah diketahui
    }
}
