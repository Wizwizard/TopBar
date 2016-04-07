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
        //ȥ��ϵͳ������
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_topbar_test);
        
        topBar = (TopBar)findViewById(R.id.topBar);
        //ΪtopBar�еİ�ťע������¼�
        topBar.setOnTopBarClickListener(new OnTopBarClickListener(){

			@Override
			public void leftClick() {
				Toast.makeText(TopBarTestActivity.this, "�����leftButton", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void rightClick() {
				Toast.makeText(TopBarTestActivity.this, "�����rightButton", Toast.LENGTH_SHORT).show();
			}
        	
        });
        /*
         * ������setButtonVisable(id,flag)�������Ƿ���ʾ��Ӧ��ť
         * ������´��룬������ť����������
         * topBar.setButtonVisable(0, false);
         * topBar.setButtonVisable(1, false);
         */
        
    }


   
}
