package thud.dangkydichvuinternet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String arrGoiCuoc[] = {"Mega Basic", "Mega family",
                            "Mega Maxi", "Mega Pro"};
    Spinner spnGoiCuoc;
    String strGoiCuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.basicicon);
        spnGoiCuoc = findViewById(R.id.spn_goicuoc);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrGoiCuoc);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnGoiCuoc.setAdapter(adapter);
        spnGoiCuoc.setOnItemSelectedListener(new ChonPhanTu());
    }

    public void DangKy(View view) {
        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        CheckBox chkTruyenHinh, chkCamera;
        chkTruyenHinh = findViewById(R.id.chk_truyenhinh);
        chkCamera = findViewById(R.id.chk_camera);
        String strKetQua = "Gói Cước: " + strGoiCuoc + "\n";
        strKetQua += "Hình thức thanh toán: " +
                rad.getText().toString() + "\n";
        strKetQua += "Dịch vụ khác: ";
        if((! chkTruyenHinh.isChecked()) && (! chkCamera.isChecked()))
            strKetQua += "không";
        else{
            if(chkTruyenHinh.isChecked()){
                strKetQua += chkTruyenHinh.getText().toString();
                if(chkCamera.isChecked())
                    strKetQua += ", ";
            }
            if(chkCamera.isChecked())
                strKetQua += chkCamera.getText().toString();
        }

        AlertDialog.Builder bdrThongbao =
                new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Kết quả đăng ký");
        bdrThongbao.setPositiveButton("Đóng",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        bdrThongbao.setMessage(strKetQua);
        bdrThongbao.create().show();
    }

    public void Dong(View view) {
        this.finish();
    }

    private class ChonPhanTu implements
            AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int position, long id) {
            strGoiCuoc = arrGoiCuoc[position];
        }
        public void onNothingSelected(AdapterView<?> parent){
            strGoiCuoc = "";
        }
    }
}