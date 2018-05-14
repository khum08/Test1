package com.example.khum.demo0223;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    private void initUI() {
        final EditText et_account = findViewById(R.id.et_account);
        final EditText et_secret = findViewById(R.id.et_secret);
        Button btn_apply = findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_account==null || et_secret== null){
                    Toast.makeText(MainActivity.this,"请输入账号和密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                String account = et_account.getText().toString();
                String secret = et_secret.getText().toString();
                register(account,secret);
            }
        });

        findViewById(R.id.tv_otherAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.this.startActivity(new Intent(MainActivity.this,MyScrollingActivity.class));
                MainActivity.this.startActivity(new Intent(MainActivity.this,PdfActivity.class));
            }
        });
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });

    }


    private void register(final String account, final String secret) {
        String url = "http://192.168.0.186:8080/account?method=register";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"注册失败",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                String data = "{account:"+account+",secret:"+secret+"}";
                map.put("data",data);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };

        requestQueue.add(request);
    }

}
