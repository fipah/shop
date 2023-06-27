package kz.meirman.shop.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean uploadPhoto(MultipartFile file, Long id);
}
