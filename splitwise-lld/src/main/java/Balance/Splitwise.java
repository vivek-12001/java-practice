package Balance;

import Expense.ExpenseSplitType;
import Expense.Split;
import Group.Group;
import Group.GroupController;
import User.User;
import User.UserController;

import java.util.ArrayList;
import java.util.List;

public class Splitwise {

    UserController userController;
    GroupController groupController;
    BalanceSheetController balanceSheetController;

    public Splitwise() {
        userController = new UserController();
        groupController = new GroupController();
        balanceSheetController = new BalanceSheetController();
    }

    public void demo() {

        addGroupToApp();

        runTestCase1();
        runTestCase2();
    }

    private void runTestCase2() {
        Group group = groupController.getGroup("G001");

        List<Split> splitList = new ArrayList<>();
        Split split1 = new Split(userController.getUser("001"), 300);
        Split split2 = new Split(userController.getUser("002"), 300);
        Split split3 = new Split(userController.getUser("003"), 300);

        splitList.add(split1);
        splitList.add(split2);
        splitList.add(split3);

        group.createExpense("E001", "Breakfast", 900, userController.getUser("002"),
                ExpenseSplitType.EQUAL, splitList);

        for (Split split : splitList) {
            balanceSheetController.showBalanceSheetOfUser(split.getUser());
        }
    }

    private void runTestCase1() {
        Group group = groupController.getGroup("G001");

        List<Split> splitList = new ArrayList<>();
        Split split1 = new Split(userController.getUser("001"), 100);
        Split split2 = new Split(userController.getUser("002"), 500);
        Split split3 = new Split(userController.getUser("003"), 300);

        splitList.add(split1);
        splitList.add(split2);
        splitList.add(split3);

        group.createExpense("E001", "Breakfast", 900, userController.getUser("001"),
                ExpenseSplitType.UNEQUAL, splitList);

        for (Split split : splitList) {
            balanceSheetController.showBalanceSheetOfUser(split.getUser());
        }
    }

    public void addGroupToApp() {

        addUsersToApp();

        List<User> userList = new ArrayList<>();
        User user = userController.getUser("001");
        userList.add(user);
        groupController.addGroup("G001", "group1", userList);
        groupController.addGroup("G002", "group2", userList);
    }

    public void addUsersToApp() {

        User user = new User("001", "vivek");
        User user1 = new User("002", "pranav");
        User user2 = new User("003", "chandan");
        User user3 = new User("004", "sahil");

        userController.addUser(user);
        userController.addUser(user1);
        userController.addUser(user2);
        userController.addUser(user3);
    }
}
