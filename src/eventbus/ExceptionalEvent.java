package eventbus;

import bean.VO.ExceptionRecord;

/**
 * Created by JK.
 * 异常事件
 */
public class ExceptionalEvent {

    private ExceptionRecord exceptionRecord;    // 包含一个异常记录

    public ExceptionRecord getExceptionRecord() {
        return exceptionRecord;
    }

    public void setExceptionRecord(ExceptionRecord exceptionRecord) {
        this.exceptionRecord = exceptionRecord;
    }
}
