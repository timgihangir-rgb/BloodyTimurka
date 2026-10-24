/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Keyboard;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Keyboard
extends SliderMenu {
    public List<String> lKeys = new ArrayList<String>();
    public List<String> lKeysRUS = new ArrayList<String>();
    public List<String> lKeysSHIFT = new ArrayList<String>();
    public List<String> lKeysNUM = new ArrayList<String>();
    public List<String> lKeys123 = new ArrayList<String>();
    public int animationStepID = 0;
    public int animationChangePosY;
    public boolean closeMenu = false;
    public long barTime;
    public boolean drawBar;
    public static boolean shift = false;
    public static boolean rus = false;
    public static boolean numbers = false;
    public int iTextWidth;
    public int iTextHeight;
    public static boolean colorPickerMode = false;
    public static int activeColor_RGB_ID = -1;
    public static boolean commandsMode = false;
    public static int changeCivilizationNameMode = 0;
    public static int changeProvinceNameMode = -1;
    public static int changeCityNameIDToo = -1;
    protected static int allianceID;
    protected static boolean allianceRenameMode;
    protected static SliderMenuTitle menuElementAlTitle;

    public Keyboard() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.settingsManager.LANGUAGE_TAG.equals("ru")) {
            this.lKeys.add("\u0439");
            this.lKeys.add("\u0446");
            this.lKeys.add("\u0443");
            this.lKeys.add("\u043a");
            this.lKeys.add("\u0435");
            this.lKeys.add("\u043d");
            this.lKeys.add("\u0433");
            this.lKeys.add("\u0448");
            this.lKeys.add("\u0449");
            this.lKeys.add("\u0437");
            this.lKeys.add("\u0444");
            this.lKeys.add("\u044b");
            this.lKeys.add("\u0432");
            this.lKeys.add("\u0430");
            this.lKeys.add("\u043f");
            this.lKeys.add("\u0440");
            this.lKeys.add("\u043e");
            this.lKeys.add("\u043b");
            this.lKeys.add("\u0434");
            this.lKeys.add("\u044f");
            this.lKeys.add("\u0447");
            this.lKeys.add("\u0441");
            this.lKeys.add("\u043c");
            this.lKeys.add("\u0438");
            this.lKeys.add("\u0442");
            this.lKeys.add("\u044c");
            this.lKeys.add("SH");
            this.lKeys.add("<<");
            this.lKeys.add("123");
            this.lKeys.add("Space");
            this.lKeys.add(",");
            this.lKeys.add(".");
            this.lKeysSHIFT.add("\u0419");
            this.lKeysSHIFT.add("\u0426");
            this.lKeysSHIFT.add("\u0423");
            this.lKeysSHIFT.add("\u041a");
            this.lKeysSHIFT.add("\u0415");
            this.lKeysSHIFT.add("\u041d");
            this.lKeysSHIFT.add("\u0413");
            this.lKeysSHIFT.add("\u0428");
            this.lKeysSHIFT.add("\u0429");
            this.lKeysSHIFT.add("\u0417");
            this.lKeysSHIFT.add("\u0424");
            this.lKeysSHIFT.add("\u042b");
            this.lKeysSHIFT.add("\u0412");
            this.lKeysSHIFT.add("\u0410");
            this.lKeysSHIFT.add("\u041f");
            this.lKeysSHIFT.add("\u0420");
            this.lKeysSHIFT.add("\u041e");
            this.lKeysSHIFT.add("\u041b");
            this.lKeysSHIFT.add("\u0414");
            this.lKeysSHIFT.add("\u042f");
            this.lKeysSHIFT.add("\u0427");
            this.lKeysSHIFT.add("\u0421");
            this.lKeysSHIFT.add("\u041c");
            this.lKeysSHIFT.add("\u0418");
            this.lKeysSHIFT.add("\u0422");
            this.lKeysSHIFT.add("\u042c");
            this.lKeysNUM.add("1");
            this.lKeysNUM.add("2");
            this.lKeysNUM.add("3");
            this.lKeysNUM.add("4");
            this.lKeysNUM.add("5");
            this.lKeysNUM.add("6");
            this.lKeysNUM.add("7");
            this.lKeysNUM.add("8");
            this.lKeysNUM.add("9");
            this.lKeysNUM.add("0");
            this.lKeys123.add("\u0445");
            this.lKeys123.add("\u0425");
            this.lKeys123.add("\u0436");
            this.lKeys123.add("\u0416");
            this.lKeys123.add("\u044d");
            this.lKeys123.add("\u042d");
            this.lKeys123.add("\u0431");
            this.lKeys123.add("\u0411");
            this.lKeys123.add("\u044e");
            this.lKeys123.add("\u042e");
            this.lKeys123.add("-");
            this.lKeys123.add("+");
            this.lKeys123.add("(");
            this.lKeys123.add(")");
            this.lKeys123.add("!");
            this.lKeys123.add("?");
        } else {
            this.lKeys.add("q");
            this.lKeys.add("w");
            this.lKeys.add("e");
            this.lKeys.add("r");
            this.lKeys.add("t");
            this.lKeys.add("y");
            this.lKeys.add("u");
            this.lKeys.add("i");
            this.lKeys.add("o");
            this.lKeys.add("p");
            this.lKeys.add("a");
            this.lKeys.add("s");
            this.lKeys.add("d");
            this.lKeys.add("f");
            this.lKeys.add("g");
            this.lKeys.add("h");
            this.lKeys.add("j");
            this.lKeys.add("k");
            this.lKeys.add("l");
            this.lKeys.add("z");
            this.lKeys.add("x");
            this.lKeys.add("c");
            this.lKeys.add("v");
            this.lKeys.add("b");
            this.lKeys.add("n");
            this.lKeys.add("m");
            this.lKeys.add("SH");
            this.lKeys.add("<<");
            this.lKeys.add("123");
            this.lKeys.add("Space");
            this.lKeys.add(",");
            this.lKeys.add(".");
            this.lKeysSHIFT.add("Q");
            this.lKeysSHIFT.add("W");
            this.lKeysSHIFT.add("E");
            this.lKeysSHIFT.add("R");
            this.lKeysSHIFT.add("T");
            this.lKeysSHIFT.add("Y");
            this.lKeysSHIFT.add("U");
            this.lKeysSHIFT.add("I");
            this.lKeysSHIFT.add("O");
            this.lKeysSHIFT.add("P");
            this.lKeysSHIFT.add("A");
            this.lKeysSHIFT.add("S");
            this.lKeysSHIFT.add("D");
            this.lKeysSHIFT.add("F");
            this.lKeysSHIFT.add("G");
            this.lKeysSHIFT.add("H");
            this.lKeysSHIFT.add("J");
            this.lKeysSHIFT.add("K");
            this.lKeysSHIFT.add("L");
            this.lKeysSHIFT.add("Z");
            this.lKeysSHIFT.add("X");
            this.lKeysSHIFT.add("C");
            this.lKeysSHIFT.add("V");
            this.lKeysSHIFT.add("B");
            this.lKeysSHIFT.add("N");
            this.lKeysSHIFT.add("M");
            this.lKeysNUM.add("1");
            this.lKeysNUM.add("2");
            this.lKeysNUM.add("3");
            this.lKeysNUM.add("4");
            this.lKeysNUM.add("5");
            this.lKeysNUM.add("6");
            this.lKeysNUM.add("7");
            this.lKeysNUM.add("8");
            this.lKeysNUM.add("9");
            this.lKeysNUM.add("0");
            this.lKeys123.add("@");
            this.lKeys123.add("*");
            this.lKeys123.add("#");
            this.lKeys123.add(":");
            this.lKeys123.add(";");
            this.lKeys123.add("&");
            this.lKeys123.add("_");
            this.lKeys123.add("(");
            this.lKeys123.add(")");
            this.lKeys123.add("-");
            this.lKeys123.add("+");
            this.lKeys123.add("'");
            this.lKeys123.add("\"");
            this.lKeys123.add("%");
            this.lKeys123.add("!");
            this.lKeys123.add("?");
        }
        for (i = 0; i < 10; ++i) {
            menuElements.add(new Button_Keyboard(this.lKeys.get(i), (CFG.GAME_WIDTH - CFG.PADDING * 11) / 10 * i + CFG.PADDING * i, CFG.PADDING * 2 + CFG.PADDING * 2 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (CFG.GAME_WIDTH - CFG.PADDING * 11) / 10, CFG.BUTTON_HEIGHT, Button.TypeOfButton.KEYBOARD, true));
        }
        for (i = 0; i < 10; ++i) {
            ((MenuElement)menuElements.get(i)).setPosX(((MenuElement)menuElements.get(i)).getPosX() + (CFG.GAME_WIDTH - ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth()) / 2);
        }
        for (i = 0; i < 9; ++i) {
            menuElements.add(new Button_Keyboard(this.lKeys.get(i + 10), ((MenuElement)menuElements.get(0)).getWidth() * i + CFG.PADDING * i, CFG.PADDING * 2 + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), ((MenuElement)menuElements.get(0)).getWidth(), CFG.BUTTON_HEIGHT, Button.TypeOfButton.KEYBOARD, true));
        }
        for (i = 10; i < 19; ++i) {
            ((MenuElement)menuElements.get(i)).setPosX(((MenuElement)menuElements.get(i)).getPosX() + (CFG.GAME_WIDTH - ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth()) / 2);
        }
        for (i = 0; i < 7; ++i) {
            menuElements.add(new Button_Keyboard(this.lKeys.get(i + 19), ((MenuElement)menuElements.get(0)).getWidth() * i + CFG.PADDING * i, CFG.PADDING * 2 + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 2 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), ((MenuElement)menuElements.get(0)).getWidth(), CFG.BUTTON_HEIGHT, Button.TypeOfButton.KEYBOARD, true));
        }
        for (i = 19; i < 26; ++i) {
            ((MenuElement)menuElements.get(i)).setPosX(((MenuElement)menuElements.get(i)).getPosX() + (CFG.GAME_WIDTH - ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth()) / 2);
        }
        menuElements.add(new Button_Keyboard(this.lKeys.get(26), CFG.PADDING, CFG.PADDING * 2 + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 2 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), ((MenuElement)menuElements.get(19)).getPosX() - CFG.PADDING * 2, CFG.BUTTON_HEIGHT, Button.TypeOfButton.KEYBOARD_OPTIONS, true));
        menuElements.add(new Button_Keyboard(this.lKeys.get(27), ((MenuElement)menuElements.get(25)).getPosX() + ((MenuElement)menuElements.get(25)).getWidth() + CFG.PADDING, CFG.PADDING * 2 + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 2 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), CFG.GAME_WIDTH - ((MenuElement)menuElements.get(25)).getPosX() - ((MenuElement)menuElements.get(25)).getWidth() - CFG.PADDING * 2, CFG.BUTTON_HEIGHT, Button.TypeOfButton.KEYBOARD_OPTIONS, true));
        menuElements.add(new Button_Keyboard(this.lKeys.get(28), CFG.PADDING, CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), ((MenuElement)menuElements.get(0)).getWidth() * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), Button.TypeOfButton.KEYBOARD_OPTIONS, true));
        menuElements.add(new Button_Keyboard(this.lKeys.get(29), CFG.PADDING * 2 + ((MenuElement)menuElements.get(0)).getWidth() * 2, CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), CFG.GAME_WIDTH - ((MenuElement)menuElements.get(0)).getWidth() * 4 - CFG.PADDING * 5, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), Button.TypeOfButton.KEYBOARD, true));
        menuElements.add(new Button_Keyboard(this.lKeys.get(30), CFG.PADDING * 3 + ((MenuElement)menuElements.get(0)).getWidth() * 2 + CFG.GAME_WIDTH - ((MenuElement)menuElements.get(0)).getWidth() * 4 - CFG.PADDING * 5, CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), ((MenuElement)menuElements.get(0)).getWidth(), (int)((float)CFG.BUTTON_HEIGHT * 0.8f), Button.TypeOfButton.KEYBOARD_OPTIONS, true));
        menuElements.add(new Button_Keyboard(this.lKeys.get(31), CFG.PADDING * 4 + ((MenuElement)menuElements.get(0)).getWidth() * 3 + CFG.GAME_WIDTH - ((MenuElement)menuElements.get(0)).getWidth() * 4 - CFG.PADDING * 5, CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), ((MenuElement)menuElements.get(0)).getWidth(), (int)((float)CFG.BUTTON_HEIGHT * 0.8f), Button.TypeOfButton.KEYBOARD_OPTIONS, true));
        menuElements.add(new Button_Keyboard(null, CFG.GAME_WIDTH - ((MenuElement)menuElements.get(0)).getWidth() * 2 - CFG.PADDING, CFG.PADDING, ((MenuElement)menuElements.get(0)).getWidth() * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), Button.TypeOfButton.KEYBOARD_SAVE, true));
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.PADDING * 2 - ((MenuElement)menuElements.get(menuElements.size() - 2)).getPosY() - ((MenuElement)menuElements.get(menuElements.size() - 2)).getHeight(), CFG.GAME_WIDTH, CFG.PADDING * 2 + ((MenuElement)menuElements.get(menuElements.size() - 2)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 2)).getHeight(), menuElements, false, false);
        this.updateLanguage();
        CFG.updateKeyboard_Actions();
    }

    @Override
    public void updateLanguage() {
        this.lKeys.set(26, CFG.langManager.get("Shift"));
        this.getMenuElement(26).setText(this.lKeys.get(26));
        this.getMenuElement(32).setText(CFG.langManager.get("Save"));
    }

    @Override
    public final void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
        this.updateChangePosY();
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + this.animationChangePosY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getHeight());
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + this.animationChangePosY, ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getHeight(), true, false);
        oSB.setColor(new Color(0.025f, 0.03f, 0.092f, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.animationChangePosY + this.getMenuElement(32).getHeight() + CFG.PADDING * 2, this.getWidth(), this.getHeight() - this.getMenuElement(32).getHeight() - CFG.PADDING * 2);
        oSB.setColor(new Color(0.14901961f, 0.1764706f, 0.21568628f, 0.65f));
        ImageManager.getImage(Images.patt).draw2(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + this.animationChangePosY + this.getMenuElement(32).getHeight() + CFG.PADDING * 2, this.getWidth(), this.getHeight() - this.getMenuElement(32).getHeight() - CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.28f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX(), this.getPosY() + this.animationChangePosY + 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth(), this.getMenuElement(32).getHeight() + CFG.PADDING * 2 - 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.425f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2, this.getPosY() + this.animationChangePosY + 2 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth() - 4, CFG.PADDING * 2);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2, this.getPosY() + this.getMenuElement(32).getHeight() + CFG.PADDING * 2 - 4 - CFG.PADDING * 2 + this.animationChangePosY + 2 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth() - 4, CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2, this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getMenuElement(32).getHeight() + CFG.PADDING * 2 - 2, this.getWidth() - 4, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX(), this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.line_32_off1).getHeight() + 1, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX(), this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getMenuElement(32).getHeight() + CFG.PADDING * 2 - 2, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 4, this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getMenuElement(32).getHeight() + CFG.PADDING * 2 - 2, this.getWidth() / 2, 1);
        oSB.setColor(Color.WHITE);
        this.drawMenuElements(oSB, 0, this.animationChangePosY, sliderMenuIsActive);
        CFG.drawText(oSB, CFG.keyboardMessage, CFG.PADDING * 2 + iTranslateX, this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() / 2 - this.iTextHeight / 2 + this.animationChangePosY + this.getPosY(), new Color(0.8156863f, 0.67058825f, 0.44313726f, 1.0f));
        if (this.barTime + (long)(this.drawBar ? 700 : 650) < System.currentTimeMillis()) {
            this.drawBar = !this.drawBar;
            this.barTime = System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        if (this.drawBar) {
            CFG.drawText(oSB, "|", CFG.PADDING * 2 + this.iTextWidth + iTranslateX, this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() / 2 - this.iTextHeight / 2 + this.animationChangePosY + this.getPosY(), Color.WHITE);
        }
    }

    @Override
    public final void actionElement(int iID) {
        switch (iID) {
            case 26: {
                this.shiftAction();
                return;
            }
            case 27: {
                CFG.keyboardDelete.action();
                break;
            }
            case 28: {
                if (activeColor_RGB_ID < 0) {
                    numbers = !numbers;
                    this.actionClose();
                }
                return;
            }
            case 29: {
                if (CFG.keyboardMessage.length() <= 0 || CFG.keyboardMessage.charAt(CFG.keyboardMessage.length() - 1) == ' ') break;
                CFG.keyboardWrite.action(" ");
                break;
            }
            case 32: {
                CFG.keyboardSave.action();
                this.closeMenu();
                activeColor_RGB_ID = -1;
                return;
            }
            default: {
                if (shift && iID < 26) {
                    if (numbers) {
                        if (iID < 10) {
                            this.writeNumber(iID);
                            break;
                        }
                        if (activeColor_RGB_ID >= 0) break;
                        CFG.keyboardWrite.action(this.lKeys123.get(iID - 10));
                        break;
                    }
                    if (activeColor_RGB_ID >= 0) break;
                    CFG.keyboardWrite.action(this.lKeysSHIFT.get(iID));
                    if (!shift) break;
                    this.shiftAction();
                    break;
                }
                if (numbers) {
                    if (iID < 10) {
                        this.writeNumber(iID);
                        break;
                    }
                    if (activeColor_RGB_ID >= 0) break;
                    CFG.keyboardWrite.action(this.lKeys123.get(iID - 10));
                    break;
                }
                if (activeColor_RGB_ID >= 0) break;
                CFG.keyboardWrite.action(this.lKeys.get(iID));
                if (!shift) break;
                this.shiftAction();
            }
        }
        CFG.glyphLayout.setText(CFG.fontMain, CFG.keyboardMessage);
        this.iTextWidth = (int)CFG.glyphLayout.width;
        this.iTextHeight = (int)CFG.glyphLayout.height;
        this.barTime = System.currentTimeMillis();
        this.drawBar = true;
    }

    protected final void shiftAction() {
        shift = !shift;
        boolean bl = shift;
        if (numbers) {
            numbers = false;
            this.actionClose();
        }
        this.getMenuElement(26).setTypeOfButton(shift ? Button.TypeOfButton.KEYBOARD_ACTIVE : Button.TypeOfButton.KEYBOARD_OPTIONS);
        if (shift) {
            int i;
            int n = i;
            for (i = numbers ? this.lKeysNUM.size() : 0; i < this.lKeysSHIFT.size(); ++i) {
                this.getMenuElement(i).setText(this.lKeysSHIFT.get(i));
            }
        } else {
            int i;
            int n = i;
            for (i = numbers ? this.lKeysNUM.size() : 0; i < this.lKeysSHIFT.size(); ++i) {
                this.getMenuElement(i).setText(this.lKeys.get(i));
            }
        }
    }

    private final void writeNumber(int iID) {
        CFG.keyboardWrite.action(this.lKeysNUM.get(iID));
    }

    @Override
    public final void onBackPressed() {
        this.closeMenu();
    }

    @Override
    public void actionClose() {
        this.getMenuElement(28).setTypeOfButton(numbers ? Button.TypeOfButton.KEYBOARD_ACTIVE : Button.TypeOfButton.KEYBOARD_OPTIONS);
        if (numbers) {
            int i;
            for (i = 0; i < this.lKeysNUM.size(); ++i) {
                this.getMenuElement(i).setText(this.lKeysNUM.get(i));
                this.getMenuElement(i).setTypeOfButton(Button.TypeOfButton.KEYBOARD_NUM);
            }
            int keysNum = this.lKeysNUM.size();
            for (i = 0; i < this.lKeys123.size(); ++i) {
                this.getMenuElement(keysNum + i).setText(this.lKeys123.get(i));
            }
        } else {
            int i;
            for (i = 0; i < this.lKeysNUM.size(); ++i) {
                this.getMenuElement(i).setText(shift ? this.lKeysSHIFT.get(i) : this.lKeys.get(i));
                this.getMenuElement(i).setTypeOfButton(Button.TypeOfButton.KEYBOARD);
            }
            for (i = this.lKeysNUM.size(); i < this.lKeysNUM.size() + this.lKeys123.size(); ++i) {
                this.getMenuElement(i).setText(shift ? this.lKeysSHIFT.get(i) : this.lKeys.get(i));
                this.getMenuElement(i).setTypeOfButton(Button.TypeOfButton.KEYBOARD);
            }
        }
    }

    private final void updateChangePosY() {
        switch (this.animationStepID) {
            case 0: 
            case 1: 
            case 12: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)this.getHeight() * 2.5f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 2: 
            case 3: 
            case 10: 
            case 11: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)this.getHeight() * 5.0f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 4: 
            case 5: 
            case 8: 
            case 9: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)this.getHeight() * 10.0f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 6: 
            case 7: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)this.getHeight() * 15.0f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 13: {
                this.animationChangePosY = 0;
            }
        }
        if (CFG.iNumOfFPS < 22) {
            this.animationStepID = 13;
            this.animationChangePosY = 0;
        }
        if (this.closeMenu && this.animationStepID == 13) {
            this.animationChangePosY = this.getHeight();
            super.setVisible(false);
        }
        ++this.animationStepID;
        CFG.setRender_3(true);
    }

    protected final void closeMenu() {
        this.closeMenu = true;
        this.resetAnimation();
    }

    @Override
    public void onMenuPressed() {
        CFG.glyphLayout.setText(CFG.fontMain, CFG.keyboardMessage);
        this.iTextWidth = (int)CFG.glyphLayout.width;
    }

    @Override
    public final void setVisible(boolean visible) {
        if (visible) {
            CFG.glyphLayout.setText(CFG.fontMain, CFG.keyboardMessage);
            this.iTextWidth = (int)CFG.glyphLayout.width;
            this.iTextHeight = (int)CFG.glyphLayout.height;
            this.barTime = System.currentTimeMillis();
            this.drawBar = true;
            super.setVisible(visible);
        }
        this.closeMenu = !visible;
        this.resetAnimation();
    }

    private final void resetAnimation() {
        this.animationStepID = 0;
        if (!this.closeMenu) {
            this.animationChangePosY = this.getHeight();
        }
    }

    static {
        allianceRenameMode = false;
    }
}

