package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadFromFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        avatarService.downloadAvatar(id, response);
    }

    @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadFromDb(@PathVariable Long id) {
        return avatarService.downloadFromDb(id);
    }

    @GetMapping
    public Page<Avatar> getWithPageable(@RequestParam Integer page, @RequestParam Integer count) throws IOException{
        return avatarService.getWithPageable(page, count);
    }

}
