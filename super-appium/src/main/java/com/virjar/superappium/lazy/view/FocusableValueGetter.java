package com.virjar.superappium.lazy.view;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class FocusableValueGetter implements ValueGetter<Boolean> {

    @Override
    public Boolean get(ViewModel viewModel) {
        return viewModel.getOriginView().isFocusable();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.focusable;
    }
}
