package library.blades.rxcore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import library.blades.rxcore.demo.ProgressActivity;

public class MainActivity extends AppCompatActivity {

    // Content View Elements

    private AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }


    // End Of Content View Elements

    private void bindViews() {
        btn = (AppCompatButton) findViewById(R.id.btn_pro);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProgressActivity.class));
            }
        });

    }

}
