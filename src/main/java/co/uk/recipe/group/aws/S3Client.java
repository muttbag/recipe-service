package co.uk.recipe.group.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class S3Client {

    @Autowired
    private String s3Bucket;

    private AmazonS3 amazonS3;

    @Autowired
    public S3Client(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider)
    {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getFirstRegionId()).build();
    }

    public String uploadPublicFile(final File file){

        //TODO: Logic around if the same image/image name already exists?
        //TODO: Image is set publicly avaible to everyone, correct user permissions so only we can access it
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Bucket, file.getName(), file);
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        final PutObjectResult result = amazonS3.putObject(putObjectRequest);

        //TODO: Constructing the image url based on success and filename, is it returned when created in anyway?
        //TODO: Logic for if the upload was successful then return url otherwise exception
        return "https://" + s3Bucket +".s3." + amazonS3.getRegion().toString() + ".amazonaws.com/" + file.getName();

    }
}
