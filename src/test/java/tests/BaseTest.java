package tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;

/**
 * Base class untuk semua test class.
 * Menangani setup dan teardown driver secara terpusat
 * sehingga tidak perlu ditulis ulang di setiap test class.
 */
public class BaseTest {

    protected AndroidDriver driver;

    /**
     * Dijalankan sebelum setiap @Test method.
     * Menginisialisasi AndroidDriver dan membuka aplikasi.
     */
    @BeforeMethod
    public void setUp() throws Exception {
        driver = DriverManager.getDriver();
    }

    /**
     * Dijalankan setelah setiap @Test method selesai (baik pass maupun fail).
     * Menutup sesi Appium dan mereset driver agar test berikutnya
     * mulai dari kondisi bersih.
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
