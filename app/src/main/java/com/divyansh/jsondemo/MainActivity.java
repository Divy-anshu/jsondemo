package com.divyansh.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnCreate, btnParse;
    TextView txtdata;
    String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate = findViewById(R.id.btnCreate);
        btnParse = findViewById(R.id.btnParse);
        txtdata = findViewById(R.id.txtdata);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    JSONObject obj1 = new JSONObject();
                    obj1.put("country","GB");
                    obj1.put("sunrise",1381107633);
                    obj1.put("sunset",1381149604);

                    JSONObject obj2 = new JSONObject();
                    obj2.put("id",711);
                    obj2.put("main","Smoke");
                    obj2.put("description","smoke");
                    obj2.put("icon","50n");

                    JSONArray array = new JSONArray();
                    array.put(obj2);

                    JSONObject obj3 = new JSONObject();
                    obj3.put("temp",304.15);
                    obj3.put("pressure",1009);

                    JSONObject object = new JSONObject();
                    object.put("sys",obj1);
                    object.put("weather",array);
                    object.put("main",obj3);
                    jsonString = object.toString();
                    txtdata.setText(jsonString);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
            }
        });

     btnParse.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             StringBuilder sb = new StringBuilder();
             try {
                 Toast.makeText(MainActivity.this, "parsing...", Toast.LENGTH_SHORT).show();
                 JSONObject jsonObject = new JSONObject(jsonString);
                 JSONObject jsonObject1 = jsonObject.getJSONObject("sys");
                 sb.append("country: "+jsonObject1.getString("country")+"\nsunrise: "+jsonObject1.getInt("sunrise")+"\nsunrise:"+jsonObject1.getInt("sunset")+"\n");
                 JSONArray jsonArray = jsonObject.getJSONArray("weather");
                 JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                 sb.append("description: "+jsonObject2.getString("description")+"\n");
                 JSONObject jsonObject3 = jsonObject.getJSONObject("main");
                 sb.append("Temperature: "+jsonObject3.getDouble("temp")+"\nPressure: "+jsonObject3.getInt("pressure"));
                 txtdata.setText(sb.toString());

             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     });
    }
}
