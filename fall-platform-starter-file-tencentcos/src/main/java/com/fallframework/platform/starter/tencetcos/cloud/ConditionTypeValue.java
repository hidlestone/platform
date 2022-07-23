package com.fallframework.platform.starter.tencetcos.cloud;

import java.util.LinkedList;
import java.util.List;

public class ConditionTypeValue {
    public String key;
    public List<String> valueList = new LinkedList<String>();

    public void setKey(String key) {
        this.key = key;
    }

    public void addValue(String value) {
        this.valueList.add(value);
    }
}
