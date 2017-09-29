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

public final class NewItem extends GameStage
{

    private boolean activated;
    private int arrowX;
    private int arrowY;
    private int c;
    private int currentItem;
    private boolean inverted;
    private Bitmap itemBmp;
    private int itemX;
    private int itemY;
    private String name;
    private int nameX;
    private int nameY;
    private String next;
    private int nextStage;
    private int nextX;
    private int nextY;
    private Paint pName1;
    private Paint pName2;
    private Paint pNext1;
    private Paint pNext2;
    private Paint pTitle1;
    private Paint pTitle2;
    private String title;
    private int titleX;
    private int titleY;

    public NewItem()
    {
    }

    private void changeStage()
    {
        if(nextStage == 8)
        {
            UIManager.fromNewItem = true;
        }
        Game.setStage(nextStage);
    }

    private void navigate(int i)
    {
        nextStage = i;
        activated = false;
        UIManager.backButtonActive = false;
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

    private void resetStage()
    {
        Common.analytics("newitem/show");
        activated = false;
        UIManager.backButtonActive = false;
        c = -1;
        PrefManager.updateConfig();
        App.shineBG.setColor(0);
        title = Game.getResString(R.string.res_newitemadded).toUpperCase();
        currentItem = GameManager.MONTH_ITEM[UIManager.getNewItemByLevel(GameManager.currentLevel)];
        name = Game.getResString(UIManager.ITEMS_NAME[currentItem]);
        Bitmap bitmap;
        if(currentItem < 12)
        {
            bitmap = BitmapManager.burgerParts[currentItem];
        } else
        {
            bitmap = BitmapManager.accompItems[-12 + currentItem];
        }
        itemBmp = bitmap;
        itemX = (Game.mBufferWidth - itemBmp.getWidth()) / 2;
        itemY = (Game.mBufferHeight - itemBmp.getHeight()) / 2;
        pName2.setColor(App.shineBG.currentColor);
        nameY = itemY + itemBmp.getHeight() + Game.scalei(32);
        if(UIManager.configIsFrom == 7)
        {
            App.pause.fadeIn();
        } else
        {
            App.fader.fadeIn();
        }
        UIManager.configIsFrom = 3;
    }

    private void setTextColor()
    {
        if(inverted)
        {
            pNext1.setColor(pTitle2.getColor());
            pNext2.setColor(-1);
            return;
        } else
        {
            pNext1.setColor(-1);
            pNext2.setColor(pTitle2.getColor());
            return;
        }
    }

    private boolean validTitle()
    {
        String as[];
        String s;
        int i;
        int j;
        as = (new String[] {
            "el"
        });
        s = Game.getResString(R.string.lang);
        i = as.length;
        j = 0;
        while(j < i) {
            boolean flag;
            flag = as[j].equals(s);
            if(flag)
                return false;
            j++;
        }
        return true;
    }

    public boolean enterOnResume()
    {
        return false;
    }

    public void invertColor()
    {
        boolean flag;
        if(inverted)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        inverted = flag;
        setTextColor();
    }

    public void onAction()
    {
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
        if(c <= 0) {
            if(c == 0)
            {
                c = -1;
                App.fader.fadeOut();
            }
        } else {
            c = -1 + c;
            if (c % 3 == 0) {
                invertColor();
            }
        }
        if(TouchScreen.eventDown && activated)
        {
            Common.analytics("newitem/button/next");
            activated = false;
            UIManager.backButtonActive = false;
            c = 15;
            nextStage = 8;
            SoundManager.playSound(28);
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            Common.analytics("newitem/back");
            navigate(8);
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
        Paint paint = pTitle1;
        float f;
        if(validTitle())
        {
            f = 46F;
        } else
        {
            f = 40F;
        }
        paint.setTextSize(App.scalefFont(f));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle1.setColor(-1);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), 0xff338eff);
        titleX = Game.mBufferCW;
        titleY = Game.scalei(68);
        pName1 = new Paint(pTitle1);
        pName1.setTextSize(App.scalefFont(42F));
        pName1.setColor(-1);
        pName2 = App.getStroked(pName1, Game.scalef(5.5F));
        nameX = Game.mBufferCW;
        next = Game.getResString(R.string.res_continue).toUpperCase();
        pNext1 = new Paint(pTitle1);
        pNext1.setTextSize(App.scalefFont(38F));
        pNext1.setTextAlign(android.graphics.Paint.Align.RIGHT);
        pNext2 = App.getStroked(pNext1, Game.scalef(5.5F), pTitle2.getColor());
        nextX = Game.scalei(444);
        nextY = Game.scalei(308);
        arrowX = Game.scalei(450);
        arrowY = Game.scalei(280);
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
        App.shineBG.draw();
        Game.drawBitmap(itemBmp, itemX, itemY);
        Game.drawText(title, titleX, titleY, pTitle2);
        Game.drawText(title, titleX, titleY, pTitle1);
        Game.drawText(name, nameX, nameY, pName2);
        Game.drawText(name, nameX, nameY, pName1);
        Game.drawText(next, nextX, nextY, pNext2);
        Game.drawText(next, nextX, nextY, pNext1);
        Bitmap bitmap;
        if(!inverted)
        {
            bitmap = BitmapManager.newItemNext;
        } else
        {
            bitmap = BitmapManager.newItemNextOn;
        }
        Game.drawBitmap(bitmap, arrowX, arrowY);
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
        switch(i)
        {
        case 4: // '\004'
        default:
            return;

        case 3: // '\003'
            Common.analytics("newitem/menu/settings");
            SoundManager.playSound(29);
            UIManager.configIsFrom = 7;
            navigate(3);
            return;

        case 5: // '\005'
            Common.analytics("newitem/menu/back");
            break;
        }
        SoundManager.playSound(29);
        navigate(1);
    }

    public void reinitColor()
    {
        inverted = false;
        setTextColor();
    }
}
