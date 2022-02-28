package thud.ql_sudungnuoc.giaodien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import thud.ql_sudungnuoc.R;

public class ThongBaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
    }

    public void HienThiThongBao(View view){
        EditText edtChuDe, edtNguoiNhan, edtDiaChi, edtNoiDung;
        String strChuDe, strNguoiNhan, strDiaChi, strNoiDung, strThongBao;
        edtChuDe = findViewById(R.id.edt_chude);
        edtNguoiNhan = findViewById(R.id.edt_nguoinhan);
        edtDiaChi = findViewById(R.id.edt_diachi);
        edtNoiDung = findViewById(R.id.edt_noidung);
        strChuDe = edtChuDe.getText().toString().trim();
        if (strChuDe.length() < 1) {
            edtChuDe.requestFocus();
            edtChuDe.selectAll();
            Toast.makeText(this, "Chủ đề không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        strNguoiNhan = edtNguoiNhan.getText().toString().trim();
        if (strNguoiNhan.length() < 1) {
            edtNguoiNhan.requestFocus();
            edtNguoiNhan.selectAll();
            Toast.makeText(this, "Người nhận không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        strDiaChi = edtDiaChi.getText().toString().trim();
        if (strDiaChi.length() < 1) {
            edtDiaChi.requestFocus();
            edtDiaChi.selectAll();
            Toast.makeText(this, "Địa chỉ không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        strNoiDung = edtNoiDung.getText().toString().trim();
        if (strNoiDung.length() < 1) {
            edtNoiDung.requestFocus();
            edtNoiDung.selectAll();
            Toast.makeText(this, "Nội dung không được rỗng",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder bdrThongbao =
                new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Gởi thông báo");
        bdrThongbao.setIcon(R.mipmap.send);
        bdrThongbao.setPositiveButton("Đồng ý",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which){
                        Toast.makeText(ThongBaoActivity.this, "Đồng ý",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        bdrThongbao.setNegativeButton("Hủy bỏ",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(ThongBaoActivity.this, "Hủy bỏ",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        strThongBao = "Chủ đề: " + strChuDe + "\n";
        strThongBao += "Người nhận: " + strNguoiNhan + "\n";
        strThongBao += "Địa chỉ: " + strDiaChi + "\n";
        strThongBao += "Nội dung: " + strNoiDung;
        bdrThongbao.setMessage(strThongBao);
        bdrThongbao.create().show();
    }

    public void DongActivity(View view){
        finish();
    }

}