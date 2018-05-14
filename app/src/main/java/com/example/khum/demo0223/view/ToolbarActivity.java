package com.example.khum.demo0223.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.khum.demo0223.R;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/25
 *     desc   :
 * </pre>
 */
public class ToolbarActivity extends AppCompatActivity {

    private LinearLayout mLeftView;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mDrawer = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mLeftView = findViewById(R.id.ll_left);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        //设置home；
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        //设置导航，这一行代码和上面两行代码的效果相同
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        //设置logo；
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.mipmap.ic_launcher);
        // 显示标题和子标题
        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        toolbar.setTitle("大标题");
//        toolbar.setSubtitle("小标题");


        ToolbarFragment toolbarFragment = new ToolbarFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_content,toolbarFragment,"toolbar").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //用户点击菜单的信息首先传递给activity，如果activity不处理，则传递给fragment。
        //return true：消费事件，不向下传递事件
        if(item.getItemId()== android.R.id.home){
            if(mDrawer.isDrawerOpen(GravityCompat.START)){
                mDrawer.closeDrawers();
            }else{
                mDrawer.openDrawer(GravityCompat.START);
            }
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}
