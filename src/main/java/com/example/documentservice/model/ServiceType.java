package com.example.documentservice.model;

public enum ServiceType {
    ACCESS,      // บริการ 1: การเข้าถึงเอกสาร
    SIGNATURE,   // บริการ 2: ลงนามเอกสาร
    SEARCH,      // บริการ 3: ค้นหาเอกสาร
    EDIT,        // บริการ 4: แก้ไขเอกสาร
    LLM_SUMMARY  // บริการ 5: สรุปเอกสารด้วย LLM
}
