package com.pethoalpar.sparkjavaclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import pethoalpar.com.alphttpclient.HttpCall;
import pethoalpar.com.alphttpclient.HttpRequest;

public class MainActivity extends AppCompatActivity {

    public static final String ADDRESS = "http://192.168.0.103:7676";

    private EditText modelEditText;
    private EditText fuelEditText;
    private EditText yearEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelEditText = (EditText) this.findViewById(R.id.editTextModel);
        fuelEditText = (EditText) this.findViewById(R.id.editTextFuel);
        yearEditText = (EditText) this.findViewById(R.id.editTextYear);

        this.findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(ADDRESS+ "/login");
                HashMap<String, String> params = new HashMap<>();
                JSONObject json = new JSONObject();
                try {
                    json.put("userName","demo");
                    json.put("password","demo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.put("",json.toString());
                httpCall.setParams(params);
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });

        this.findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(ADDRESS+ "/data/add");
                HashMap<String, String> params = new HashMap<>();
                params.put("",new Gson().toJson(new Car(modelEditText.getText().toString(),fuelEditText.getText().toString(),Integer.parseInt(yearEditText.getText().toString()))));
                httpCall.setParams(params);
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });

        this.findViewById(R.id.buttonGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.GET);
                httpCall.setUrl(ADDRESS+ "/data/getAllCars");
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });

        this.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(ADDRESS+ "/logout");
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });
    }
}
