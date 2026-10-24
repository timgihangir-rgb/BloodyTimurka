/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Message;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageBox_GameData
implements Serializable {
    public static final long serialVersionUID = 0L;
    public List<Message> lMessages = new ArrayList<Message>();
    public int iMessagesSize = 0;

    MessageBox_GameData() {
    }

    public final void updateNextTurn(int nCivID) {
        for (int i = 0; i < this.getMessagesSize(); ++i) {
            if (!this.lMessages.get(i).updateNextTurn()) continue;
            if (!this.lMessages.get((int)i).requestsResponse) {
                if (this.lMessages.get((int)i).messageType == Message_Type.GIFT) {
                    this.lMessages.get(i).onAccept(nCivID);
                }
                this.lMessages.remove(i--);
                this.iMessagesSize = this.lMessages.size();
                continue;
            }
            if (CFG.game.getCiv(nCivID).getControlledByPlayer() || this.lMessages.get((int)i).iNumOfTurnsLeft >= -25) continue;
            this.lMessages.remove(i--);
            this.iMessagesSize = this.lMessages.size();
        }
    }

    public final void addMessage(Message nMessage) {
        if (nMessage.messageType == Message_Type.DISEASE) {
            int nNumOfDisseaseMessages = 0;
            for (int i = 0; i < this.getMessagesSize(); ++i) {
                if (this.lMessages.get((int)i).messageType != Message_Type.DISEASE) continue;
                ++nNumOfDisseaseMessages;
            }
            if (nNumOfDisseaseMessages > 2) {
                return;
            }
        } else {
            for (int i = 0; i < this.getMessagesSize(); ++i) {
                if (this.lMessages.get((int)i).iFromCivID != nMessage.iFromCivID || this.lMessages.get((int)i).messageType != nMessage.messageType) continue;
                if (nMessage.messageType == Message_Type.TECHNOLOGY_RESEARCHED) {
                    this.lMessages.get((int)i).iNumOfTurnsLeft = nMessage.iNumOfTurnsLeft;
                    return;
                }
                if (nMessage.messageType == Message_Type.UNCIVILIZED) {
                    this.lMessages.get((int)i).iNumOfTurnsLeft = nMessage.iNumOfTurnsLeft;
                    return;
                }
                if (nMessage.messageType != Message_Type.GIFT && nMessage.messageType != Message_Type.WE_CAN_SIGN_PEACE && nMessage.messageType != Message_Type.WE_CAN_SIGN_PEACE_STATUS_QUO && nMessage.messageType != Message_Type.GIFT_REFUSED && nMessage.messageType != Message_Type.GIFT_ACCEPTED && nMessage.messageType != Message_Type.PLUNDER_REPROT && nMessage.messageType != Message_Type.PLUNDER_REPROT_PLUNDRED && nMessage.messageType != Message_Type.REVOLT && nMessage.messageType != Message_Type.JOINED_A_WAR && nMessage.messageType != Message_Type.TRANSFER_CONTROL && nMessage.messageType != Message_Type.VASSALIZATION_ACCEPTED && nMessage.messageType != Message_Type.VASSALIZATION_REJECTED && nMessage.messageType != Message_Type.TRANSFER_CONTROL_REFUSED && nMessage.messageType != Message_Type.TRANSFER_CONTROL_ACCEPTED && nMessage.messageType != Message_Type.PROVINCES_NOT_SUPPLIED_STRAVES && nMessage.messageType != Message_Type.PROVINCES_NOT_SUPPLIED_LOST_CONTROL && nMessage.messageType != Message_Type.PROVINCES_NOT_SUPPLIED_LOST_CONTROL_ENEMY_LOST && nMessage.messageType != Message_Type.PROVINCES_LOST_CONTROL && nMessage.messageType != Message_Type.TRUCE && nMessage.messageType != Message_Type.TRUCE_EXPIRED && nMessage.messageType != Message_Type.LOAN_REPAID && nMessage.messageType != Message_Type.BULIT_FARM && nMessage.messageType != Message_Type.BULIT_PORT && nMessage.messageType != Message_Type.BULIT_TOWER && nMessage.messageType != Message_Type.BULIT_FORT && nMessage.messageType != Message_Type.BULIT_LIBRARY && nMessage.messageType != Message_Type.BUILT_ARMOURY && nMessage.messageType != Message_Type.BUILT_WORKSHOP && nMessage.messageType != Message_Type.FESTIVAL_IS_OVER && nMessage.messageType != Message_Type.ASSMILIATION_IS_OVER && nMessage.messageType != Message_Type.INVEST_IS_OVER && nMessage.messageType != Message_Type.INVEST_IS_OVER_DEVELOPMENT) {
                    return;
                }
                if (nMessage.messageType != Message_Type.TRANSFER_CONTROL || nMessage.iValue != this.lMessages.get((int)i).iValue) continue;
                return;
            }
        }
        this.lMessages.add(nMessage);
        this.iMessagesSize = this.lMessages.size();
    }

    public final void removeMessage(int i) {
        try {
            this.lMessages.remove(i);
            this.iMessagesSize = this.lMessages.size();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public final void removeMessage_TypeFrom(int nFromCivID, Message_Type nType) {
        try {
            for (int i = this.getMessagesSize() - 1; i >= 0; --i) {
                if (this.getMessage((int)i).iFromCivID != nFromCivID || this.getMessage((int)i).messageType != nType) continue;
                this.removeMessage(i);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    public Message getMessage(int i) {
        return this.lMessages.get(i);
    }

    public final void clearMessages() {
        this.lMessages.clear();
        this.iMessagesSize = 0;
    }

    public final int getMessagesSize() {
        return this.iMessagesSize;
    }
}

