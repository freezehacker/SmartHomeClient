package view;

/**
 * Created by JK.
 *
 * 全局的通信接口
 * 有一定的解耦作用
 */
public interface IReceiver {

    void receive(String message);
}
