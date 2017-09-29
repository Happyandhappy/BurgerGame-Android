package com.magmamobile.game.Burger.stages;

import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.CartoonFader;
import com.magmamobile.game.Burger.display.SunshineBG;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.MenuButton;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;

public final class EndGame extends GameStage
{

    private final int MENU_RATE = 0;
    private final int MENU_SHARE = 1;
    private boolean activated;
    private MenuButton buttons[];
    private Label label;
    private int nextStage;
    private Paint pTitle1;
    private Paint pTitle2;
    private Painter painter;
    private boolean shortResume;
    private String title;
    private int titleX;
    private int titleY;

    public EndGame()
    {
    }

    private void changeStage()
    {
        Game.setStage(nextStage);
    }

    private void menuHandler(int i)
    {
        shortResume = true;
        buttons[i].invertColor();
        SoundManager.playSound(29);
        switch(i)
        {
        default:
            return;

        case 0: // '\0'
            Common.analytics("endgame/button/rate");
            Game.showMarketUpdate();
            return;

        case 1: // '\001'
            Common.analytics("endgame/button/share");
            break;
        }
        Common.share();
    }

    private void navigate(int i)
    {
        activated = false;
        UIManager.backButtonActive = false;
        nextStage = i;
        App.fader.fadeOut();
    }

    private void resetStage()
    {
        MenuButton amenubutton[] = buttons;
        int i = amenubutton.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                if(!shortResume)
                {
                    Common.analytics("endgame/show");
                    activated = false;
                    UIManager.backButtonActive = false;
                    UIManager.configIsFrom = 3;
                    PrefManager.updateConfig();
                    App.shineBG.setColor(2);
                    App.fader.fadeIn();
                    return;
                } else
                {
                    shortResume = false;
                    return;
                }
            }
            amenubutton[j].reinitColor();
            j++;
        } while(true);
    }

    public boolean enterOnResume()
    {
        resetStage();
        return false;
    }

    public void onAction()
    {
        int i;
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
                    changeStage();
                }
            } else
            {
                App.fader.animate();
            }
        }
        App.shineBG.rotate();
        if(TouchScreen.eventDown && activated) {
            i = 0;
            while (i < buttons.length) {
                if (TouchScreen.isInRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height)) {
                    menuHandler(i);
                    break;
                }
                i++;
            }
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            Common.analytics("endgame/back");
            navigate(1);
        }
    }

    public void onEnter()
    {
        super.onEnter();
        resetStage();
    }

    public void onInitialize()
    {
        pTitle1 = new Paint();
        pTitle1.setAntiAlias(true);
        pTitle1.setTypeface(App.defaultFont);
        pTitle1.setTextSize(App.scalefFont(46F));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle1.setColor(-1);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), SunshineBG.COLORS[2]);
        titleX = Game.mBufferCW;
        titleY = Game.scalei(68);
        title = Game.getResString(R.string.res_congrat).toUpperCase();
        painter = new Painter();
        painter.setFontColor(SunshineBG.COLORS[2]);
        painter.setFontFace(App.defaultFont);
        painter.setFontSize(App.scalefFont(42F));
        painter.setStrokeColor(-1);
        painter.setStrokeWidth(Game.scalef(5.5F));
        label = new Label();
        label.setWidth(Game.scalei(460));
        label.x = (Game.mBufferWidth - label.getWidth()) / 2;
        label.y = Game.scalei(128);
        label.setPainter(painter);
        label.setVerticalAlign((byte)1);
        label.setWordWrap(true);
        label.setText(Game.getResString(R.string.res_endgame));
        MenuButton amenubutton[] = new MenuButton[2];
        amenubutton[0] = new MenuButton();
        amenubutton[1] = new MenuButton();
        buttons = amenubutton;
        int i = 0;
        do
        {
            if(i >= buttons.length)
            {
                buttons[0].setLabel(Game.getResString(R.string.res_rate).toUpperCase());
                buttons[1].setLabel(Game.getResString(R.string.res_share).toUpperCase());
                return;
            }
            buttons[i].setColors(SunshineBG.COLORS[2], -1);
            buttons[i].setSize(App.scalefFont(46F), Game.scalef(6F));
            buttons[i].center(Game.mBufferCW, Game.scalei(212 + i * 60));
            buttons[i].centerRect(Game.scalei(460), Game.scalei(48));
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
        if(PrefManager.configs[3])
        {
            App.shineBG.draw();
        }
        Game.drawText(title, titleX, titleY, pTitle2);
        Game.drawText(title, titleX, titleY, pTitle1);
        label.onRender();
        MenuButton amenubutton[] = buttons;
        int i = amenubutton.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                App.fader.restore();
                App.showTrophy();
                App.showDebug();
                return;
            }
            amenubutton[j].draw();
            j++;
        } while(true);
    }

    public void paramNavigate(int i)
    {
        switch(i)
        {
        case 4: // '\004'
        default:
            return;

        case 3: // '\003'
            Common.analytics("endgame/menu/settings");
            SoundManager.playSound(29);
            UIManager.configIsFrom = 11;
            navigate(3);
            return;

        case 5: // '\005'
            Common.analytics("endgame/menu/quit");
            break;
        }
        SoundManager.playSound(29);
        navigate(1);
    }
}
