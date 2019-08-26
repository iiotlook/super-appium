package com.virjar.superappium;

import android.view.View;
import android.view.ViewGroup;

import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.lazy.ValueGetters;
import com.virjar.superappium.util.Constants;

import java.util.Collection;
import java.util.Map;

public class ViewImage {
    private View originView;
    private Map<String, ValueGetter> attributes;
    private ViewImage parent = null;
    private ValueGetter<String> type;
    private int indexOfParent = -1;

    public ViewImage(View originView) {
        this.originView = originView;
        attributes = ValueGetters.valueGetters(this);
        type = attrName(Constants.className);
    }


    @SuppressWarnings("unchecked")
    private <T> ValueGetter<T> attrName(String attrName) {
        return attributes.get(attrName);
    }

    public Collection<String> attributeKeys() {
        return attributes.keySet();
    }

    public Object attribute(String key) {
        ValueGetter valueGetter = attributes.get(key);
        if (valueGetter == null) {
            return null;
        }
        return valueGetter.get(this);
    }


    public View getOriginView() {
        return originView;
    }

    private Integer theChildCount = null;

    public int childCount() {
        if (theChildCount != null) {
            return theChildCount;
        }
        if (!(originView instanceof ViewGroup)) {
            return 0;
        }
        ViewGroup viewGroup = (ViewGroup) originView;
        theChildCount = viewGroup.getChildCount();
        return theChildCount;
    }

    private ViewImage[] children;

    public ViewImage childAt(int index) {
        if (childCount() < 0) {
            throw new IllegalStateException("can not parse child node for none ViewGroup object!!");
        }
        if (children == null) {
            children = new ViewImage[childCount()];
        }
        ViewImage viewImage = children[index];
        if (viewImage != null) {
            return viewImage;
        }
        ViewGroup viewGroup = (ViewGroup) originView;
        viewImage = new ViewImage(viewGroup.getChildAt(index));
        viewImage.parent = this;
        viewImage.indexOfParent = index;
        children[index] = viewImage;
        return viewImage;
    }

    public Integer index() {
        return indexOfParent;
    }


}
