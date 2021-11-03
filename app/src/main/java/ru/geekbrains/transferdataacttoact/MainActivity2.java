package ru.geekbrains.transferdataacttoact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        profile = getIntent().getParcelableExtra(MainActivity.YOUR_NAME);
        if (profile == null) {
            profile = new Profile();
        }
        initView();
    }

    private void initView() {

        EditText edname = findViewById(R.id.et_name);
        EditText edage = findViewById(R.id.et_age);
        EditText edphone = findViewById(R.id.et_phone);

        edname.setText(profile.getName());
        edage.setText(String.valueOf(profile.getAge()));
        edphone.setText(String.valueOf(profile.getPhone()));

        findViewById(R.id.btn_back).setOnClickListener(v -> {

            profile = new Profile(
                    edname.getText().toString(),
                    Integer.parseInt(edage.getText().toString()),
                    edphone.getText().toString()
            );

            Intent data = new Intent();
            data.putExtra(MainActivity.YOUR_NAME, profile);

            setResult(RESULT_OK, data);
            finish();
        });
    }
}