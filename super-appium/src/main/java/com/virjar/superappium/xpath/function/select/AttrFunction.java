package com.virjar.superappium.xpath.function.select;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.util.StringUtils;
import com.virjar.superappium.xpath.model.XNode;

import java.util.List;

public class AttrFunction extends AttrBaseFunction {

    @Override
    public void handle(boolean allAttr, String attrKey, ViewImage element, List<XNode> ret) {
        if (allAttr) {
            ret.add(XNode.t(element.attributes()));
        } else {
            Object value = element.attribute(attrKey);
            if (value == null) {
                return;
            }
            String str = value.toString();

            if (StringUtils.isNotBlank(str)) {
                ret.add(XNode.t(str));
            }
        }
    }

    @Override
    public String getName() {
        return "@";
    }
}
