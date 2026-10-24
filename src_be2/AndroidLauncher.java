/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AndroidLinkHandler;
import age.of.civilizations2.jakowski.lukasz.AoCGame;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFiles;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class AndroidLauncher
extends AndroidApplication {
    /*
     * Unable to fully structure code
     */
    protected void onCreate(Bundle var1_1) {
        block19: {
            block18: {
                super.onCreate(var1_1);
                var1_1 = new AlertDialog.Builder((Context)this).setTitle((CharSequence)"\u0414\u043e\u0431\u0440\u043e \u043f\u043e\u0436\u0430\u043b\u043e\u0432\u0430\u0442\u044c \u0432 Bloody Europe II").setMessage((CharSequence)"Bloody Europe II - \u043c\u043e\u0434\u0438\u0444\u0438\u043a\u0430\u0446\u0438\u044f, \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0435 \u043a\u043e\u0442\u043e\u0440\u043e\u0439 \u043f\u0440\u043e\u0438\u0441\u0445\u043e\u0434\u0438\u0442 \u0432 \u0415\u0432\u0440\u043e\u043f\u0435 \u0438 \u0435\u0451 \u043e\u043a\u0440\u0435\u0441\u0442\u043d\u043e\u0441\u0442\u044f\u0445. \u0412 \u043c\u043e\u0434\u0435 \u043f\u0440\u0438\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u0435\u0442 \u0442\u0440\u0438 \u0431\u043e\u043b\u044c\u0448\u0438\u0435 \u043a\u0430\u0440\u0442\u044b, \u0431\u043e\u043b\u044c\u0448\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0441\u0446\u0435\u043d\u0430\u0440\u0438\u0435\u0432 \u0438 \u0444\u043e\u0440\u043c\u0438\u0440\u0443\u0435\u043c\u044b\u0445 \u0446\u0438\u0432\u0438\u043b\u0438\u0437\u0430\u0446\u0438\u0439.").create();
                var1_1.setCancelable(true);
                var1_1.create();
                var1_1.show();
                var1_1 = new AndroidApplicationConfiguration();
                var1_1.useImmersiveMode = true;
                var2_3 = true;
                var3_4 = true;
                var4_5 = true;
                var5_6 = var2_3;
                var6_7 = var3_4;
                var5_6 = var2_3;
                var6_7 = var3_4;
                var5_6 = var2_3;
                var6_7 = var3_4;
                var8_13 = new InputStreamReader(this.openFileInput("config.ini"));
                var5_6 = var2_3;
                var6_7 = var3_4;
                try {
                    var7_8 = new BufferedReader((Reader)var8_13);
                }
                catch (NumberFormatException var7_11) {
                    var6_7 = var5_6;
                    ** GOTO lbl46
                }
                catch (IOException var7_12) {
                    ** continue;
                }
                while (true) {
                    var5_6 = var4_5;
                    var6_7 = var4_5;
                    var8_13 = var7_8.readLine();
                    var6_7 = var4_5;
                    if (var8_13 == null) break block18;
                    var5_6 = var4_5;
                    var6_7 = var4_5;
                    var8_13 = var8_13.replace(";", "").split("=");
                    var5_6 = var4_5;
                    var6_7 = var4_5;
                    if (!var8_13[0].equals("LANDSCAPE")) continue;
                    var5_6 = var4_5;
                    var6_7 = var4_5;
                    var4_5 = Boolean.parseBoolean(var8_13[1]);
                    continue;
                    break;
                }
                catch (IndexOutOfBoundsException var7_9) {
                    var6_7 = true;
                }
            }
lbl46:
            // 4 sources

            while (true) {
                if (!var6_7) break block19;
                this.setRequestedOrientation(6);
lbl49:
                // 2 sources

                while (true) {
                    this.initialize(new AoCGame(new AndroidLinkHandler((Activity)this)), (AndroidApplicationConfiguration)var1_1);
                    ((AndroidFiles)Gdx.files).setAPKExpansion(4, 0);
lbl54:
                    // 2 sources

                    return;
                    break;
                }
                break;
            }
            catch (IllegalArgumentException var7_10) {
                var6_7 = true;
                ** GOTO lbl46
            }
        }
        this.setRequestedOrientation(1);
        ** while (true)
        catch (GdxRuntimeException var1_2) {
            ** continue;
        }
    }
}

