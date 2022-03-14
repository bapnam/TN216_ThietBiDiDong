package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import thud.ql_sudungnuoc.R;

public class MainActivity_ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview);

        List<String> listThucDon;
        ListView listviewThucDon;
        ArrayAdapter<String> adapter;
        listviewThucDon = findViewById(R.id.listview_thucdon);
        listThucDon = new ArrayList<String>();
        listThucDon.add("Nhân viên");
        listThucDon.add("Thông báo");
        listThucDon.add("Đăng nhập lại");
        listThucDon.add("Thoát");

        adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1, listThucDon);
        listviewThucDon.setAdapter(adapter);
        listviewThucDon.setOnItemClickListener(new ChonActivity());

    }//end

    private class ChonActivity implements
            AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent,
                                View view, int position, long id) {
            if(position ==3)
                finish();
            else {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity_ListView.this,
                                ThongTinNhanVienActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity_ListView.this,
                                ThongBaoActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity_ListView.this,
                                DangNhapActivity.class);
                        break;
                }
                startActivity(intent);
            }
        }
    }//end



}