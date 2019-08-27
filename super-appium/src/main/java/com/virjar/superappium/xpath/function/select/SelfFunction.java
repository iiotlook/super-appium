package com.virjar.superappium.xpath.function.select;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.ViewImages;
import com.virjar.superappium.xpath.model.XNode;
import com.virjar.superappium.xpath.model.XNodes;
import com.virjar.superappium.xpath.model.XpathNode;

import java.util.List;

public class SelfFunction implements SelectFunction {
    @Override
    public XNodes call(XpathNode.ScopeEm scopeEm, ViewImages elements, List<String> args) {
        XNodes xNodes = new XNodes();
        for (ViewImage viewImage : elements) {
            xNodes.add(XNode.e(viewImage));
        }
        return xNodes;
    }

    @Override
    public String getName() {
        return "self";
    }
}
