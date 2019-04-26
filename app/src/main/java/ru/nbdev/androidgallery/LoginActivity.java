package ru.nbdev.androidgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextName;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextName = findViewById(R.id.text_input_edit_text_name);
        buttonLogin = findViewById(R.id.button_login);

        setListeners();
    }

    private void setListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameWrong()) {
                    showNameError(v);
                    return;
                }
                runMainActivity();
            }
        });
    }

    private boolean isNameWrong() {
        return (textInputEditTextName.getText() == null) ||
                (textInputEditTextName.getText().length() == 0);
    }

    private void showNameError(View v) {
        Snackbar snackbar = Snackbar.make(v, getString(R.string.empty_name_error_text), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void runMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
