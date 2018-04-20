package cc.lince.skin.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.Observable;
import java.util.WeakHashMap;

import cc.lince.skin.util.SkinPreferences;
import cc.lince.skin.util.SkinUtils;


public class SkinManager extends Observable {

    private Context mContext;

    private static SkinManager mInstance;

    private WeakHashMap<String, Resources> mResources;

    private SkinManager(Application application) {
        mContext = application;
    }

    public static SkinManager getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("SkinManager has not initialized");
        }
        return mInstance;
    }

    /**
     * 初始化，创建单例
     *
     * @param application Application
     */
    public static void init(Application application) {
        if (mInstance == null) {
            synchronized (SkinManager.class) {
                if (mInstance == null) {
                    mInstance = new SkinManager(application);
                }
            }
        }
        SkinPreferences.init(application);
        ActivityLifecycle.init(application);
    }

    /**
     * 恢复默认主题
     */
    public void restore() {
        changeSkin(null);
    }

    /**
     * 更换主题
     *
     * @param name 主题包的文件名 eg: skinpackage.skin
     */
    public void changeSkin(String name) {
        new ChangeSkinTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, name);
    }

    private class ChangeSkinTask extends AsyncTask<String, Void, Resources> {

        @Override
        protected Resources doInBackground(String... strings) {


            if (strings.length == 1 && TextUtils.isEmpty(strings[0])) {
                // 如果没有指定皮肤包，则恢复默认主题
                // 保存包名
                SkinPreferences.getInstance()
                        .setSkinPackageName(mContext.getPackageName())
                        .commitEditor();
                return mContext.getResources();
            }

            // 解析皮肤包中的资源
            // 找到皮肤包
            String skinApk = SkinUtils.findSkinApk(mContext, strings[0]);
            // 得到皮肤包的包名
            String skinPackageName = SkinUtils.getSkinPackageName(mContext, skinApk);
            // 保存包名
            SkinPreferences.getInstance()
                    .setSkinPackageName(skinPackageName)
                    .commitEditor();

            if (mResources == null) {
                mResources = new WeakHashMap<>();
            }
            Resources resources = mResources.get(strings[0]);
            if (resources == null) {
                // 得到皮肤包的资源类
                resources = SkinUtils.getSkinResources(mContext, skinApk);
                // 资源类根据皮肤名做缓存
                mResources.put(strings[0], resources);
            }
            if (resources != null) {
                Log.d("skin", "resources: " + resources.hashCode());
            }
            return resources;
        }

        /**
         * 通知观察者{@link SkinFactory2}更新
         */
        @Override
        protected void onPostExecute(Resources resources) {
            SkinManager.getInstance().setChanged();
            SkinManager.getInstance().notifyObservers(resources);
        }
    }
}