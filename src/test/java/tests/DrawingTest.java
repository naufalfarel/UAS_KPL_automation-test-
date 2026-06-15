package tests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DrawingPage;
import pages.ProductsPage;

/**
 * ============================================================
 * TC_003 – Drawing di Signature Pad
 * ============================================================
 * Title       : Verifikasi user dapat melakukan coretan/drawing
 *               dan menyimpannya ke galeri.
 * Pre-conditions:
 *   1. Aplikasi MyDemoApp sudah terinstal di device.
 *   2. Halaman "Products" sedang ditampilkan.
 * Test Steps  :
 *   1. Buka menu samping, klik "Drawing"
 *   2. Gambar/coret pada area signature pad
 *   3. Tap tombol "Save"
 * Expected Result:
 *   1. Muncul pop-up dialog konfirmasi sukses:
 *      "Drawing saved successfully to gallery".
 *   2. Ketika tombol OK di-tap, pop-up dialog tertutup.
 * ============================================================
 */
public class DrawingTest extends BaseTest {

    @Test(description = "TC_003 - Verifikasi drawing di signature pad berhasil disimpan")
    public void testDrawingSuccess() {
        // Tunggu halaman catalog awal selesai loading
        ProductsPage productsPage = new ProductsPage(driver);

        // Langkah 1: Navigasi ke halaman Drawing lewat menu (dengan retry toleransi animasi)
        WebElement menuBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("View menu")));
        
        boolean menuOpened = false;
        for (int i = 0; i < 3; i++) {
            try {
                menuBtn.click();
                new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Drawing']")));
                menuOpened = true;
                break;
            } catch (Exception e) {
                // Coba klik lagi jika menu belum terbuka
            }
        }

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Drawing']")))
            .click();

        // Instansiasi DrawingPage setelah navigasi berhasil
        DrawingPage drawingPage = new DrawingPage(driver);

        // Langkah 2: Lakukan coretan/gambar di signature pad
        drawingPage.drawSignature();

        // Langkah 3: Klik tombol Save
        drawingPage.clickSave();

        // Expected Result 1: Verifikasi isi alert popup
        String alertText = drawingPage.getAlertMessage();
        Assert.assertEquals(
            alertText,
            "Drawing saved successfully to gallery",
            "Pesan konfirmasi penyimpanan gambar tidak sesuai!"
        );

        // Expected Result 2: Tutup alert popup dengan mengklik OK
        drawingPage.clickAlertOk();
    }
}
