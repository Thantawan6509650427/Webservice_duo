package com.example.documentservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.documentservice.dto.ServiceResponseDto;
import com.example.documentservice.dto.SignDocumentDto;
import com.example.documentservice.model.Document;
import com.example.documentservice.model.ServiceRequest;
import com.example.documentservice.model.ServiceStatus;
import com.example.documentservice.model.ServiceType;
import com.example.documentservice.model.User;
import com.example.documentservice.repository.DocumentRepository;
import com.example.documentservice.repository.ServiceRequestRepository;
import com.example.documentservice.repository.UserRepository;
//บริการที่ 2: การลงนามเอกสาร
@Service
public class SignatureService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ServiceRequestRepository requestRepository;

    public ServiceResponseDto signDocument(SignDocumentDto dto) {
        Optional<User> sender = userRepository.findById(dto.getSenderId()); // ใช้ getter
        Optional<User> receiver = userRepository.findById(dto.getReceiverId()); // ใช้ getter
        Optional<Document> document = documentRepository.findById(dto.getDocumentId()); // ใช้ getter

        if (sender.isEmpty() || receiver.isEmpty() || document.isEmpty()) {
            return new ServiceResponseDto("failed", "Invalid data", null);
        }

        if (dto.getComment() == null || dto.getComment().isBlank()) { // ใช้ getter
            return new ServiceResponseDto("failed", "Signature missing", null);
        }

        ServiceRequest request = new ServiceRequest();
        request.setRequester(receiver.get());
        request.setProvider(sender.get());
        request.setDocument(document.get());
        request.setServiceType(ServiceType.SIGNATURE);
        request.setStatus(ServiceStatus.APPROVED);

        // อัปเดตสถานะของเอกสาร
        Document signedDocument = document.get();
        signedDocument.setStatus("SIGNED");
        documentRepository.save(signedDocument);


        requestRepository.save(request);

        return new ServiceResponseDto("success", "Document signed", dto.getComment()); // ใช้ getter
    }
}