package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Page Object Model untuk halaman Drawing.
 */
public class DrawingPage {

    private AndroidDriver driver;

    public DrawingPage(AndroidDriver driver) {
        this.driver = driver;
        // Tunggu halaman Drawing selesai loading ditandai dengan signature_pad muncul
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.saucelabs.mydemoapp.android:id/signature_pad")));
    }

    public void navigateToDrawing() {
        // Klik tombol menu (View menu) dengan retry toleransi animasi
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
        
        // Klik menu item Drawing
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Drawing']")))
            .click();
    }

    /**
     * Mendapatkan elemen signature pad.
     */
    public WebElement getSignaturePad() {
        return driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/signature_pad"));
    }

    /**
     * Melakukan aksi coretan/gambar di atas signature pad.
     */
    public void drawSignature() {
        WebElement pad = getSignaturePad();
        org.openqa.selenium.Point location = pad.getLocation();
        org.openqa.selenium.Dimension size = pad.getSize();
        
        // Tentukan titik koordinat (relatif terhadap pad)
        int startX = location.getX() + (size.getWidth() / 4);
        int startY = location.getY() + (size.getHeight() / 2);
        int endX = location.getX() + (size.getWidth() * 3 / 4);
        int endY = location.getY() + (size.getHeight() / 2);

        // Simulasi drag dengan PointerInput
        org.openqa.selenium.interactions.PointerInput finger = new org.openqa.selenium.interactions.PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
        org.openqa.selenium.interactions.Sequence draw = new org.openqa.selenium.interactions.Sequence(finger, 1);

        draw.addAction(finger.createPointerMove(Duration.ZERO, org.openqa.selenium.interactions.PointerInput.Origin.viewport(), startX, startY));
        draw.addAction(finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        draw.addAction(finger.createPointerMove(Duration.ofMillis(500), org.openqa.selenium.interactions.PointerInput.Origin.viewport(), endX, endY));
        draw.addAction(finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(java.util.Collections.singletonList(draw));
    }

    /**
     * Klik tombol Save.
     */
    public void clickSave() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.saucelabs.mydemoapp.android:id/saveBtn")))
            .click();
    }

    /**
     * Klik tombol Clear.
     */
    public void clickClear() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.saucelabs.mydemoapp.android:id/clearBtn")))
            .click();
    }

    /**
     * Mendapatkan pesan sukses dari pop-up dialog.
     */
    public String getAlertMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/message")))
            .getText();
    }

    /**
     * Klik tombol OK untuk menutup dialog.
     */
    public void clickAlertOk() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1")))
            .click();
    }
}
