package com.cloud.service.impl;

import com.cloud.item.pojo.Item;
import com.cloud.mapper.ItemMapper;
import com.cloud.service.ItemService;
import com.cloud.service.RedisService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<Item> queryItemList() {
        //先从缓存中获取商品数据
        List<Item> list = (List<Item>) redisService.get("item");
        if(!CollectionUtils.isEmpty(list)){
            //返回缓存中的数据
            return list;
        }
        //没有数据则获取数据库中的数据
        List<Item> itemList = itemMapper.selectAll();
        //将数据添加到缓存中
        redisService.set("item",itemList);
        return itemList;
    }

    /**
     * 封装发送到消息队列的方法
     *
     * @param id
     * @param type
     */
    private void sendMessage(Long id, String type) {
        try {
            amqpTemplate.convertAndSend("item." + type, id);
        } catch (Exception e) {
            log.error("{}商品消息发送异常，商品ID：{}", type, id, e);
        }
    }
}
