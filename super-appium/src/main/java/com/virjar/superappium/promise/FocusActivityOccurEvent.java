package com.virjar.superappium.promise;

import android.app.Activity;

public interface FocusActivityOccurEvent {
    boolean onFocusActivityOccur(Activity activity);

    void onActivityEmpty();

    //onLostFocus

    void onAppBackend();
}
