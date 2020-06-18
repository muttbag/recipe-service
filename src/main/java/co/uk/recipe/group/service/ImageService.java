package co.uk.recipe.group.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    void uploadImageToBucket(MultipartFile multipartFile, boolean enablePublicReadAccess);

}
