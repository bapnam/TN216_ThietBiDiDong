package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import thud.ql_sudungnuoc.R;
import thud.ql_sudungnuoc.dulieu.KhachHang;
import thud.ql_sudungnuoc.xuly.KhachHangAdapter;

public class ThongTinKhachHangActivity extends AppCompatActivity {

    KhachHangAdapter khAdapter;
    KhachHang kh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);

        khAdapter = new KhachHangAdapter(this);
        HienThiKhachHang();
    }

    private void HienThiKhachHang() {
        List<KhachHang> dsKhachHang = khAdapter.ListAllKhachHang();
        TextView txtThongTin = findViewById(R.id.txt_thongtin);
        String strThongTin = "Thông tin khách hàng";
        int n = dsKhachHang.size();
        if(n > 0){
            for(int i=0; i<n; i++) {
                kh = dsKhachHang.get(i);
                strThongTin += "\n\n Họ tên: " + kh.getHoten();
                strThongTin += "\n Số điện thoại: " +
                        kh.getDienthoai();
                strThongTin += "\n Đối tượng sử dụng: " +
                        kh.getDoituong();
                strThongTin += "\n Hình thức thanh toán: " +
                        kh.getThanhtoan();
                strThongTin += "\n Khu vực: " + kh.getKhuvuc();
            }
            txtThongTin.setText(strThongTin);
        }
        else
            txtThongTin.setText("Chưa có khách hàng trong CSDL");
    }

    public void ChinhSuaKhachHang(View view) {
        khAdapter.updateKhachHang(2, "Lê Thị Nhung", "0908000901",
                "Kinh doanh", "Chuyển khoản", 7);
        HienThiKhachHang();
    }

    public void ThemKhachHang(View view) {
        kh = new KhachHang(1, " Nguyễn Văn Hùng", "0908000900",
                " Hộ nghèo", "Chuyển khoản", 10);
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
        HienThiKhachHang();
    }

    public void XoaKhachHang(View view) {
        khAdapter.deleteKhachHang(2);
        HienThiKhachHang();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        khAdapter.close();
    }
}