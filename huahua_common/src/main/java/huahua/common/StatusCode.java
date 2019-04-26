package huahua.common;

import lombok.Data;

/**
 * @author admin
 *
 * @Date: 2019/4/10 16:26
 * @Description:
 */
@Data
public class StatusCode {
    /**
     * 成功
     */
    public static final int OK = 20000;
    /**
     *  失败
     */
    public static final int ERROR = 20001;
    /**
     *  用户名或密码错误
     */
    public static final int LOGINERROR = 20002;
    /**
     *  权限不
     */
    public static final int ACCESSERROR = 20003;
    /**
     *   远程调用失败
     */
    public static final int REMOTEERROR = 20004;
    /**
     *    重复操
     */
   public static final int REPERROR = 20005;

}
