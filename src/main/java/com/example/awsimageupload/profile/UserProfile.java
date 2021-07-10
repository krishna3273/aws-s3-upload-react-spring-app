package com.example.awsimageupload.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {
    private UUID userId;
    private String username;
    private String userImageLink;

    public UserProfile(UUID userId, String username, String userImageLink) {
        this.userId = userId;
        this.username = username;
        this.userImageLink = userImageLink;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getUserImageLink() {
        return Optional.ofNullable(userImageLink);
    }

    public void setUserImageLink(String userImageLink) {
        this.userImageLink = userImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userId,that.userId) &&
                Objects.equals(username,that.username) &&
                Objects.equals(userImageLink,that.userImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userImageLink);
    }
}
