package com.example.damaznia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class adicionarProduto extends AppCompatActivity {
    float total=0;
    String valorCompra;
    carrinho kk = new carrinho();
    String compra;


    ListView listView1;
    String mTitle[] = {"Ameixas", "Desincha", "Amendoas Confeitadas", "Amendoas", "Amendoim"};
    String mDescription[] = {"Ameixas R$ 2,99", "Desincha R$ 3,99", "Amendoas Confeitadas R$ 4,99", "Amendoas R$ 5,99", "Amendoim R$ 6,99"};


    int images[] = {R.drawable.ameixas, R.drawable.desincha, R.drawable.amendoasconfeitadas, R.drawable.amendoas, R.drawable.amendoim};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto);

        listView1 = findViewById(R.id.listView1);
        // now create an adapter class

        final MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, mTitle, mDescription, images);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adicionarProduto.this, "Adicionamos o Produto: "+adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
                if(adapterView.getItemAtPosition(i).equals("Ameixas")){
                    total+=2.99;
                }
                if(adapterView.getItemAtPosition(i).equals("Desincha")){
                    total+=3.99;
                }
                if(adapterView.getItemAtPosition(i).equals("Amendoas Confeitadas")){
                    total+=4.99;
                }
                if(adapterView.getItemAtPosition(i).equals("Amendoas")){
                    total+=5.99;
                }
                if(adapterView.getItemAtPosition(i).equals("Amendoim")){
                    total+=6.99;
                }
                //Toast.makeText(adicionarCarrinho.this, "VOCE CLICOU "+total, Toast.LENGTH_LONG).show();
                TextView textView = ( TextView ) findViewById ( R.id.totalDaCompra);
                valorCompra = Float.toString(total);
                textView.setText (valorCompra);
                compra = textView.getText().toString();
            }
        });
    }

    public void finalizarCompra(View view) {
        somaTotal ad = new somaTotal();
        ad.setTotal(valorCompra);
        //Toast.makeText(adicionarCarrinho.this, "GET TOTAL PREENCHIDO "+ad.getTotal(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(adicionarProduto.this,    carrinho.class);
        startActivity(intent);


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