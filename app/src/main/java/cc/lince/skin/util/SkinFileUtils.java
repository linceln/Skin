package cc.lince.skin.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

import cc.lince.skin.constant.SkinConstants;


public class SkinFileUtils {

    public static String getSkinDir(Context context) {
        File skinDir = new File(getCacheDir(context), SkinConstants.SKIN_PATH);
        if (!skinDir.exists()) {
            skinDir.mkdirs();
        }
        return skinDir.getAbsolutePath();
    }

    private static String getCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir.getAbsolutePath();
            }
        }

        return context.getCacheDir().getAbsolutePath();
    }


    public static boolean isFileExists(String path) {
        return !TextUtils.isEmpty(path) && new File(path).exists();
    }
}
