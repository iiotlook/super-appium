package com.virjar.superappium.xpath.function.filter;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.util.BooleanUtils;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.util.List;

public class BooleanFunction implements FilterFunction {
    @Override
    public Object call(ViewImage element, List<SyntaxNode> params) {
        if (params.size() == 0) {
            return false;
        }
        Object calc = params.get(0).calc(element);
        if (calc == null) {
            return false;
        }
        if (calc instanceof Boolean) {
            return calc;
        }
        if (calc instanceof String) {
            return BooleanUtils.toBoolean(calc.toString());
        }
        if (calc instanceof Integer) {
            return ((Integer) calc) != 0;
        }
        if (calc instanceof Number) {
            return ((Number) calc).doubleValue() > 0D;
        }
        return false;
    }

    @Override
    public String getName() {
        return "boolean";
    }
}

