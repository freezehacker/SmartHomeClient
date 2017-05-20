package repository;

import bean.PO.User;

import java.util.List;

/**
 * Created by JK.
 */
public interface UserRepository {

    void insert(User user);

    void update(User user);

    void delete(int id);

    void deleteAll();

    User get(int id);

    List<User> list();

    boolean exist(int id);
}
