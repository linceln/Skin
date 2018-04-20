package cc.lince.skin.helper;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import cc.lince.skin.R;

public class SkinImageHelper extends SkinHelper {

    private ImageView mView;

    private int mSrcId;

    public SkinImageHelper(ImageView view) {
        this.mView = view;
    }

    @Override
    public void obtainStyledAttributes(AttributeSet set, int defStyleAttr, int defStyleRes) {
        TypedArray ta = mView.getContext().obtainStyledAttributes(set, R.styleable.SkinImageHelper, defStyleAttr, defStyleRes);
        if (ta.hasValue(R.styleable.SkinImageHelper_android_src)) {
            mSrcId = ta.getResourceId(R.styleable.SkinImageHelper_android_src, 0);
        }
        ta.recycle();
    }

    @Override
    public void changeSkin(Resources resources) {

        Context context = mView.getContext();
        int newSrcId = getSkinResourceId(resources, context, mSrcId);
        if (newSrcId == 0) {
            return;
        }
        // 根据皮肤包的 resource id 得到皮肤包对应的资源
        Drawable drawable = resources.getDrawable(newSrcId);
        mView.setImageDrawable(drawable);
    }
}
