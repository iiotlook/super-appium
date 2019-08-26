package com.virjar.superappium.lazy.view;

import android.view.View;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class IdGetter implements ValueGetter<String> {
    @Override
    public String get(ViewModel viewModel) {
        View originView = viewModel.getOriginView();
        int id = originView.getId();
        if (id <= 0) {
            return null;
        }
        return originView.getResources().getResourceName(id);
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.id;
    }
}
