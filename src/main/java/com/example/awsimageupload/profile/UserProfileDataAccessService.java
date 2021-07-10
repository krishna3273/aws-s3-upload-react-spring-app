package com.example.awsimageupload.profile;

import com.example.awsimageupload.datastore.DummyUserDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {
    
    private final DummyUserDataStore dummyUserDataStore;

    @Autowired
    public UserProfileDataAccessService(DummyUserDataStore dummyUserDataStore) {
        this.dummyUserDataStore = dummyUserDataStore;
    }

    List<UserProfile> getUserProfiles(){
        return dummyUserDataStore.getUserProfiles();
    }
}

