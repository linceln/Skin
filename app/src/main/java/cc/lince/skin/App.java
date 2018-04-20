package cc.lince.skin;

import android.app.Application;

import cc.lince.skin.core.SkinManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}
