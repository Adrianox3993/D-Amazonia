package com.example.damaznia;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class buscarProduto extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    String mTitle[] = {"Ameixa", "Desincha", "Amendoas Confeitadas", "Amendoas", "Amendoim"};
    String mDescription[] = {"Semente 9,99", "Desincha R$ 9,99", "Amendoas Confeitadas R$ 9,99", "Amendoas R$ 9,99", "Amendoim R$ 9,99"};
    int images[] = {R.drawable.ameixas, R.drawable.desincha, R.drawable.amendoasconfeitadas, R.drawable.amendoas, R.drawable.amendoim};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_produto);

        searchView = findViewById(R.id.searchs);
        listView = findViewById(R.id.listView);
        // now create an adapter class

        final MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(buscarProduto.this, "Facebook Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(buscarProduto.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(buscarProduto.this, "Twitter Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(buscarProduto.this, "Instagram Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(buscarProduto.this, "Youtube Description", Toast.LENGTH_SHORT).show();
                }


            }
        });
        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       //         Toast.makeText(buscarProduto.this, "VOCE CLICOU "+adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // so item click is done now check list view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
     //           Toast.makeText(buscarProduto.this, "TESTE DE BUSCA"+s, Toast.LENGTH_SHORT).show();
                        adapter.getFilter().filter(s);
          //      listView.getAdapter().equals(s);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    }


    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, int simple_list_item_1, String[] title, String[] description, int[] imgs) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            return row;
        }

    }
}