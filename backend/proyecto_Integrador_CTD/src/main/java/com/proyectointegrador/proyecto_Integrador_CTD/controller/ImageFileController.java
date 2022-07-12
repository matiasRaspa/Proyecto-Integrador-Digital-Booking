package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.exception.UploadFileException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.aws.S3ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping("/imageFile")
public class ImageFileController {

    @Autowired
    private S3ImageServiceImp s3TempService;


    @PostMapping("/up")
    public ResponseEntity<String> upAnObject(@RequestParam("file") MultipartFile file, @RequestParam("productId") Long productId) {

        try {
            String path = s3TempService.putImage(file, productId);
            return ResponseEntity.created(null).body(path);
        } catch (Exception e) {
            throw new UploadFileException("Error uploading file to S3", e);
        }
    }

    @PostMapping("/upAll")
    public ResponseEntity<List<String>> upObjects(@RequestParam("files") List<MultipartFile> files, @RequestParam("productId") Long productId) {

        try {
            List<String> paths = s3TempService.putAllImages(files, productId);
            return ResponseEntity.created(null).body(paths);
        } catch (Exception e) {
            throw new UploadFileException("Error uploading files to S3", e);
        }
    }

}

