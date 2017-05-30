package utils;

import bean.PO.User;
import bean.VO.ExceptionRecord;
import eventbus.ConsoleEvent;
import eventbus.EventBus;
import eventbus.ExceptionalEvent;
import eventbus.OpenDoorEvent;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

/**
 * Created by JK.
 * <p>
 * RFID模块的实例
 * 单例模式
 */
public class RFIDHelper {

    private UserRepository userRepository = new UserRepositoryImpl();

    private static RFIDHelper instance = null;

    private RFIDHelper() {
    }

    public static RFIDHelper getInstance() {
        if (instance == null) {
            instance = new RFIDHelper();
        }
        return instance;
    }

    /* 检测身份 */
    public void check(String id, RFIDResult result) {
        try {
            Integer iid = Integer.valueOf(id);
            if (userRepository.exist(iid)) {
                result.onSuccess("刷卡成功！");
            } else {
                result.onFailure("刷卡失败(非戶主)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.onFailure("刷卡失败，请检查RFID标签");
        }
    }

    public void onCheck(String rfidId) {
        try {
            Integer id = Integer.valueOf(rfidId);
            if (userRepository.exist(id)) {

                /* 开门 */

                User user = userRepository.get(id);
                EventBus.getDefault().post(new OpenDoorEvent(TimeUtils.getCurrentDateTimeString(), rfidId));
                EventBus.getDefault().post(new ConsoleEvent(user.getU_name() + "(id:" + user.getU_id() + ")刷卡进门"
                        , TimeUtils.getCurrentTimeString()));

                //System.out.println("开门：" + rfidId);
            } else {

                /* 生成异常事件 */

                ExceptionRecord record = new ExceptionRecord();
                record.setEr_date(TimeUtils.getCurrentDateTimeString());
                record.setEr_cause("有非户主（" + rfidId + "）进行刷卡");

                ExceptionalEvent exceptionalEvent = new ExceptionalEvent();
                exceptionalEvent.setExceptionRecord(record);
                EventBus.getDefault().post(exceptionalEvent);

                //System.out.println("不开门：" + rfidId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     *   开门（拒绝开门）的回调函数
     */
    public interface RFIDResult {
        void onSuccess(String info);

        void onFailure(String report);
    }
}
