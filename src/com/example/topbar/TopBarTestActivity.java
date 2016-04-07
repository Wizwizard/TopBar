package com.example.topbar;

import custom.TopBar;
import custom.TopBar.OnTopBarClickListener;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


public class TopBarTestActivity extends ActionBarActivity {
	
	TopBar topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉系统标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_topbar_test);
        
        topBar = (TopBar)findViewById(R.id.topBar);
        //为topBar中的按钮注册监听事件
        topBar.setOnTopBarClickListener(new OnTopBarClickListener(){

			@Override
			public void leftClick() {
				Toast.makeText(TopBarTestActivity.this, "点击了leftButton", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void rightClick() {
				Toast.makeText(TopBarTestActivity.this, "点击了rightButton", Toast.LENGTH_SHORT).show();
			}
        	
        });
        /*
         * 可以用setButtonVisable(id,flag)来控制是否显示相应按钮
         * 添加以下代码，两个按钮都被隐藏了
         * topBar.setButtonVisable(0, false);
         * topBar.setButtonVisable(1, false);
         */
        
    }


   
}
