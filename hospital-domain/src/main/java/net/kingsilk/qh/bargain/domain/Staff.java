package net.kingsilk.qh.bargain.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 员工表
 */
public class Staff extends User {
    /**
     * 是否已经禁用。
     * <p>
     * true - 已经禁用。
     */
    private boolean disabled;

    /**
     * 权限列表。
     * 暂时定义为有子活动的权限集合，有该子活动，就有权使用该子活动
     */
    private Set<String> authorities = new HashSet<>();

    /**
     * 备注
     */
    private String memo;


    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
