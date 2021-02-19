package james.my.newmychattingapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import james.my.newmychattingapp.Fragments.CallsFragment;
import james.my.newmychattingapp.Fragments.ChatsFragment;
import james.my.newmychattingapp.Fragments.StatusFragment;

public class MainActivity<badgeDrawable, val> extends AppCompatActivity {


    ProgressDialog dialog;
    AlertDialog alertDialog;
    FrameLayout frameLayout;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    BadgeDrawable badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        loadFragment(new ChatsFragment());

        bottomNavigationView = findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.frame_layout);

        bottomNavigationView.setOnNavigationItemSelectedListener(this::onOptionsItemSelected);

        //Dialog set  karva
        dialog = new ProgressDialog(this);
        dialog.setMessage("Profile updating...");
        dialog.setCancelable(true);
        dialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        fragment = null;
            switch (item.getItemId())
            {

                //Bottom Navigation Menus started
                case R.id.menu_chats:
                    fragment = new ChatsFragment();
                    break;
                case R.id.menu_status:
                    fragment = new StatusFragment();
                    break;
                case R.id.menu_calls:
                    fragment = new CallsFragment();
                    break;
                //Bottom Navigation Menus end

                case R.id.topmenu_search:
                    Toast.makeText(this, "Search Selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.topmenu_groups:
                    Toast.makeText(this, "Group Selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, GroupChatActivity.class);
                    startActivity(intent);
                    break;
                case R.id.topmenu_invites:
                    Toast.makeText(this, "Invites Selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.topmenu_settings:
                    Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.topmenu_logout:

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setIcon(R.drawable.whatsapp);

                    builder.setTitle("Are you sure want to logout ?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Yes clicked", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "No Clicked", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.create();
                    builder.show();
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                    break;
            }

            return loadFragment(fragment);
        }


    private boolean loadFragment(Fragment  fragment)
    {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();

            return true;
        }
        return false;
    }

}