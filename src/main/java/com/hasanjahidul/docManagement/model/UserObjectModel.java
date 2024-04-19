package com.hasanjahidul.docManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class UserObjectModel {
    private Long userId;
    private String name;
    private String username;
    private String email;
}
