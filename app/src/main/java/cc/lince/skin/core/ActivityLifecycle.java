package cc.lince.skin.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;

import java.lang.reflect.Field;
import java.util.WeakHashMap;


public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private static ActivityLifecycle mInstance;

    private WeakHashMap<Context, SkinFactory2> mFactories;

    private ActivityLifecycle(Application application) {
        // 注册所有 Activity 生命周期监听
        application.registerActivityLifecycleCallbacks(this);
        // 为 LayoutInflater 设置 Factory2
        installLayoutFactory(application);
        // 添加观察者，每个 Context 对应一个 SkinFactory2 观察者
        SkinManager.getInstance().addObserver(getFactory(application));
    }

    /**
     * 初始化，创建单例
     *
     * @param application Application
     */
    public static void init(Application application) {
        if (mInstance == null) {
            synchronized (ActivityLifecycle.class) {
                if (mInstance == null) {
                    mInstance = new ActivityLifecycle(application);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        installLayoutFactory(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        SkinManager.getInstance().addObserver(getFactory(activity));
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        SkinManager.getInstance().deleteObserver(getFactory(activity));
        mFactories.remove(activity);
    }

    /**
     * 为 LayoutInflater 设置 Factory2
     *
     * @param context Context
     */
    private void installLayoutFactory(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);
            LayoutInflaterCompat.setFactory2(layoutInflater, getFactory(context));
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private SkinFactory2 getFactory(Context context) {
        if (mFactories == null) {
            mFactories = new WeakHashMap<>();
        }
        SkinFactory2 factory2 = mFactories.get(context);
        if (factory2 == null) {
            factory2 = new SkinFactory2();
        }
        if (!mFactories.containsValue(factory2)) {
            mFactories.put(context, factory2);
        }
        return factory2;
    }
}
