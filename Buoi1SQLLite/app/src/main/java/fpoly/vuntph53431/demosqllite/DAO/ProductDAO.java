package fpoly.vuntph53431.demosqllite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.vuntph53431.demosqllite.Database.DbHelper;
import fpoly.vuntph53431.demosqllite.Model.Product;

public class ProductDAO {

    private DbHelper dbHelper;

    private SQLiteDatabase database;

    public ProductDAO(Context mcontext) {
        dbHelper = new DbHelper(mcontext);
        database = dbHelper.getWritableDatabase(); //.getWri... là vừa đọc vừa ghi ngược lại .getRead... sẽ chỉ đọc
    }

    //hàm thêm sản phẩm

    public boolean addProduct(Product mproduct){
        ContentValues values = new ContentValues();
        values.put("TITLE",mproduct.getTitle());
        values.put("CONTENT",mproduct.getContent());
        values.put("DATE",mproduct.getDate());
        values.put("STATUS",mproduct.getStatus());
        long result = database.insert("SANPHAM",null,values);
        if(result>0)
            return true;
        return false;

    }

    public boolean deleteProduct(Product mproduct){
        long check = database.delete("SANPHAM","ID=?",new String[]{String.valueOf(mproduct.getId())});
        if (check>0)
            return true;
        return false;
    }

    public boolean updateProduct(Product mproduct){
        ContentValues values = new ContentValues();
        values.put("TITLE",mproduct.getTitle());
        values.put("CONTENT",mproduct.getContent());
        values.put("DATE",mproduct.getDate());
        values.put("STATUS",mproduct.getStatus());
        long check = database.update("SANPHAM",values,"ID=?", new String[]{String.valueOf(mproduct.getId())});
        if (check>0)
            return true;
        return false;
    }

    //lấy sản phẩm ra
    public ArrayList<Product> getProduct(){
        ArrayList<Product> parrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SANPHAM",null);
        while (cursor.moveToNext()){
            Product product = new Product(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            product.setId(cursor.getInt(0));
            parrayList.add(product);
        }
        return parrayList;
    }
}
