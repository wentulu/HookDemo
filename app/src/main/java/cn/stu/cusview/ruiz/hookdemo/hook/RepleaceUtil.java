package cn.stu.cusview.ruiz.hookdemo.hook;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;

import java.lang.reflect.Field;

import cn.stu.cusview.ruiz.hookdemo.hook.startact.InstrumentationProxy;

public class RepleaceUtil {

    private static final String TAG = "RepleaceUtil";


    public static void repleaceInstrumentation(Activity activity) {
        try {
            Field mInstrumentation = Activity.class.getDeclaredField("mInstrumentation");
            mInstrumentation.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activity);
            Instrumentation instrumentationProxy = new InstrumentationProxy(instrumentation);
            mInstrumentation.set(activity, instrumentationProxy);
            Log.e(TAG, "repleaceInstrumentation: " + activity.getClass().getCanonicalName() + " 替换成功");
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "repleaceInstrumentation: " + activity.getClass().getCanonicalName(), e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "repleaceInstrumentation: " + activity.getClass().getCanonicalName(), e);
        } catch (Exception e) {
            Log.e(TAG, "repleaceInstrumentation: " + activity.getClass().getCanonicalName(), e);
        }


    }

    public  void replaceActivityThreadInstrumentation(Activity activity) {
        Log.e(TAG, "replaceActivityThreadInstrumentation: hook start" );
        try {
            Class  clzz = Class.forName("android.app.ActivityThread");
            Field  scrrrentThreadField = clzz.getDeclaredField("sCurrentActivityThread");
            scrrrentThreadField.setAccessible(true);

            Object activityThread = scrrrentThreadField.get(null);


            Field  instrumenttationField = clzz.getDeclaredField("mInstrumentation");
            instrumenttationField.setAccessible(true);
            Instrumentation  instrumentation = (Instrumentation) instrumenttationField.get(activityThread);

            InstrumentationProxy instrumentationProxy = new InstrumentationProxy(instrumentation);

            instrumenttationField.set(activityThread, instrumentationProxy);
            Log.e(TAG, "replaceActivityThreadInstrumentation: replace success" );
        } catch (Exception e) {
            Log.e(TAG, "replaceActivityThreadInstrumentation: ",e );
        }


    }


}
