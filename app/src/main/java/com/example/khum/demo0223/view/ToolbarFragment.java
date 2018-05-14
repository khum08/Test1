package com.example.khum.demo0223.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khum.demo0223.R;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/25
 *     desc   :
 * </pre>
 */
public class ToolbarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main,null);
        setHasOptionsMenu(true);//fragment可以为activity的Options Menu提供菜单项
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //用户点击菜单的信息首先传递给activity，如果activity不处理，则传递给fragment。
        //return true：消费事件，不向下传递事件
        switch (item.getItemId()){
            case R.id.text1:
                Toast.makeText(getActivity(),"hahha",Toast.LENGTH_LONG).show();
                break;
            case R.id.text:
                Toast.makeText(getActivity(),"kekeek",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}













