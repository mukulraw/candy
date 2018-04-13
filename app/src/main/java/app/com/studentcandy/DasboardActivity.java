package app.com.studentcandy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import app.com.studentcandy.fragment.ChatFragment;
import app.com.studentcandy.fragment.HomeFragment;
import app.com.studentcandy.fragment.NotificationFragment;
import app.com.studentcandy.fragment.ProfileFragment;


public class DasboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
int flag,id;

  /*  private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_chat:
                    mTextMessage.setText("Chat");
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText("Profile");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.navigation_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        id = item.getItemId();
        displaySelectedScreen(id);

        return true;
    }

    private void displaySelectedScreen(int itemId)
    {
        Fragment fragment = null;

        switch (itemId)
        {
            case R.id.navigation_home:


                flag = 0;


                fragment = new HomeFragment();


                break;
            case R.id.navigation_profile:

                fragment = new ProfileFragment();


                break;


            case R.id.navigation_chat:
                flag = 0;

                fragment = new ChatFragment();
                break;


            case R.id.navigation_notifications:
                flag = 0;

                fragment = new NotificationFragment();

                break;
        }

        if (fragment != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
        }


    }
    }



