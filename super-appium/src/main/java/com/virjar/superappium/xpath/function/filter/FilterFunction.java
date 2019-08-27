package com.virjar.superappium.xpath.function.filter;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.function.NameAware;
import com.virjar.superappium.xpath.parser.expression.SyntaxNode;

import java.util.List;

public interface FilterFunction extends NameAware {
    Object call(ViewImage viewImage, List<SyntaxNode> params);
}
