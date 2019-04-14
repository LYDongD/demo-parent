package com.liam.demo.model.pojo;

import java.util.List;

public class OrderCustom extends Order{

    private User user;

    private List<OrderDetail> orderDetailList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
