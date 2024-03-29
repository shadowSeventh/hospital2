package top.ball.rice.hospital.server.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考 spring-data 中的 Page，方便 Jackson 实例化。
 */
public class UniPageResp<T> {

    private int totalPages;

    private long totalElements;

    private int number;

    private int size;

    private List<T> content = new ArrayList<>();

    private List<UniOrder> orders = new ArrayList<>();

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public List<UniOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UniOrder> orders) {
        this.orders = orders;
    }
}
