package com.virjar.superappium.lazy;

import com.virjar.superappium.ViewModel;

public interface ValueGetter<T> {
    T get(ViewModel viewModel);

    boolean support(Class type);

    String attr();
}
