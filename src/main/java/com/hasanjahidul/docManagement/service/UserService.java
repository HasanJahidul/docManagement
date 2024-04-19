package com.hasanjahidul.docManagement.service;

import com.hasanjahidul.docManagement.config.UserContextHolder;
import com.hasanjahidul.docManagement.entity.User;
import com.hasanjahidul.docManagement.model.UserObjectModel;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void setUserInfo(User entity){
        try{
            UserObjectModel object = new UserObjectModel();
            object.setUserId(entity.getId());
            object.setUsername(entity.getUserName());
            object.setEmail(entity.getEmail());
            UserContextHolder.setUserDetails(object);
        }catch (Exception e){
            System.out.println("Error setting user to ThreadLocal UserContextHolder -> "+e.getMessage());
        }

    }
}
