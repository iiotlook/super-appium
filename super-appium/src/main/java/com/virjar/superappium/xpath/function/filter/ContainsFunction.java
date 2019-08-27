package com.virjar.superappium.xpath.function.filter;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.util.List;

public class ContainsFunction implements FilterFunction {
    @Override
    public Object call(ViewImage element, List<SyntaxNode> params) {
        //  Preconditions.checkArgument(params.size() >= 2, "contains need 2 params");
        return params.get(0).calc(element).toString().contains(params.get(1).calc(element).toString());
    }

    @Override
    public String getName() {
        return "contains";
    }
}
