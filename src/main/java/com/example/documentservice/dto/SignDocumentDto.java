package com.example.documentservice.dto;

/**
 * DTO สำหรับการส่งหรือรับเอกสารเพื่อให้ลงนามหรือแสดงความเห็น
 */
public class SignDocumentDto {

    private Long senderId;     // ผู้ส่งเอกสาร (เช่น B)
    private Long receiverId;   // ผู้ลงนาม (เช่น A)
    private Long documentId;
    private String comment;    // ความเห็น / ลายเซ็น / ข้อความ

    // Constructors
    public SignDocumentDto() {
    }

    public SignDocumentDto(Long senderId, Long receiverId, Long documentId, String comment) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.documentId = documentId;
        this.comment = comment;
    }

    // Getters
    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public String getComment() {
        return comment;
    }

    // Setters
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // toString() method
    @Override
    public String toString() {
        return "SignDocumentDto{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", documentId=" + documentId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
