package kz.meirman.shop.services.impl;

import kz.meirman.shop.entity.Product;
import kz.meirman.shop.repositories.ProductRepository;
import kz.meirman.shop.services.FileService;
import kz.meirman.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public boolean uploadPhoto(MultipartFile file, Long id) {
        try {
            byte bytes[] = file.getBytes();
            String fileName = "photo" + id;
            String filePath = "build/resources/main/static/" + fileName + ".jpg";
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            Product product = productRepository.findAllById(id);
            product.setPicture(fileName);
            productRepository.save(product);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
