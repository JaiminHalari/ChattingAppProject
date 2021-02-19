package james.my.newmychattingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneNumberActivity extends AppCompatActivity {

    //java code for phone number validation
    String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";


    TextInputEditText editText;

    Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        editText = findViewById(R.id.edittext_enterphonenumber_id);
        btn_continue = findViewById(R.id.button_continue_id);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = editText.getText().toString();

                if (phonenumber.isEmpty() || phonenumber.equals("") || phonenumber.equals(null))
                {
                    editText.setError("Enter Mobile Number");
                    editText.requestFocus();
                }
                else if (phonenumber.length() < 10)
                {
                    editText.setError("Enter Valid Number");
                    editText.requestFocus();
                }
                else {
                    Intent intent = new Intent(PhoneNumberActivity.this, OtpActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}