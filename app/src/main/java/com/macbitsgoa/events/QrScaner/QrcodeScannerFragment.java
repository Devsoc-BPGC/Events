package com.macbitsgoa.events.QrScaner;
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


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QrcodeScannerFragment extends Fragment {
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
    int k=0;



    public QrcodeScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode_scanner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scanSucessPopup = new Dialog(getContext());
        scanSucessPopup.setContentView(R.layout.qrcode_scanned_dialog);
        CodeScannerView scannerView = view.findViewById(R.id.scanner_view);
        codeScanner= new CodeScanner(getContext(),scannerView);
        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        codeScanner.releaseResources();
                        codeScanner.stopPreview();
                        view.findViewById(R.id.scanner_view).setVisibility(View.GONE);
                        Log.e("flow","after gone");
                        for (int i = 0; i < userdetails.qrcodesScanned.size(); i++) {
                            Log.e("codesscannedbyuser",userdetails.qrcodesScanned.get(i).getQrcode());}
                        for(int i=0;i<qrcodelist.size();i++)
                        {
                            Log.e("codes on firebase",qrcodelist.get(i).getQrcode());
                        }
                        if(userdetails.qrcodesScanned.size()>0){
                            Log.e("flow","size>0");




                            for(int j=0;j<userdetails.qrcodesScanned.size();j++)
                            {

                                if(userdetails.qrcodesScanned.get(j).getQrcode().equals(result.getText()))
                                {
                                    //check if already scanned
                                    Log.e("flow","already scanned ");

                                    Toast.makeText(getContext(),"this qr code has already been scanned",Toast.LENGTH_LONG).show();
                                    oldcodeflaf =true;

                                } }

                            if(!oldcodeflaf)
                            {
                                //if not scanned already
                                Log.e("flow","not scanned already ");
                                for(int i=0;i<qrcodelist.size();i++)
                                {

                                    if(qrcodelist.get(i).getQrcode().equals(result.getText()))
                                    {
                                        QrCodeModal item = new QrCodeModal(qrcodelist.get(i).getQrcode(),qrcodelist.get(i).getDescrip(),qrcodelist.get(i).getPoints());
                                        userdetails.qrcodesScanned.add(item);
                                        UserQrCodeListRef.child(String.valueOf(userdetails.getQrcodesScanned().size())).setValue(item);
                                        userDataRef.child("totalpoints").setValue(totalPoints+qrcodelist.get(i).getPoints());
                                        Toast.makeText(getContext(),"new qrcode scanned ",Toast.LENGTH_LONG).show();


                                         showPopup(qrcodelist.get(i).getQrcode(),String.valueOf(qrcodelist.get(i).getPoints()),qrcodelist.get(i).getDescrip());



                                        Newcodeflag =true;
                                        break;

                                    }
                                }

                                if(!Newcodeflag){
                                    // some other qr code is scanned
                                    Toast.makeText(getContext(),"Invalid QrCode Scanned0",Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(getContext(),"new qrcode scanned ",Toast.LENGTH_LONG).show();
                                    UserQrCodeListRef.child(String.valueOf(userdetails.getQrcodesScanned().size())).setValue(item);
                                    userDataRef.child("totalpoints").setValue(totalPoints+qrcodelist.get(i).getPoints());
                                     showPopup(qrcodelist.get(i).getQrcode(),String.valueOf(qrcodelist.get(i).getPoints()),qrcodelist.get(i).getDescrip());

                                    break;

                                }} if(!Newcodeflag) {
                            // some other qr code is scanned

                            Toast.makeText(getContext(),"Invalid QrCode Scanned1",Toast.LENGTH_LONG).show();
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




        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        if (currentUser != null){
            QrCodeListRef = firebaseDatabase.getReference().child("DosmEvent").child("QrCodeList");

            userDataRef = firebaseDatabase.getReference().child("DosmEvent").child("Users").child(currentUser.getUid());
            Log.e("current user id ",currentUser.getUid());
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

                    for(int i=0;i<qrcodelist.size();i++)
                    {
                        Log.e("codes on firebase",qrcodelist.get(i).getQrcode());
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            UserQrCodeListRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (currentUser != null) {

                        userdetails.qrcodesScanned.clear();

                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String Qrcode = child.child("qrcode").getValue(String.class);
                            String Descrip = child.child("descrip").getValue(String.class);
                            Long Points = child.child("points").getValue(Long.class);
                            totalPoints += child.child("points").getValue(Double.class);

                            userdetails.qrcodesScanned.add(new QrCodeModal(Qrcode, Descrip, Points));
                            userdetails.setPoints(totalPoints);




                        }
                        for (int i = 0; i < userdetails.qrcodesScanned.size(); i++) {
                            Log.e("codesscannedbyuser",userdetails.qrcodesScanned.get(i).getQrcode());
                        }
                    } else {
                    }
                    view.findViewById(R.id.progressbar).setVisibility(View.GONE);
                    view.findViewById(R.id.scanner_view).setVisibility(View.VISIBLE);
                    if(k==0)
                    { codeScanner.startPreview();
                    k++;}


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Log.e("error", databaseError.toString());

                }
            });
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        codeScanner.releaseResources();
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
