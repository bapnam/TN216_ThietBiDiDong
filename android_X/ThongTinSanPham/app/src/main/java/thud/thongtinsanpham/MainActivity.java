package thud.thongtinsanpham;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edtTenSP, edtChiTietSP, edtGiaSP;
    TextInputLayout layoutTenSP, layoutChiTietSP, layoutGiaSP, layoutHinhSP;
    ImageView imgHinhSP;
    static Bitmap bitmap = null;
    static final int MA_BNNGOAI = 1;
    static final int MA_CAMERA = 2;
    static String[] DSQUYEN_BNNGOAI =
            {Manifest.permission.READ_EXTERNAL_STORAGE};
    static String[] DSQUYEN_CAMERA = {Manifest.permission.CAMERA};
    boolean QUYEN_BNNGOAI = false, QUYEN_CAMERA = false;
    ActivityResultLauncher<Intent> runCamera;
    ActivityResultLauncher<Intent> runGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTenSP = findViewById(R.id.edt_tenSP);
        edtChiTietSP = findViewById(R.id.edt_chitietSP);
        edtGiaSP = findViewById(R.id.edt_giaSP);
        imgHinhSP = findViewById(R.id.img_hinhSP);
        layoutTenSP = findViewById(R.id.layout_tenSP);
        layoutChiTietSP = findViewById(R.id.layout_chitietSP);
        layoutGiaSP = findViewById(R.id.layout_dongia);
        layoutHinhSP = findViewById(R.id.layout_hinhSP);

        KiemTraQuyenTruyCap();

        runCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            bitmap = (Bitmap) result.getData().
                                    getExtras().get("data");
                            imgHinhSP.setImageBitmap(bitmap);
                        } else
                            Toast.makeText(MainActivity.this, "L???i ch???p h??nh!",
                                    Toast.LENGTH_LONG).show();
                    }
                });

        runGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            if (result.getData() != null) {
                                Uri contentURI = result.getData().getData();
                                try {
                                    bitmap = MediaStore.Images.Media.
                                            getBitmap(getContentResolver(),
                                                    contentURI);
                                    imgHinhSP.setImageBitmap(bitmap);
                                } catch (IOException e) {
                                }
                            }
                        } else
                            Toast.makeText(MainActivity.this, "L???i ch???n h??nh!",
                                    Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void KiemTraQuyenTruyCap() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    DSQUYEN_BNNGOAI, MA_BNNGOAI);
        }
        else
            QUYEN_BNNGOAI = true;
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    DSQUYEN_CAMERA, MA_CAMERA);
        }
        else
            QUYEN_CAMERA = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MA_BNNGOAI:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    QUYEN_BNNGOAI = true;
                } else {
                    Toast.makeText(this, "L???i kh??ng c?? quy???n thao t??c tr??n t???p tin!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case MA_CAMERA:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    QUYEN_CAMERA = true;
                } else {
                    Toast.makeText(this, "L???i kh??ng c?? quy???n thao t??c tr??n Camera!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return;

    }

    public void LuuSanPham(View view) {
        String tenSP, chiTietSP, giaDA;
        tenSP = edtTenSP.getText().toString().trim();
        if (tenSP.length() < 1) {
            edtTenSP.requestFocus();
            edtTenSP.selectAll();
            layoutTenSP.setError("T??n s???n ph???m kh??ng ???????c r???ng!");
            return;
        }
        else
            layoutTenSP.setError(null);
        chiTietSP = edtChiTietSP.getText().toString().trim();
        if (chiTietSP.length() < 1) {
            edtChiTietSP.requestFocus();
            edtChiTietSP.selectAll();
            layoutChiTietSP.setError("Th??ng tin chi ti???t kh??ng ???????c r???ng!");
            return;
        }
        else
            layoutChiTietSP.setError(null);
        giaDA = edtGiaSP.getText().toString().trim();
        if (giaDA.length() < 1) {
            edtGiaSP.requestFocus();
            edtGiaSP.selectAll();
            layoutGiaSP.setError("????n gi?? s???n ph???m kh??ng ???????c r???ng!");
            return;
        }
        else
            layoutGiaSP.setError(null);
        if (bitmap == null) {
            layoutHinhSP.setError("Ch??a ch???n h??nh ???nh s???n ph???m!");
            return;
        }
        else
            layoutHinhSP.setError(null);
        Toast.makeText(this, "Nh???p th??ng tin s???n ph???m th??nh c??ng!",
                Toast.LENGTH_SHORT).show();
    }

    public void DongActivity(View view) {
        finish();
    }

    public void HinhSanPham(View view) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Th???c hi???n");
        String[] pictureDialogItems = {
                " Ch???n h??nh c?? s???n",
                " Ch???p h??nh m???i" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent galleryIntent = new Intent(
                                        Intent.ACTION_PICK,
                                        MediaStore.Images.
                                                Media.EXTERNAL_CONTENT_URI);
                                runGallery.launch(galleryIntent);
                                break;
                            case 1:
                                Intent intent = new Intent(
                                        MediaStore.
                                                ACTION_IMAGE_CAPTURE);
                                runCamera.launch(intent);
                                break;
                        }
                    }
                });
        pictureDialog.show();

    }

}