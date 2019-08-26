package com.virjar.superappium.lazy;

import com.virjar.superappium.ViewImage;

public interface ValueGetter<T> {
    T get(ViewImage viewImage);

    boolean support(Class type);

    String attr();
}
