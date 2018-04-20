package cc.lince.skin.helper;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;

import cc.lince.skin.R;

public class SkinBackgroundHelper extends SkinHelper {

    private View mView;

    private int mBackgroundId;

    public SkinBackgroundHelper(View view) {
        this.mView = view;
    }

    @Override
    public void obtainStyledAttributes(AttributeSet set,
                                       @AttrRes int defStyleAttr,
                                       @StyleRes int defStyleRes) {

        TypedArray ta = mView.getContext().obtainStyledAttributes(set, R.styleable.SkinBackgroundHelper, defStyleAttr, 0);
        if (ta.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
            mBackgroundId = ta.getResourceId(R.styleable.SkinBackgroundHelper_android_background, 0);
        }
        ta.recycle();
    }

    @Override
    public void changeSkin(Resources resources) {
        Context context = mView.getContext();
        int newBgId = getSkinResourceId(resources, context, mBackgroundId);
        if (newBgId == 0) {
            return;
        }
        // 根据皮肤包的 resource id 得到皮肤包对应的资源
        Drawable drawable = resources.getDrawable(newBgId);
        mView.setBackgroundDrawable(drawable);
    }
}
