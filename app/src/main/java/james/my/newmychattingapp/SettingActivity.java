package james.my.newmychattingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {

    MaterialTextView textPrivacyPolicy;
    CircleImageView profileimage;
    TextInputEditText edituserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        textPrivacyPolicy = findViewById(R.id.txtPrivacyPolicy);
        profileimage = findViewById(R.id.profileImage);
        edituserName = findViewById(R.id.etUserNameInProfile);

        String data = getIntent().getStringExtra("username");
        edituserName.setText(data);

        textPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacypolicyintent = new Intent(Intent.ACTION_VIEW);
                privacypolicyintent.setData(Uri.parse("https://www.whatsapp.com/privacy/?lang=en"));
                startActivity(privacypolicyintent);
            }
        });

    }
}