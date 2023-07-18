package com.example.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

public class func extends AppCompatActivity {

    private SwitchCompat switchOnOff; // Change the variable type to SwitchCompat
    private TextView tvSwitchYes;
    private TextView tvSwitchNo;

    private Map<String, String> users;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build);

        users = new HashMap<>();
        users.put("h370@gmail.com", "mystro@2002");
        users.put("toxicmystro@gmail.com", "mzhat5555");
        users.put("h370286@gmail.com", "mystro12345");
        users.put("hma_1414@gmail.com", "me@1414");


        ImageView showPasswordImage = findViewById(R.id.imageViewme);
        showPasswordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        // <--Forget your password?-->

        TextView forgetPassword = findViewById(R.id.textView6);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(func.this, ForgetPass.class));
            }
        });

        // <--Create an account-->

        TextView createLabel = findViewById(R.id.textView8);
        createLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(func.this, SignupActivity.class));
            }
        });

        switchOnOff = findViewById(R.id.switchOnOff); // Find SwitchCompat instead of Switch
        tvSwitchYes = findViewById(R.id.tvSwitchYes);
        tvSwitchNo = findViewById(R.id.tvSwitchNo);

        updateTextViewColors(switchOnOff.isChecked());

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextViewColors(isChecked);
                switchText(isChecked);
            }
        });
    }

    private void switchText(boolean isChecked) {
        TextView sign = findViewById(R.id.textView3);
        TextView emailLabel = findViewById(R.id.textView4);
        TextView passwordLabel = findViewById(R.id.textView5);
        TextView forgetMe = findViewById(R.id.textView6);
        TextView createAccount = findViewById(R.id.textView8);

        if (isChecked) {
            sign.setText("تسجيل الدخول");
            sign.setPadding(0, 0, 203, 0);

            emailLabel.setText("البريد الإلكتروني أو رقم الهاتف المسجل");
            emailLabel.setPadding(0, 0, 30, 0);

            passwordLabel.setText("كلمة المرور");
            passwordLabel.setPadding(0, 0, 30, 0);

            forgetMe.setText("نسيت كلمة المرور؟");
            forgetMe.setPadding(0, 0, 666, 0);

            createAccount.setText("إنشاء حساب");
            createAccount.setPadding(0, 0, 426, 0);
        } else {
            // If the switch is OFF, update the text for English language
            sign.setText("Sign in");
            emailLabel.setText("E-mail or registered phone number:");
            passwordLabel.setText("Password");
            forgetMe.setText("forget your password?");
            forgetMe.setPadding(0, 0, 0, 0);
            createAccount.setText("Create an account");
            createAccount.setPadding(0, 0, 0, 0);
        }
    }

    private void updateTextViewColors(boolean isSwitchOn) {
        if (isSwitchOn) {
            // Switch is ON, set AR to white and EN to blue
            tvSwitchYes.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
            tvSwitchNo.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        } else {
            // Switch is OFF, set EN to white and AR to blue
            tvSwitchYes.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            tvSwitchNo.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
        }
    }

    public void onButtonClick(View view) {
        EditText email = findViewById(R.id.editTextTextEmailAddress2);
        EditText password = findViewById(R.id.editTextTextPassword3);

        String enteredEmail = email.getText().toString();
        String enteredPassword = password.getText().toString();

        TextView errorEmailLabel = findViewById(R.id.errorEmailLabel);
        TextView errorPassLabel = findViewById(R.id.errorPasswordLabel);

        // Validate email format (you can use a more robust email validation regex)
        if (!isValidEmail(enteredEmail)) {
            errorEmailLabel.setVisibility(View.VISIBLE);
            errorPassLabel.setVisibility(View.GONE);
            return;
        }

        // Validate password length (you can define a minimum password length)
        if (enteredPassword.length() < 6) {
            errorEmailLabel.setVisibility(View.GONE);
            errorPassLabel.setVisibility(View.VISIBLE);
            return;
        }

        // Check if the entered credentials match any user
        if (users.containsKey(enteredEmail) && users.get(enteredEmail).equals(enteredPassword)) {
            errorEmailLabel.setVisibility(View.GONE);
            errorPassLabel.setVisibility(View.GONE);
            startActivity(new Intent(func.this, hello.class));
        } else {
            errorEmailLabel.setVisibility(View.VISIBLE);
            errorPassLabel.setVisibility(View.VISIBLE);
        }
    }

    private boolean isValidEmail(String email) {
        // Use a regex pattern to validate email format (you can use a more robust pattern)
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }



    private void togglePasswordVisibility() {
        EditText password = findViewById(R.id.editTextTextPassword3);
        ImageView showPasswordImage = findViewById(R.id.imageViewme);

        if (isPasswordVisible) {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPasswordImage.setImageResource(R.drawable.untitled);
        } else {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showPasswordImage.setImageResource(R.drawable.pngtree_hide_feature_with_eye_crossed_shape_button_png_image_5621640_removebg_preview);
        }

        password.setSelection(password.getText().length());

        isPasswordVisible = !isPasswordVisible;
    }

}
