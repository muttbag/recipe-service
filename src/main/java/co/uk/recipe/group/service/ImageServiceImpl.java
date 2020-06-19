package co.uk.recipe.group.service;

import co.uk.recipe.group.aws.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    S3Client s3Client;

    @Override
    public String uploadImageToBucket(MultipartFile multipartFile) throws IOException {

        //TODO: Deal with exception that is a method signiture right now
        //TODO: Uploaded File being created on server, limit file size.
        //TODO: Validation that it is an image being uploaded
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        final String imageUrl = s3Client.uploadPublicFile(file);

        file.delete();

        return imageUrl;
    }
}
