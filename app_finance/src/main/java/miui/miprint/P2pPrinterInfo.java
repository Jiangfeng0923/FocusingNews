package miui.miprint;

import android.net.Uri;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.print.PrinterId;

public class P2pPrinterInfo implements Parcelable {
    private PrinterId mPrinterId;
    private String mTitle;
    private String mSummary;
    private String mLocation;
    private Uri mUuid;
    private WifiP2pDevice mPeer;

    public P2pPrinterInfo(){
    }

    public P2pPrinterInfo(PrinterId printerId, String title, String summary, String location, Uri uuid, WifiP2pDevice peer) {
        mPrinterId = printerId;
        mTitle = title;
        mSummary = summary;
        mLocation = location;
        mUuid = uuid;
        mPeer = peer;
    }


    public P2pPrinterInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mPrinterId, flags);
        parcel.writeString(mTitle);
        parcel.writeString(mSummary);
        parcel.writeString(mLocation);
        parcel.writeParcelable(mUuid, flags);
        parcel.writeParcelable(mPeer, flags);
    }

    public void readFromParcel(Parcel parcel) {
        mPrinterId = parcel.readParcelable(null);
        mTitle = parcel.readString();
        mSummary = parcel.readString();
        mLocation = parcel.readString();
        mUuid = parcel.readParcelable(null);
        mPeer = parcel.readParcelable(null);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DiscoveredPrinterInfo{");
        if (mPrinterId != null) {
            builder.append("mPrinterId=").append(mPrinterId.toString());
        }
        builder.append(", mTitle=").append(mTitle);
        builder.append(", mSummary=").append(mSummary);
        builder.append(", mLocation=").append(mLocation);
        if (mUuid != null) {
            builder.append(", mUuid=").append(mUuid.toString());
        }
        if (mPeer != null) {
            builder.append(", mPeer=").append(mPeer.toString());
        }
        builder.append('}');
        return builder.toString();
    }

    public Uri getUri() {
        return mUuid;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Creator<P2pPrinterInfo> CREATOR = new Creator<P2pPrinterInfo>() {
        public P2pPrinterInfo createFromParcel(Parcel source) {
            return new P2pPrinterInfo(source);
        }

        public P2pPrinterInfo[] newArray(int size) {
            return new P2pPrinterInfo[size];
        }
    };

    public static final class Builder {
        private PrinterId mPrinterId;
        private String mTitle;
        private String mSummary;
        private String mLocation;
        private Uri mUuid;
        private WifiP2pDevice mPeer;

        public Builder(PrinterId printerId, String title, String summary, WifiP2pDevice peer) {
            mPrinterId = printerId;
            mTitle = title;
            mSummary = summary;
            mPeer = peer;
        }

        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        public Builder setUri(Uri uuid) {
            mUuid = uuid;
            return this;
        }

        public P2pPrinterInfo build() {
            return new P2pPrinterInfo(mPrinterId, mTitle, mSummary, mLocation,
                    mUuid, mPeer);
        }
    }
}

