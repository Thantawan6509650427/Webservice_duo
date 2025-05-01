package com.example.documentservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.documentservice.dto.EditRequestDto;
import com.example.documentservice.dto.ServiceResponseDto;
import com.example.documentservice.model.Document;
import com.example.documentservice.model.ServiceRequest;
import com.example.documentservice.model.ServiceStatus;
import com.example.documentservice.model.ServiceType;
import com.example.documentservice.model.User;
import com.example.documentservice.repository.DocumentRepository;
import com.example.documentservice.repository.ServiceRequestRepository;
import com.example.documentservice.repository.UserRepository;

/**
 * บริการสำหรับจัดการการแก้ไขเอกสาร
 * - ตรวจสอบสิทธิ์การแก้ไข
 * - ดำเนินการแก้ไขเอกสาร
 * - บันทึกประวัติการแก้ไข
 */
@Service
public class EditService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DocumentRepository documentRepository;
    
    @Autowired
    private ServiceRequestRepository requestRepository;

    /**
     * เมธอดสำหรับร้องขอแก้ไขเอกสาร
     * @param dto ข้อมูลการร้องขอแก้ไข ประกอบด้วย:
     *            - requesterId: ID ผู้ร้องขอ
     *            - editorId: ID ผู้แก้ไข
     *            - documentId: ID เอกสาร
     *            - editCommand: คำสั่งแก้ไข
     * @return ServiceResponseDto ผลลัพธ์การดำเนินการ
     */
    public ServiceResponseDto requestEdit(EditRequestDto dto) {
        // ตรวจสอบผู้แก้ไขและเอกสาร
        Optional<User> editor = userRepository.findById(dto.getEditorId()); // ใช้ getter
        Optional<Document> document = documentRepository.findById(dto.getDocumentId()); // ใช้ getter

        if (editor.isEmpty() || document.isEmpty()) {
            return new ServiceResponseDto("failed", "ไม่พบผู้แก้ไขหรือเอกสาร", null);
        }

        // ตรวจสอบสิทธิ์การแก้ไข
        if (!document.get().getOwner().getId().equals(dto.getEditorId())) { // ใช้ getter
            return new ServiceResponseDto("failed", "ไม่มีสิทธิ์แก้ไขเอกสาร", null);
        }

        // ดำเนินการแก้ไขเอกสาร
        String newContent = document.get().getContent() + "\n[แก้ไขเมื่อ: " + dto.getEditCommand() + "]"; // ใช้ getter
        document.get().setContent(newContent);
        documentRepository.save(document.get());

        // บันทึกประวัติการร้องขอ
        ServiceRequest request = new ServiceRequest();
        request.setRequester(userRepository.findById(dto.getRequesterId()).orElse(null)); // ใช้ getter
        request.setProvider(editor.get());
        request.setDocument(document.get());
        request.setServiceType(ServiceType.EDIT);
        request.setStatus(ServiceStatus.APPROVED);

        requestRepository.save(request);

        return new ServiceResponseDto("success", "แก้ไขเอกสารสำเร็จ", dto.getEditCommand()); // ใช้ getter
    }
}

