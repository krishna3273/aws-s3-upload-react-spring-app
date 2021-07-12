package com.example.awsimageupload.profile;

import com.example.awsimageupload.filestore.FileStore;
import com.example.awsimageupload.bucket.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private  final FileStore fileStore;
    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserImage(UUID userProfileId, MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file ["+file.getSize()+"]");
        }
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException(String.format("File Must Be An Image [%s]",file.getContentType()));
        }

        UserProfile user = getUserProfileOrThrow(userProfileId);

        Map<String,String> metadata=new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));


        String path=String.format("%s/%s",BucketName.PROFILE_IMAGE.getBucketName(),user.getUserId());
        String filename=String.format("%s-%s",file.getOriginalFilename(),UUID.randomUUID());
        System.out.println(filename);
        try {
            fileStore.save(path,filename,Optional.of(metadata),file.getInputStream());
            user.setUserImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User Profile %s not found", userProfileId)));
    }

    public byte[] downloadUserImage(UUID userProfileId) {
        UserProfile user=getUserProfileOrThrow(userProfileId);
        String path=String.format("%s/%s",BucketName.PROFILE_IMAGE.getBucketName(),user.getUserId());
        return user.getUserImageLink().map(link->fileStore.download(path,link)).orElse(new byte[0]);
    }
}
