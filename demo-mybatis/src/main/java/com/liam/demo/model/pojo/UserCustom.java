package com.liam.demo.model.pojo;

import java.util.List;

public class UserCustom extends User{

    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
