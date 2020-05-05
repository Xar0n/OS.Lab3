package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.String;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] data = {"windows-1251", "koi8-r"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Лабораторная 3 Нецветаев Антон ИИТиАД ЭВМБ-18-1");
        setContentView(R.layout.activity_main);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button btn = (Button) findViewById(R.id.button);
        Button btnRead = (Button) findViewById(R.id.buttonRead);
        Button btnWrite = (Button) findViewById(R.id.buttonWrite);
        final EditText editText1 = (EditText) findViewById(R.id.editText6);
        final EditText editText2 = (EditText) findViewById(R.id.editText5);
        final EditText editTextRead = (EditText) findViewById(R.id.editText);
        final EditText editTextWrite = (EditText) findViewById(R.id.editText2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(1);
        OnClickListener oclBtn = new OnClickListener() {
            public void onClick(View v) {
                try {
                    if (editText1.getText() == null || editText1.getText().toString().trim().isEmpty()) throw new Exception("Поле пусто");
                    byte[] inputText = editText1.getText().toString().getBytes(spinner.getSelectedItem().toString());
                    String outText = new String(inputText, spinner2.getSelectedItem().toString());
                    editText2.setText(outText);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),
                            ex.getMessage(), Toast.LENGTH_LONG).show();
                    editText2.setText("");
                }
            }
        };
        btn.setOnClickListener(oclBtn);
        OnClickListener oclBtnRead = new OnClickListener() {
            public void onClick(View v) {
                try {
                    if (editTextRead.getText() == null || editTextRead.getText().toString().trim().isEmpty()) throw new Exception("Поле пусто");
                    openFile(editTextRead.getText().toString(), editText1);
                    Toast.makeText(getApplicationContext(),
                            "Файл успешно прочитан", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),
                            ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        btnRead.setOnClickListener(oclBtnRead);
        OnClickListener oclBtnWrite = new OnClickListener() {
            public void onClick(View v) {
                try {
                    if (editTextWrite.getText() == null || editTextWrite.getText().toString().trim().isEmpty()) throw new Exception("Поле пусто");
                    saveFile(editTextWrite.getText().toString(), editText2);
                    Toast.makeText(getApplicationContext(),
                            "Файл успешно записан", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),
                            ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        btnWrite.setOnClickListener(oclBtnWrite);
    }

    private void openFile(String fileName, EditText editText) {
        try {
            InputStream inputStream = openFileInput(fileName);
            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                inputStream.close();
                editText.setText(builder.toString());
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void saveFile(String fileName, EditText editText) {
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(editText.getText().toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}