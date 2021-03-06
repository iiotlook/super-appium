package com.virjar.superappium.xpath.function.filter;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.util.List;

public class LowerCaseFunction extends AbstractStringFunction {
    @Override
    public Object call(ViewImage element, List<SyntaxNode> params) {
        return firstParamToString(element, params).toLowerCase();
    }

    @Override
    public String getName() {
        return "lower-case";
    }
}
