package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import thud.ql_sudungnuoc.R;

public class ThongTinNhanVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
    }

    public void LuuNhanVien(View view) {
        EditText edtTaiKhoan, edtMatKhau, edtQuyenSD, edtDienThoai;
        String strTaiKhoan, strMatKhau, strQuyenSD, strDienThoai,
                strThongTinNV;
        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_matkhau);
        edtQuyenSD = findViewById(R.id.edt_quyensd);
        edtDienThoai = findViewById(R.id.edt_dienthoai);
        strTaiKhoan = edtTaiKhoan.getText().toString().trim();
        if (strTaiKhoan.length() < 1) {
            edtTaiKhoan.requestFocus();
            edtTaiKhoan.selectAll();
            Toast.makeText(this, "Tài khoản không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        strMatKhau = edtMatKhau.getText().toString().trim();
        if (strMatKhau.length() < 1) {
            edtMatKhau.requestFocus();
            edtMatKhau.selectAll();
            Toast.makeText(this, "Mật khẩu không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        strQuyenSD = edtQuyenSD.getText().toString().trim();
        if (strQuyenSD.length() < 1) {
            edtQuyenSD.requestFocus();
            edtQuyenSD.selectAll();
            Toast.makeText(this, "Quyền sử dụng không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        strDienThoai = edtDienThoai.getText().toString().trim();
        if (strDienThoai.length() < 1) {
            edtDienThoai.requestFocus();
            edtDienThoai.selectAll();
            Toast.makeText(this, "Điện thoại không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder bdrThongbao = new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Thông tin nhân viên");
        bdrThongbao.setIcon(R.mipmap.user);
        bdrThongbao.setPositiveButton("Đồng ý",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ThongTinNhanVienActivity.this, "Đồng ý",
                        Toast.LENGTH_SHORT).show();
            }

        });

        bdrThongbao.setNegativeButton("Hủy bỏ",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ThongTinNhanVienActivity.this, "Hủy bỏ",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        strThongTinNV = "Tài khoản: " + strTaiKhoan + "\n";
        strThongTinNV += "Mật khẩu: " + strMatKhau + "\n";
        strThongTinNV += "Quyền sử dụng: " + strQuyenSD + "\n";
        strThongTinNV += "Điện thoại: " + strDienThoai;
        bdrThongbao.setMessage(strThongTinNV);
        bdrThongbao.create().show();
    }
}