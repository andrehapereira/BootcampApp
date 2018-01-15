package services;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class MockUserService implements UserService {
    private Map<String, User> userList = new HashMap<>();
    private String name = "userservice";
    private JDBCUserService jdbcService = (JDBCUserService)ServiceRegistry.getInstance().getService("jdbcservice");

    @Override
    public boolean autenthicate(String username, String password) {
        if (username.equals("") || username.matches("\\s+") || password.equals("") || password.matches("\\s+")){
            return false;
        }
        User user = findByName(username);
        if (user == null) {
            return false;
        }
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        if (user.getUsername().equals("") || user.getUsername().matches("\\s+") || user.getPassword().equals("") || user.getPassword().matches("\\s+")){
            return;
        }
        if(findByName(user.getUsername()) == null) {
            jdbcService.addUserDB(user);
            //userList.put(user.getUsername(),user);
        }
    }

    @Override
    public User findByName(String username) {
        System.out.println(jdbcService);
        return jdbcService.findUserDB(username);
    }

    @Override
    public int count() {
        return jdbcService.userCountDB();
    }

    public Map<String, User> getUserList() {
        return userList;
    }

    public String getName() {
        return name;
    }
}
