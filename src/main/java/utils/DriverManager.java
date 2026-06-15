package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver() throws Exception {
        if (driver == null) {

            UiAutomator2Options options = new UiAutomator2Options();

            // ===========================================
            // BAGIAN YANG WAJIB DISESUAIKAN DENGAN PERANGKAT KAMU
            // ===========================================

            // Nama platform, biasanya tetap "Android"
            options.setPlatformName("Android");

            // Nama device/HP fisik -> cek dengan command: adb devices
            // Contoh: "emulator-5554" atau UDID HP fisik (misal: "a7c8e9f0" atau "192.168.1.5:5555")
            options.setDeviceName("RRCR7009NFJ");

            // Versi OS Android di HP kamu
            // Cek di Settings > About Phone > Android Version
            options.setPlatformVersion("15");

            // Path absolut ke file APK kamu (lokasi di laptop)
            // APK sudah ada di folder app/ di root project ini
            options.setApp(System.getProperty("user.dir") + java.io.File.separator + "app" + java.io.File.separator + "MyDemoApp.apk");

            // appPackage dan appActivity didapat dari hasil 'aapt dump badging'
            options.setAppPackage("com.saucelabs.mydemoapp.android");
            options.setAppActivity(".view.activities.SplashActivity");

            // Automation engine, gunakan UiAutomator2 (default untuk Android modern)
            options.setAutomationName("UiAutomator2");

            // Jangan reset state app setiap kali run (opsional, percepat testing)
            options.setNoReset(false);

            // ===========================================
            // AKHIR BAGIAN YANG WAJIB DISESUAIKAN
            // ===========================================

            // URL Appium Server -> default jika dijalankan lokal
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            // Implicit wait, supaya driver menunggu elemen muncul sebelum error
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
