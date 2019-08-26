package com.virjar.superappium.xpath.model;

import com.virjar.superappium.ViewModel;

public class XNode {
    public enum NodeType {
        NODE, TEXT
    }

    private ViewModel element;
    private boolean isText;
    private String textVal;

    public ViewModel getElement() {
        return element;
    }

    public XNode setElement(ViewModel element) {
        this.element = element;
        return this;
    }

    public String getTextVal() {
        return textVal;
    }

    public XNode setTextVal(String textVal) {
        this.textVal = textVal;
        return this;
    }

    public boolean isText() {
        return isText;
    }

    public XNode setText(boolean text) {
        isText = text;
        return this;
    }

    public static XNode e(ViewModel viewModel) {
        XNode xNode = new XNode();
        xNode.setElement(viewModel).setText(false);
        return xNode;
    }

    public static XNode t(String text) {
        XNode xNode = new XNode();
        xNode.setTextVal(text).setText(true);
        return xNode;
    }


    @Override
    public String toString() {
        if (isText) {
            return textVal;
        } else {
            return element.toString();
        }
    }
}
