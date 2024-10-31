package fpoly.vuntph53431.demosqllite.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.vuntph53431.demosqllite.MainActivity;
import fpoly.vuntph53431.demosqllite.Model.Product;
import fpoly.vuntph53431.demosqllite.R;

public class ProductAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<Product> parraylist = new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<Product> parraylist) {
        this.context = context;
        this.parraylist = parraylist;
    }

    @Override
    public int getCount() {
        return parraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        view = inflater.inflate(R.layout.items_listview,viewGroup,false);
        TextView title = view.findViewById(R.id.tvtitle);
        TextView content = view.findViewById(R.id.tvcontent);
        TextView date = view.findViewById(R.id.tvdate);
        TextView status = view.findViewById(R.id.tvstatus);
        Button update = view.findViewById(R.id.btnupdate);
        Button delete = view.findViewById(R.id.btnxoa);

        title.setText(String.valueOf(parraylist.get(i).getTitle()));
        content.setText(parraylist.get(i).getContent());
        date.setText(String.valueOf(parraylist.get(i).getDate()));
        status.setText(parraylist.get(i).getStatus());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).delete(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).update(i);
            }
        });

        return view;

    }
}