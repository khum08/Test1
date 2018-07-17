package com.kotlin.khum.opensqlcipher.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kotlin.khum.opensqlcipher.App;
import com.kotlin.khum.opensqlcipher.R;
import com.kotlin.khum.opensqlcipher.utils.StaticField;

import static com.kotlin.khum.opensqlcipher.utils.StaticField.SERVER_URL;
import static com.kotlin.khum.opensqlcipher.utils.StaticField.ShareFileName;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/7/17
 *     desc   :
 * </pre>
 */
public class EditUrlDialog extends Dialog {

    public EditUrlDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_url);
        final EditText tv_input_url = findViewById(R.id.tv_input_url);
        TextView tv_cancel = findViewById(R.id.tv_cancel);
        TextView tv_confirm = findViewById(R.id.tv_confirm);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = tv_input_url.getText().toString();
                if(TextUtils.isEmpty(url)){
                    App.serverUrl = StaticField.baseUrl;
                    v.getContext().getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).edit().putString(SERVER_URL, StaticField.baseUrl).apply();
                    EditUrlDialog.this.dismiss();
                    return;
                }
                if(!url.endsWith("/")){
                    Toast.makeText(v.getContext(),"填写的URL必须以 \"/\" 结尾",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!url.startsWith("http")){
                    Toast.makeText(v.getContext(),"填写的URL必须是 http 开头",Toast.LENGTH_LONG).show();
                    return;
                }
                App.serverUrl = url;
                v.getContext().getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).edit().putString(SERVER_URL, url).apply();
                EditUrlDialog.this.dismiss();
            }
        });


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUrlDialog.this.dismiss();
            }
        });

    }
}
