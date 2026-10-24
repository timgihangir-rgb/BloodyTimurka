/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.LinkHandler;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class AndroidLinkHandler
implements LinkHandler {
    Activity mActivity;

    public AndroidLinkHandler(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void openPage(String string2, String string3) {
        try {
            this.mActivity.getPackageManager().getPackageInfo(string2, 0);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
            string2 = intent;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)string3));
        }
        this.mActivity.startActivity((Intent)string2);
    }
}

