/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import java.io.Serializable;

public class Civilization_SentMessages
implements Serializable {
    public static final long serialVersionUID = 0L;
    public int iToCivID;
    public Message_Type messageType = Message_Type.GIFT;
    public int iSentInTurnID;

    public Civilization_SentMessages(int iToCivID, Message_Type messageType) {
        this.iToCivID = iToCivID;
        this.messageType = messageType;
        this.iSentInTurnID = Game_Calendar.TURN_ID;
    }
}

