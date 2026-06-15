package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Page Object Model untuk halaman Login.
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
public class LoginPage {

    private AndroidDriver driver;

    // Constructor menerima driver yang sudah diinisialisasi
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigasi dari halaman Catalog ke halaman Login lewat menu.
     */
    public void navigateToLogin() {
        // Tunggu tombol menu (View menu) dapat diklik, lalu klik (dengan retry toleransi animasi)
        WebElement menuBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("View menu")));
        
        boolean menuOpened = false;
        for (int i = 0; i < 3; i++) {
            try {
                menuBtn.click();
                new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Menu Item")));
                menuOpened = true;
                break;
            } catch (Exception e) {
                // Coba klik lagi jika menu belum terbuka
            }
        }
        
        // Tunggu menu item Log In dapat diklik, lalu klik
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Login Menu Item")))
            .click();

        // Tunggu halaman login terbuka ditandai dengan munculnya username field
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET")));
    }

    // Locator menggunakan resource-id asli versi Native Android
    private WebElement usernameField() {
        return driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET"));
    }

    private WebElement passwordField() {
        return driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET"));
    }

    private WebElement loginButton() {
        return driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn"));
    }

    /**
     * Melakukan aksi login dengan username dan password yang diberikan.
     *
     * @param username email/username yang akan diinput
     * @param password password yang akan diinput
     */
    public void login(String username, String password) {
        usernameField().sendKeys(username);
        passwordField().sendKeys(password);
        loginButton().click();
    }
}
