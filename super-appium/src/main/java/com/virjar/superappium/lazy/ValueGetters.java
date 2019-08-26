package com.virjar.superappium.lazy;

import android.view.View;

import com.virjar.superappium.ViewModel;
import com.virjar.superappium.lazy.basic.HintGetter;
import com.virjar.superappium.lazy.basic.ImageUriGetter;
import com.virjar.superappium.lazy.basic.TextGetter;
import com.virjar.superappium.lazy.view.ClassNameGetter;
import com.virjar.superappium.lazy.view.ClickableValueGetter;
import com.virjar.superappium.lazy.view.ContentDescriptionValueGetter;
import com.virjar.superappium.lazy.view.EnabledValueGetter;
import com.virjar.superappium.lazy.view.FocusableValueGetter;
import com.virjar.superappium.lazy.view.IdGetter;
import com.virjar.superappium.lazy.view.IndexGetter;
import com.virjar.superappium.lazy.view.LongClickableValueGetter;
import com.virjar.superappium.lazy.view.PackageNameValueGetter;
import com.virjar.superappium.lazy.view.SelectedValueGetter;

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

    public static Map<String, ValueGetter> valueGetters(ViewModel viewModel) {
        Class<? extends View> aClass = viewModel.getOriginView().getClass();
        boolean saveCache = false;
        if (aClass.getClassLoader().equals(View.class.getClassLoader())) {
            saveCache = true;
        }
        return valueGetters(viewModel, saveCache);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, ValueGetter> valueGetters(ViewModel viewModel, boolean saveCache) {
        Class<? extends View> theClass = viewModel.getOriginView().getClass();
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

        //avoid reload value data
        Map<String, ValueGetter> ret = new HashMap<>();
        for (Map.Entry<String, ValueGetter> entry : rule.entrySet()) {
            ret.put(entry.getKey(), new LazyValueGetter(entry.getValue()));
        }
        return ret;
    }


}
