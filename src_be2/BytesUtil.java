/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import java.io.ByteArrayInputStream;
import java.io.EOFException;

public class BytesUtil {
    public static int getInt(ByteArrayInputStream stream) throws EOFException {
        int ch4;
        int ch3;
        int ch2;
        int ch1 = stream.read();
        if ((ch1 | (ch2 = stream.read()) | (ch3 = stream.read()) | (ch4 = stream.read())) < 0) {
            throw new EOFException();
        }
        return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }
}

