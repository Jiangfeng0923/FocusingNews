// IMiP2pManager.aidl
package miui.miprint;

import android.net.wifi.p2p.WifiP2pDevice;
import miui.miprint.IMiP2pPrinterListener;

/**
 * Created by jiangfeng3 on 19-6-29.
 */

 interface IMiP2pPrinterManager {
    void registerP2pPrinterListener(IMiP2pPrinterListener listener);
    void unregisterP2pPrinterListener(IMiP2pPrinterListener listener);
    void getP2pPrinters();
    void connectP2pPrinter(in WifiP2pDevice device);
}
