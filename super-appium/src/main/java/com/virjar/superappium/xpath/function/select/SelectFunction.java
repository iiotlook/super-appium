package com.virjar.superappium.xpath.function.select;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.ViewImages;
import com.virjar.superappium.xpath.function.NameAware;
import com.virjar.superappium.xpath.model.XNode;
import com.virjar.superappium.xpath.model.XNodes;
import com.virjar.superappium.xpath.model.XpathNode;

import java.util.List;

public interface SelectFunction extends NameAware {
   XNodes call(XpathNode.ScopeEm scopeEm, ViewImages elements, List<String> args);
}
