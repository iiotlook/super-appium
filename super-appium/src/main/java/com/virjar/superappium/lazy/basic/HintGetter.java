package com.virjar.superappium.lazy.basic;

import android.widget.TextView;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class HintGetter implements ValueGetter {
    @Override
    public Object get(ViewImage viewImage) {
        TextView textView = (TextView) viewImage.getOriginView();
        CharSequence hint = textView.getHint();
        if (hint == null) {
            return null;
        }
        return hint.toString();
    }

    @Override
    public String attr() {
        return Constants.hint;
    }

    @Override
    public boolean support(Class type) {
        return TextView.class.isAssignableFrom(type);
    }
}
