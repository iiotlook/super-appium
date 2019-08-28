package com.virjar.superappium.xmodel.view;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xmodel.ValueGetter;
import com.virjar.superappium.util.Constants;

public class LongClickableValueGetter implements ValueGetter<Boolean> {

    @Override
    public Boolean get(ViewImage viewImage) {
        return viewImage.getOriginView().isLongClickable();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.longClickable;
    }
}
