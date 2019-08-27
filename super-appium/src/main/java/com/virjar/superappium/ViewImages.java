package com.virjar.superappium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewImages extends ArrayList<ViewImage> {
    public ViewImages(ViewImage... parentNodes) {
        addAll(Arrays.asList(parentNodes));
    }

    public ViewImages(List<ViewImage> tempList) {
        super(tempList);
    }

    public ViewImages(int initialCapacity) {
        super(initialCapacity);
    }
}
