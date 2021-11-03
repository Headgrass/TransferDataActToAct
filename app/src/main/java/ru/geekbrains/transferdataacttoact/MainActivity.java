package ru.geekbrains.transferdataacttoact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    public static final String YOUR_NAME = "YOUR_NAME";
    private EditText editText;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.editTextTextPersonName);
        profile = new Profile();

        findViewById(R.id.btn_browser).setOnClickListener(v -> {
            Uri uri = Uri.parse(editText.getText().toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });


        findViewById(R.id.btn_hello).setOnClickListener(v -> {
            TextView textView = findViewById(R.id.tv_hello);
            textView.setText("Привет, " + editText.getText().toString());
        });
//При нажатии кнопки "Настройки" передаем во вторую активити значение поля editText
        findViewById(R.id.btn_settings).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);

            profile = new Profile(
                    editText.getText().toString(),
                    profile.getAge(),
                    profile.getPhone()
            );

            intent.putExtra(YOUR_NAME, profile);
            startActivityForResult(intent, REQUEST_CODE_SETTING_ACTIVITY);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_SETTING_ACTIVITY && resultCode == RESULT_OK && data != null) {
            profile = data.getParcelableExtra(YOUR_NAME);
            if (profile != null) {
                editText.setText((profile.getName()));
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}