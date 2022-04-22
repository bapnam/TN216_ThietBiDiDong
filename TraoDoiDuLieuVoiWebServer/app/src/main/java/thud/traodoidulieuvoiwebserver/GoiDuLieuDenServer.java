package thud.traodoidulieuvoiwebserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoiDuLieuDenServer extends AppCompatActivity {

    TextInputEditText edtHoTen, edtQuocGia, edtNoiDung;
    TextInputLayout layoutHoTen, layoutQuocGia, layoutNoiDung;
    String strHoTen, strQuocGia, strNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_du_lieu_den_server);

        edtHoTen = findViewById(R.id.edt_hoten);
        layoutHoTen = findViewById(R.id.layout_hoten);
        edtQuocGia = findViewById(R.id.edt_quocgia);
        layoutQuocGia = findViewById(R.id.layout_quocgia);
        edtNoiDung = findViewById(R.id.edt_noidung);
        layoutNoiDung = findViewById(R.id.layout_noidung);
    }

    public void ThucHienGoiDuLieu(View view) {
        strHoTen = edtHoTen.getText().toString().trim();
        if(strHoTen.length() == 0){
            layoutHoTen.setError("Lỗi chưa nhập họ tên!");
            edtHoTen.requestFocus();
            return;
        }
        else
            layoutHoTen.setError(null);
        strQuocGia = edtQuocGia.getText().toString().trim();
        if(strQuocGia.length() == 0){
            layoutQuocGia.setError("Lỗi chưa nhập quốc gia!");
            edtQuocGia.requestFocus();
            return;
        }
        else
            layoutQuocGia.setError(null);
        strNoiDung = edtNoiDung.getText().toString().trim();
        if(strNoiDung.length() == 0){
            layoutNoiDung.setError("Lỗi chưa nhập nội dung!");
            edtNoiDung.requestFocus();
            return;
        }
        else
            layoutNoiDung.setError(null);
        if (Publics.HasInternet(this)) {
            GoiDuLieu goiDuLieu = new GoiDuLieu();
            goiDuLieu.execute();
        } else {
            Toast.makeText(this, "Lỗi kết nối Internet!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void XoaNoiDung(View view) {
        edtHoTen.setText("");
        edtQuocGia.setText("");
        edtNoiDung.setText("");
        edtHoTen.requestFocus();
    }

    private class GoiDuLieu extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String ketqua = "";
            try {
                URL url = new URL(Publics.URLGOIDULIEU);
                HttpURLConnection conn = (HttpURLConnection)
                        url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(false);
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.connect();

                JSONObject dulieu = new JSONObject();
                dulieu.put("Name", strHoTen);
                dulieu.put("Country", strQuocGia);
                dulieu.put("Twitter", strNoiDung);
                OutputStream os = conn.getOutputStream();
                OutputStreamWriter out = new OutputStreamWriter(os,
                        "UTF-8");
                BufferedWriter writer = new BufferedWriter(out);
                writer.write(dulieu.toString());
                writer.flush();
                ketqua = conn.getResponseMessage();
                writer.close();
                out.close();
                os.close();
                conn.disconnect();
            } catch (Exception e) {
            }
            return ketqua;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equalsIgnoreCase("OK")) {
                setResult(RESULT_OK);
                Toast.makeText(GoiDuLieuDenServer.this,
                        "Gởi dữ liệu thành công!",
                        Toast.LENGTH_LONG).show();
            } else {
                setResult(RESULT_CANCELED);
                Toast.makeText(GoiDuLieuDenServer.this,
                        "Gởi dữ liệu không thành công!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



}