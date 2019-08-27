package com.virjar.superappium.xpath.model;

import com.virjar.superappium.xpath.function.axis.AxisFunction;
import com.virjar.superappium.xpath.function.select.SelectFunction;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class XpathNode {
    public enum ScopeEm {
        INCHILREN("/"), // 默认只在子代中筛选,有轴时由轴定义筛选域
        RECURSIVE("//"), // 向下递归查找
        CUR("./"), // 当前节点下
        CURREC(".//"); // 当前节点向下递归
        private String val;

        ScopeEm(String type) {
            this.val = type;
        }

        public String val() {
            return this.val;
        }
    }

    /**
     * 查找方向,
     */
    @Getter
    @Setter
    private ScopeEm scopeEm;
    /**
     * 轴
     */
    @Getter
    @Setter
    private AxisFunction axis;

    @Getter
    @Setter
    private List<String> axisParams;

    /**
     * 谓语
     */
    @Getter
    @Setter
    private LinkedList<Predicate> predicates = new LinkedList<>();

    @Setter
    @Getter
    private SelectFunction selectFunction;

    @Getter
    @Setter
    private List<String> selectParams;
}
