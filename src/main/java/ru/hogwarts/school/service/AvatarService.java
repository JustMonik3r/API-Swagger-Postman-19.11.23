package ru.hogwarts.school.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;
    String getExtensions(String fileName);
    void downloadAvatar(Long id, HttpServletResponse response) throws IOException;
    ResponseEntity<byte[]> downloadFromDb(Long id);
}
