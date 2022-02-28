package thud.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Message", "onPause thực thi");
        Toast.makeText(this, "onPause Thực Thi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Message", "onResume thực thi");
        Toast.makeText(this, "onResume Thực Thi", Toast.LENGTH_LONG).show();

    }

    public void ThucThiActivity(View view){
        Intent intent = new Intent(this, SimpleActivity.class);
        startActivity(intent);
    }

}