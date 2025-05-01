package com.example.documentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.documentservice.model.Document;
import com.example.documentservice.model.User;

/**
 * Repository สำหรับจัดการข้อมูลเอกสาร
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

    // ค้นหาเอกสารทั้งหมดของผู้ใช้คนหนึ่ง
    List<Document> findByOwner(User owner);

    // ค้นหาด้วย title (สำหรับบริการค้นหา)
    List<Document> findByTitleContainingIgnoreCase(String keyword);
}

