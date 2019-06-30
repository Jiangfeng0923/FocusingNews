package com.fun.finance.myapplication.printer;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.fun.finance.myapplication.IMiP2pPrinterListener;
import com.fun.finance.myapplication.IMiP2pPrinterManager;

import miui.miprint.P2pPrinterInfo;

public class MyService extends Service {
    private static final boolean DEBUG = true;
    private static final String TAG = "MiFindP2pPrintersS";

    private P2pPrinterManager mP2pPrinterManager;
    public class P2pPrinterManager extends IMiP2pPrinterManager.Stub {

        private RemoteCallbackList<IMiP2pPrinterListener> mP2pPrinterListeners = new RemoteCallbackList<IMiP2pPrinterListener>();

        @Override
        public void registerP2pPrinterListener(IMiP2pPrinterListener listener) throws RemoteException {
            if (listener != null) {
                mP2pPrinterListeners.register(listener);
            }
        }

        @Override
        public void unregisterP2pPrinterListener(IMiP2pPrinterListener listener) throws RemoteException {
            if (listener != null) {
                mP2pPrinterListeners.unregister(listener);
            }
        }

        @Override
        public void getP2pPrinters() throws RemoteException {
            if (DEBUG) Log.d(TAG, "getDiscoveredPrinters");
            dispatchP2pPrintersChange(new P2pPrinterInfo(),0);
        }

        @Override
        public void connectP2pPrinter(WifiP2pDevice device) throws RemoteException {
            if (DEBUG) Log.d(TAG, "connectP2pPrinter");
            // Attempt to add the discovered device as a P2P printer
            dispatchPrinterConnectionState(true);
        }

        synchronized void dispatchP2pPrintersChange(P2pPrinterInfo info, int state) {
            if (DEBUG) Log.d(TAG, "dispatchP2pPrintersChange");
            try {
                final int size = mP2pPrinterListeners.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    IMiP2pPrinterListener listener = mP2pPrinterListeners.getBroadcastItem(i);
                    listener.onP2pPrinterChange(info, state);
                }
                mP2pPrinterListeners.finishBroadcast();
            } catch (RemoteException e) {
                Log.e(TAG, "Can not call IMiP2pPrinterListener.", e);
            }
        }

        synchronized void dispatchPrinterConnectionState(boolean result) {
            if (DEBUG) Log.d(TAG, "dispatchPrinterConnectionState");
            try {
                final int size = mP2pPrinterListeners.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    IMiP2pPrinterListener listener = mP2pPrinterListeners.getBroadcastItem(i);
                    listener.onPrinterConnectionComplete(result);
                }
                mP2pPrinterListeners.finishBroadcast();
            } catch (RemoteException e) {
                Log.e(TAG, "Can not call IMiP2pPrinterListener.", e);
            }
        }

        void onDestroy() {
            mP2pPrinterListeners.kill();
        }

    }
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mP2pPrinterManager = new P2pPrinterManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mP2pPrinterManager.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mP2pPrinterManager.asBinder();
    }
}
