package utils;

import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

/**
 * Created by JK.
 * <p>
 * 验证身份
 * 單例模式
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

    /* 檢測身份 */
    public void check(String id, RFIDResult result) {
        try {
            Integer iid = Integer.valueOf(id);
            if (userRepository.exist(iid)) {
                result.onSuccess("刷卡成功!");
            } else {
                result.onFailure("刷卡失敗，非戶主操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.onFailure("刷卡失敗，請檢查標籤ID");
        }
    }

    /*
     *   門禁模塊的回調。
     *   開門或拒絕的操作可以寫在這裡
     */
    public interface RFIDResult {
        void onSuccess(String info);

        void onFailure(String report);
    }
}
