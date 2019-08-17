package com.macbitsgoa.events.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Camera;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.macbitsgoa.events.R;

public class QrScannerActivity extends AppCompatActivity  {
   private CodeScanner codeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        CodeScannerView scannerView =findViewById(R.id.scanner_view);
        Log.e("kartik","inside on create");

        codeScanner= new CodeScanner(QrScannerActivity.this,scannerView);
        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
          Log.e("kartik","inside on decoded");
          Intent data = new Intent();
          data.putExtra("result",result.getText());
          QrScannerActivity.this.runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  Toast.makeText(QrScannerActivity.this,result.getText(),Toast.LENGTH_LONG).show();


              }
          });
//          setResult(HomeActivity.RESULT_OK,data);
//          finish();



            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                codeScanner.startPreview();
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

                Log.e("kartik","inside on resume thread");
                codeScanner.startPreview();





    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }


}
