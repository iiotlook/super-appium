package com.virjar.superappium.xpath.function.filter;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.util.List;

public class LastFunction implements FilterFunction {
    @Override
    public Object call(ViewImage element, List<SyntaxNode> params) {
        // return XpathUtil.getElIndexInSameTags(element) == XpathUtil.sameTagElNums(element);
        return element.index() == element.parentNode().childCount() - 1;
    }

    @Override
    public String getName() {
        return "last";
    }
}
