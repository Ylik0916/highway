package com.hg.system.domain.vo;

public class RouterType {
    private String name;
    private String value;

    public RouterType() {
    }

    public RouterType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RouterType{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
