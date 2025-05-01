package com.example.documentservice.dto;

/**
 * DTO สำหรับร้องขอให้แก้ไขข้อมูลในเอกสารที่ระบุ
 */
public class EditRequestDto {

    private Long requesterId;   // ผู้ขอให้แก้ไข (เช่น B)
    private Long editorId;      // ผู้ที่จะทำการแก้ไข (เช่น A)
    private Long documentId;
    private String editCommand; // รายละเอียดสิ่งที่ต้องการให้แก้ไข เช่น "เปลี่ยนชื่อผู้รับ"

    // Constructors
    public EditRequestDto() {
    }

    public EditRequestDto(Long requesterId, Long editorId, Long documentId, String editCommand) {
        this.requesterId = requesterId;
        this.editorId = editorId;
        this.documentId = documentId;
        this.editCommand = editCommand;
    }

    // Getters
    public Long getRequesterId() {
        return requesterId;
    }

    public Long getEditorId() {
        return editorId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public String getEditCommand() {
        return editCommand;
    }

    // Setters
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public void setEditorId(Long editorId) {
        this.editorId = editorId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public void setEditCommand(String editCommand) {
        this.editCommand = editCommand;
    }

    // toString() method
    @Override
    public String toString() {
        return "EditRequestDto{" +
                "requesterId=" + requesterId +
                ", editorId=" + editorId +
                ", documentId=" + documentId +
                ", editCommand='" + editCommand + '\'' +
                '}';
    }
}
