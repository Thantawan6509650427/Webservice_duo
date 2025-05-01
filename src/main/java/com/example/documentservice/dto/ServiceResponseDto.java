package com.example.documentservice.dto;

import java.time.LocalDateTime;

/**
 * DTO สำหรับตอบกลับผลลัพธ์ของแต่ละบริการ
 */
public class ServiceResponseDto {

    private String status;       // "success" | "failed"
    private String message;      // รายละเอียด เช่น "Document shared", "Permission denied"
    private Object data;         // ข้อมูลเพิ่มเติม เช่น รายชื่อเอกสาร, ข้อความ LLM summary
    private final LocalDateTime timestamp;

    // Constructors
    public ServiceResponseDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ServiceResponseDto(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters (except timestamp which is final)
    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
