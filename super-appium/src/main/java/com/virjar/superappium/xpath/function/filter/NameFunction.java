package com.virjar.superappium.xpath.function.filter;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.util.List;

public class NameFunction implements FilterFunction {
    @Override
    public Object call(ViewImage element, List<SyntaxNode> params) {
        return element.getType();
    }

    @Override
    public String getName() {
        return "name";
    }
}
