package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.aws;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IImageService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.ImageService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Getter
@Setter
public class S3ImageServiceImp {
    private final static Logger logger = Logger.getLogger(S3ImageServiceImp.class);

    @Autowired
    private AWSS3Service awsService;

    @Autowired
    private ImageService imageService;

    private String bucketName = "bucket-grupo6-c1";

    //upload object
    public String putImage(MultipartFile file, Long productId) throws Exception {
        File newfile = convertMultiPartToFile(file);
        logger.info("Uploading object to S3" + bucketName);
        String urlImage =
                awsService.putObject(
                        bucketName,
                        "img/productImg/" + productId + "/" +System.currentTimeMillis()+newfile.getName(),
                        newfile
                );
        try {
            ImageDto imageDto = new ImageDto("product " + productId.toString(), urlImage, new ProductDto(productId));
            imageService.save(imageDto);
            logger.info("iamge saved in db " + urlImage);
        } catch (Exception e) {
            logger.error(e);
        }
        return urlImage;
    }


    public List<String> putAllImages(List<MultipartFile> files, Long productId) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                File newfile = convertMultiPartToFile(file);
                urls.add(awsService.putObject(
                        bucketName,
                        //concatenate productId and file name to avoid have files with same name and date
                        "img/productImg/" + productId + "/" +System.currentTimeMillis()+newfile.getName(),
                        newfile
                ));

            } catch (Exception e) {
                logger.error("error saving images Dto: " + e);
            }
        }
        try {
            for (String url : urls) {
                ImageDto imageDto = new ImageDto("product " + productId.toString(), url, new ProductDto(productId));
                imageService.save(imageDto);
            }
        } catch (Exception e) {
            logger.error("error saving images Dto: " + e);

        }
        return urls;
    }


    // -- method to convert MultiPartFile to File -- //
    public File convertMultiPartToFile(MultipartFile multipart) throws Exception {
        File convFile = new File(Objects.requireNonNull(Objects.requireNonNull(multipart.getOriginalFilename()).replace(" ", "-")));
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart.getBytes());
        fos.close();
        return convFile;
    }


}
