package com.zyb.ota;


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
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG="jglota";
    private static final String OTA_PACKAGE_PATH = "/data/media/0/update.zip";

    private Button mUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        Log.d(TAG, "===============>>>>>>>> test ota start");
        testOTA();

        //setContentView(R.layout.activity);
        //mUpdate = (Button) findViewById(R.id.update);
        //mUpdate.setOnClickListener(this);
    }

    private void testOTA() {
        final File file = new File(OTA_PACKAGE_PATH);
        final Handler mHandler = new Handler();
        final Context mContext;

        if (file.exists()) {
            try {
                Log.d(TAG, "file=" + file.getPath());
                RecoverySystem.ProgressListener listener = new RecoverySystem.ProgressListener() {
                    @Override
                    public void onProgress(int i) {
                        Log.d(TAG, "OTA_PACKAGE_PATH=" + OTA_PACKAGE_PATH + " i=" + i);
                        if (i == 100) {
                            Log.d(TAG, "===============>>>>>>>> process is in 100.will reboot to ota");
                            try {
                                RecoverySystem.installPackage(MainActivity.this, file);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                RecoverySystem.processPackage(MainActivity.this, file, listener, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(MainActivity.this,"Sdcard目录未找到update.zip文件",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                testOTA();

                //try {
                //    // RecoverySystem.verifyPackage(ota, null , null);
                //    RecoverySystem.installPackage(this, ota);
                //} catch (Exception e) {
                //    e.printStackTrace();
                //}
                //break;
        }
    }

}
