# UAS Kualitas Perangkat Lunak – Mobile Automation Testing dengan Appium

## Deskripsi Project

Project ini merupakan implementasi automation testing end-to-end untuk aplikasi
mobile Android menggunakan **Appium** dan **Java**. Project ini dibuat sebagai
pemenuhan tugas Ujian Akhir Semester (UAS) mata kuliah Kualitas Perangkat Lunak.

Aplikasi yang diuji: **MyDemoApp Saucelabs (Android)**

Test case yang dicakup:

1. **TC_001** - Login berhasil dengan kredensial valid
2. **TC_002** - Penambahan produk ke keranjang (Add to Cart)
3. **TC_003** - Menggambar pada kanvas (Drawing Signature Pad) & menyimpannya ke galeri

---

## 🛠️ Tech Stack

| Komponen           | Versi  |
| ------------------ | ------ |
| Java               | 11+    |
| Appium Java Client | 9.3.0  |
| Selenium           | 4.19.1 |
| TestNG             | 7.10.2 |
| Maven              | 3.6+   |

---

## 📋 Prerequisites

Pastikan sudah menginstal semua tools berikut sebelum menjalankan project:

1. **Java JDK 11 atau lebih baru** – [Download di sini](https://adoptium.net/)
2. **Node.js** (untuk menjalankan Appium Server) – [Download di sini](https://nodejs.org/)
3. **Appium Server** – install via npm:
   ```bash
   npm install -g appium
   appium driver install uiautomator2
   ```
4. **Android SDK Platform-Tools (adb)** – Diperlukan untuk mendeteksi HP via USB. Cukup download platform-tools [di sini](https://developer.android.com/tools/releases/platform-tools) dan tambahkan ke PATH.
5. **Maven** – [Download di sini](https://maven.apache.org/download.cgi)
6. **Appium Inspector** (opsional, untuk validasi locator UI) – [Download di sini](https://github.com/appium/appium-inspector/releases)

---

## ⚙️ Cara Setup & Menjalankan

### 1. Clone Repository

```bash
git clone https://github.com/username/appium-mobile-automation.git
cd appium-mobile-automation
```

### 2. Siapkan HP Android (Device Fisik)

- Aktifkan **Developer Options** (Opsi Pengembang) di HP Anda: Masuk ke _Settings > About Phone_, ketuk _Build Number_ sebanyak 7 kali hingga muncul notifikasi bahwa Opsi Pengembang telah aktif.
- Aktifkan **USB Debugging** di menu _Developer Options_.
- Hubungkan HP Android ke laptop menggunakan kabel data USB.
- Jika muncul pop-up izin USB Debugging di layar HP, pilih **Allow / Izinkan**.
- Cek apakah HP sudah terdeteksi di laptop dengan perintah:
  ```bash
  adb devices
  ```
  Contoh output: `a7c8e9f0   device` _(catat kode alfanumerik ini, ini adalah UDID HP Anda)_.

### 3. Sesuaikan Konfigurasi Driver

Buka file [`src/main/java/utils/DriverManager.java`](src/main/java/utils/DriverManager.java) dan sesuaikan bagian berikut:

```java
// Sesuaikan dengan UDID hasil 'adb devices' tadi
options.setDeviceName("a7c8e9f0");

// Sesuaikan dengan versi Android HP fisik kamu (misal: 11, 12, 13, 14)
options.setPlatformVersion("12");
```

> **Catatan:** Path APK sudah otomatis diarahkan ke folder `app/MyDemoApp.apk`
> di root project ini, jadi tidak perlu diubah selama APK ada di folder yang benar.

### 4. Jalankan Appium Server

Buka **terminal baru** (jangan ditutup), jalankan:

```bash
appium
```

Pastikan server berjalan dan menampilkan: `Appium REST http interface listener started on 127.0.0.1:4723`

### 5. Jalankan Test

Di **terminal lain** (dari root folder project), jalankan:

```bash
mvn clean test
```

Hasil test dapat dilihat di:

- **Console output** – langsung terlihat PASS/FAIL
- **`target/surefire-reports/`** – laporan XML dan HTML detail

---

## 📂 Struktur Project

```
appium-mobile-automation/
├── app/
│   └── MyDemoApp.apk              # APK aplikasi yang diuji
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── LoginPage.java     # POM: Halaman Login
│   │       │   ├── ProductsPage.java  # POM: Halaman Products & Cart
│   │       │   └── DrawingPage.java   # POM: Halaman Drawing (Signature Pad)
│   │       └── utils/
│   │           └── DriverManager.java # Setup Appium capabilities
│   └── test/
│       └── java/
│           └── tests/
│               ├── BaseTest.java      # Setup & teardown driver
│               ├── LoginTest.java     # TC_001: Login berhasil
│               ├── AddToCartTest.java # TC_002: Add to Cart
│               └── DrawingTest.java   # TC_003: Drawing Signature Pad
├── testng.xml                         # Konfigurasi TestNG suite
├── pom.xml                            # Maven dependencies
├── .gitignore
└── README.md
```

---

## 🧪 Test Cases

### TC_001 – Login Berhasil

| Field               | Detail                                                                                                               |
| ------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **Test Case ID**    | TC_001                                                                                                               |
| **Title**           | Verifikasi user dapat login dengan kredensial valid                                                                  |
| **Pre-conditions**  | 1. Aplikasi MyDemoApp sudah terinstal di emulator/device. 2. Aplikasi dalam kondisi terbuka di halaman Login.        |
| **Test Steps**      | 1. Tap field "Username" 2. Input `bob@example.com` 3. Tap field "Password" 4. Input `10203040` 5. Tap tombol "Login" |
| **Expected Result** | User berhasil masuk dan diarahkan ke halaman "Products", ditandai dengan munculnya elemen daftar produk.             |

### TC_002 – Tambah Produk ke Keranjang

| Field               | Detail                                                                                                           |
| ------------------- | ---------------------------------------------------------------------------------------------------------------- |
| **Test Case ID**    | TC_002                                                                                                           |
| **Title**           | Verifikasi user dapat menambahkan produk ke cart dan jumlah cart bertambah                                       |
| **Pre-conditions**  | 1. User sudah berhasil login (TC_001 PASS). 2. Halaman "Products" sedang ditampilkan.                            |
| **Test Steps**      | 1. Pilih produk pertama ("Sauce Labs Backpack") 2. Tap tombol "Add to Cart" 3. Tap icon Cart di pojok kanan atas |
| **Expected Result** | 1. Badge/counter pada icon cart berubah menjadi "1". 2. Halaman Cart menampilkan produk yang sudah ditambahkan.  |

---

## ⚠️ Catatan Penting

Sebelum benar-benar dijalankan, pastikan tiga hal ini sudah dilakukan:

1. **Validasi Locator via Appium Inspector** — Semua `accessibilityId` dan `resource-id`
   di `LoginPage.java` dan `ProductsPage.java` adalah referensi umum. Wajib divalidasi
   menggunakan Appium Inspector karena bisa berbeda tergantung versi APK.

2. **Cek appPackage & appActivity** — Jalankan command berikut untuk memverifikasi:

   ```bash
   aapt dump badging app/MyDemoApp.apk | grep package
   aapt dump badging app/MyDemoApp.apk | grep launchable-activity
   ```

3. **Urutan Startup** — Pastikan Appium Server sudah berjalan dan HP Android Anda sudah terhubung (USB debugging aktif) **sebelum** menjalankan `mvn clean test`.

---

## 👤 Author

| Field           | Detail                   |
| --------------- | ------------------------ |
| **Nama**        | [Nama Kamu]              |
| **NIM**         | [NIM Kamu]               |
| **Mata Kuliah** | Kualitas Perangkat Lunak |
| **Semester**    | [Semester]               |
