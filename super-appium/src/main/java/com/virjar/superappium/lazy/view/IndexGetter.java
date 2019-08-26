package com.virjar.superappium.lazy.view;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class IndexGetter implements ValueGetter<Integer> {
    @Override
    public Integer get(ViewModel viewModel) {
        return viewModel.index();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.index;
    }
}
