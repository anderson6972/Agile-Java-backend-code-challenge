package com.primeit.agilejava.adapter.in.rest.dto;

import com.primeit.agilejava.models.UserData;

import java.util.List;
import java.util.Map;

public class UserTreeDTO {
    private Map<String, Map<String, Map<String, List<UserData>>>> usersTree;

    public UserTreeDTO(Map<String, Map<String, Map<String, List<UserData>>>> usersTree) {
        this.usersTree = usersTree;
    }

    public Map<String, Map<String, Map<String, List<UserData>>>> getUsersTree() {
        return usersTree;
    }

    public void setUsersTree(Map<String, Map<String, Map<String, List<UserData>>>> usersTree) {
        this.usersTree = usersTree;
    }
}

