package com.virjar.superappium.xmodel.basic;

import android.widget.TextView;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xmodel.ValueGetter;
import com.virjar.superappium.util.Constants;

public class TextGetter implements ValueGetter<String> {
    @Override
    public String get(ViewImage viewImage) {
        TextView textView = (TextView) viewImage.getOriginView();
        CharSequence text = textView.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    @Override
    public boolean support(Class type) {
        return TextView.class.isAssignableFrom(type);
    }

    @Override
    public String attr() {
        return Constants.text;
    }
}
