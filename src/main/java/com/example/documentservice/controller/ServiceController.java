package com.example.documentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.documentservice.dto.EditRequestDto;
import com.example.documentservice.dto.ServiceResponseDto;
import com.example.documentservice.dto.SignDocumentDto;
import com.example.documentservice.service.EditService;
import com.example.documentservice.service.SignatureService;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private EditService editService;

    /**
     * บริการที่ 2: ลงนามเอกสาร (B ส่งคำขอลงนามให้ A)
     */
    @PostMapping("/sign")
    public ServiceResponseDto signDocument(@RequestBody SignDocumentDto dto) {
        return signatureService.signDocument(dto);
    }

    /**
     * บริการที่ 4: ร้องขอให้แก้ไขเอกสาร (B ขอให้ A แก้ไขเอกสาร)
     */
    @PostMapping("/edit")
    public ServiceResponseDto requestEdit(@RequestBody EditRequestDto dto) {
        return editService.requestEdit(dto);
    }
}

