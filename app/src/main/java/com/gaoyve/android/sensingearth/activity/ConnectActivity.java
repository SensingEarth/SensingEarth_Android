package com.gaoyve.android.sensingearth.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyve.android.sensingearth.R;

import java.util.regex.Pattern;

/**
 * SensingEarth
 * Created by Gerry on 11/19/16.
 * Copyright © 2016 Gerry. All rights reserved.
 */

public class ConnectActivity extends AppCompatActivity {
    private EditText mPortEditText;
    private Button mConnectButton;
    private final String PORT_REGEX = "^([1-9]|[1-9]\\d{1,3}|[1-6][0-5][0-5][0-3][0-5])$"; // 1-65535

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        mPortEditText = (EditText) findViewById(R.id.port_edit_text);
        mConnectButton = (Button) findViewById(R.id.connect_button);
        mConnectButton.setOnClickListener(connectButtonListener);
    }

    private View.OnClickListener connectButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String portStr = mPortEditText.getText().toString().trim();
            boolean inputValid = Pattern.matches(PORT_REGEX, portStr);
            if (inputValid) {
                int port = Integer.parseInt(portStr);
                Intent intent = StartActivity.newIntent(ConnectActivity.this, port);
                startActivity(intent);
                System.out.println("ConnectButton Clicked");
            } else {
                Toast toast = Toast.makeText(ConnectActivity.this, R.string.port_error,Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if( v != null) v.setGravity(Gravity.CENTER);
                toast.show();
            }
        }
    };
}
