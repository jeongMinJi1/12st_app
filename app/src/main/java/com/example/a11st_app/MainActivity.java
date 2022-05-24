package com.example.a11st_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText_java_1;
    TextView textView_java_1;

    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_java_1 = findViewById(R.id.edittext_main_1);
        textView_java_1 = findViewById(R.id.textview_main_1);

        Button rebuttton = findViewById(R.id.button_main_1);
        rebuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        if (requestQueue == null) {
             requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }


public void makeRequest() {
    String url = editText_java_1.getText().toString();

    StringRequest request = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    println("응답 -> " + response);
                    processResponse(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    println("에러 ->"  + error.getMessage());
                }
            }
    ) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<String,String>();

            return  params;
        }
    };

    request.setShouldCache(false);
    requestQueue.add(request);
    println("요청 보냄");
}

public void println(String data) {
    textView_java_1.append(data + "\n");
    }

    public void processResponse(String response)
    {
        Gson gson = new Gson();
        movielist movielist  = gson.fromJson(response,movielist.class);
        println("영화 정보의 수 : " + movielist.boxOfficeResult.dailyBoxOfficeList.size());
    }

}