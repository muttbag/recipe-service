package co.uk.recipe.group.service;

import co.uk.recipe.group.aws.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    S3Client s3Client;

    @Override
    public void uploadImageToBucket(MultipartFile multipartFile) {

//        multipartFile.get

    }
}
