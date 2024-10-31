package fpoly.vuntph53431.demosqllite.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NAME_DB="Product.db";

    public static final int VERSION_DB = 1;

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //HÀM NÀY SẼ CHẠY KHI CHƯA CÓ FILE DATABASE
        //HÀM NÀY SẼ DÙNG ĐỂ TẠO BẢNG
        //TẠO BẢNG SẢN PHẨM
        String createTable="CREATE TABLE SANPHAM(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TITLE TEXT NOT NULL," +
                "CONTENT INTEGER NOT NULL," +
                "DATE TEXT NOT NULL," +
                "STATUS TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        //HÀM NÀY CHỈ CHẠY KHI VERSION DATABASE THAY ĐỔI
        //DÙNG ĐỂ XÓA BẢNG
    }
}
