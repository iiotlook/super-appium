package com.virjar.superappium.lazy.view;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class ContentDescriptionValueGetter implements ValueGetter<String> {

    @Override
    public String get(ViewModel viewModel) {
        CharSequence contentDescription = viewModel.getOriginView().getContentDescription();
        if (contentDescription == null) {
            return null;
        }
        return contentDescription.toString();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.contentDescription;
    }
}
