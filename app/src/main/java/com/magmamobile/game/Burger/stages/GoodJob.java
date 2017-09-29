package com.magmamobile.game.Burger.stages;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.CartoonFader;
import com.magmamobile.game.Burger.display.ScrollBG;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;
import java.util.Arrays;

public final class GoodJob extends GameStage
{

    private final int COUNTER_FRAMES = 75;
    private final int GAME_LOOSE = 2;
    private final int GAME_WIN = 1;
    private final int INFO_COLOR = 0xff985d00;
    private final int NEXT_PHASE = 12;
    private final int TEXT_COLOR = -25600;
    private final int TOTAL_DATA = 5;
    private boolean activated;
    private int arrowX;
    private int arrowY;
    private boolean best[];
    private int bestX[];
    private int bestY[];
    private boolean complete;
    private int count;
    private int data[];
    private int dataX;
    private int dataY[];
    private int equalX;
    private int f;
    private int goalX;
    private int iconX[];
    private int iconY[];
    private String infoStr[];
    private int infoX;
    private int infoY[];
    private boolean inverted;
    private int itemX[];
    private int itemY[];
    private boolean lastLevel;
    private float mAdded;
    private int moneyX;
    private int moneyY[];
    private String next;
    private int nextStage;
    private int nextX;
    private int nextY;
    private Paint pData1;
    private Paint pData2;
    private Paint pGoal1;
    private Paint pGoal2;
    private Paint pInfo1;
    private Paint pInfo2;
    private Paint pNext1;
    private Paint pNext2;
    private Paint pTitle1;
    private Paint pTitle2;
    private Paint pTotal1;
    private Paint pTotal2;
    private int rejectX;
    private int rejectY;
    private String title;
    private int titleX;
    private int titleY;
    private int validX;
    private int validY;
    private int winLoose;

    public GoodJob()
    {
    }

    private void addInfo(int i, boolean flag)
    {
        if(!flag)
        {
            SoundManager.playSound(17);
        }
        switch(i) {
            default:
                break;
            case 0:
                infoStr[0] = (new StringBuilder("x ")).append(GameManager.orderSend).toString();
                if(GameManager.orderSend > DataManager.dayOrders)
                {
                    DataManager.dayOrders = GameManager.orderSend;
                    best(0, flag);
                    return;
                }
                break;
            case 1:
                infoStr[1] = (new StringBuilder("x ")).append(GameManager.burgerSend).toString();
                if(GameManager.burgerSend > DataManager.dayBurgers)
                {
                    DataManager.dayBurgers = GameManager.burgerSend;
                    best(1, flag);
                    return;
                }
                break;
            case 2:
                infoStr[2] = (new StringBuilder("x ")).append(GameManager.dishSend).toString();
                if(GameManager.dishSend > DataManager.dayDishes)
                {
                    DataManager.dayDishes = GameManager.dishSend;
                    best(2, flag);
                    return;
                }
                break;
            case 3:
                infoStr[3] = (new StringBuilder("x ")).append(GameManager.drinkSend).toString();
                if(GameManager.drinkSend > DataManager.dayDrinks)
                {
                    DataManager.dayDrinks = GameManager.drinkSend;
                    best(3, flag);
                    return;
                }
                break;
            case 4:
                infoStr[4] = (new StringBuilder("x ")).append(GameManager.dessertSend).toString();
                if(GameManager.orderSend > DataManager.dayOrders)
                {
                    DataManager.dayOrders = GameManager.orderSend;
                    best(4, flag);
                    return;
                }
                break;
            case 5:
                if(GameManager.dayIncome > DataManager.dayIncomes)
                {
                    DataManager.dayIncomes = GameManager.dayIncome;
                    best(5, flag);
                    return;
                }
                break;
            case 6:
                if(GameManager.dayTips > DataManager.dayTips)
                {
                    DataManager.dayTips = GameManager.dayTips;
                    best(6, flag);
                    return;
                }
                break;
            case 7:
                if(GameManager.dayTotal > DataManager.dayTotal)
                {
                    DataManager.dayTotal = GameManager.dayTotal;
                    best(7, flag);
                    return;
                }
                break;
        }
    }

    private void best(int i, boolean flag)
    {
        if(GameManager.gameMode == 0 && !best[i] && DataManager.getDay() == GameManager.currentLevel)
        {
            if(!flag)
            {
                SoundManager.playSound(19);
            }
            best[i] = true;
        }
    }

    private void changeStage()
    {
        Game.setStage(nextStage);
    }

    private void complete()
    {
        activated = true;
        UIManager.backButtonActive = true;
        complete = true;
        int i = 0;
        do
        {
            if(i >= 5)
            {
                data[1] = GameManager.dayIncome;
                addInfo(5, true);
                data[2] = GameManager.dayTips;
                addInfo(6, true);
                data[3] = GameManager.dayTotal;
                addInfo(7, true);
                finish();
                return;
            }
            count = i;
            addInfo(i, true);
            i++;
        } while(true);
    }

    private void counter()
    {
        int i = GameManager.dayTotal - (int)((float)f * mAdded);
        SoundManager.playSound(31);
        if(i <= GameManager.dayIncome)
        {
            data[1] = i;
            int ai1[] = data;
            int k;
            if(data[1] >= GameManager.dayIncome)
            {
                k = GameManager.dayIncome;
            } else
            {
                k = data[1];
            }
            ai1[1] = k;
            addInfo(5, true);
        } else
        {
            if(data[1] != GameManager.dayIncome)
            {
                data[1] = GameManager.dayIncome;
            }
            data[2] = i - GameManager.dayIncome;
            int ai[] = data;
            int j;
            if(data[2] > GameManager.dayTips)
            {
                j = GameManager.dayTips;
            } else
            {
                j = data[2];
            }
            ai[2] = j;
            addInfo(6, true);
        }
        data[3] = i;
    }

    private void finish()
    {
        switch(GameManager.gameMode)
        {
        default:
            return;

        case 0: // '\0'
            if(GameManager.dayTotal >= GameManager.dayGoal)
            {
                winLoose = 1;
                if(best[7])
                {
                    title = Game.getResString(R.string.res_newrecord).toUpperCase();
                } else
                {
                    title = Game.getResString(R.string.res_goodjob).toUpperCase();
                }
                saveDayData();
                next = Game.getResString(R.string.res_continue).toUpperCase();
                SoundManager.playSound(32);
                if(GameManager.currentLevel < 364)
                {
                    GameManager.nextLevel();
                    return;
                } else
                {
                    lastLevel = true;
                    return;
                }
            } else
            {
                winLoose = 2;
                title = Game.getResString(R.string.res_tryagain).toUpperCase();
                next = Game.getResString(R.string.res_retry).toUpperCase();
                SoundManager.playSound(33);
                return;
            }

        case 1: // '\001'
            next = Game.getResString(R.string.res_replay).toUpperCase();
            break;
        }
        if(GameManager.dayTotal > GameManager.dayGoal)
        {
            winLoose = 1;
            title = Game.getResString(R.string.res_newrecord).toUpperCase();
            SoundManager.playSound(32);
            return;
        } else
        {
            winLoose = 2;
            title = Game.getResString(R.string.res_goodjob).toUpperCase();
            SoundManager.playSound(33);
            return;
        }
    }

    private void navigate(int i)
    {
        nextStage = i;
        activated = false;
        UIManager.backButtonActive = false;
        App.fader.fadeOut();
    }

    private void nextStage()
    {
        activated = false;
        UIManager.backButtonActive = false;
        f = 15;
        SoundManager.playSound(28);
        int i;
        String s;
        if(lastLevel)
        {
            i = 11;
        } else
        {
            i = 8;
        }
        nextStage = i;
        if(winLoose == 1)
        {
            s = "goodjob/button/nextlevel";
        } else
        {
            s = "goodjob/button/retrylevel";
        }
        Common.analytics(s);
        count = 12;
    }

    private void resetStage()
    {
        int i;
        int j;
        int k;
        activated = false;
        UIManager.backButtonActive = false;
        UIManager.barP.setColor(0xFFC341);
        App.scrollBG.setColor(4);
        title = "";
        inverted = false;
        complete = false;
        lastLevel = false;
        Arrays.fill(best, false);
        count = -1;
        f = 32;
        winLoose = 0;
        reinitColor();
        i = 0;
        while(i < 5) {
            infoStr[i] = "x";
            i++;
        }
        goalX = Game.scalei(280) + Game.getTextWidth(String.valueOf(GameManager.dayGoal), pGoal1);
        dataX = Game.getTextWidth(String.valueOf(GameManager.dayIncome), pData1);
        dataX = Math.max(dataX, Game.getTextWidth(String.valueOf(GameManager.dayTips), pData1));
        dataX = Math.max(dataX, Game.getTextWidth(String.valueOf(GameManager.dayTotal), pTotal1));
        dataX = dataX + Game.scalei(280);
        int ai[] = new int[4];
        ai[0] = Game.scalei(70);
        ai[1] = Game.scalei(114);
        ai[2] = Game.scalei(160);
        ai[3] = Game.scalei(212);
        dataY = ai;
        int ai1[] = new int[4];
        ai1[0] = GameManager.dayGoal;
        data = ai1;
        j = 0;
        moneyX = dataX;
        k = 0;
        while(k < 4)
        {
            moneyY[k] = (dataY[k] + Game.scalei(3)) - BitmapManager.goodJobM[j].getHeight();
            if(k != 1)
            {
                j++;
            }
            k++;
        }
        UIManager.configIsFrom = 3;
        App.fader.fadeIn();
    }

    private void saveDayData()
    {
        if(GameManager.gameMode == 0)
        {
            DataManager.updateDayData();
            DataManager.saveDayData(GameManager.currentLevel);
        }
    }

    private void setTextColor()
    {
        if(inverted)
        {
            pNext1.setColor(pTitle1.getColor());
            pNext2.setColor(-1);
            return;
        } else
        {
            pNext1.setColor(-1);
            pNext2.setColor(pTitle1.getColor());
            return;
        }
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
        App.scrollBG.scroll();
        if(!complete)
        {
            f = -1 + f;
            if(f <= 0)
            {
                count = 1 + count;
                if(count < 5)
                {
                    f = 10;
                    addInfo(count, false);
                } else
                if(count == 5)
                {
                    if(GameManager.dayTotal > 75)
                    {
                        f = 75;
                        mAdded = (float)GameManager.dayTotal / (float)f;
                    } else
                    if(GameManager.dayTotal == 0)
                    {
                        f = 0;
                        mAdded = 0.0F;
                        count = 6;
                    } else
                    {
                        f = GameManager.dayTotal;
                        mAdded = 1.0F;
                    }
                } else
                if(count == 6)
                {
                    f = 12;
                    data[1] = GameManager.dayIncome;
                    data[2] = GameManager.dayTips;
                    data[3] = GameManager.dayTotal;
                    addInfo(7, false);
                } else
                {
                    complete();
                }
            }
            if(count == 5)
            {
                counter();
            }
        }
        if(count == 12)
        {
            if(f > 0)
            {
                f = -1 + f;
                if(f % 3 == 0)
                {
                    invertColor();
                }
            } else
            if(f == 0)
            {
                f = -1;
                App.fader.fadeOut();
            }
        }
        if(TouchScreen.eventDown && activated)
        {
            if(complete)
            {
                activated = false;
                nextStage();
            } else
            {
                Common.analytics("goodjob/button/forcecomplete");
                complete();
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
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            byte byte0;
            if(!complete)
            {
                complete();
                Common.analytics("goodjob/back/forcecomplete");
            } else
            {
                Common.analytics("goodjob/back");
            }
            if(GameManager.gameMode == 0)
            {
                byte0 = 6;
            } else
            {
                byte0 = 5;
            }
            navigate(byte0);
        }
    }

    public void onEnter()
    {
        super.onEnter();
        resetStage();
    }

    public void onInitialize()
    {
        titleX = Game.mBufferCW;
        titleY = Game.scalei(32);
        pTitle1 = new Paint();
        pTitle1.setAntiAlias(true);
        pTitle1.setColor(0xFFFF9C00);
        pTitle1.setTypeface(App.defaultFont);
        pTitle1.setTextSize(App.scalefFont(42F));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), -1);
        itemX = new int[5];
        itemY = new int[5];
        infoX = Game.scalei(116);
        infoY = new int[5];
        infoStr = new String[5];
        int i = 0;
        do
        {
            if(i >= 5)
            {
                pInfo1 = new Paint(pTitle1);
                pInfo1.setColor(-1);
                pInfo1.setTextSize(App.scalefFont(30F));
                pInfo1.setTextAlign(android.graphics.Paint.Align.LEFT);
                pInfo2 = App.getStroked(pInfo1, Game.scalef(4F), 0xff985d00);
                pGoal1 = new Paint(pTitle1);
                pGoal1.setColor(0xff985d00);
                pGoal1.setTextSize(App.scalefFont(28F));
                pGoal1.setTextAlign(android.graphics.Paint.Align.RIGHT);
                pGoal2 = App.getStroked(pGoal1, Game.scalef(4F), -1);
                pData1 = new Paint(pInfo1);
                pData1.setTextSize(App.scalefFont(36F));
                pData1.setTextAlign(android.graphics.Paint.Align.RIGHT);
                pData2 = App.getStroked(pData1, Game.scalef(4F), 0xff985d00);
                pTotal1 = new Paint(pGoal1);
                pTotal1.setTextSize(App.scalefFont(42F));
                pTotal2 = App.getStroked(pTotal1, Game.scalef(4F), -1);
                int ai[] = new int[4];
                ai[0] = Game.scalei(274) - BitmapManager.goodJobGoal.getWidth();
                ai[1] = Game.scalei(274) - BitmapManager.goodJobIncome.getWidth();
                ai[2] = Game.scalei(274) - BitmapManager.goodJobTips.getWidth();
                ai[3] = Game.scalei(252) - BitmapManager.goodJobTotal.getWidth();
                iconX = ai;
                int ai1[] = new int[4];
                ai1[0] = Game.scalei(76) - BitmapManager.goodJobGoal.getHeight();
                ai1[1] = Game.scalei(122) - BitmapManager.goodJobIncome.getHeight();
                ai1[2] = Game.scalei(168) - BitmapManager.goodJobTips.getHeight();
                ai1[3] = Game.scalei(222) - BitmapManager.goodJobTotal.getHeight();
                iconY = ai1;
                equalX = Game.scalei(274);
                moneyY = new int[4];
                pNext1 = new Paint(pTitle1);
                pNext1.setColor(-1);
                pNext1.setTextSize(App.scalefFont(38F));
                pNext1.setTextAlign(android.graphics.Paint.Align.RIGHT);
                pNext2 = App.getStroked(pNext1, Game.scalef(5F), -25600);
                nextX = Game.scalei(448);
                nextY = Game.scalei(256);
                arrowX = nextX + Game.scalei(6);
                arrowY = (nextY + Game.scalei(7)) - BitmapManager.goodJobArrow.getHeight();
                best = new boolean[8];
                int ai2[] = new int[8];
                ai2[0] = itemX[0] + Game.scalei(60);
                ai2[1] = itemX[1] + Game.scalei(30);
                ai2[2] = itemX[2] + Game.scalei(35);
                ai2[3] = itemX[3] + Game.scalei(34);
                ai2[4] = itemX[4] + Game.scalei(34);
                ai2[5] = iconX[1] + Game.scalei(38);
                ai2[6] = iconX[2] + Game.scalei(36);
                ai2[7] = iconX[3] + Game.scalei(26);
                bestX = ai2;
                int ai3[] = new int[8];
                ai3[0] = itemY[0] - Game.scalei(2);
                ai3[1] = itemY[1] - Game.scalei(5);
                ai3[2] = itemY[2] - Game.scalei(3);
                ai3[3] = itemY[3] - Game.scalei(4);
                ai3[4] = itemY[4] + Game.scalei(6);
                ai3[5] = iconY[1] - Game.scalei(6);
                ai3[6] = iconY[2] - Game.scalei(7);
                ai3[7] = iconY[3] - Game.scalei(6);
                bestY = ai3;
                validX = iconX[3] + Game.scalei(22);
                validY = iconY[3] + Game.scalei(1);
                rejectX = iconX[3] + Game.scalei(8);
                rejectY = iconY[3] + Game.scalei(3);
                return;
            }
            itemX[i] = Game.scalei(110) - BitmapManager.goodJobIcons[i].getWidth();
            itemY[i] = Game.scalei(86 + i * 42) - BitmapManager.goodJobIcons[i].getHeight();
            infoY[i] = Game.scalei(80 + i * 42);
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
        int i;
        App.fader.apply();
        App.scrollBG.draw();
        Game.drawRect(0.0F, 0.0F, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawRect(0.0F, UIManager.barY, UIManager.barW, UIManager.barH, UIManager.barP);
        if(title != "")
        {
            Game.drawText(title, titleX, titleY, pTitle2);
            Game.drawText(title, titleX, titleY, pTitle1);
        }
        i = 0;
        while(i < 5) {
            Game.drawBitmap(BitmapManager.goodJobIcons[i], itemX[i], itemY[i]);
            Game.drawText(infoStr[i], infoX, infoY[i], pInfo2);
            Game.drawText(infoStr[i], infoX, infoY[i], pInfo1);
            i++;
        }
        int j;
        Game.drawBitmap(BitmapManager.goodJobGoal, iconX[0], iconY[0]);
        Game.drawText(String.valueOf(data[0]), goalX, dataY[0], pGoal2);
        Game.drawText(String.valueOf(data[0]), goalX, dataY[0], pGoal1);
        Game.drawBitmap(BitmapManager.goodJobM[0], goalX, moneyY[0]);
        Game.drawBitmap(BitmapManager.goodJobIncome, iconX[1], iconY[1]);
        Game.drawText(String.valueOf(data[1]), dataX, dataY[1], pData2);
        Game.drawText(String.valueOf(data[1]), dataX, dataY[1], pData1);
        Game.drawBitmap(BitmapManager.goodJobM[1], moneyX, moneyY[1]);
        Game.drawBitmap(BitmapManager.goodJobTips, iconX[2], iconY[2]);
        Game.drawText(String.valueOf(data[2]), dataX, dataY[2], pData2);
        Game.drawText(String.valueOf(data[2]), dataX, dataY[2], pData1);
        Game.drawBitmap(BitmapManager.goodJobM[1], moneyX, moneyY[2]);
        Game.drawBitmap(BitmapManager.goodJobTotal, iconX[3], iconY[3]);
        Game.drawText("=", equalX, dataY[3], pTotal2);
        Game.drawText("=", equalX, dataY[3], pTotal1);
        Game.drawText(String.valueOf(data[3]), dataX, dataY[3], pTotal2);
        Game.drawText(String.valueOf(data[3]), dataX, dataY[3], pTotal1);
        Game.drawBitmap(BitmapManager.goodJobM[2], moneyX, moneyY[3]);
        if(complete)
        {
            Game.drawText(next, nextX, nextY, pNext2);
            Game.drawText(next, nextX, nextY, pNext1);
            if(!inverted)
            {
                Game.drawBitmap(BitmapManager.goodJobArrow, arrowX, arrowY);
            } else
            {
                Game.drawBitmap(BitmapManager.goodJobArrowOn, arrowX, arrowY);
            }
        }
        j = 0;
        while(j < 8) {
            if(best[j])
            {
                Game.drawBitmap(BitmapManager.goodJobBest, bestX[j], bestY[j]);
            }
            j++;
        }
        switch(winLoose) {
            default:
                break;
            case 1:
                Game.drawBitmap(BitmapManager.goodJobValid, validX, validY);
                break;
            case 2:
                Game.drawBitmap(BitmapManager.goodJobReject, rejectX, rejectY);
                break;
        }
        App.fader.restore();
        App.showTrophy();
        App.showDebug();
    }

    public void paramNavigate(int i)
    {
        saveDayData();
        switch(i)
        {
        case 4: // '\004'
        default:
            return;

        case 3: // '\003'
            SoundManager.playSound(29);
            UIManager.configIsFrom = 9;
            Common.analytics("goodjob/menu/settings");
            navigate(3);
            return;

        case 5: // '\005'
            SoundManager.playSound(29);
            break;
        }
        Common.analytics("goodjob/menu/quit");
        navigate(1);
    }

    public void reinitColor()
    {
        inverted = false;
        setTextColor();
    }
}
