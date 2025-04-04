
# 📊 Real-Time Heart Rate Data Visualization Web App with Movesense Sensor

This project is a web application that connects via **Bluetooth Low Energy (BLE)** to a **Movesense heart rate sensor**. It enables **real-time acquisition and visualization** of biometric data directly in the browser using modern web technologies.

---

## 📌 Project Overview

The goal of this project is to demonstrate the integration of **Movesense**, a physiological sensor device, into a **Web Bluetooth API**-based application. The web app acquires and plots **heart rate (BPM)** using **Chart.js** in real time. The project is part of a bachelor thesis.

---

## 🚀 Features

- 🔍 Discover and connect to Movesense BLE devices
- ❤️ Live BPM (beats per minute) reading
- 📤 Disconnect functionality
- 🧠 Data filtering for invalid or noisy signals
- ⚠️ Anomaly detection with user notification for abnormal heart rates
- 💾 Data persistence via external database for long-term storage and analysis

---

## 🛠️ Technologies Used

- **HTML5 / CSS3 / JavaScript / Java / Maven / Docker / MySQL**
- [**Chart.js**](https://www.chartjs.org/) for dynamic chart rendering
- **Web Bluetooth API** for BLE communication (browser-side)
- No server-side components or database required

---

## 🔗 BLE Integration Details

This application connects to the **Heart Rate Service** (`0000180d-0000-1000-8000-00805f9b34fb`) on the Movesense device.

### 📚 Services and Characteristics

- **Service:** `Heart Rate`  
  UUID: `0000180d-0000-1000-8000-00805f9b34fb`

- **Characteristic (Read access):**  
  `Aerobic Heart Rate Lower Limit`  
  UUID: `00002a7e-0000-1000-8000-00805f9b34fb`  
  *(used to manually request heart rate samples without using notifications)*

> ⚠️ **Note:** Many standard characteristics such as `heart_rate_measurement` rely on notifications which are not supported by every sensor/browser. This implementation leverages readable characteristics to support broader compatibility.

---

## 🔒 Limitations

- ❗ Web Bluetooth API is not supported in all browsers
- 📱 BLE communication may be unstable across different operating systems.
- 🔌 Works only over **HTTPS or localhost**.
- 🧪 Not certified for medical use – intended for educational/demo purposes

---

## 🧪 How to Run

1. Clone or download this repository
2. **To run the project locally:**
   - Execute the `MovesenseWebApplication` Java class to start the server.
   - Open `dashboard.html` in a **Chrome browser** (preferably Android or Desktop).
   - The server will start on the default port, but **MySQL** must also be running on its port to store data.
   - **Enable HTTPS locally** by generating self-signed certificates and SSH keys for local secure communication.
3. Click on `Discover Devices`.
4. Select a **Movesense device** (e.g., name starts with "Movesense").
5. Begin viewing BPM data in real time!

Alternatively, the project is deployed and available at:  
[https://movesenseapp.onrender.com/dashboard.html](https://movesenseapp.onrender.com/dashboard.html)


---


## 📄 License

This project is licensed under the MIT License.

---

## 👤 Author

**Francesco Giuri** – *Bachelor Degree Thesis*  
Thesis on the integration of the **Movesense sensor** in a **web-based Android-compatible application** to acquire and visualize **heart rate data** using **Bluetooth Low Energy (BLE)** technology.
