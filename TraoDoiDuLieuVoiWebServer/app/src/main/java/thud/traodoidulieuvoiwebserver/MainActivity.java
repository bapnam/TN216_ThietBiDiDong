package thud.traodoidulieuvoiwebserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] arrMenu = {"Nhận dữ liệu", "Gởi dữ liệu"};
    ListView listviewDuLieu;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewDuLieu = findViewById(R.id.lst_menu);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrMenu);
        listviewDuLieu.setAdapter(adapter);
        listviewDuLieu.setOnItemClickListener(new ChonCongViec());
    }

    private class ChonCongViec implements
            android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            Intent intent = new Intent(MainActivity.this,
                    NhanDuLieuTuServer.class);
            if (position == 1)
                intent = new Intent(MainActivity.this,
                        GoiDuLieuDenServer.class);
            startActivity(intent);
        }
    }
}