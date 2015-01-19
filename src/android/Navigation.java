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

public class Navigation extends CordovaPlugin {

    private static final String GET_ACTION = "navigate";

    @Override
    public boolean execute(String action, JSONArray args,
            final CallbackContext callbackContext) {
        if (args.length() != 2) {
			callbackContext.error("args invalid");
			return false;
		}
        if (GET_ACTION.equals(action)) {
            double lng = args.getDouble(0);
            double lat = args.getDouble(1);
            Uri uri = Uri.parse("geo:" + lng + "," + lat);  
            Intent it = new Intent(Intent.Action_VIEW,uri);  
            cordova.getActivity().startActivity(it);
            return true;
        }
        return false;
    }
}