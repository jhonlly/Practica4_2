package com.majada.jhona.practica4_2;
//TODO Almacenar fichero en la tarjeta SD.
import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button btn_agregar;
    private EditText et1;
    private TextView et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.et1);
        et2= (TextView) findViewById(R.id.et2);
        et2.setClickable(true);
        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargar_datos();
            }
        });

    }

    private boolean existe_archivo(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f])) {
                return true;
            }
        return false;
    }

    public void cargar_datos() {
        //String[] archivos = fileList();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), "mytextfile.txt");
        //if (existe_archivo(archivos, "mytextfile.txt"))
            try {
                FileInputStream fIn = new FileInputStream(file);
                InputStreamReader archivo = new InputStreamReader(fIn);
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";

                while (linea != null) {
                    todo = todo + linea + " ";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et2.setText(todo);
            } catch (IOException e) {
                Toast.makeText(this, "No se pudo leer",
                        Toast.LENGTH_SHORT).show();
            }
    }
//Grabar datos
    public void agregar(View v) {
        try {
            File tarjeta = Environment.getExternalStorageDirectory();
            File file = new File(tarjeta.getAbsolutePath(), "mytextfile.txt");
            OutputStreamWriter archivo = new OutputStreamWriter(new FileOutputStream(
                    file));

            archivo.append("\n"+ et1.getText().toString());
            archivo.flush();
            archivo.close();
           Toast.makeText(this, "Datos guardados",Toast.LENGTH_SHORT).show();
            et1.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo grabar los datos.",Toast.LENGTH_SHORT).show();
        }

    }

}
