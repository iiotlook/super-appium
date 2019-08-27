package com.virjar.superappium.xpath.util;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.exception.EvaluateException;
import com.virjar.superappium.xpath.model.XNode;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class XpathUtil {
    public static List<ViewImage> transformToElement(List<XNode> input) {
        List<ViewImage> ret = new LinkedList<>();
        for (XNode xNode : input) {
            if (!xNode.isText()) {
                ret.add(xNode.getElement());
            }
        }
        return ret;
    }

    public static String toPlainString(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    public static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        if (number instanceof Integer) {
            return new BigDecimal(number.intValue());
        }
        if (number instanceof Double || number instanceof Float) {// BigDecimal float 也是转double
            return new BigDecimal(number.doubleValue());
        }
        if (number instanceof Long) {
            return BigDecimal.valueOf(number.longValue());
        }
        return new BigDecimal(number.toString());
    }

    public static Integer firstParamToInt(List<SyntaxNode> params, ViewImage element, String functionName) {
        if (params.size() > 0) {
            Object calc = params.get(0).calc(element);
            if (calc != null && !(calc instanceof Integer)) {
                throw new EvaluateException(functionName + " parameter must be integer");
            } else {
                if (calc != null) {
                    return (Integer) calc;
                }
            }
        }
        return null;
    }
}
