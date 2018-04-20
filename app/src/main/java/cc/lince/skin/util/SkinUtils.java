package cc.lince.skin.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import cc.lince.skin.constant.SkinConstants;


public class SkinUtils {

    public static String findSkinApk(Context context, String name) {
        // 暂时先从 assets 目录中复制一份做测试
        return copySkinFromAssets(context, name);
    }

    private static String copySkinFromAssets(Context context, String name) {
        String skinPath = new File(SkinFileUtils.getSkinDir(context), name).getAbsolutePath();
        try {
            InputStream is = context.getAssets().open(SkinConstants.SKIN_PATH
                    + File.separator + name);
            OutputStream os = new FileOutputStream(skinPath);
            int byteCount;
            byte[] bytes = new byte[1024];
            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinPath;
    }

    /**
     * 获取皮肤包包名.
     *
     * @param skinPkgPath sdcard中皮肤包路径完整路径
     * @return {@link String} 包名
     */
    public static String getSkinPackageName(Context context, String skinPkgPath) {
        PackageManager mPm = context.getPackageManager();
        PackageInfo info = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.packageName;
        } else {
            return null;
        }
    }

    /**
     * 获取皮肤包资源
     *
     * @param skinPkgPath sdcard中皮肤包路径.
     * @return {@link Resources} 资源
     */
    @Nullable
    public static Resources getSkinResources(Context context, String skinPkgPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinPkgPath);

            Resources superRes = context.getResources();
            return new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}