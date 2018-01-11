package top.ball.rice.hospital.server.common;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * 参考 spring-data 中的 Page，方便 Jackson 实例化。
 */
public class UniPageReq<T> {

    @QueryParam("size")
    @DefaultValue("10")
    private int size;

    @QueryParam("page")
    @DefaultValue("0")
    private int page;

    @QueryParam("sort")
    private List<String> sort;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }
}
