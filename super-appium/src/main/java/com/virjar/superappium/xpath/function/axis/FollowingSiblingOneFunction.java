package com.virjar.superappium.xpath.function.axis;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.ViewImages;

import java.util.List;

public class FollowingSiblingOneFunction implements AxisFunction {
    @Override
    public ViewImages call(ViewImage e, List<String> args) {
        ViewImages rs = new ViewImages();
        if (e.nextSibling() != null) {
            rs.add(e.nextSibling());
        }
        return rs;
    }

    @Override
    public String getName() {
        return "following-sibling-one";
    }
}
