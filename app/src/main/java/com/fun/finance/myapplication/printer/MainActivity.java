package com.fun.finance.myapplication.printer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fun.finance.myapplication.IMiP2pPrinterListener;
import com.fun.finance.myapplication.IMiP2pPrinterManager;
import com.fun.finance.myapplication.R;

import miui.miprint.P2pPrinterInfo;

public class MainActivity extends AppCompatActivity {
    private static final boolean DEBUG = true;
    private static final String TAG = "TestActivity";
    private static final String ACTION = "com.miui.MiFindP2pPrintersService";

    private IMiP2pPrinterManager mP2pPrinterManager;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DEBUG) Log.d(TAG, "onDestroy ");
        if (mP2pPrinterManager != null) {
            try {
                mP2pPrinterManager.unregisterP2pPrinterListener(mP2pPrinterListener);
            } catch (RemoteException e) {
                //never catch
            }
        }
        unbindService(mCon);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(ACTION);
        intent.setPackage(getPackageName());
        bindService(intent, mCon, Context.BIND_AUTO_CREATE);

        View btn = findViewById(R.id.test_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (DEBUG) Log.d(TAG, "getP2pPrinters");
                    mP2pPrinterManager.getP2pPrinters();
                } catch (RemoteException e) {
                    //never catch
                }
            }
        });
    }

    private IMiP2pPrinterListener mP2pPrinterListener = new IMiP2pPrinterListener.Stub() {
        @Override
        public void onP2pPrinterChange(P2pPrinterInfo printer, int state) throws RemoteException {
            if (DEBUG) Log.d(TAG, "onP2pPrinterChange:" + printer.toString() + " state:" + state);
        }

        @Override
        public void onPrinterConnectionComplete(boolean result) throws RemoteException {
            if (DEBUG) Log.d(TAG, "onPrinterConnectionComplete:" + result);
        }
    };

    private ServiceConnection mCon = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (DEBUG) Log.d(TAG, "onServiceConnected name :" + name.toString() + " service:" + service);
            mP2pPrinterManager = IMiP2pPrinterManager.Stub.asInterface(service);
            try {
                mP2pPrinterManager.registerP2pPrinterListener(mP2pPrinterListener);
            } catch (RemoteException e) {
                //never catch
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (DEBUG) Log.d(TAG, "onServiceDisconnected ");
            try {
                mP2pPrinterManager.unregisterP2pPrinterListener(mP2pPrinterListener);
            } catch (RemoteException e) {
                //never catch
            }
        }

        @Override
        public void onBindingDied(ComponentName name) {

        }

    };
}
