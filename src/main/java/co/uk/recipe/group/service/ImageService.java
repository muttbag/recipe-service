package co.uk.recipe.group.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImageToBucket(MultipartFile multipartFile, boolean enablePublicReadAccess);

}
