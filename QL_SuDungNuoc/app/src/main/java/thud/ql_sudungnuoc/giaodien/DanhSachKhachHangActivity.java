package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thud.ql_sudungnuoc.R;
import thud.ql_sudungnuoc.dulieu.KhachHang;
import thud.ql_sudungnuoc.xuly.KhachHangAdapter;
import thud.ql_sudungnuoc.xuly.KhachHangArrayAdapter;

public class DanhSachKhachHangActivity extends AppCompatActivity {

    KhachHangAdapter khAdapter;
    KhachHangArrayAdapter khArrayAdapter;
    List<KhachHang> lstKhachHang;
    ListView lvKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khach_hang);

        khAdapter = new KhachHangAdapter(this);
        lvKhachHang = findViewById(R.id.lst_khachhang);
        lstKhachHang = khAdapter.ListAllKhachHang();
        khArrayAdapter = new KhachHangArrayAdapter(this,
                R.layout.danhsach_khachhang_list,
                (ArrayList<KhachHang>) lstKhachHang);
        lvKhachHang.setAdapter(khArrayAdapter);
    }

    public void ThemKhachHang(View view){
        KhachHang kh;
        kh = new KhachHang(1, "Nguyễn Văn Hùng", "0908000900",
                "Hộ nghèo", "Chuyển khoản", 10);
        if(khAdapter.KiemTraKH(kh.getMskh()))
            Toast.makeText(this, "Khách hàng đã có trong CSDL",
                    Toast.LENGTH_SHORT).show();
        else
            khAdapter.insertKhachHang(kh);
        kh = new KhachHang(2, "Lê Thị Nhung", "0908000901",
                "Sản xuất", "Chuyển khoản", 5);
        if(khAdapter.KiemTraKH(kh.getMskh()))
            Toast.makeText(this, "Khách hàng đã có trong CSDL",
                    Toast.LENGTH_SHORT).show();
        else
            khAdapter.insertKhachHang(kh);
        kh = new KhachHang(3, "Nguyễn Thị Cẩm Tiên", "0908000902",
                "Hộ bình thường", "Tiền mặt", 9);
        if(khAdapter.KiemTraKH(kh.getMskh()))
            Toast.makeText(this, "Khách hàng đã có trong CSDL",
                    Toast.LENGTH_SHORT).show();
        else
            khAdapter.insertKhachHang(kh);
        khArrayAdapter.setKhArray((ArrayList<KhachHang>)
                khAdapter.ListAllKhachHang());
    }

    public void ChinhSuaKhachHang(View view){
        khAdapter.updateKhachHang(2, "Lê Thị Nhung",
                "0908000901", "Kinh doanh", "Tiền mặt", 7);
        khArrayAdapter.setKhArray((ArrayList<KhachHang>)
                khAdapter.ListAllKhachHang());
    }
    public void XoaKhachHang(View view){
        khAdapter.deleteKhachHang(1);
        khArrayAdapter.setKhArray((ArrayList<KhachHang>)
                khAdapter.ListAllKhachHang());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        khAdapter.close();
    }

}