package thud.dangkytour;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    String arrLoaiTour[] = {
            "Chinh Phục đỉnh Langbiang",
            "Du lịch nhà vườn", "Thành phố tình yêu",
            "Giao lưu Văn hóa Cồng chiêng",
            "Nam Thiên đệ nhất thác"};
    int arrDonGia[] = {220, 280, 170, 150, 350};
    Spinner spnLoaiTour;
    String strKhachHang, strEmail, strLoaiTour, strThanhToan;
    int dongia, sokhach, sotien;
    TextInputLayout layoutKhachHang, layoutEmail, layoutSoKhach;
    TextInputEditText edtKhachHang, edtEmail, edtSoKhach;
    EditText edtDonGia, edtSoTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.globeicon);

        edtKhachHang = findViewById(R.id.edt_hotenKH);
        edtEmail = findViewById(R.id.edt_emailKH);
        edtDonGia = findViewById(R.id.edt_dongia);
        edtSoKhach = findViewById(R.id.edt_sokhach);
        edtSoTien = findViewById(R.id.edt_sotien);
        layoutKhachHang = findViewById(R.id.layout_hotenKH);
        layoutEmail = findViewById(R.id.layout_emailKH);
        layoutSoKhach = findViewById(R.id.layout_sokhach);
        spnLoaiTour = findViewById(R.id.spn_loaitour);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrLoaiTour);
        adapter.setDropDownViewResource(
                android.R.layout.simple_list_item_single_choice);
        spnLoaiTour.setAdapter(adapter);
        spnLoaiTour.setOnItemSelectedListener(new ChonLoaiTour());


    }

    public void DangKy(View view) {
        strKhachHang = edtKhachHang.getText().toString().trim();
        if(strKhachHang.length() == 0){
            layoutKhachHang.setError("Lỗi chưa nhập họ tên khách hàng");
            edtKhachHang.requestFocus();
            return;
        }
        else
            layoutKhachHang.setError(null);
        strEmail = edtEmail.getText().toString().trim();
        if(strEmail.length() == 0){
            layoutEmail.setError("Lỗi chưa nhập địa chỉ Email");
            edtEmail.requestFocus();
            return;
        }
        else
            layoutEmail.setError(null);
        String strSoKhach = edtSoKhach.getText().toString().trim();
        if(strSoKhach.length() == 0 | Integer.parseInt(strSoKhach) == 0){
            layoutSoKhach.setError("Lỗi nhập số khách đặt Tour");
            edtSoKhach.requestFocus();
            return;
        }
        else {
            layoutSoKhach.setError(null);
            sokhach = Integer.parseInt(strSoKhach);
        }
        sotien = sokhach * dongia;
        edtSoTien.setText("Số tiền: " + sotien);
        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        strThanhToan = rad.getText().toString();
        String strChuDe = "Thông tin đăng ký Tour du lịch";
        String strThongTin = "Họ tên khách hàng: " + strKhachHang;
        strThongTin += "\nLoại Tour: " + strLoaiTour;
        strThongTin += "\nĐơn giá: " + dongia;
        strThongTin += "\nSố khách: " + sokhach;
        strThongTin += "\nHình thức thanh toán: " + strThanhToan;
        strThongTin += "\nSố tiền: " + sotien;
        strThongTin += "\n\nChúc quý khách vui vẻ!";
        GoiEmail(new String[] { strEmail }, strChuDe, strThongTin);
    }

    public void DongActivity(View view) {
        finish();
    }

    private class ChonLoaiTour implements
            android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int position, long id) {
            strLoaiTour = arrLoaiTour[position];
            dongia = arrDonGia[position];
            edtDonGia.setText("Đơn giá: " + dongia );
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            strLoaiTour = "";
            dongia = 0;
        }
    }

    private void GoiEmail(String[] address, String subject, String body){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent. setType("message/rfc822");
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if( intent.resolveActivity(this.getPackageManager()) != null )
            startActivity(Intent.createChooser(intent,
                    "Chọn ứng dụng gởi Email:"));
        else
            Toast.makeText(this, "Lỗi thực hiện gởi Email",
                    Toast.LENGTH_LONG).show();

    }
}