package com.mopub.nativeads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;
import org.json.JSONTokener;

class HttpResponses {
    private HttpResponses() {}

    static Bitmap asBitmap(final DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }

        final byte[] bytes = downloadResponse.getByteArray();
        final int contentLength = (int) downloadResponse.getContentLength();
        return BitmapFactory.decodeByteArray(bytes, 0, contentLength);
    }

    static JSONObject asJsonObject(final DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }

        try {
            final String responseString = asResponseString(downloadResponse);

            final JSONTokener tokener = new JSONTokener(responseString);
            return new JSONObject(tokener);
        } catch (Exception e) {
            return null;
        }
    }

    static String asResponseString(final DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }

        try {
            return new String(downloadResponse.getByteArray(), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
