package com.assignment.dailype.Requests;
import java.util.List;
import java.util.Map;

public class UpdateUserRequest {
    private List<String> userIds;
    private Map<String, Object> updateData;

    // Getters and setters
    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public Map<String, Object> getUpdateData() {
        return updateData;
    }

    public void setUpdateData(Map<String, Object> updateData) {
        this.updateData = updateData;
    }
}