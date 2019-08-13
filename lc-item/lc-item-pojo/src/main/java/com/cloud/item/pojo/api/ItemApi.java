package com.cloud.item.pojo.api;

import com.cloud.item.pojo.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ItemApi {
    @GetMapping("list")
    ResponseEntity<List<Item>> queryItemList();
}
