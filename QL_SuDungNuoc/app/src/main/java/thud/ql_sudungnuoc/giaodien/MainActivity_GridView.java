package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import thud.ql_sudungnuoc.R;
import thud.ql_sudungnuoc.xuly.ImageAdapter;

public class MainActivity_GridView extends AppCompatActivity {

    private Integer[] Images = {R.mipmap.nhanvien, R.mipmap.thongbao,
            R.mipmap.khachhang, R.mipmap.dangnhap, R.mipmap.thoat};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid_view);

        GridView gdvMenu = findViewById(R.id.gdv_menu);
        ImageAdapter adapter = new ImageAdapter(this, Images);
        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener(new ChonCongViec());

    }

    private class ChonCongViec implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == 4)
                finish();
            else {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity_GridView.this,
                                ThongTinNhanVienActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity_GridView.this,
                                ThongBaoActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity_GridView.this,
                                DanhSachKhachHangActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity_GridView.this,
                                DangNhapActivity.class);
                        break;
                }
                startActivity(intent);
            }
        }
    }
}