package com.virjar.superappium.xpath.function.axis;

import com.virjar.superappium.ViewImage;

import java.util.List;

public class DescendantOrSelfFunction implements AxisFunction {
    @Override
    public List<ViewImage> call(ViewImage e, List<String> args) {
        List<ViewImage> rs = e.getAllElements();
        rs.add(e);
        return rs;
    }

    @Override
    public String getName() {
        return "descendantOrSelf";
    }
}
