package thud.ql_sudungnuoc.dulieu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SuDungNuoc.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_KHACHHANG = "khachhang";
    public static final String KH_MSKH = "mskh";
    public static final String KH_HOTEN = "hoten";
    public static final String KH_DIENTHOAI = "dienthoai";
    public static final String KH_DOITUONG = "doituong";
    public static final String KH_THANHTOAN = "thanhtoan";
    public static final String KH_KHUVUC = "khuvuc";
    private static final String CREATE_TABLE_KHACHHANG
            = "Create Table " + TABLE_KHACHHANG + "("
            + KH_MSKH + " Integer Primary Key Autoincrement, "
            + KH_HOTEN + " Text, " + KH_DIENTHOAI + " Text, "
            + KH_DOITUONG + " Text, " + KH_THANHTOAN + " Text, "
            + KH_KHUVUC + " Integer);";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KHACHHANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("Drop Table If Exists " + TABLE_KHACHHANG);
        onCreate(db);
    }
}
