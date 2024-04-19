package com.hasanjahidul.docManagement.config;

import com.hasanjahidul.docManagement.model.UserObjectModel;

public class UserContextHolder {
    private static final ThreadLocal<UserObjectModel> userContext = new ThreadLocal<>();

    public static void setUserDetails(UserObjectModel userDetails) {
        userContext.set(userDetails);
    }

    public static UserObjectModel getUserDetails() {
        return userContext.get();
    }

    public static void clear() {
        userContext.remove();
    }
}
