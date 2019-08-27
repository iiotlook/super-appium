package com.virjar.superappium.xpath.function.select;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.function.NameAware;
import com.virjar.superappium.xpath.model.XNode;
import com.virjar.superappium.xpath.model.XpathNode;

import java.util.List;

public interface SelectFunction extends NameAware {
    List<XNode> call(XpathNode.ScopeEm scopeEm, List<ViewImage> elements, List<String> args);
}
