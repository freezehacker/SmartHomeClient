package repository.impl;

import bean.PO.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import repository.UserRepository;
import utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JK.
 */
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void insert(User user) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "INSERT INTO `User` VALUES(?,?,?,?)";
        try {
            qr.update(sql, new Object[]{ user.getU_id(), user.getU_name(), user.getU_gender(), user.getU_phone() });
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(User user) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "UPDATE `User` " +
                "SET `u_name`=?, `u_gender`=?, `u_phone`=? " +
                "WHERE `u_id`=?";
        try {
            qr.update(sql, new Object[]{ user.getU_name(), user.getU_gender(), user.getU_phone(), user.getU_id() });
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int id) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "DELETE FROM `User` " +
                "WHERE `u_id`=?";
        try {
            qr.update(sql, id);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAll() {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "DELETE FROM `User`";
        try {
            qr.update(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public User get(int id) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM `User` " +
                "WHERE `u_id`=?";
        try {
            return (User)qr.query(sql, new BeanHandler(User.class), id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<User> list() {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM `User`";
        try {
            return qr.query(sql, resultSet -> {
                List<User> ret = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setU_id(resultSet.getInt("u_id"));
                    user.setU_gender(resultSet.getString("u_gender"));
                    user.setU_name(resultSet.getString("u_name"));
                    user.setU_phone(resultSet.getString("u_phone"));
                    ret.add(user);
                }
                return ret;
            });
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean exist(int id) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM `User` WHERE `u_id`=?";
        try {
            Number n = (Number)qr.query(sql, new ScalarHandler<Integer>(1), id);
            return n.intValue() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
