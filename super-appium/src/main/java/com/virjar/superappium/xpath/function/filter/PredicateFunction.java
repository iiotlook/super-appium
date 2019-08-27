package com.virjar.superappium.xpath.function.filter;

import android.util.Log;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.util.BooleanUtils;
import com.virjar.superappium.util.Constants;
import com.virjar.superappium.util.StringUtils;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;
import com.virjar.superappium.xpath.util.XpathUtil;

import java.util.List;

public class PredicateFunction implements FilterFunction {
    @Override
    public Object call(ViewImage element, List<SyntaxNode> params) {
        if (element == null) {
            return false;
        }
        Object ret = params.get(0).calc(element);
        if (ret == null) {
            return false;
        }

        if (ret instanceof Number) {
            int i = ((Number) ret).intValue();
            return element.index() == i;
        }

        if (ret instanceof Boolean) {
            return ret;
        }

        if (ret instanceof CharSequence) {
            String s = ret.toString();
            Boolean booleanValue = BooleanUtils.toBooleanObject(s);
            if (booleanValue != null) {
                return booleanValue;
            }
            return StringUtils.isNotBlank(s);
        }

        //log.warn("can not recognize predicate expression calc result:" + ret);
        Log.w(Constants.TAG, "can not recognize predicate expression calc result:" + ret);
        return false;
    }

    @Override
    public String getName() {
        return "inner_predicate";
    }
}
