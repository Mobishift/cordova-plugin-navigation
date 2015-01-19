package com.mobishift.cordova.plugins.navigation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.os.Bundle;
import android.location.Location;
import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;

import java.net.URISyntaxException;

public class Navigation extends CordovaPlugin {

    private static final String GET_ACTION = "navigate";

    private boolean isPackageInstalled(String packagename) {
        Context context = cordova.getActivity();
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean execute(String action, JSONArray args,
            final CallbackContext callbackContext) {
        if (args.length() != 6) {
			callbackContext.error("args invalid");
			return false;
		}
        if (GET_ACTION.equals(action)) {
            double flat, flng, tlat, tlng;
            String fname, tname;
            try{
                flat = args.getDouble(0);
                flng = args.getDouble(1);
                fname = args.getString(2);
                tlat = args.getDouble(3);
                tlng = args.getDouble(4);
                tname = args.getString(5);
            } catch (JSONException e) {
				callbackContext.error(e.getMessage());
				return false;
            }
            if (isPackageInstalled("com.autonavi.minimap")) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("androidamap://navi?sourceApplication=appname&poiname=" + tname + "&lat=" + tlat + "&lon=" + tlng + "&dev=0&style=2"));
                intent.setPackage("com.autonavi.minimap");
                cordova.getActivity().startActivity(intent);
            } else if (isPackageInstalled("com.baidu.BaiduMap")) {
                Intent intent;
                try {
                    intent = Intent.getIntent("intent://map/direction?coord_type=gcj02&origin=latlng:" + flat + "," + flng + "|name:" + fname + "&destination=latlng:" + tlat + "," + tlng + "|name:" + tname + "&mode=driving&src=上海乐界网络科技有限公司|乐停车#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                } catch (URISyntaxException e) {
                    callbackContext.error(e.getMessage());
                    return false;
                }
                cordova.getActivity().startActivity(intent);
            } else {
                callbackContext.error("no map installed");
            }
            return true;
        }
        return false;
    }
}