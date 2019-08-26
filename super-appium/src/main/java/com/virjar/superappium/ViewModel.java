package com.virjar.superappium;

import android.view.View;
import android.view.ViewGroup;

import com.virjar.superappium.lazy.ValueGetter;
import com.virjar.superappium.lazy.ValueGetters;
import com.virjar.superappium.util.Constants;

import java.util.Collection;
import java.util.Map;

public class ViewModel {
    private View originView;
    private Map<String, ValueGetter> attributes;
    private ViewModel parent = null;
    private ValueGetter<String> type;
    private int indexOfParent = -1;

    public ViewModel(View originView) {
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

    private ViewModel[] children;

    public ViewModel childAt(int index) {
        if (childCount() < 0) {
            throw new IllegalStateException("can not parse child node for none ViewGroup object!!");
        }
        if (children == null) {
            children = new ViewModel[childCount()];
        }
        ViewModel viewModel = children[index];
        if (viewModel != null) {
            return viewModel;
        }
        ViewGroup viewGroup = (ViewGroup) originView;
        viewModel = new ViewModel(viewGroup.getChildAt(index));
        viewModel.parent = this;
        viewModel.indexOfParent = index;
        children[index] = viewModel;
        return viewModel;
    }

    public Integer index() {
        return indexOfParent;
    }


}
