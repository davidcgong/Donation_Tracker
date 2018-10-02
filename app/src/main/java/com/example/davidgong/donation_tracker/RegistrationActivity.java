package com.example.davidgong.donation_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {
    private AutoCompleteTextView username;
    private EditText password;
    private EditText confirmPassword;
    private Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //grabbing veiws
        Button registerBtn = (Button) findViewById(R.id.btn_register);
        username = (AutoCompleteTextView) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        confirmPassword = (EditText) findViewById(R.id.txt_confirmPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernametxt = username.getText().toString();
                String passwordtxt = password.getText().toString();
                String confirmPasswordtxt = confirmPassword.getText().toString();
                boolean valid = true;

                username.setError(null);
                password.setError(null);
                confirmPassword.setError(null);
                if (TextUtils.isEmpty(usernametxt)) {
                    username.setError(getString(R.string.error_field_required));
                } else if (model.containsUsername(usernametxt)) {
                    username.setError(getString(R.string.error_username_taken));
                    valid = false;
                } else if (!model.validUsername(usernametxt)) {
                    username.setError(getString(R.string.error_invalid_username));
                    valid = false;
                }
                if (TextUtils.isEmpty(passwordtxt)) {
                    password.setError(getString(R.string.error_field_required));
                } else if (!model.validPassword(passwordtxt)) {
                    password.setError(getString(R.string.error_invalid_password));
                    valid = false;
                }
                if (TextUtils.isEmpty(confirmPasswordtxt)) {
                    confirmPassword.setError(getString(R.string.error_field_required));
                } else if (!passwordtxt.equals(confirmPasswordtxt)) {
                    confirmPassword.setError(getString(R.string.error_password_mismatch));
                    valid = false;
                }

                if (valid) {
                    model.addAccount(usernametxt, passwordtxt);
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
