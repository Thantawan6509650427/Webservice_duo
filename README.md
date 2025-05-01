
# 📄 Project Duo - Document Service System

ระบบจำลองการส่งคำขอลงนามและแก้ไขเอกสารระหว่าง 2 ฝั่ง:  
- 🅰️ **Document Service (ฝั่ง A)**: ฝั่งรับคำขอ ลงนาม แก้ไข และเก็บข้อมูล  
- 🅱️ **Client Service (ฝั่ง B)**: ฝั่งส่งคำขอเพื่อให้ A ดำเนินการ

---

## 📁 โครงสร้างโปรเจกต์

```
src/
 └─ main/
     ├─ java/
     │   └─ com.example.documentservice/
     │       ├─ controller/
     │       │   └─ ServiceController.java         ← เรียก API เช่น ส่งคำขอลงนาม, ขอแก้ไข
     │
     │       ├─ dto/
     │       │   ├─ SignDocumentDto.java           ← B ส่งคำขอลงนามให้ A
     │       │   ├─ EditRequestDto.java            ← B ขอให้ A แก้ไขเอกสาร
     │       │   └─ ServiceResponseDto.java        ← รับผลลัพธ์การดำเนินการทั้งหมด
     │
     │       ├─ service/
     │       │   ├─ SignatureService.java          ← B ใช้ส่งคำขอลงนามให้ A
     │       │   └─ EditService.java               ← B ขอให้ A แก้ไขเอกสาร
     │
     │       ├─ model/
     │       │   ├─ Document.java                  ← ใช้เวลาจัดการข้อมูลเอกสาร
     │       │   ├─ ServiceRequest.java            ← บันทึกคำร้องบริการ
     │       │   ├─ ServiceStatus.java             ← สถานะของคำร้อง เช่น REQUESTED, APPROVED
     │       │   ├─ ServiceType.java               ← ประเภทบริการ เช่น ACCESS, SIGNATURE
     │       │   └─ User.java                      ← ใช้เก็บข้อมูลของ A และ B
     │
     │       ├─ repository/
     │       │   ├─ DocumentRepository.java        ← ค้นหา/ดึงเอกสาร
     │       │   └─ ServiceRequestRepository.java  ← เก็บ/เรียกคำร้อง
     │
     └─ resources/
         ├─ application.properties                 ← ตั้งค่า database, H2 console
         └─ data.sql                               ← เติมข้อมูลเบื้องต้น เช่น User A, B และ Document
 └─ postman/   
```

---

## ⚙️ ติดตั้งและรันระบบ

### ✅ ความต้องการ

- Java JDK 17 หรือ 21  
- Apache Maven  
- Postman

---

## 🅰️ ฝั่ง A - Document Service

### 📌 ติดตั้ง

```bash
cd A/67-2_CS367_assignment1_6509650369
mvn clean install
```

### ▶️ รันระบบ

```bash
mvn spring-boot:run
```

> รันที่: `http://localhost:8081`

> หากต้องการเปลี่ยนพอร์ต: แก้ไฟล์ `src/main/resources/application.properties`  
```properties
server.port=8081
```

---

## 🅱️ ฝั่ง B - Client Service

### 📌 ติดตั้ง

```bash
cd B/67-2_CS367_assignment1_650965xxxx
mvn clean install
```

### ▶️ รันระบบ

```bash
mvn spring-boot:run
```

> รันที่: `http://localhost:8082`

> หากต้องการเปลี่ยนพอร์ต: แก้ไฟล์ `src/main/resources/application.properties`  
```properties
server.port=8082
```

---

## 📬 ทดสอบ API ด้วย Postman

### 1. เปิด Postman
### 2. ไปที่ `Import` > เลือกไฟล์ Postman ด้านล่าง:

| ไฟล์ | ใช้สำหรับ |
|------|------------|
| `postman/A-requests.postman_collection.json` | ทดสอบ API ฝั่ง A |
| `postman/B-requests.postman_collection.json` | ทดสอบ API ฝั่ง B |

### 3. ตัวอย่าง Endpoint:

#### ✅ ฝั่ง B ส่งคำขอไป A:
- `POST /api/send-sign-request`
- `POST /api/send-edit-request`

#### ✅ ฝั่ง A รับคำขอ:
- `POST /api/services/sign`
- `POST /api/services/edit`
- `GET /api/documents/{id}`

---

## 🧪 ตรวจสอบข้อมูล

เปิดเบราว์เซอร์ไปที่:
```
http://localhost:8081/h2-console
```

- JDBC URL: ใช้ค่าจาก `application.properties` เช่น:
```
jdbc:h2:mem:testdb
```

- Username/Password: ดูจากไฟล์ properties เช่น:
```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

---

## 🛠 Troubleshooting

| ปัญหา | สาเหตุ | วิธีแก้ |
|-------|--------|--------|
| `release version 21 not supported` | Java ที่ติดตั้งต่ำกว่า 21 | เปลี่ยนเป็น Java 17 หรือแก้ใน `pom.xml` เป็น `<java.version>17</java.version>` |
| `No plugin found for prefix 'spring-boot'` | รันคำสั่ง Maven จากโฟลเดอร์ผิด | ตรวจสอบว่าอยู่ในโฟลเดอร์ที่มี `pom.xml` |
| `Connection refused` | อีกฝั่งยังไม่รันระบบ | ตรวจสอบให้แน่ใจว่า A และ B รันพร้อมกัน |

---

## 📌 ผู้พัฒนา

- 6509650369 - ณรีพัฒน์ รุ่งรำพรรณ
- 6509650427 - ทานตะวัน จิตสาร
