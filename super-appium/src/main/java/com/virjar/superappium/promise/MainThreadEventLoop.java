package com.virjar.superappium.promise;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;

import com.virjar.superappium.util.ReflectUtil;

public class MainThreadEventLoop {
    private static Object mainThread = ReflectUtil.getMainThread();
    private static Handler mainThreadHandler = new Handler(Looper.getMainLooper());
//    private static LinkedList<EventTaskDriver> mTaskQueue = new LinkedList<>();
//
//
//    private static class EventTaskDriver implements Runnable {
//        @Override
//        public void run() {
//            if (mTaskQueue.size() == 0) {
//                mainThreadHandler.postDelayed(this, 500);
//                return;
//            }
//            mainThreadHandler.post(mTaskQueue.removeFirst());
//        }
//    }

    public static void runOnFocusActivity(FocusActivityOccurEvent focusActivityOccurEvent) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            findAndFire(focusActivityOccurEvent);
        } else {
            mainThreadHandler.post(() -> findAndFire(focusActivityOccurEvent));
        }
    }


    private static void findAndFire(FocusActivityOccurEvent focusActivityOccurEvent) {
        ArrayMap mActivities = ReflectUtil.getObjectField(mainThread, "mActivities");
        Activity topActivity = null;

        if (mActivities.values().size() == 0) {
            focusActivityOccurEvent.onActivityEmpty();
            return;
        }
        for (Object activityClientRecord : mActivities.values()) {
            Activity tempActivity = ReflectUtil.getObjectField(activityClientRecord, "activity");
            if (tempActivity.hasWindowFocus()) {
                topActivity = tempActivity;
                break;
            }
        }

        if (topActivity == null) {
            focusActivityOccurEvent.onAppBackend();
            return;
        }
        focusActivityOccurEvent.onFocusActivityOccur(topActivity);
    }


}
