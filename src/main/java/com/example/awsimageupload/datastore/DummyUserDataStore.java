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
        userProfiles.add(new UserProfile(UUID.fromString("18ca46d2-2df6-4264-ac29-b08851af57d0"),"krishna",null));
        userProfiles.add(new UserProfile(UUID.fromString("fc8c83f8-5b8e-4e08-91a0-d21db43f76da"),"mahesh",null));
        userProfiles.add(new UserProfile(UUID.fromString("c02af2fb-e248-4843-b6c7-173eab32769e"),"teja",null));
    }

    public  List<UserProfile> getUserProfiles() {
        return userProfiles;
    }
}
