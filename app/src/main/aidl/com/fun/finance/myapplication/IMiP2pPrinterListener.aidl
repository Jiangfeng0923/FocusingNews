// IP2pListener.aidl
package com.fun.finance.myapplication;

import miui.miprint.P2pPrinterInfo;

/**
 * Created by jiangfeng3 on 19-6-29.
 */

interface IMiP2pPrinterListener {
    void onP2pPrinterChange(in P2pPrinterInfo printer, int state);
    void onPrinterConnectionComplete(boolean result);
}
