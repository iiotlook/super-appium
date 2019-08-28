package com.virjar.superappium.xmodel.view;

import android.content.Context;
import android.view.View;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xmodel.ValueGetter;
import com.virjar.superappium.util.Constants;
import com.virjar.superappium.util.ReflectUtil;

public class PackageNameValueGetter implements ValueGetter<String> {

    @Override
    public String get(ViewImage viewImage) {
        View originView = viewImage.getOriginView();
        Context context = ReflectUtil.getFieldValue(originView, "mContext");
        return context.getPackageName();
    }

    @Override
    public boolean support(Class type) {
        return true;
    }

    @Override
    public String attr() {
        return Constants.packageName;
    }
}
