package com.virjar.superappium.xpath.model;

import java.util.LinkedList;
import java.util.List;

public class XNodes extends LinkedList<XNode> {

    public XNodes(List<XNode> handleNode) {
        super(handleNode);
    }

    public XNodes(XNode e) {
        add(e);
    }
}
