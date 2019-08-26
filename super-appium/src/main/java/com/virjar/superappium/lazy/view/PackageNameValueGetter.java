package com.virjar.superappium.lazy.view;

import android.content.Context;
import android.view.View;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;
import com.virjar.superappium.util.ReflectUtil;

public class PackageNameValueGetter implements ValueGetter<String> {

    @Override
    public String get(ViewModel viewModel) {
        View originView = viewModel.getOriginView();
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
