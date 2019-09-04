package com.macbitsgoa.events.DosmEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.Authentication.SignupActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.QrScaner.QrCodeModal;
import com.macbitsgoa.events.QrScaner.QrScannerActivity;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.profile.ProfileActivity;

import java.util.ArrayList;


public class ProfileAndLeaderboardActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentPagerAdapter fragmentPagerAdapter;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    TextView scanCode;
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
        setContentView(R.layout.activity_profile_and_leaderboard);
        scanCode=findViewById(R.id.scan_code_txt);
        scanSucessPopup = new Dialog(ProfileAndLeaderboardActivity.this);
        scanSucessPopup.setContentView(R.layout.qrcode_scanned_dialog);


        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tabs);
        fragmentPagerAdapter =new FragmentsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
       tabLayout.getTabAt(0).setText("Profile Details");
        tabLayout.getTabAt(1).setText("LeaderBoard");
        //tabLayout.getTabAt(2).setText("LeaderBoard");
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if(currentUser==null)
        {
            startActivity(new Intent(ProfileAndLeaderboardActivity.this, SignupActivity.class));
            finish();
        }else {
            QrCodeListRef = firebaseDatabase.getReference().child("DosmEvent").child("QrCodeList");

            userDataRef = firebaseDatabase.getReference().child("DosmEvent").child("Users").child(currentUser.getUid());
            UserQrCodeListRef = firebaseDatabase.getReference().child("DosmEvent").child("Users").child(currentUser.getUid()).child("qrcodesScanned");
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



                        }
                        for (int i = 0; i < userdetails.qrcodesScanned.size(); i++) {
                             Log.e("userscanned",userdetails.qrcodesScanned.get(i).getQrcode());
                        }
                    } else {
                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Log.e("error", databaseError.toString());

                }
            });
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
                    for (int i = 0; i < qrcodelist.size(); i++) {
                        Log.e("codes on firebase",qrcodelist.get(i).getQrcode());
                    }

                    scanCode.setEnabled(true);
//
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }



     scanCode.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivityForResult(new Intent(ProfileAndLeaderboardActivity.this, QrScannerActivity.class),101);
         }
     });

    }


    public  void showPopup(String qrcode1,String points1,String descrip1){
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
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode==RESULT_OK)
        {
            String code =data.getStringExtra("qrcode");
            Toast.makeText(ProfileAndLeaderboardActivity.this,"result is "+code,Toast.LENGTH_LONG).show();
            if(userdetails.qrcodesScanned.size()>0){
                Log.e("flow","one");

                for(int j=0;j<userdetails.qrcodesScanned.size();j++)
                {

                    if(userdetails.qrcodesScanned.get(j).getQrcode().equals(code))
                    {
                        //check if already scanned
                        Log.e("flow","two");

                        Toast.makeText(ProfileAndLeaderboardActivity.this,"this qr code has already been scanned",Toast.LENGTH_LONG).show();
                        oldcodeflaf =true;

                    } }

                if(!oldcodeflaf)
                {
                    //if not scanned already

                    for(int i=0;i<qrcodelist.size();i++)
                    {

                        if(qrcodelist.get(i).getQrcode().equals(code))
                        {
                            QrCodeModal item = new QrCodeModal(qrcodelist.get(i).getQrcode(),qrcodelist.get(i).getDescrip(),qrcodelist.get(i).getPoints());
                            userdetails.qrcodesScanned.add(item);
                            UserQrCodeListRef.child(String.valueOf(userdetails.getQrcodesScanned().size())).setValue(item);
                            userDataRef.child("totalpoints").setValue(totalPoints+qrcodelist.get(i).getPoints());

                             showPopup(qrcodelist.get(i).getQrcode(),String.valueOf(qrcodelist.get(i).getPoints()),qrcodelist.get(i).getDescrip());



                            Newcodeflag =true;
                            break;

                        }}

                    if(!Newcodeflag){
                        // some other qr code is scanned
                        Toast.makeText(ProfileAndLeaderboardActivity.this,"Invalid QrCode Scanned0",Toast.LENGTH_LONG).show();
                    }


                }

            }
            else
            {


                // inserting first qrcode scanned
                for(int i=0;i<qrcodelist.size();i++)
                {

                    if(qrcodelist.get(i).getQrcode().equals(code))
                    {

                        QrCodeModal item = new QrCodeModal(qrcodelist.get(i).getQrcode(),qrcodelist.get(i).getDescrip(),qrcodelist.get(i).getPoints());
                        userdetails.qrcodesScanned.add(item);
                        Newcodeflag=true;
                        Toast.makeText(ProfileAndLeaderboardActivity.this,"new qrcode scanned ",Toast.LENGTH_LONG).show();
                        UserQrCodeListRef.child(String.valueOf(userdetails.getQrcodesScanned().size())).setValue(item);
                        userDataRef.child("totalpoints").setValue(totalPoints+qrcodelist.get(i).getPoints());
                        showPopup(qrcodelist.get(i).getQrcode(),String.valueOf(qrcodelist.get(i).getPoints()),qrcodelist.get(i).getDescrip());

                        break;

                    }} if(!Newcodeflag) {
                // some other qr code is scanned

                Toast.makeText(ProfileAndLeaderboardActivity.this,"Invalid QrCode Scanned1",Toast.LENGTH_LONG).show();
            }




            }
        }
    }
}
