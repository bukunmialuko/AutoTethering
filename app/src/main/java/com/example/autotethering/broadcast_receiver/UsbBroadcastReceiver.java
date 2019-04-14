
package com.example.autotethering.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class UsbBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (usbIsConnected(context) || wifiIsConnected(context)) {
            launchSettings(context);
        }

    }

    public boolean usbIsConnected(Context c) {
        Intent intent = c.registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
        return intent.getExtras().getBoolean("connected");
    }

    private void launchSettings(Context context) {
        boolean throwsException = false;
        //--
        try {
            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.TetherSettings");
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            throwsException = true;
        }


        //--
        if (throwsException) {
            Intent dialogIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(dialogIntent);
        }
    }

    private boolean wifiIsConnected(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return wifi.isConnected();

    }


}
