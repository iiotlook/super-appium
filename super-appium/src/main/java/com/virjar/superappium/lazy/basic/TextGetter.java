package com.virjar.superappium.lazy.basic;

import android.widget.TextView;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.util.Constants;

public class TextGetter implements ValueGetter<String> {
    @Override
    public String get(ViewModel viewModel) {
        TextView textView = (TextView) viewModel.getOriginView();
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
