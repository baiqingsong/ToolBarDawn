package com.dawn.toolbardawn;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomToolBar toolbar = (CustomToolBar) findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setMainTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ym_any_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastUI("back");
            }
        });
    }
}
