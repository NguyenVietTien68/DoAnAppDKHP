package com.example.doanappdkhp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnectMysSQL extends AppCompatActivity {
    TextView text, errorText;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_connect_mys_sql);

        text = findViewById(R.id.textView);
        errorText = findViewById(R.id.textView2);
        show = findViewById(R.id.button);
        //testDB();
//        new InfoAsyncTask().execute();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Async().execute();
            }
        });
    }
    class Async extends AsyncTask<Void, Void, Void> {

        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager
                        .getConnection("jdbc:mysql://192.168.1.6:3306/sqlquanlyhocphan","andro", "andro");

                Statement statement = con.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM nhom ");

                while(resultSet.next()) {

                    records += resultSet.getString(1) ;
                }
            }
            catch(Exception e)
            {
                error = e.toString();
            }
            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {

            text.setText(records);
            Toast.makeText(TestConnectMysSQL.this, ""+records, Toast.LENGTH_SHORT).show();

            if(error != "")

                errorText.setText(error);

            super.onPostExecute(aVoid);
        }
    }
}