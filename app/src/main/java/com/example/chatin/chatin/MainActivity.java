package com.example.chatin.chatin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ViewPager mViewPager;
    private SectionsPagerAdaptor mSectionsPagerAdaptor;

    private DatabaseReference mUserRef, RootRef;
    private TabLayout mTabLayout;

    private String currentUserID;
    //private Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle saveInstanceState) {

      super.onCreate(saveInstanceState);
      setContentView(R.layout.activity_main);

      mAuth = FirebaseAuth.getInstance();
      RootRef = FirebaseDatabase.getInstance().getReference();
      //mToolbar = findViewById(R.id.main_page_toolbar);
      //setSupportActionBar(mToolbar);
      getSupportActionBar().setTitle("ChatIn");

//      if (mAuth.getCurrentUser() != null) {
//
//          mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
//      }

      //Tabs
      mViewPager = (ViewPager)findViewById(R.id.main_tabPager);
      mSectionsPagerAdaptor = new SectionsPagerAdaptor(getSupportFragmentManager());

      mViewPager.setAdapter(mSectionsPagerAdaptor);
      mTabLayout = (TabLayout)findViewById(R.id.main_tabs);
      mTabLayout.setupWithViewPager(mViewPager);

  }

  public void onStart() {
      super.onStart();

      FirebaseUser currentUser = mAuth.getCurrentUser();

      if(currentUser == null)
      {
          sendToStart();
      } else {

          updateUserStatus("online");
          //mUserRef.child("Users").child("online").setValue("true");

      }
  }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            updateUserStatus("offline");
            //mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
        }
    }

    @Override
    protected void onDestroy(){
      super.onDestroy();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            updateUserStatus("offline");
            //mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
        }
    }

    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this, startActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

         getMenuInflater().inflate(R.menu.main_menu,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_logout_btn) {

            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        if (item.getItemId() == R.id.main_settings_btn) {
            Intent settingsIntent  = new Intent(MainActivity.this , SettingsActivity.class);
            startActivity(settingsIntent);
        }
        if (item.getItemId() == R.id.main_all_btn) {

            Intent findfriendsIntent  = new Intent(MainActivity.this , UsersActivity.class);
            startActivity(findfriendsIntent);
        }

        return true;
    }

    private void updateUserStatus(String state)
    {
        String saveCurrentTime, saveCurrentDate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> onlineStateMap = new HashMap<>();
        onlineStateMap.put("time", saveCurrentTime);
        onlineStateMap.put("date", saveCurrentDate);
        onlineStateMap.put("state", state);

        currentUserID = mAuth.getCurrentUser().getUid();

        RootRef.child("Users").child(currentUserID).child("userState")
                .updateChildren(onlineStateMap);
    }
}
