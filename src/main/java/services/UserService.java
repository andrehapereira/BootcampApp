package services;


import model.User;

public interface UserService extends Service {
     boolean autenthicate(String username, String password);
     void addUser(User user);
     User findByName(String username);
     int count();
}
