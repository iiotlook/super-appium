package com.virjar.superappium.lazy.view;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class ClassNameGetter implements ValueGetter<String> {

    @Override
    public String get(ViewImage viewImage) {
        return viewImage.getOriginView().getClass().getName();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.className;
    }
}
