package com.magmamobile.game.Burger.stages;

import android.graphics.*;
import android.os.SystemClock;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.CartoonFader;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.AchievementItem;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;
import java.util.Arrays;

public final class Achievement extends GameStage
{

    private final float FRICTION = 0.85F;
    private boolean activated;
    private int bgHeight;
    private int bottomY;
    private double cTime;
    private String counter;
    private int counterX;
    private double eTime;
    private AchievementItem items[];
    private int logoX;
    private float maxScrollY;
    private Matrix mtxBG;
    private Matrix mtxScroll;
    private int nameX;
    private int nextStage;
    private Paint pAlias;
    private Paint pBG;
    private Paint pCounter;
    private Paint pName;
    private Paint pScroller;
    private Paint pTCount;
    private Paint pTitle;
    private float radius;
    private double sTime;
    private float scrollY;
    private RectF scroller;
    private float scrollerH;
    private float scrollerHeight;
    private float scrollerX;
    private float scrollerY;
    private int separatorX;
    private int separatorY;
    private Integer sortedItems[];
    private float speedY;
    private int tDownY;
    private int tMoveY;
    private String title;
    private int titleX;
    private int titleY;
    private int validX;
    private boolean velocityUsed;

    public Achievement()
    {
    }

    private void changeStage()
    {
        Game.setStage(nextStage);
    }

    private void navigate(int i)
    {
        nextStage = i;
        activated = false;
        UIManager.backButtonActive = false;
        App.fader.fadeOut();
    }

    private void resetStage()
    {
        activated = false;
        BitmapManager.setBackground(2);
        pBG.setShader(new BitmapShader(BitmapManager.background, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.REPEAT));
        bgHeight = BitmapManager.background.getHeight();
        PrefManager.updateGameData();
        updateItems();
        counter = (new StringBuilder(String.valueOf(AchievementManager.count[41][0]))).append("/").append(42).toString();
        UIManager.backButtonActive = false;
        App.fader.fadeIn();
    }

    private void scroll()
    {
        if(scrollY > 0.0F) {
            scrollY = 0.0F;
            speedY = 0.0F;
        } else if(scrollY < -maxScrollY) {
            scrollY = -maxScrollY;
            speedY = 0.0F;
        }
        mtxBG.setTranslate(0.0F, scrollY % (float)bgHeight);
        pBG.getShader().setLocalMatrix(mtxBG);
        scroller.offsetTo(scrollerX, scrollerY - (scrollY / maxScrollY) * scrollerHeight);
    }

    private void setTexts()
    {
        int i;
        int j;
        i = 0;
        j = 0;
        while(j < 7) {
            int k = 0;
            while(k < 4)
            {
                items[i].name = Game.getResString(AchievementManager.TITLE[i]).toUpperCase();
                items[i].label.setText(Game.getResString(AchievementManager.LABEL[i]).replace("%1$d", String.valueOf(AchievementManager.scoreValues[j][k])));
                i++;
                k++;
            }
            j++;
        }
        int l = 0;
        while(l < 5) {
            items[i].name = Game.getResString(AchievementManager.TITLE[i]).toUpperCase();
            items[i].label.setText(Game.getResString(AchievementManager.LABEL[i]));
            i++;
            l++;
        }
        int i1 = 0;
        while(i1 < 4) {
            items[i].name = Game.getResString(AchievementManager.TITLE[i]).toUpperCase();
            items[i].label.setText(Game.getResString(AchievementManager.LABEL[i]).replace("%1$d", String.valueOf(AchievementManager.scoreValues[7][i1])));
            i++;
            i1++;
        }
        int j1 = 0;
        while(j1 < 5)
        {
            items[i].name = Game.getResString(AchievementManager.TITLE[i]).toUpperCase();
            items[i].label.setText(Game.getResString(AchievementManager.LABEL[i]));
            i++;
            j1++;
        }
    }

    private int sortItems()
    {
        Arrays.sort(sortedItems, new CompareItems());
        int i = BitmapManager.successTop.getHeight() + Game.scalei(60);
        int j = 0;
        do
        {
            if(j >= 42)
            {
                return i;
            }
            AchievementItem achievementitem = items[sortedItems[j].intValue()];
            achievementitem.y = i;
            achievementitem.logoY = achievementitem.y + Game.scalei(0);
            achievementitem.validY = achievementitem.logoY;
            achievementitem.nameY = achievementitem.y + Game.scalei(20);
            achievementitem.counterY = achievementitem.y + Game.scalei(16);
            achievementitem.label.setY(achievementitem.y + Game.scalei(30));
            achievementitem.h = Math.max(Game.scalei(30) + achievementitem.label.getMesuredHeight(), BitmapManager.successLock.getHeight());
            achievementitem.separatorY = achievementitem.y + achievementitem.h + Game.scalei(8);
            if(j < 41)
            {
                achievementitem.h = achievementitem.h + (Game.scalei(16) + BitmapManager.successSeparator.getHeight());
            } else
            {
                achievementitem.last = true;
            }
            i += achievementitem.h;
            j++;
        } while(true);
    }

    private void updateItems()
    {
        AchievementManager.compute();
        int i = 0;
        do
        {
            if(i >= 42)
            {
                sortItems();
                return;
            }
            items[i].count = (new StringBuilder(String.valueOf(AchievementManager.count[i][0]))).append(" / ").append(AchievementManager.count[i][1]).toString();
            sortedItems[i] = Integer.valueOf(i);
            i++;
        } while(true);
    }

    private boolean validLang()
    {
        String as[];
        int i;
        int j;
        as = (new String[] {
            "de", "pt-rBR", "id", "in", "el", "ar"
        });
        i = as.length;
        j = 0;
        while(j < i) {
            boolean flag;
            flag = as[j].equals(Game.getResString(R.string.lang));
            if(flag)
                return false;
            j++;
        }
        return true;
    }

    private boolean validLangName()
    {
        String as[];
        int i;
        int j;
        as = (new String[] {
            "lt", "sr", "cs", "hr", "el", "ar"
        });
        i = as.length;
        j = 0;
        while(j < i) {
            boolean flag;
            flag = as[j].equals(Game.getResString(R.string.lang));
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

    public void onAction()
    {
        double d;
        float f;
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
        if(Math.abs(speedY) > 50F)
        {
            cTime = SystemClock.elapsedRealtime();
            eTime = cTime - sTime;
            if(eTime != 0.0D)
            {
                d = scrollY;
                f = 0.85F * speedY;
                speedY = f;
                scrollY = (float)(d + 2D * ((double)f / eTime));
                scroll();
            } else
            {
                speedY = 0.0F;
            }
            sTime = cTime;
        }
        if(activated) {
            if (TouchScreen.eventDown) {
                tDownY = TouchScreen.y;
                velocityUsed = false;
                App.tracker.addMovement(TouchScreen.x, TouchScreen.y);
                speedY = 0.0F;
            } else if (TouchScreen.eventMoving) {
                tMoveY = TouchScreen.y;
                scrollY = scrollY + (float) (tMoveY - tDownY);
                tDownY = tMoveY;
                scroll();
                App.tracker.addMovement(TouchScreen.x, TouchScreen.y);
                velocityUsed = true;
            } else if (TouchScreen.eventUp && velocityUsed) {
                App.tracker.addMovement(TouchScreen.x, TouchScreen.y);
                App.tracker.computeCurrentVelocity(1000);
                speedY = App.tracker.getYVelocity();
                sTime = SystemClock.elapsedRealtime();
            }
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            Common.analytics("achievement/back");
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
        float f1;
        int i;
        int j;
        pBG = new Paint();
        pBG.setAntiAlias(true);
        mtxBG = new Matrix();
        pAlias = new Paint();
        pAlias.setAntiAlias(true);
        mtxScroll = new Matrix();
        pName = new Paint();
        pName.setColor(-1);
        pName.setTypeface(App.defaultFont);
        Paint paint = pName;
        float f;
        int k;
        if(validLangName())
        {
            f = 28F;
        } else
        {
            f = 20F;
        }
        paint.setTextSize(App.scalefFont(f));
        pCounter = new Paint(pName);
        pCounter.setTextSize(App.scalefFont(24F));
        pCounter.setTextAlign(android.graphics.Paint.Align.RIGHT);
        pTitle = new Paint();
        pTitle.setColor(0xffd3d3d3);
        pTitle.setTypeface(App.defaultFont);
        pTitle.setTextSize(App.scalefFont(46F));
        pTitle.setColor(-1);
        pTCount = new Paint(pTitle);
        pTCount.setTextAlign(android.graphics.Paint.Align.RIGHT);
        pScroller = new Paint();
        pScroller.setColor(-1);
        pScroller.setAlpha(128);
        title = Game.getResString(R.string.res_successtitle);
        counter = "";
        titleX = Game.scalei(32);
        titleY = Game.scalei(56);
        logoX = Game.scalei(28);
        nameX = Game.scalei(82);
        counterX = Game.scalei(448);
        validX = Game.scalei(418);
        separatorX = (Game.scalei(480) - BitmapManager.successSeparator.getWidth()) / 2;
        separatorY = Game.scalei(72) - BitmapManager.successSeparator.getHeight();
        scrollerX = Game.scalei(456);
        scrollerY = Game.scalei(16);
        radius = Game.scalei(3);
        items = new AchievementItem[42];
        if(validLang())
        {
            f1 = 16F;
        } else
        {
            f1 = 13F;
        }
        i = 0;
        while(i < 42)  {
            items[i] = new AchievementItem();
            AchievementItem achievementitem = items[i];
            achievementitem.label.x = nameX;
            achievementitem.label.setWidth(Game.scalei(316));
            achievementitem.label.setTypeface(Typeface.SANS_SERIF);
            achievementitem.label.setSize(App.scalefFont(f1));
            achievementitem.label.setColor(0xffd3d3d3);
            achievementitem.label.setVerticalAlign((byte)1);
            achievementitem.label.setHorizontalAlign((byte)1);
            i++;
        }
        setTexts();
        sortedItems = new Integer[42];
        j = 0;
        while(j < 42)
        {
            sortedItems[j] = Integer.valueOf(j);
            j++;
        }
        k = sortItems();
        bottomY = k;
        maxScrollY = (k + BitmapManager.successBottom.getHeight()) - Game.scalei(320);
        scrollerH = ((float)Game.mBufferHeight / maxScrollY) * (float)Game.mBufferHeight;
        scrollerHeight = (float)Game.mBufferHeight - (scrollerH + (float)Game.scalei(32));
        scroller = new RectF(scrollerX, scrollerY, scrollerX + (float)Game.scalei(6), scrollerY + scrollerH);
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
        int j;
        AchievementItem achievementitem;
        App.fader.apply();
        Game.drawPaint(pBG);
        mtxScroll.setTranslate(0.0F, scrollY);
        Game.mCanvas.save();
        Game.mCanvas.setMatrix(mtxScroll);
        if((float)separatorY - scrollY > (float)(-titleY))
        {
            Game.mCanvas.drawBitmap(BitmapManager.successTop, 0.0F, 0.0F, pAlias);
            Game.mCanvas.drawText(title, titleX, titleY, pTitle);
            Game.mCanvas.drawText(counter, counterX, titleY, pTCount);
            Game.mCanvas.drawBitmap(BitmapManager.successSeparator, separatorX, separatorY, pAlias);
        }
        i = 0;
        while(i < 42) {
            j = sortedItems[i].intValue();
            achievementitem = items[j];
            if((float)(achievementitem.y + achievementitem.h) >= -scrollY)
            {
                if((float)achievementitem.y > (float)Game.getBufferHeight() - scrollY)
                    break;
                if(AchievementManager.state[j])
                {
                    Game.mCanvas.drawBitmap(BitmapManager.successIcon[j], logoX, achievementitem.logoY, pAlias);
                } else
                {
                    Game.mCanvas.drawBitmap(BitmapManager.successLock, logoX, achievementitem.logoY, pAlias);
                }
                Game.mCanvas.drawText(achievementitem.name, nameX, achievementitem.nameY, pName);
                if(!AchievementManager.state[j] && j != 32)
                {
                    Game.mCanvas.drawText(achievementitem.count, counterX, achievementitem.counterY, pCounter);
                } else
                if(AchievementManager.state[j])
                {
                    Game.mCanvas.drawBitmap(BitmapManager.successValid, validX, achievementitem.validY, pAlias);
                }
                achievementitem.label.onRender();
                if(!achievementitem.last)
                {
                    Game.mCanvas.drawBitmap(BitmapManager.successSeparator, separatorX, achievementitem.separatorY, pAlias);
                }
            }
            i++;
        }
        if((float)bottomY - scrollY > (float)Game.mBufferHeight)
        {
            Game.mCanvas.drawBitmap(BitmapManager.successBottom, 0.0F, bottomY, pAlias);
        }
        Game.mCanvas.restore();
        Game.mCanvas.drawRoundRect(scroller, radius, radius, pScroller);
        App.fader.restore();
        App.showTrophy();
        App.showDebug();
    }

    public void paramNavigate(int i)
    {
        switch(i)
        {
        default:
            return;

        case 3: // '\003'
            SoundManager.playSound(29);
            UIManager.configIsFrom = 2;
            navigate(3);
            return;

        case 7: // '\007'
            SoundManager.playSound(29);
            break;
        }
        navigate(1);
    }
}
