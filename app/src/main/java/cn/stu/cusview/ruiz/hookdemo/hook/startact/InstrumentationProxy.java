package cn.stu.cusview.ruiz.hookdemo.hook.startact;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstrumentationProxy extends Instrumentation {
    private static final String TAG = "InstrumentationProxy";

    private Instrumentation mInstrumentation;

    public InstrumentationProxy(Instrumentation instrumentation) {
        this.mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        Log.e(TAG, "execStartActivity: Hook Proxy" );

        try {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity",
                    Context.class,IBinder.class,IBinder.class,Activity.class,Intent.class,int.class,Bundle.class);

           return (ActivityResult) execStartActivity.invoke(mInstrumentation,who,contextThread,token,target,intent,requestCode,options);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "execStartActivity: ",e );
        } catch (IllegalAccessException e) {
            Log.e(TAG, "execStartActivity: ", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "execStartActivity: ", e);
        }
        Log.e(TAG, "execStartActivity: Proxy failed" );
        return null;
    }

}
