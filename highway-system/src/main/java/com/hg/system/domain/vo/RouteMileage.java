package com.hg.system.domain.vo;

import java.util.List;

public class RouteMileage {
    private List<String> name;
    private List<String> value;

    public RouteMileage() {
    }

    public RouteMileage(List<String> name, List<String> value) {
        this.name = name;
        this.value = value;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RouteMileage{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}
