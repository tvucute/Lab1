package fpoly.vuntph53431.demosqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.vuntph53431.demosqllite.Adapter.ProductAdapter;
import fpoly.vuntph53431.demosqllite.DAO.ProductDAO;
import fpoly.vuntph53431.demosqllite.Model.Product;

public class MainActivity extends AppCompatActivity {

    private EditText edtName,edtPrice,edtSize,edtstatus;

    private Button btnAdd,btnShow;

    private ListView lvLists;
    private ProductDAO productDAO;

    private ArrayList<Product> parraylist = new ArrayList<>();

    private ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtname);
        edtPrice = findViewById(R.id.edtprice);
        edtSize = findViewById(R.id.edtsize);
        btnAdd = findViewById(R.id.btnadd);
        btnShow = findViewById(R.id.btnshow);
        lvLists = findViewById(R.id.lvlists);
        edtstatus = findViewById(R.id.edtstatus);
        productDAO = new ProductDAO(MainActivity.this);
        loadData();
        adapter = new ProductAdapter(MainActivity.this,parraylist);
        lvLists.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String title = edtName.getText().toString();
                    String content = edtPrice.getText().toString();
                    String date = edtSize.getText().toString();
                    String status = edtstatus.getText().toString();
                    Product product = new Product(title,content,date,status);
                    boolean isSuccess  = productDAO.addProduct(product);
                    if (isSuccess){
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Dữ liệu thêm vào không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void delete(int index){
        productDAO.deleteProduct(parraylist.get(index));
        parraylist.remove(index);
        adapter.notifyDataSetChanged();
    }

    public void update(int index){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup,null);
        alert.setView(view);

        EditText udname = view.findViewById(R.id.edtudname);
        EditText udstatus =view.findViewById(R.id.edtudstatus);
        EditText udprice = view.findViewById(R.id.edtudprice);
        EditText udsize = view.findViewById(R.id.edtudsize);
        Button btnoke = view.findViewById(R.id.btnoke);
        Button btncancel = view.findViewById(R.id.btncancel);

        udname.setText(parraylist.get(index).getTitle());
        udprice.setText(parraylist.get(index).getContent());
        udsize.setText(parraylist.get(index).getDate());
        udstatus.setText(parraylist.get(index).getStatus());

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String newname = udname.getText().toString();
                    String newprice = udprice.getText().toString();
                    String newsize = udsize.getText().toString();
                    String newstatus = udstatus.getText().toString();

                    parraylist.get(index).setTitle(newname);
                    parraylist.get(index).setContent(newprice);
                    parraylist.get(index).setDate(newsize);
                    parraylist.get(index).setStatus(newstatus);

                    productDAO.updateProduct(parraylist.get(index));
                    adapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Nhập thất bại, mời nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData(){
        parraylist.removeAll(parraylist);
        for (Product product:productDAO.getProduct()){
            parraylist.add(product);
        }
    }
}