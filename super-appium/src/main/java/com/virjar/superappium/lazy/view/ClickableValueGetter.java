package com.virjar.superappium.lazy.view;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class ClickableValueGetter implements ValueGetter<Boolean> {

    @Override
    public Boolean get(ViewImage viewImage) {
        return viewImage.getOriginView().isClickable();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.clickable;
    }
}
