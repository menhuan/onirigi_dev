package com.manage.model;

import org.springframework.stereotype.Component;

/**
 * 检查在线人数
 * Created by ASUS on 2017/8/29.
 */
@Component
public class HostHolder {

    private static ThreadLocal<UserBean> users = new ThreadLocal<>();

    /**
     * 得到用户
     *
     * @return
     */
    public UserBean getUser() {
        return users.get();
    }


    public void setUsers(UserBean user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }

}
