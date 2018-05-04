package com.namankhurpia.diaryapp_store_in_txt_file;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.edittext);
        btn=(Button)findViewById(R.id.savebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText.getText().toString().isEmpty()) {
                    String data = editText.getText().toString();
                    writeToFile(data);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter text",Toast.LENGTH_SHORT).show();
                }


            }
        });

        if(readFromFile()!=null)
        {
            editText.setText(readFromFile());
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Nothing was stored earlier",Toast.LENGTH_SHORT).show();
        }


    }

    private void writeToFile(String myData)
    {
        try{

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("diary.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(myData);
            outputStreamWriter.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String readFromFile()
    {
        String result="";

        try{

            InputStream inputStream=openFileInput("diary.txt");

            if(inputStream!=null)
            {
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                String tempString="";
                StringBuilder stringBuilder=new StringBuilder();
                while((tempString=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(tempString);
                }

                inputStream.close();

                result=stringBuilder.toString();

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

}
