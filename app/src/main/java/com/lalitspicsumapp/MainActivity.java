package com.lalitspicsumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lalitspicsumapp.model.Authors;
import com.lalitspicsumapp.network.APIClient;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GridView gdview;
    CustomAdapter customAdapter;
    public static List<Authors> authorsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResourceBundle.clearCache();
        gdview = findViewById(R.id.gridview);
        authorsList =   new ArrayList<>();
        Call<List<Authors>> call    = APIClient.apIinterface().getAuthors();
        call.enqueue(new Callback<List<Authors>>() {
            @Override
            public void onResponse(Call<List<Authors>> call, Response<List<Authors>> response) {

                if(response.isSuccessful()){
                    authorsList = response.body();
                    customAdapter = new CustomAdapter(response.body(),MainActivity.this);
                    gdview.setAdapter(customAdapter);
                    gdview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent   =   new Intent();
//                            intent.putExtra("author",authorsList.get(i).getAuthor());
//                            intent.putExtra("image","https://picsum.photos/300/300?image="+authorsList.get(i).getId());
                            startActivity(new Intent(getApplicationContext(), details.class)
                                    .putExtra("author",authorsList.get(i).getAuthor())
                                    .putExtra("image","https://picsum.photos/300/300?image="+authorsList.get(i).getId()));
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "An Error !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Authors>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An Error !"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public class CustomAdapter extends BaseAdapter{
        public List<Authors> authorsList;
        public Context context;

        public CustomAdapter(List<Authors> authorsList, Context context) {
            this.authorsList = authorsList;
            this.context = context;
        }

        public List<Authors> getAuthorsList() {
            return authorsList;
        }

        public void setAuthorsList(List<Authors> authorsList) {
            this.authorsList = authorsList;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return authorsList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View conview, ViewGroup viewGroup) {
            View view = LayoutInflater.from(context).inflate(R.layout.rowdata, null);
            TextView caption    =   view.findViewById(R.id.textView);
            ImageView img    =   view.findViewById(R.id.imageView);

            //set Data
            caption.setText(authorsList.get(i).getAuthor());
            Glide.with(context)
                    .load("https://picsum.photos/300/300?image="+authorsList.get(i).getId())
                    .into(img);
            return view;
        }
    }
}
