package cc.lince.skin.helper;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;

import cc.lince.skin.util.SkinPreferences;

public abstract class SkinHelper {

    protected static final String SYSTEM_ID_PREFIX = "1";

    protected static final int INVALID_ID = 0;

    /**
     * 根据主应用资源 ID 得到 皮肤包中的资源 ID
     *
     * @param resources  {@link Resources} 皮肤包的资源类
     * @param context    上下文
     * @param originalId 主应用资源 ID
     * @return
     */
    protected final int getSkinResourceId(Resources resources, Context context, @IdRes int originalId) {
        originalId = checkResourceId(originalId);
        if (originalId == INVALID_ID) {
            return INVALID_ID;
        }
        // 1. 根据主应用 resource id 查找 resource name
        String resName = context.getResources().getResourceEntryName(originalId);
        String resType = context.getResources().getResourceTypeName(originalId);
        // 2. 根据 resource name 查找皮肤包 resource id
        String skinPackageName = SkinPreferences.getInstance().getSkinPackageName();
        return resources.getIdentifier(resName, resType, skinPackageName);
    }

    private int checkResourceId(int resId) {
        String hexResId = Integer.toHexString(resId);
        return hexResId.startsWith(SYSTEM_ID_PREFIX) ? INVALID_ID : resId;
    }

    /**
     * 解析自定义属性
     * eg: SkinBackgroundHelper_android_background
     * SkinTextHelper_android_textColor
     * SkinImageHelper_android_src
     *
     * @param set
     * @param defStyleAttr
     * @param defStyleRes
     */
    public abstract void obtainStyledAttributes(AttributeSet set,
                                                @AttrRes int defStyleAttr,
                                                @StyleRes int defStyleRes);

    /**
     * 1. 根据 resource id 查找 resource name
     * 2. 根据 resource name 查找皮肤包的 resource id
     * 3. 根据皮肤包的 resource id 得到皮肤包对应的资源
     *
     * @param resources {@link Resources} 资源类
     */
    public abstract void changeSkin(Resources resources);
}
