package com.example.khum.demo0223;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        initView();
    }

    private void initView() {
        PDFView pdfView = (PDFView)findViewById(R.id.pdfView);
        String pdfPath = "http://192.168.0.15:84/files/contract_pdf/2018-03-15/2018-03-15-17-13-20-5aaa3930538e6.pdf";
        pdfView.fromUri(Uri.parse(pdfPath)).load();
    }


}
