package cc.lince.skin.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SkinPreferences {

    private static final String FILE_NAME = "skin-data";

    private static final String KEY_SKIN_NAME = "skin-name";

    private static final String KEY_PACKAGE_NAME = "skin-package-name";

    private final Context mApp;

    private static SkinPreferences sInstance;

    private final SharedPreferences mPref;

    private final SharedPreferences.Editor mEditor;

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinPreferences.class) {
                if (sInstance == null) {
                    sInstance = new SkinPreferences(context.getApplicationContext());
                }
            }
        }
    }

    public static SkinPreferences getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("SkinPreferences has not initialized");
        }
        return sInstance;
    }

    private SkinPreferences(Context applicationContext) {
        mApp = applicationContext;
        mPref = mApp.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public SkinPreferences setSkinPackageName(String packageName) {
        mEditor.putString(KEY_PACKAGE_NAME, packageName);
        return this;
    }

    public String getSkinPackageName() {
        return mPref.getString(KEY_PACKAGE_NAME, "");
    }

    public SkinPreferences setSkinName(String skinName) {
        mEditor.putString(KEY_SKIN_NAME, skinName);
        return this;
    }

    public String getSkinName() {
        return mPref.getString(KEY_SKIN_NAME, "");
    }

//    public SkinPreferences setSkinStrategy(int strategy) {
//        mEditor.putInt(KEY_SKIN_STRATEGY, strategy);
//        return this;
//    }
//
//    public int getSkinStrategy() {
//        return mPref.getInt(KEY_SKIN_STRATEGY, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
//    }

    public void commitEditor() {
        mEditor.apply();
    }
}
