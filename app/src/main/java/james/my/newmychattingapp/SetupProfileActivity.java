package james.my.newmychattingapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class SetupProfileActivity extends AppCompatActivity {

    TextInputEditText userNameBox;
    Button btn_profile;
    CircleImageView imageProfile;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        btn_profile = findViewById(R.id.button_profile);
        imageProfile = findViewById(R.id.image_profile);
        userNameBox = findViewById(R.id.UsernameBox);



        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_image_intent = new Intent();
                profile_image_intent.setType("image/*");
                profile_image_intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(profile_image_intent , "Select picture"), PICK_IMAGE);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameBox.getText().toString();
                if (userName.isEmpty())
                {
                    userNameBox.setError("Enter Username.");
                    userNameBox.requestFocus();

                    //profileimage & profileusername ka data intent through one activity se dusre activity me transfer karne ke liye
                    String profileusername = userNameBox.getText().toString();
                    Intent userNameintent = new Intent();
                    userNameintent.putExtra("username",profileusername);
                    startActivity(userNameintent);
                }
                else {
                    Intent intent = new Intent(SetupProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    //Profile image get & Set karva mate
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == PICK_IMAGE && resultCode == RESULT_OK)
       {
           //Data get karva
           imageUri = data.getData();
           try {
               try {
                   //image bitmap ma store karva mate
                   Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                   //image ne imageview ma show karva
                   imageProfile.setImageBitmap(bitmap);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           } finally {

           }
       }

    }
}