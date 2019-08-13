package com.cloud.service;

import com.cloud.item.pojo.Item;

import java.util.List;

public interface ItemService {
    
    List<Item> queryItemList();

    void deleteItem(Long id);
}
