package cc.lince.skin.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import cc.lince.skin.core.SkinSupportive;
import cc.lince.skin.helper.SkinBackgroundHelper;
import cc.lince.skin.helper.SkinHelper;
import cc.lince.skin.helper.SkinTextHelper;

public class SkinSupportiveTextView extends AppCompatTextView implements SkinSupportive {

    private SkinHelper mBackgroundHelper;
    private SkinHelper mTextHelper;

    public SkinSupportiveTextView(Context context) {
        this(context, null);
    }

    public SkinSupportiveTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinSupportiveTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundHelper = new SkinBackgroundHelper(this);
        mBackgroundHelper.obtainStyledAttributes(attrs, defStyleAttr, 0);
        mTextHelper = new SkinTextHelper(this);
        mTextHelper.obtainStyledAttributes(attrs, defStyleAttr, 0);
    }

    @Override
    public void changeSkin(Resources resources) {
        mBackgroundHelper.changeSkin(resources);
        mTextHelper.changeSkin(resources);
    }
}