package com.firstapp.retrofit_api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    TextView textView;

    ModelClass modelClass;
    Retrofit retrofit;
    MyApi myApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.text_view);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        retrofit=new Retrofit.Builder().baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        myApi=retrofit.create(MyApi.class);

        loadData();





    }

    private void loadData(){
        progressDialog.show();

        progressDialog.setMessage("Fetching Data-------");



        Call<ModelClass>call=myApi.getData();

        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                progressDialog.dismiss();

                if(response.code()!=200)
                {
                    Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                }
                else
                {
                    ModelClass modelClass=response.body();

                    String value="";

                    value+="UserId:- "+modelClass.getUserId()+"\n";
                    value+="Id:- "+modelClass.getId()+"\n";
                    value+="Title:- "+modelClass.getTitle()+"\n";
                    value+="Body:- "+modelClass.getBody()+"\n";
                    value+="Author:- "+modelClass.getReference()+"\n";

                    textView.append(value);



                }
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}