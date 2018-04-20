package cc.lince.skin.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cc.lince.skin.widget.SkinSupportiveImageView;
import cc.lince.skin.widget.SkinSupportiveLinearLayout;
import cc.lince.skin.widget.SkinSupportiveTextView;

/**
 * 1. 调用 LayoutInflater.setFactory2() 来使用
 * 2. 观察者，换肤时收到通知
 */
public class SkinFactory2 implements LayoutInflater.Factory2, Observer {

    private List<SkinSupportive> mSkinViews = new ArrayList<>();

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(parent, name, context, attrs);
        if (view instanceof SkinSupportive) {
            // 将需要换肤的控件缓存起来
            mSkinViews.add((SkinSupportive) view);
        }
        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }

    private View createView(View parent, String name, Context context, AttributeSet attrs) {
        // TODO 创建 View 逻辑优化
        if ("TextView".equals(name)) {
            return new SkinSupportiveTextView(context, attrs);
        } else if ("ImageView".equals(name)) {
            return new SkinSupportiveImageView(context, attrs);
        } else if ("LinearLayout".equals(name)) {
            return new SkinSupportiveLinearLayout(context, attrs);
        }
        return null;
    }


    @Override
    public void update(Observable observable, Object o) {
        // 换肤时回调更新控件
        if (o instanceof Resources) {
            Resources resources = (Resources) o;
            for (SkinSupportive ss : mSkinViews) {
                ss.changeSkin(resources);
            }
        }
    }
}