package com.virjar.superappium.xpath.function.axis;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.ViewImages;

import java.util.List;

public class ParentFunction implements AxisFunction {
    @Override
    public ViewImages call(ViewImage e, List<String> args) {
        return new ViewImages(e.parentNode());
    }

    @Override
    public String getName() {
        return "parent";
    }
}
