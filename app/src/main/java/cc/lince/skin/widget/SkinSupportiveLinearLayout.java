package cc.lince.skin.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cc.lince.skin.core.SkinSupportive;
import cc.lince.skin.helper.SkinBackgroundHelper;

public class SkinSupportiveLinearLayout extends LinearLayout implements SkinSupportive {

    private SkinBackgroundHelper mBackgroundHelper;

    public SkinSupportiveLinearLayout(Context context) {
        this(context, null);
    }

    public SkinSupportiveLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinSupportiveLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundHelper = new SkinBackgroundHelper(this);
        mBackgroundHelper.obtainStyledAttributes(attrs, defStyleAttr, 0);
    }

    @Override
    public void changeSkin(Resources resources) {
        mBackgroundHelper.changeSkin(resources);
    }
}