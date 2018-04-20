package cc.lince.skin.helper;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import cc.lince.skin.R;

public class SkinTextHelper extends SkinHelper {

    private final TextView mView;

    private int mTextColorId;

    public SkinTextHelper(TextView view) {
        this.mView = view;
    }

    @Override
    public void obtainStyledAttributes(AttributeSet set, int defStyleAttr, int defStyleRes) {
        TypedArray ta = mView.getContext().obtainStyledAttributes(set, R.styleable.SkinTextViewHelper, defStyleAttr, 0);
        if (ta.hasValue(R.styleable.SkinTextViewHelper_android_textColor)) {
            mTextColorId = ta.getResourceId(R.styleable.SkinTextViewHelper_android_textColor, 0);
        }
        ta.recycle();
    }

    @Override
    public void changeSkin(Resources resources) {
        Context context = mView.getContext();
        int newColorId = getSkinResourceId(resources, context, mTextColorId);
        if (newColorId == 0) {
            return;
        }
        // 根据皮肤包的 resource id 得到皮肤包对应的资源
        int color = resources.getColor(newColorId);
        mView.setTextColor(color);
    }
}