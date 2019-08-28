package com.virjar.superappium.xmodel.basic;

import android.widget.ImageView;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xmodel.ValueGetter;
import com.virjar.superappium.util.Constants;
import com.virjar.superappium.util.ReflectUtil;

public class ImageUriGetter implements ValueGetter {
    @Override
    public Object get(ViewImage viewImage) {
        return ReflectUtil.getFieldValue(viewImage.getOriginView(), "mUri");
    }

    @Override
    public String attr() {
        return Constants.mUri;
    }

    @Override
    public boolean support(Class type) {
        return ImageView.class.isAssignableFrom(type);
    }
}
