package com.macbitsgoa.events.QrScaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.macbitsgoa.events.Authentication.SignupActivity;
import com.macbitsgoa.events.DosmEvent.UserDetailsModal;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

public class QrScannerActivity extends AppCompatActivity  {
   private CodeScanner codeScanner;
   private FirebaseAuth auth;
   private boolean Newcodeflag = false;
   private boolean oldcodeflaf =false;
   ArrayList<QrCodeModal> qrcodelist =new ArrayList<>();
   UserDetailsModal userdetails =new UserDetailsModal();
   FirebaseDatabase firebaseDatabase;
   DatabaseReference QrCodeListRef;
   DatabaseReference UserQrCodeListRef;
    DatabaseReference userDataRef;
    Dialog scanSucessPopup;
   double totalPoints =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        Log.e("kartik", "inside on create");
        scanSucessPopup = new Dialog(QrScannerActivity.this);
        scanSucessPopup.setContentView(R.layout.qrcode_scanned_dialog);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            startActivity(new Intent(QrScannerActivity.this, SignupActivity.class));
            finish();

        } else {
            Log.e("uid", currentUser.getUid());

        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (currentUser != null){
            QrCodeListRef = firebaseDatabase.getReference().child("DosmEvent").child("QrCodeList");

        userDataRef = firebaseDatabase.getReference().child("DosmEvent").child("Users").child(currentUser.getUid());
        UserQrCodeListRef = firebaseDatabase.getReference().child("DosmEvent").child("Users").child(currentUser.getUid()).child("qrcodesScanned");
        QrCodeListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                qrcodelist.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String Qrcode = child.child("code").getValue(String.class);
                    String Descrip = child.child("descrip").getValue(String.class);
                    Long Points = child.child("points").getValue(Long.class);
                    qrcodelist.add(new QrCodeModal(Qrcode, Descrip, Points));
                }
//                Log.e("qrcode",qrcodelist.get(1).getDescrip());
//                Log.e("qrcode",qrcodelist.get(1).getQrcode());
//                Log.e("qrcode",String.valueOf(qrcodelist.get(1).getPoints()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        UserQrCodeListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (currentUser != null) {

                    // userdetails.name = dataSnapshot.child(currentUser.getUid()).child("name").getValue(String.class);
                    userdetails.qrcodesScanned.clear();

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String Qrcode = child.child("qrcode").getValue(String.class);
                        String Descrip = child.child("descrip").getValue(String.class);
                        Long Points = child.child("points").getValue(Long.class);
                        totalPoints += child.child("points").getValue(Double.class);

                        userdetails.qrcodesScanned.add(new QrCodeModal(Qrcode, Descrip, Points));
                        userdetails.setPoints(totalPoints);

                        Log.e("userstatus", "user is not null");
                        Log.e("size of array", String.valueOf(userdetails.qrcodesScanned.size()));
                        Log.e(" array item", child.child("qrcode").getValue(String.class));


                    }
                    for (int i = 0; i < userdetails.qrcodesScanned.size(); i++) {
                        // Log.e("codes on firebase",userdetails.qrcodesScanned.get(i).getQrcode());
                    }
                } else {
                }
                findViewById(R.id.progressbar).setVisibility(View.GONE);
                findViewById(R.id.scanner_view).setVisibility(View.VISIBLE);
                codeScanner.startPreview();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error", databaseError.toString());

            }
        });
    }



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

                  Log.e("codeScanned",result.getText());

                  Log.e("flow","zero");


                  if(userdetails.qrcodesScanned.size()>0){
                      Log.e("flow","one");

                      for(int j=0;j<userdetails.qrcodesScanned.size();j++)
                  {

                      if(userdetails.qrcodesScanned.get(j).getQrcode().equals(result.getText()))
                      {
                          //check if already scanned
                          Log.e("flow","two");

                          Toast.makeText(QrScannerActivity.this,"this qr code has already been scanned",Toast.LENGTH_LONG).show();
                          oldcodeflaf =true;

                      } }

                      if(!oldcodeflaf)
                      {
                          //if not scanned already

                          for(int i=0;i<qrcodelist.size();i++)
                      {

                          if(qrcodelist.get(i).getQrcode().equals(result.getText()))
                          {
                              QrCodeModal item = new QrCodeModal(qrcodelist.get(i).getQrcode(),qrcodelist.get(i).getDescrip(),qrcodelist.get(i).getPoints());
                              userdetails.qrcodesScanned.add(item);
                              UserQrCodeListRef.child(String.valueOf(userdetails.getQrcodesScanned().size())).setValue(item);
                              userDataRef.child("totalpoints").setValue(totalPoints+qrcodelist.get(i).getPoints());

                            //  showPopup(qrcodelist.get(i).getQrcode(),String.valueOf(qrcodelist.get(i).getPoints()),qrcodelist.get(i).getDescrip());



                              Newcodeflag =true;
                              break;

                          }}

                          if(!Newcodeflag){
                               // some other qr code is scanned
                              Toast.makeText(QrScannerActivity.this,"Invalid QrCode Scanned0",Toast.LENGTH_LONG).show();
                          }


                      }

                  }
                  else
                      {


                    // inserting first qrcode scanned
                      for(int i=0;i<qrcodelist.size();i++)
                  {

                      if(qrcodelist.get(i).getQrcode().equals(result.getText()))
                      {

                          QrCodeModal item = new QrCodeModal(qrcodelist.get(i).getQrcode(),qrcodelist.get(i).getDescrip(),qrcodelist.get(i).getPoints());
                          userdetails.qrcodesScanned.add(item);
                          Newcodeflag=true;
                          Toast.makeText(QrScannerActivity.this,"new qrcode scanned ",Toast.LENGTH_LONG).show();
                          UserQrCodeListRef.child(String.valueOf(userdetails.getQrcodesScanned().size())).setValue(item);
                          userDataRef.child("totalpoints").setValue(totalPoints+qrcodelist.get(i).getPoints());
                         // showPopup(qrcodelist.get(i).getQrcode(),String.valueOf(qrcodelist.get(i).getPoints()),qrcodelist.get(i).getDescrip());

                          break;

                      }} if(!Newcodeflag) {
                                                        // some other qr code is scanned

                          Toast.makeText(QrScannerActivity.this,"Invalid QrCode Scanned1",Toast.LENGTH_LONG).show();
                      }




                  }





              }
          });




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





    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    public void sendDatatoProfileFragment(String qrcode,String points,String descrip)
    {
        Bundle data=new Bundle();
        data.putString("qrcode",qrcode);
        data.putString("points",points);
        data.putString("descrip",descrip);


    }

 public  void showPopup(String qrcode1,String points1,String descrip1)
 {   codeScanner.stopPreview();
     scanSucessPopup.show();
     scanSucessPopup.setCanceledOnTouchOutside(false);
     TextView qrcode =scanSucessPopup.findViewById(R.id.qrcode);
     TextView points =scanSucessPopup.findViewById(R.id.points);
     TextView descrip =scanSucessPopup.findViewById(R.id.description);
     Button btn = scanSucessPopup.findViewById(R.id.btn);
     qrcode.setText("Qr code: "+qrcode1);
     points.setText("points: "+points1);
     descrip.setText("description: "+descrip1);



     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             scanSucessPopup.dismiss();
             codeScanner.startPreview();
         }
     });


 }
}
