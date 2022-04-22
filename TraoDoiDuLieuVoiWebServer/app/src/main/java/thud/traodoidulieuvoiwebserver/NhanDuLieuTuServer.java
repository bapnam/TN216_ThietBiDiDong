package thud.traodoidulieuvoiwebserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NhanDuLieuTuServer extends AppCompatActivity {

    ListView listViewDuLieu;
    List<String> listDuLieu;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_du_lieu_tu_server);

        listViewDuLieu = findViewById(R.id.lst_dulieu);
        listDuLieu = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listDuLieu);
        listViewDuLieu.setAdapter(adapter);

    }

    public void ThucHienNhanDuLieu(View view) {
        if (Publics.HasInternet(this)){
            NhanDuLieu nhanDuLieu = new NhanDuLieu() ;
            nhanDuLieu.execute();
        } else {
            Toast.makeText(this,
                    "Lỗi kết nối Internet!", Toast.LENGTH_LONG).show();
        }

    }

    public void XoaDuLieu(View view) {
        listDuLieu.clear();
        adapter.notifyDataSetChanged();
    }

    private class NhanDuLieu extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            int ketqua = 0;
            try {
                URL url = new URL(Publics.URLNHANDULIEU);
                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type",
                        "application/json");
                conn.setDoOutput(false);
                conn.setDoInput(true);
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.connect();

                InputStream in = new BufferedInputStream(conn.getInputStream());
                String strDuLieu = Publics.StreamToString(in);
                int responsecode = conn.getResponseCode();

                if(responsecode == HttpsURLConnection.HTTP_OK){
                    JSONObject jsonObj = new JSONObject(strDuLieu);
                    JSONObject objData = jsonObj.getJSONObject("data");
                    JSONArray arrDuLieu = objData.getJSONArray("memes");
                    for (int i=0; i < arrDuLieu.length(); i++){
                        JSONObject dulieu = arrDuLieu.getJSONObject(i);
                        String id = dulieu.getString("id");
                        String hoten = dulieu.getString("name");
                        listDuLieu.add(id + " - " + hoten);
                        ketqua++;
                    }
                }
                in.close();
                conn.disconnect();

            } catch (Exception e){

            }

            return ketqua;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer > 0){
                setResult(RESULT_OK);
                adapter.notifyDataSetChanged();
                Toast.makeText(NhanDuLieuTuServer.this,
                        "Nhận được" + integer + " thông tin mới",
                        Toast.LENGTH_LONG).show();

            } else {
                setResult(RESULT_CANCELED);
                Toast.makeText(getApplicationContext(),
                        "Không nhận được thông tin mới",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}