# Dad Jokes App 🤣

Aplikasi ini adalah project mobile sederhana yang menampilkan kumpulan dad jokes dari server backend. Aplikasi dikembangkan dengan pembagian tugas antara backend, frontend, dan UI/UX designer.

---

## 📱 Fitur Aplikasi
- Menampilkan 1 dad joke random saat aplikasi dibuka.
- Refresh untuk mendapatkan joke baru.
- Menyimpan jokes favorit.
- Melihat history jokes yang sudah pernah dibuka.

---

## 🧩 Storyboard Aplikasi

### 1. UI/UX Designer
- Mendesain tampilan sederhana dan user friendly.
- Mengutamakan navigasi cepat: Home ➔ Detail Joke ➔ Favorite List.
- Warna dominan putih dan biru muda untuk kesan fun.
- Tombol **"Get New Joke"** besar di tengah layar.
- Ikon hati ❤️ untuk menyimpan favorit.

**Storyboard Alur UI/UX:**
---

### 2. Frontend Developer
- Mengimplementasikan desain dari UI/UX.
- Mengambil jokes dari API backend menggunakan HTTP request.
- Menampilkan joke baru setiap kali user klik tombol refresh.
- Menyimpan favorit jokes di local storage.
- Membuat halaman history dan favorites.

**Storyboard Alur Frontend:**
---

### 3. Backend Developer
- Menyediakan REST API untuk frontend.
- Menyediakan endpoint:
  - `GET /jokes/random` ➔ Mengirimkan 1 joke random.
  - `POST /favorites` ➔ Menyimpan joke favorit user.
- Database menyimpan koleksi jokes dan daftar favorit.

**Storyboard Alur Backend:**
---

## ⚙️ Teknologi yang Digunakan
- **Frontend**: Android Studio (Kotlin)
- **Backend**: Node.js + Express
- **Database**: MongoDB
- **Design**: Figma untuk UI/UX prototyping

---

## 📋 SCRUM Board
Proses pengembangan aplikasi mengikuti metode SCRUM, dengan task dibagi dalam kolom **To Do**, **In Progress**, dan **Done**.

Link board dapat dilihat di:
👉 [Jira Board - Dad Jokes App](https://briansembiring11-1744454109243.atlassian.net/jira/software/projects/PM/boards/1)

---

## 🧑‍💻 Pembagian Tugas
- **UI/UX Designer**: Mendesain layout aplikasi dan user flow.
- **Frontend Developer**: Implementasi tampilan dan interaksi aplikasi.
- **Backend Developer**: Menyiapkan server API dan database.

---

## 📋 Hasil SCRUM
Dokumentasi pengerjaan proyek menggunakan metode SCRUM dapat dilihat di Trello atau Jira berikut:

👉 [Jira Board - PM Project](https://briansembiring11-1744454109243.atlassian.net/jira/software/projects/PM/boards/1)
