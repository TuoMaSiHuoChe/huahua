package huahua.common;

import lombok.*;

/**
 * @author admin
 * @Auther: admin
 * @Date: 2019/4/10 16:05
 * @Description:
 */
@Data
public class Result {
    /**
     * 返回状态
     */
    private boolean flag;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 多参构造器
     * @param flag
     * @param code
     * @param message
     * @param data
     */
    public Result(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    /**
     * 无参构造器
     */
    public Result() {
    }
    /**
     * 多参构造器
     * @param flag
     * @param code
     * @param message
     */
    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
