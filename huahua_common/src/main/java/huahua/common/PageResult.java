package huahua.common;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 * @Date: 2019/4/10 16:24
 * @Description: 分页工具类
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
}
