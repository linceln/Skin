package cc.lince.skin.core;

import android.content.res.Resources;

/**
 * 需要换肤的控件需要实现该接口实现换肤逻辑
 */
public interface SkinSupportive {
    void changeSkin(Resources resources);
}
