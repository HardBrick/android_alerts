package com.android.joseg.persistencia;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnAlertNormal;
    Button btnAlertFecha;
    Button btnAlertHora;
    Button btnAlertList;
    Button btnAlertListaCheck;
    Button btnGrabar;
    Button btnLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlertNormal = (Button)findViewById(R.id.btnAlertNormal);
        btnAlertFecha = (Button) findViewById(R.id.btnAlertFecha);
        btnAlertHora = (Button)findViewById(R.id.btnAlertHora);
        btnAlertList = (Button)findViewById(R.id.btnAlertList);
        btnAlertListaCheck = (Button)findViewById(R.id.btnAlertListaCheck);
        btnGrabar = (Button)findViewById(R.id.btnGrabar);
        btnLeer = (Button)findViewById(R.id.btnLeer);


        btnAlertNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertOpc = new AlertDialog.Builder(view.getContext())
                        .setTitle("Titulo de Alerta")
                        .setIcon(R.drawable.ic_android_black_24dp)
                        .setMessage("Mensaje de la Alerta")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"Aceptar",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No",null);
                AlertDialog alert = alertOpc.create();
                alert.show();


            }
        });


        btnAlertFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog fechaDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Log.d("TAG_",dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, 2018, 10, 14);
                fechaDialog.show();


            }
        });

        btnAlertHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog horaDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Log.d("TAG_",hourOfDay + ":" + minute);
                    }
                },19,25,true);
                horaDialog.show();
            }
        });

        btnAlertList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = {
                        "Uno",
                        "Dos",
                        "Tres"
                };
                AlertDialog.Builder alertOpc = new AlertDialog.Builder(view.getContext())
                        .setTitle("Titulo de Alerta")
                        .setIcon(R.drawable.ic_android_black_24dp)
                        .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"Item " + i,Toast.LENGTH_SHORT).show();
                            }
                        });
                alertOpc.create().show();
            }
        });



        btnAlertListaCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = {
                        "Uno",
                        "Dos",
                        "Tres"
                };
                final boolean[] selItems = new boolean[3];

                AlertDialog.Builder alertOpc = new AlertDialog.Builder(view.getContext())
                        .setTitle("Titulo de Alerta")
                        .setIcon(R.drawable.ic_android_black_24dp)
                        .setMultiChoiceItems(items, selItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int item, boolean b) {
                                selItems[item] = b;
                            }
                        })
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int item) {
                                for (int i = 0 ; i<selItems.length;i++){
                                    Log.d("TAG_","Item "  + i + " " + selItems[i]);
                                }
                            }
                        })
                        .setNegativeButton("Cancelar",null);
                alertOpc.create().show();
            }
        });

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("datos",MODE_PRIVATE);
                SharedPreferences.Editor editPreferences = preferences.edit();
                editPreferences.putString("user","joseg");
                editPreferences.putString("pass", "hola123");
                editPreferences.commit();

            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("datos",MODE_PRIVATE);
                Map<String,?> keys = preferences.getAll();
                for (Map.Entry<String,?> entry : keys.entrySet()
                     ) {
                    Log.d("TAG_", entry.getKey() + " - " + entry.getValue());
                }
            }
        });
    }
}
