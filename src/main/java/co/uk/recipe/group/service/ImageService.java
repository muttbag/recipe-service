package co.uk.recipe.group.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String uploadImageToBucket(MultipartFile multipartFile) throws IOException;

}
