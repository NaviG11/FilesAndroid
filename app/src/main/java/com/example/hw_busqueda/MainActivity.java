package com.example.hw_busqueda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tv, tv2, tv3, tv4, tv5;
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Doesn't find the file", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void search(View view) {
        String profession = e1.getText().toString();
        String department = e2.getText().toString();
//        || !department.equals("")
        if (!profession.equals(""))
            archivo(profession, department);
    }

    public void new_search(View view) {
        //Reset inputs and outputs
    }

    //Leer clientes.csv *0* 1 2 3
    //Leer cuentas.csv 0 *1* 2
    public void archivo(String x, String y) {
        x = x.toUpperCase();
        y = y.toUpperCase();
        File dir = Environment.getExternalStorageDirectory();
        File clientes = new File(dir.getAbsolutePath() + File.separator + "clientes.csv");
        File cuentas = new File(dir.getAbsolutePath() + File.separator + "cuentas.csv");
        File profesiones = new File(dir.getAbsolutePath() + File.separator + "profesiones.csv");
        File departamentos = new File(dir.getAbsolutePath() + File.separator + "departamentos.csv");

        try {
            BufferedReader cli = new BufferedReader(new FileReader(clientes));
            BufferedReader cuen = new BufferedReader(new FileReader(cuentas));
            BufferedReader pro = new BufferedReader(new FileReader(profesiones));
            BufferedReader dep = new BufferedReader(new FileReader(departamentos));
            String cliente;
            String cuenta;
            String profesion;
            String departamento;
            String res = "";
            String res2 = "";
            String res3 = "";
            String res4 = "";
            String depa = "";
            String prof = "";
            String codeProfession="";

            float inv = 0;
            int contProfession = 0;
            int contDepartment = 0;
            int contClient = 0;
            // x es profesion
            // y es departamento
            while ((profesion = pro.readLine()) != null) {
                //res += linea + "\n";
                String[] profe = profesion.split(";");
                if (profe[1].equals(x)) {
//                    res3 = res3 + " " + profe[1] + "\n";
                    // find de profession's code
                    codeProfession = profe[0];
                    break;
                }
            }
            pro.close();
            while ((departamento = dep.readLine()) != null) {
                //res += linea + "\n";
                String[] depar = departamento.split(";");
                if (depar[0].equals(depa)) {
                    res4 = res4 + " " + depar[1] + "\n";
                    break;
                }
            }
            dep.close();
            // Implement business logic
            while ((cliente = cli.readLine()) != null) {
                //res += linea + "\n";
                String[] lin = cliente.split(";");
                if (lin[2].equals(codeProfession)) {
//                    res = res + " " + lin[1] + "\n";
//                    prof = lin[2];
//                    depa = lin[3];
                    contClient++;
//                    break;
                }
            }

            cli.close();
            while ((cuenta = cuen.readLine()) != null) {
                //res += linea + "\n";
                String[] cue = cuenta.split(";");
                if (cue[1].equals(x)) {
                    res2 = res2 + "   " + cue[0] + "\n";
                }
            }
            cuen.close();


//            if (res.equals(""))
//                tv.setText("No existe el codigo del producto que deseas");
//            else
//                tv.setText(linea);
            // Mostrar nombres
            tv.setText(contClient+"");
            //Mostrar Cuentas
            tv2.setText(res);
            //Mostrar Profesion
            tv3.setText(res3);
            tv4.setText(res4);
//            tv4.setText(((float) inv / (float) tot) * 100 + " %");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void inicializar() {
//        tv = findViewById(R.id.textView4);
        tv2 = findViewById(R.id.textView6);
        tv3 = findViewById(R.id.textView8);
        tv4 = findViewById(R.id.textView10);
        tv5 = findViewById(R.id.textView12);

        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);

    }
}