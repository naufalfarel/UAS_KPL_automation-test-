package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

/**
 * Page Object Model untuk halaman Products (Catalog).
 *
 * PENTING: Locator di bawah ini adalah referensi umum.
 * Kamu WAJIB memvalidasi locator ini menggunakan Appium Inspector
 * setelah membuka session dengan APK yang kamu download.
 *
 * Cara cek locator:
 * 1. Jalankan Appium Server & Emulator
 * 2. Buka Appium Inspector -> Start Session
 * 3. Klik elemen di screenshot -> lihat resource-id / content-desc di panel kanan
 */
public class ProductsPage {

    private AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
        // Tunggu halaman catalog selesai loading ditandai dengan recyclerview muncul
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productRV")));
    }

    /**
     * Mendapatkan daftar semua produk yang tampil di halaman catalog.
     * Digunakan untuk verifikasi bahwa halaman Products sudah terbuka (TC_001).
     *
     * @return List elemen produk (menggunakan titleTV)
     */
    public List<WebElement> getProductList() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV")));
        return driver.findElements(AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV"));
    }

    /**
     * Menekan produk pertama (lewat image), lalu menekan tombol "Add to Cart" di halaman detail produk.
     */
    public void addFirstProductToCart() {
        // Klik produk pertama di catalog (menggunakan productIV yang clickable="true")
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productIV")))
            .click();
        
        // Tunggu tombol "Add to cart" di halaman detail produk lalu klik
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt")))
            .click();
    }

    /**
     * Menekan icon Cart di pojok kanan atas untuk membuka halaman Cart.
     */
    public void openCart() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("View cart")))
            .click();
    }

    /**
     * Mendapatkan elemen badge/counter pada icon Cart.
     * Gunakan .getText() untuk mendapatkan jumlah item.
     *
     * @return WebElement badge counter cart
     */
    public WebElement getCartBadge() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartTV")));
    }
}
