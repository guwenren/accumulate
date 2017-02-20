package com.guwr.accumulate.test.datastructure.linklist;

import com.alibaba.fastjson.JSON;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.linklist.LinkNode
 * Date         2017/2/20
 * Time         14:42
 * Description  链表
 */
public class LinkNode {
    private Integer id;
    private LinkNode next;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
