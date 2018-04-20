package cc.lince.skin.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import cc.lince.skin.core.SkinSupportive;
import cc.lince.skin.helper.SkinImageHelper;

public class SkinSupportiveImageView extends AppCompatImageView implements SkinSupportive {

    private SkinImageHelper mImageHelper;

    public SkinSupportiveImageView(Context context) {
        this(context, null);
    }

    public SkinSupportiveImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinSupportiveImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImageHelper = new SkinImageHelper(this);
        mImageHelper.obtainStyledAttributes(attrs, defStyleAttr, 0);
    }

    @Override
    public void changeSkin(Resources resources) {
        mImageHelper.changeSkin(resources);
    }
}
