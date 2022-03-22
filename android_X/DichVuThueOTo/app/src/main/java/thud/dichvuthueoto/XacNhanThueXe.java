package thud.dichvuthueoto;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class XacNhanThueXe extends AppCompatActivity {

    String strHoTen, strDienThoai, strThanhToan, strThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thue_xe);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.car_2);
        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();
        strHoTen = bundleNhan.getString("HoTen", "");
        strDienThoai = bundleNhan.getString("DienThoai", "");
        strThanhToan = bundleNhan.getString("ThanhToan", "");
        strThongTin = "Họ tên khách hàng: " + strHoTen;
        strThongTin += "\nSố điện thoại: " + strDienThoai;
        strThongTin += "\nHình thức thanh toán: " + strThanhToan;
        TextView txtKhachHang = findViewById(R.id.txt_khachhang);
        txtKhachHang.setText(strThongTin);
    }

    public void DongY(View view) {
        setResult(RESULT_OK);
        finish();
    }

    public void HuyBo(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}