package com.firstapp.using_retrofit_load_image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListModel listModel;
    MyListAdapter myListAdapter;
    MyApiCall myApiCall;
    Retrofit retrofit;
    List<ListModel>listModelList=new ArrayList<>();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.List);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        retrofit=new Retrofit.Builder().baseUrl("https://run.mocky.io.v3/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        myApiCall=retrofit.create(MyApiCall.class);

        myListAdapter=new MyListAdapter(this,listModelList);
        recyclerView.setAdapter(myListAdapter);

        loadData();

    }

    private void loadData() {
        progressDialog.show();

        Call<List<ListModel>> call=myApiCall.getList();

        call.enqueue(new Callback<List<ListModel>>() {
            @Override
            public void onResponse(Call<List<ListModel>> call, Response<List<ListModel>> response) {
                progressDialog.dismiss();
                if(response.code()!=200)
                {
                    Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    List<ListModel> listModelList1=response.body();

                    for (ListModel listModel:listModelList1)
                    {
                        int album=listModel.getAlbumId();
                        String title=listModel.getTitle();
                        String url=listModel.getUrl();
                        String thumbUrl=listModel.getThumbnailUrl();

                        listModel=new ListModel(album,title,url,thumbUrl);
                        listModelList1.add(listModel);

                        myListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ListModel>> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}