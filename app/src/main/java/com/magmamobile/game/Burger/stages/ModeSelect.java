package com.magmamobile.game.Burger.stages;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.widget.Toast;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.*;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.MenuButton;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;

public final class ModeSelect extends GameStage
{

    private final int MENU_BACK = 3;
    private final int TEXT_COLOR = 0xffd24848;
    private final int TOTAL_MENUS = 3;
    private boolean activated;
    private int dx;
    private int iX;
    private int iY[];
    private int mY[] = {
        118, 178, 238
    };
    private MenuButton menuBtn[];
    private int nextStage;
    private Paint pTitle1;
    private Paint pTitle2;
    private int selected;
    private String title;
    private int tx;
    private int ty;

    public ModeSelect()
    {
    }

    private void buttonHandler(int i)
    {
        selected = i;
        GameManager.setMode(i);
        menuBtn[selected].play();
        activated = false;
        UIManager.backButtonActive = false;
        SoundManager.playSound(28);
    }

    private void changeStage()
    {
        Game.setStage(nextStage);
    }

    private int getStartX()
    {
        int i = 0;
        MenuButton amenubutton[] = menuBtn;
        int j = amenubutton.length;
        int k = 0;
        do
        {
            if(k >= j)
            {
                return (Game.mBufferWidth - (i + (BitmapManager.modeIcons[0].getWidth() + dx))) / 2;
            }
            MenuButton menubutton = amenubutton[k];
            int l = Game.getTextWidth(menubutton.label, menubutton.pStroke);
            if(l > i)
            {
                i = l;
            }
            k++;
        } while(true);
    }

    private void navigate(int i)
    {
        nextStage = i;
        if(i == 3)
        {
            App.pause.fadeOut();
            return;
        } else
        {
            App.fader.fadeOut();
            return;
        }
    }

    public boolean enterOnResume()
    {
        return false;
    }

    public void onAction()
    {
        App.scrollBG.scroll();
        if(TouchScreen.eventDown && activated) {
            int i = 0;
            while (i < 3) {
                if (TouchScreen.isInRect(menuBtn[i].x, menuBtn[i].y, menuBtn[i].width, menuBtn[i].height)) {
                    switch (i) {
                        case 0: // '\0'
                            buttonHandler(0);
                            nextStage = 6;
                            Common.analytics("mode/button/career");
                            break;

                        case 1: // '\001'
                            if (menuBtn[i].enabled) {
                                buttonHandler(1);
                                nextStage = 8;
                                SoundManager.fadding = true;
                                Common.analytics("mode/button/timeattack");
                            } else {
                                call(0);
                                Common.analytics("mode/button/timeattack/locked");
                            }
                            break;

                        case 2: // '\002'
                            if (menuBtn[i].enabled) {
                                buttonHandler(2);
                                nextStage = 8;
                                SoundManager.fadding = true;
                                Common.analytics("mode/button/freeplay");
                            } else {
                                call(0);
                                Common.analytics("mode/button/freeplay/locked");
                            }
                            break;
                    }
                    break;
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
                    changeStage();
                }
            } else
            {
                App.pause.animate();
            }
        }
        if(selected == -1)
        {
            if(App.fader.isFinished())
            {
                App.fader.stop();
                UIManager.backButtonActive = true;
                activated = true;
            }
        } else
        if(selected >= 0)
        {
            if(selected != 3)
            {
                if(menuBtn[selected].isFinished())
                {
                    menuBtn[selected].stop();
                    App.fader.fadeOut();
                } else
                if(menuBtn[selected].isPlaying())
                {
                    menuBtn[selected].animate();
                }
            }
            if(App.fader.isFinished())
            {
                App.fader.stop();
                changeStage();
            }
        }
        if(App.fader.isPlaying())
        {
            App.fader.animate();
        }
        if(SoundManager.fadding)
        {
            SoundManager.musicFadeOut(0.5F);
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            selected = 3;
            navigate(1);
        }
    }

    public void onCall(int i)
    {
        switch(i)
        {
        default:
            return;

        case 0: // '\0'
            Toast.makeText(Game.getContext(), Game.getResString(R.string.res_toastweek), Toast.LENGTH_SHORT).show();
            break;
        }
    }

    public void onEnter()
    {
        super.onEnter();
        resetStage();
    }

    public void onInitialize()
    {
        int i;
        int j;
        title = Game.getResString(R.string.res_modetitle).toUpperCase();
        tx = Game.scalei(240);
        ty = Game.scalei(48);
        dx = Game.scalei(12);
        i = BitmapManager.modeIcons[0].getWidth() + dx;
        MenuButton amenubutton[] = new MenuButton[3];
        amenubutton[0] = new MenuButton();
        amenubutton[1] = new MenuButton();
        amenubutton[2] = new MenuButton();
        menuBtn = amenubutton;
        menuBtn[0].setLabel(Game.getResString(R.string.res_career).toUpperCase());
        menuBtn[1].setLabel(Game.getResString(R.string.res_timeattack).toUpperCase());
        menuBtn[2].setLabel(Game.getResString(R.string.res_freeplay).toUpperCase());
        j = 0;
        while(j < 3) {
            menuBtn[j].setColors(-1, 0xffd24848);
            menuBtn[j].setSize(App.scalefFont(46F), Game.scalef(6F));
            j++;
        }
        int k;
        iY = new int[3];
        iX = getStartX();
        k = 0;
        while(k < 3)
        {
            menuBtn[k].left(i + iX, Game.scalei(mY[k]));
            menuBtn[k].rect(Game.scalei(480), Game.scalei(80));
            int ai[] = iY;
            int l = mY[k];
            byte byte0;
            if(k == 2)
            {
                byte0 = 38;
            } else
            {
                byte0 = 34;
            }
            ai[k] = Game.scalei(l - byte0);
            k++;

        }
        pTitle1 = new Paint();
        pTitle1.setAntiAlias(Game.getAntiAliasEnabled());
        pTitle1.setColor(0xffd24848);
        pTitle1.setTypeface(App.defaultFont);
        pTitle1.setTextSize(App.scalefFont(46F));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), -1);
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
        int i;
        MenuButton menubutton;
        App.fader.apply();
        App.scrollBG.draw();
        Game.drawRect(0.0F, 0.0F, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawRect(0.0F, UIManager.barY, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawText(title, tx, ty, pTitle2);
        Game.drawText(title, tx, ty, pTitle1);
        i = 0;
        while(i < 3)
        {
            menubutton = menuBtn[i];
            if(!menubutton.enabled)
                Game.drawBitmap(BitmapManager.modeIconsOff[i], iX, iY[i]);
            else if(menubutton.inverted)
                Game.drawBitmap(BitmapManager.modeIconsOn[i], iX, iY[i]);
            else
                Game.drawBitmap(BitmapManager.modeIcons[i], iX, iY[i]);
            menubutton.draw();
            i++;
        }
        App.fader.restore();
        if(App.pause.isPlaying())
        {
            App.pause.draw();
        }
        App.showTrophy();
        App.showDebug();
    }

    public void paramNavigate(int i)
    {
        selected = 3;
        switch(i)
        {
        case 4: // '\004'
        default:
            return;

        case 3: // '\003'
            SoundManager.playSound(29);
            UIManager.configIsFrom = 5;
            Common.analytics("mode/menu/settings");
            navigate(3);
            return;

        case 5: // '\005'
            SoundManager.playSound(29);
            break;
        }
        Common.analytics("mode/menu/quit");
        navigate(1);
    }

    public void resetStage()
    {
        int i = 0;
        activated = false;
        selected = -1;
        UIManager.backButtonActive = false;
        UIManager.barP.setColor(0xFFFF8A8A);
        boolean flag;
        boolean flag1;
        MenuButton amenubutton[];
        int j;
        if(Game.getPrefInt("higherLevel", 0) >= 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if(Game.getPrefInt("higherLevel", 0) >= 3)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        menuBtn[1].setEnabled(flag);
        menuBtn[2].setEnabled(flag1);
        amenubutton = menuBtn;
        j = amenubutton.length;
        do
        {
            if(i >= j)
            {
                App.scrollBG.setColor(3);
                if(UIManager.configIsFrom == 5)
                {
                    App.pause.fadeIn();
                } else
                {
                    App.fader.fadeIn();
                }
                UIManager.configIsFrom = 3;
                return;
            }
            amenubutton[i].reinitColor();
            i++;
        } while(true);
    }
}
