package com.virjar.superappium.xmodel;

import com.virjar.superappium.ViewImage;

public interface ValueGetter<T> {
    T get(ViewImage viewImage);

    boolean support(Class type);

    String attr();
}
