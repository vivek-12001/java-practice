package Group;

import User.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupController {

    List<Group> groupList;

    public GroupController() {
        groupList = new ArrayList<>();
    }

    public void addGroup(String groupId, String groupName, List<User> user) {
        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setGroupMembers(user);
        groupList.add(group);
    }

    public Group getGroup(String groupId) {
        for (Group group : groupList) {
            if (group.getGroupId().equals(groupId)) {
                return group;
            }
        }
        return null;
    }
}
