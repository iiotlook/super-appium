package com.virjar.superappium.xmodel;

import android.view.View;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xmodel.basic.HintGetter;
import com.virjar.superappium.xmodel.basic.ImageUriGetter;
import com.virjar.superappium.xmodel.basic.TextGetter;
import com.virjar.superappium.xmodel.view.ClassNameGetter;
import com.virjar.superappium.xmodel.view.ClickableValueGetter;
import com.virjar.superappium.xmodel.view.ContentDescriptionValueGetter;
import com.virjar.superappium.xmodel.view.EnabledValueGetter;
import com.virjar.superappium.xmodel.view.FocusableValueGetter;
import com.virjar.superappium.xmodel.view.IdGetter;
import com.virjar.superappium.xmodel.view.IndexGetter;
import com.virjar.superappium.xmodel.view.LongClickableValueGetter;
import com.virjar.superappium.xmodel.view.PackageNameValueGetter;
import com.virjar.superappium.xmodel.view.SelectedValueGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueGetters {
    private static List<ValueGetter> valueGetters = new ArrayList<>();

    static {
        registerViewGetter();
        registerBasic();
    }

    public static void registerValueGetter(ValueGetter valueGetter) {
        valueGetters.add(valueGetter);
    }

    private static void registerViewGetter() {
        valueGetters.add(new ClassNameGetter());
        valueGetters.add(new ClickableValueGetter());
        valueGetters.add(new ContentDescriptionValueGetter());
        valueGetters.add(new EnabledValueGetter());
        valueGetters.add(new FocusableValueGetter());
        valueGetters.add(new LongClickableValueGetter());
        valueGetters.add(new PackageNameValueGetter());
        valueGetters.add(new SelectedValueGetter());
        valueGetters.add(new IdGetter());
        valueGetters.add(new IndexGetter());
    }

    private static void registerBasic() {
        valueGetters.add(new TextGetter());
        valueGetters.add(new HintGetter());
        valueGetters.add(new ImageUriGetter());
    }

    private static Map<Class<? extends View>, Map<String, ValueGetter>> cache = new HashMap<>();

    public static Map<String, LazyValueGetter> valueGetters(ViewImage viewImage) {
        Class<? extends View> aClass = viewImage.getOriginView().getClass();
        boolean saveCache = false;
        if (aClass.getClassLoader().equals(View.class.getClassLoader())) {
            saveCache = true;
        }
        return valueGetters(viewImage, saveCache);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, LazyValueGetter> valueGetters(ViewImage viewImage, boolean saveCache) {
        Class<? extends View> theClass = viewImage.getOriginView().getClass();
        Map<String, ValueGetter> rule;
        if (cache.containsKey(theClass)) {
            rule = cache.get(theClass);
        } else {
            // calculate
            rule = new HashMap<>();
            for (ValueGetter valueGetter : valueGetters) {
                if (valueGetter.support(theClass)) {
                    rule.put(valueGetter.attr(), valueGetter);
                }
            }
            if (saveCache) {
                cache.put(theClass, rule);
            }
        }

//        //avoid reload value data
        Map<String, LazyValueGetter> ret = new HashMap<>();
        for (Map.Entry<String, ValueGetter> entry : rule.entrySet()) {
            ret.put(entry.getKey(), new LazyValueGetter(entry.getValue(), viewImage));
        }
        return ret;
    }


}
