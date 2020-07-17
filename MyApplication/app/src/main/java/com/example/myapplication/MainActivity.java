package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button b_on, b_off, b_disc, b_list;
    ListView list;

    private static final int REQUEST_ENABLE = 0;
    private static final int REQUEST_DISCOVERABLE = 0;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_on = (Button)findViewById(R.id.b_on);
        b_off = (Button)findViewById(R.id.b_off);
        b_disc = (Button)findViewById(R.id.b_disc);
        b_list = (Button)findViewById(R.id.b_list);

        list = (ListView)findViewById(R.id.list);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter().enable();

        if(bluetoothAdapter == null){
            Toast.makeText(this, "Bluetooth not supported!", Toast.LENGTH_SHORT).show();
            finish();
        }

        b_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //turn on bluetooth
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLE);

            }
        });

        b_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //turn on bluetooth
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLE);

            }
        });

        b_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //turn off bluetooth
               bluetoothAdapter.disable();
            }
        });

        b_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make the device discoverable
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent, REQUEST_DISCOVERABLE);

            }
        });

        b_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //list paired devices
                Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();

                ArrayList<String> devices = new ArrayList<>();

                for(BluetoothDevice bt: pairedDevice){
                    devices.add(bt.getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, devices);
                list.setAdapter(arrayAdapter);
            }
        });
    }
}