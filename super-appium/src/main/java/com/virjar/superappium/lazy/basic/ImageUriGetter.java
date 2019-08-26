package com.virjar.superappium.lazy.basic;

import android.widget.ImageView;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;
import com.virjar.superappium.util.ReflectUtil;

public class ImageUriGetter implements ValueGetter {
    @Override
    public Object get(ViewModel viewModel) {
        return ReflectUtil.getFieldValue(viewModel.getOriginView(), "mUri");
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
