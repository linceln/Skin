package cc.lince.skin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cc.lince.skin.core.SkinManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                SkinManager.getInstance().restore();
                break;
            case R.id.btn1:
                SkinManager.getInstance().changeSkin("skinplugin1.skin");
                break;
            case R.id.btn2:
                SkinManager.getInstance().changeSkin("skinplugin2.skin");
                break;
        }
    }
}