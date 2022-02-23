package com.oriensi.ota;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RecoverySystem;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {


    private EditText mOtaPathView;
    private Button mUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);
        mOtaPathView = (EditText) findViewById(R.id.ota_path);
        mUpdate = (Button) findViewById(R.id.update);
        mUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                File ota = new File(mOtaPathView.getText().toString());
                try {
                    // RecoverySystem.verifyPackage(ota, null , null);
                    RecoverySystem.installPackage(this, ota);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
