package top.ball.rice.hospital.service;

import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ParamUtils {

    public static Sort.Order toOrder(String sortStr) {
        String[] strs = sortStr.split(",");
        Assert.isTrue(strs.length == 1 || strs.length == 2, "排序字符串格式正确 : `" + sortStr + "`");
        Assert.isTrue(StringUtils.hasText(strs[0]), "没有指定要排序的字段");

        Sort.Direction dir = Sort.Direction.ASC;
        if (strs.length == 2 && StringUtils.hasText(strs[1])) {
            dir = Sort.Direction.fromString(strs[1]);
        }

        return new Sort.Order(dir, strs[0]);
    }


    public static Sort toSort(String... sortStrs) {

        List<Sort.Order> orders = Arrays.stream(sortStrs)
                .filter(sortStr -> !StringUtils.isEmpty(sortStr))
                .map(sortStr -> toOrder(sortStr))
                .collect(Collectors.toList());

        if (orders.size() == 0) {
            return null;
        }
        return new Sort(orders);

    }

    public static Sort toSort(List<String> sortStrs) {

        List<Sort.Order> orders = sortStrs.stream()
                .filter(sortStr -> !StringUtils.isEmpty(sortStr))
                .map(sortStr -> toOrder(sortStr))
                .collect(Collectors.toList());

        if (orders.size() == 0) {
            return null;
        }
        return new Sort(orders);

    }

}
