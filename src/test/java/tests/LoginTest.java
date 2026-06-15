package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

/**
 * ============================================================
 * TC_001 – Login Berhasil
 * ============================================================
 * Title       : Verifikasi user dapat login dengan kredensial valid
 * Pre-conditions:
 *   1. Aplikasi MyDemoApp sudah terinstal di emulator/device.
 *   2. Aplikasi dalam kondisi terbuka di halaman Login.
 * Test Steps  :
 *   1. Tap pada field "Username"
 *   2. Input bob@example.com
 *   3. Tap pada field "Password"
 *   4. Input 10203040
 *   5. Tap tombol "Login"
 * Expected Result:
 *   User berhasil masuk dan diarahkan ke halaman "Products"
 *   (Product Catalog), ditandai dengan munculnya elemen daftar produk.
 * ============================================================
 */
public class LoginTest extends BaseTest {

    @Test(description = "TC_001 - Verifikasi login berhasil dengan kredensial valid")
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // Langkah awal: Navigasi ke halaman Login lewat menu
        loginPage.navigateToLogin();

        // Step 1-5: Input username, password, lalu klik tombol Login
        loginPage.login("bob@example.com", "10203040");

        // Expected Result: Halaman Products menampilkan daftar item
        // (artinya login berhasil dan navigasi ke Products Page terjadi)
        Assert.assertTrue(
            productsPage.getProductList().size() > 0,
            "Login gagal: halaman Products tidak menampilkan produk apapun"
        );
    }
}
