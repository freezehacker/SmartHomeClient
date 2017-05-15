package repository;

import bean.VO.ExceptionRecord;

import java.util.List;

/**
 * Created by JK.
 */
public interface ExpRecordRepository {

    void insert(ExceptionRecord exceptionRecord);

    List<ExceptionRecord> list();

    void deleteAll();


    List<ExceptionRecord> queryByDay(String day);

    void deleteByDay(String day);
}
