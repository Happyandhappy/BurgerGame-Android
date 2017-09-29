package com.magmamobile.game.Burger.stages;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.*;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;

public final class Config extends GameStage
{

    private boolean activated;
    private int ch;
    private int cw;
    private int cx;
    private int cy[];
    private int dx;
    private int ix;
    private int iy[];
    private Paint pText1;
    private Paint pText2;
    private Paint pTitle1;
    private Paint pTitle2;
    private int px;
    private int py[];
    private String texts[];
    private String title;
    private int tx;
    private int ty;
    private int wMax;

    public Config()
    {
    }

    private void back()
    {
        activated = false;
        UIManager.backButtonActive = false;
        if(UIManager.configIsFrom == 1)
        {
            App.fader.fadeOut();
            return;
        } else
        {
            App.pause.fadeOut();
            return;
        }
    }

    private int getStartX()
    {
        wMax = 0;
        int i = 0;
        do
        {
            if(i >= 4)
            {
                return (Game.mBufferWidth - (BitmapManager.prefIcons[0].getWidth() + dx + wMax + dx + BitmapManager.cocheOn.getWidth())) / 2;
            }
            int j = Game.getTextWidth(texts[i], pText2);
            if(j <= wMax)
            {
                j = wMax;
            }
            wMax = j;
            i++;
        } while(true);
    }

    private void prevStage()
    {
        if(UIManager.configIsFrom != 3)
        {
            Game.setStage(UIManager.configIsFrom);
            return;
        } else
        {
            Game.setStage(1);
            return;
        }
    }

    private void resetStage()
    {
        activated = false;
        PrefManager.updateConfig();
        UIManager.backButtonActive = false;
        UIManager.barP.setColor(0xff79e178);
        App.scrollBG.setColor(2);
        if(UIManager.configIsFrom == 1)
        {
            App.fader.fadeIn();
            return;
        } else
        {
            App.pause.fadeIn();
            return;
        }
    }

    public boolean enterOnResume()
    {
        return false;
    }

    public void onAction()
    {
        boolean flag;
        String s;
        boolean flag1;
        String s1;
        boolean flag2;
        String s2;
        boolean flag3;
        String s3;
        App.scrollBG.scroll();
        if(TouchScreen.eventDown && activated) {
            int i = 0;
            while (i < 4) {
                if (TouchScreen.isInRect(cx, cy[i], cw, ch)) {
                    switch (i) {
                        default:
                            break;
                        case 0:
                            if (Game.getSoundEnable()) {
                                flag3 = false;
                            } else {
                                flag3 = true;
                            }
                            Game.setSoundEnable(flag3);
                            if (Game.getSoundEnable()) {
                                s3 = "settings/button/enablesound";
                            } else {
                                s3 = "settings/button/disablesound";
                            }
                            Common.analytics(s3);
                            SoundManager.playSound(28);
                            break;
                        case 1:
                            if (Game.getMusicEnable()) {
                                flag2 = false;
                            } else {
                                flag2 = true;
                            }
                            Game.setMusicEnable(flag2);
                            if (Game.getMusicEnable()) {
                                s2 = "settings/button/enablemusic";
                            } else {
                                s2 = "settings/button/disablemusic";
                            }
                            Common.analytics(s2);
                            SoundManager.playMusic(0);
                            if (UIManager.configIsFrom != 8 && UIManager.configIsFrom != 10) {
                                if (Game.getMusicEnable()) {
                                    SoundManager.playBGMusic();
                                } else {
                                    SoundManager.fadding = true;
                                }
                            }
                            break;
                        case 2:
                            if (Game.getVibrateEnable()) {
                                flag1 = false;
                            } else {
                                flag1 = true;
                            }
                            Game.setVibrateEnable(flag1);
                            if (Game.getVibrateEnable()) {
                                s1 = "settings/button/enablevibration";
                            } else {
                                s1 = "settings/button/disablevibration";
                            }
                            Common.analytics(s1);
                            VibrationManager.vibrate(500);
                            break;
                        case 3:
                            if (Game.getPrefBoolean("prefAnimate", true)) {
                                flag = false;
                            } else {
                                flag = true;
                            }
                            Game.setPrefBoolean("prefAnimate", flag);
                            if (Game.getPrefBoolean("prefAnimate", true)) {
                                s = "settings/button/enableanimation";
                            } else {
                                s = "settings/button/disableanimation";
                            }
                            Common.analytics(s);
                            break;
                    }
                    PrefManager.updateConfig();
                }
                i++;
            }
        }
        if(App.pause.isPlaying())
        {
            if(App.pause.isFinished())
            {
                App.pause.stop();
                if(App.pause.bgAlpha == 0)
                {
                    activated = true;
                    UIManager.backButtonActive = true;
                } else
                {
                    prevStage();
                }
            } else
            {
                App.pause.animate();
            }
        }
        if(App.fader.isPlaying())
        {
            if(App.fader.isFinished())
            {
                App.fader.stop();
                if(!App.fader.opaque)
                {
                    activated = true;
                    UIManager.backButtonActive = true;
                } else
                {
                    prevStage();
                }
            } else
            {
                App.fader.animate();
            }
        }
        if(SoundManager.fadding)
        {
            SoundManager.musicFadeOut(1.0F);
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            Common.analytics("settings/back");
            back();
        }
    }

    public void onEnter()
    {
        super.onEnter();
        resetStage();
    }

    public void onInitialize()
    {
        title = Game.getResString(R.string.res_preftitle).toUpperCase();
        String as[] = new String[4];
        as[0] = Game.getResString(R.string.res_prefsound);
        as[1] = Game.getResString(R.string.res_prefmusic);
        as[2] = Game.getResString(R.string.res_prefvibrate);
        as[3] = Game.getResString(R.string.res_prefanimate);
        texts = as;
        pText1 = new Paint();
        pText1.setAntiAlias(Game.getAntiAliasEnabled());
        pText1.setColor(-1);
        pText1.setTypeface(App.defaultFont);
        pText1.setTextSize(App.scalefFont(38F));
        pText1.setTextAlign(android.graphics.Paint.Align.LEFT);
        pText2 = App.getStroked(pText1, Game.scalef(6F), 0xff00d800);
        pTitle1 = new Paint(pText1);
        pTitle1.setTextSize(App.scalefFont(46F));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle1.setColor(0xff00d800);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), -1);
        dx = Game.scalei(12);
        tx = Game.scalei(240);
        ty = Game.scalei(48);
        ix = getStartX();
        px = ix + BitmapManager.prefIcons[0].getWidth() + dx;
        cx = px + wMax + dx;
        py = new int[4];
        cy = new int[4];
        iy = new int[4];
        int i = 0;
        do
        {
            if(i >= 4)
            {
                cw = BitmapManager.cocheOn.getWidth();
                ch = BitmapManager.cocheOn.getHeight();
                return;
            }
            py[i] = Game.scalei(100 + i * 48);
            cy[i] = py[i] - Game.scalei(38);
            iy[i] = py[i] - Game.scalei(32);
            i++;
        } while(true);
    }

    public void onLateResume()
    {
        resetStage();
    }

    public void onLeave()
    {
        super.onLeave();
    }

    public void onRender()
    {
        App.fader.apply();
        App.scrollBG.draw();
        Game.drawRect(0.0F, 0.0F, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawRect(0.0F, UIManager.barY, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawText(title, tx, ty, pTitle2);
        Game.drawText(title, tx, ty, pTitle1);
        int i = 0;
        do
        {
            if(i >= 4)
            {
                App.fader.restore();
                if(App.pause.isPlaying())
                {
                    App.pause.draw();
                }
                App.showTrophy();
                App.showDebug();
                return;
            }
            Game.drawBitmap(BitmapManager.prefIcons[i], ix, iy[i]);
            Game.drawText(texts[i], px, py[i], pText2);
            Game.drawText(texts[i], px, py[i], pText1);
            Bitmap bitmap;
            if(PrefManager.configs[i])
            {
                bitmap = BitmapManager.cocheOn;
            } else
            {
                bitmap = BitmapManager.cocheOff;
            }
            Game.drawBitmap(bitmap, cx, cy[i]);
            i++;
        } while(true);
    }

    public void paramNavigate(int i)
    {
        switch(i)
        {
        case 6: // '\006'
        default:
            return;

        case 5: // '\005'
        case 7: // '\007'
            Common.analytics("settings/menu/back");
            break;
        }
        SoundManager.playSound(29);
        back();
    }
}
