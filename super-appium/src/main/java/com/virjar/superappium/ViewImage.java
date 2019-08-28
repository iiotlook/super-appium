package com.virjar.superappium;

import android.view.View;
import android.view.ViewGroup;

import com.virjar.superappium.xmodel.LazyValueGetter;
import com.virjar.superappium.xmodel.ValueGetters;
import com.virjar.superappium.traversor.Collector;
import com.virjar.superappium.traversor.Evaluator;
import com.virjar.superappium.util.Constants;
import com.virjar.superappium.util.Lists;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ViewImage {
    private View originView;
    private Map<String, LazyValueGetter> attributes;
    private ViewImage parent = null;
    private LazyValueGetter<String> type;
    private LazyValueGetter<String> text;
    private int indexOfParent = -1;

    public ViewImage(View originView) {
        this.originView = originView;
        attributes = ValueGetters.valueGetters(this);
        type = attrName(Constants.className);
        text = attrName(Constants.text);
    }

    public String getType() {
        return type.get();
    }

    public String getText() {
        return text.get();
    }

    @SuppressWarnings("unchecked")
    private <T> LazyValueGetter<T> attrName(String attrName) {
        return attributes.get(attrName);
    }

    public Collection<String> attributeKeys() {
        return attributes.keySet();
    }

    public Object attribute(String key) {
        LazyValueGetter valueGetter = attributes.get(key);
        if (valueGetter == null) {
            return null;
        }
        return valueGetter.get();
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


    public List<ViewImage> parents() {
        List<ViewImage> ret = Lists.newArrayList();
        ViewImage parent = this.parent;
        while (parent != null) {
            ret.add(parent);
            parent = parent.parent;
        }
        return ret;
    }

    public List<ViewImage> children() {
        if (childCount() < 0) {
            return Lists.newArrayList();
        }
        List<ViewImage> ret = new ArrayList<>(childCount());
        for (int i = 0; i < theChildCount; i++) {
            ret.add(childAt(i));
        }
        return ret;
    }

    public ViewImages getAllElements() {
        return Collector.collect(new Evaluator.AllElements(), this);
    }

    public ViewImage parentNode() {
        return parent;
    }

    public ViewImage nextSibling() {
        if (parent == null) {
            //root
            return null;
        }
        int nextSiblingIndex = indexOfParent + 1;
        if (parent.childCount() > nextSiblingIndex) {
            return parent.childAt(nextSiblingIndex);
        }
        return null;
    }

    public ViewImage previousSibling() {
        if (parent == null) {
            //root
            return null;
        }
        int nextSiblingIndex = indexOfParent - 1;
        if (nextSiblingIndex < 0) {
            return null;
        }
        return parent.childAt(nextSiblingIndex);
    }

    public ViewImages siblings() {
        if (parent == null) {
            return new ViewImages();
        }
        int parentChildren = parent.childCount();
        ViewImages viewImages = new ViewImages(parentChildren - 1);
        for (int i = 0; i < parentChildren; i++) {
            ViewImage viewImage = parent.childAt(i);
            if (viewImage == this) {
                continue;
            }
            viewImages.add(viewImage);
        }
        return viewImages;
    }

    public String attributes() {
        JSONObject jsonObject = new JSONObject();
        for (String key : attributeKeys()) {
            try {
                jsonObject.put(key, attribute(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }
}
