package repository.impl;

import bean.PO.User;
import bean.VO.ExceptionRecord;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import repository.ExpRecordRepository;
import utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JK.
 */
public class ExpRecordRepositoryImpl implements ExpRecordRepository {

    @Override
    public void insert(ExceptionRecord exceptionRecord) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "INSERT INTO `ExceptionRecord` VALUES(?,?,?,?)";
        try {
            qr.update(sql, new Object[]{ exceptionRecord.getEr_username(),
                    exceptionRecord.getEr_device(),
                    exceptionRecord.getEr_cause(),
                    exceptionRecord.getEr_date() });
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<ExceptionRecord> list() {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM `ExceptionRecord`";
        try {
            return (List<ExceptionRecord>)qr.query(sql, new BeanListHandler(ExceptionRecord.class));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteAll() {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "DELETE FROM `ExceptionRecord`";
        try {
            qr.update(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    @Override
    public List<ExceptionRecord> queryByDay(String day) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM `ExceptionRecord` " +
                "WHERE `er_date` LIKE ?";
        try {
            return qr.query(sql, new ResultSetHandler<List<ExceptionRecord>>() {
                @Override
                public List<ExceptionRecord> handle(ResultSet resultSet) throws SQLException {
                    List<ExceptionRecord> ret = new ArrayList<>();

                    while (resultSet.next()) {
                        ExceptionRecord er = new ExceptionRecord();
                        er.setEr_username(resultSet.getString("er_username"));
                        er.setEr_device(resultSet.getString("er_device"));
                        er.setEr_cause(resultSet.getString("er_cause"));
                        er.setEr_date(resultSet.getString("er_date"));
                        ret.add(er);
                    }

                    return ret;
                }
            }, day + "%");
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteByDay(String day) {
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        String sql = "DELETE FROM `ExceptionRecord` " +
                "WHERE `er_date` LIKE ?";
        try {
            qr.update(sql,
                    day + "%");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
