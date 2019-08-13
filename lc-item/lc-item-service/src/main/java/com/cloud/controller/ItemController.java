package com.cloud.controller;

import com.cloud.item.pojo.Item;
import com.cloud.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
@Api("商品服务接口")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @ApiOperation(value = "获取商品列表", notes = "获取商品列表")
    @GetMapping("list")
    public ResponseEntity<List<Item>> queryItemList(){
        List<Item> list = itemService.queryItemList();
        if(CollectionUtils.isEmpty(list)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
