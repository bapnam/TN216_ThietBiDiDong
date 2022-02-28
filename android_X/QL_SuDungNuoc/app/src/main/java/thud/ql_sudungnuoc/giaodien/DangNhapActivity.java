package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import thud.ql_sudungnuoc.R;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtTaiKhoan, edtmatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        edtmatKhau = findViewById(R.id.edt_matkhau);
    }

    public void KiemTraNguoiDung(View view) {
        String strTaiKhoan, strMatKhau;
        strTaiKhoan = edtTaiKhoan.getText().toString().trim();
        if(strTaiKhoan.length() == 0){
            edtTaiKhoan.setError("Lỗi chưa nhập tài khoản");
            edtTaiKhoan.requestFocus();
            return;
        }
        else
            edtTaiKhoan.setError(null);
        strMatKhau = edtmatKhau.getText().toString().trim();
        if(strMatKhau.length() == 0){
            edtmatKhau.setError("Lỗi chưa nhập mật khẩu");
            edtmatKhau.requestFocus();
            return;
        }
        else
            edtmatKhau.setError(null);

        if (strTaiKhoan.equals("admin") && strMatKhau.equals("123")) {
            edtmatKhau.setError(null);
            Toast.makeText(this, "Đăng nhập thành công!",
                    Toast.LENGTH_LONG).show();
            finish();
            Intent intent = new Intent(this, ThongTinNhanVienActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Lỗi tài khoản hoặc mật khẩu không đúng!",
                    Toast.LENGTH_LONG).show();
            edtmatKhau.setError("Lỗi tài khoản hoặc mật khẩu không đúng!");
        }

    }
}