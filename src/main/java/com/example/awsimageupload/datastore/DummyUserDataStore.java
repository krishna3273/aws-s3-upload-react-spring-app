package com.example.awsimageupload.datastore;

import com.example.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class DummyUserDataStore {
    private static  final List<UserProfile> userProfiles=new ArrayList<>();

    static {
        userProfiles.add(new UserProfile(UUID.randomUUID(),"krishna",null));
        userProfiles.add(new UserProfile(UUID.randomUUID(),"mahesh",null));
        userProfiles.add(new UserProfile(UUID.randomUUID(),"teja",null));
    }

    public  List<UserProfile> getUserProfiles() {
        return userProfiles;
    }
}
