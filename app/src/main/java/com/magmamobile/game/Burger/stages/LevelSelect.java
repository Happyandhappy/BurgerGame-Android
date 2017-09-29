package com.magmamobile.game.Burger.stages;

import android.graphics.*;
import android.util.Log;

import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.*;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.Trainer;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;

public final class LevelSelect extends GameStage
{

    private static int selected[];
    private final int COLORS[] = {
        0xffd5691a, 0xffad1457, 0xff1e6b1f, 0xff661e67, 0xffa47b19, 0xff035086, 0xffc14f34, 0xff78143f, 0xff174f22, 0xff4a244c, 
        0xff715619, 0xff0f256e
    };
    private final int CURRENT_STAGE = -1;
    private final int DAYS[] = {
        R.string.res_monday, R.string.res_tuesday, R.string.res_wednesday, R.string.res_thursday, R.string.res_friday, R.string.res_saturday, R.string.res_sunday
    };
    private final int DAYS_LEFT[];
    private final int MIN_VELOCITY = 100;
    private final int MONTHS[] = {
        R.string.res_january, R.string.res_february, R.string.res_march, R.string.res_april, R.string.res_may, 
		R.string.res_june, R.string.res_july, R.string.res_august, R.string.res_september, R.string.res_october, 
        R.string.res_november, R.string.res_december
    };
    private final int TICKET_COLOR = 0xff606060;
    private boolean activated;
    private int arrowCx1;
    private int arrowCx2;
    private int arrowCy;
    private int arrowH;
    private int arrowW;
    private int arrowX1;
    private int arrowX2;
    private int arrowXS1;
    private int arrowXS2;
    private int arrowY;
    private int arrowYS;
    private int blocX[];
    private int blocY[];
    private int currentMonth;
    private String datas[];
    private int dayX[];
    private int dayY;
    private String days[];
    private int f;
    private float fSelect;
    private int flagX;
    private int flagY;
    private PageFlip flip;
    private int frame;
    private int gridH;
    private int gridY;
    private boolean isInVelocity;
    private int lastDay[];
    private int lastMonth;
    private int logoX;
    private int logoY;
    private int mGoalX;
    private int mGoalY;
    private int maskH;
    private int maskW;
    private int maskX[];
    private int maskY;
    private String month[];
    private int monthX;
    private int monthY;
    private Matrix mtx;
    private Matrix mtxLeft;
    private Matrix mtxRight;
    private int nextH;
    private int nextStage;
    private int nextW;
    private int nextX;
    private int nextY;
    private Paint pAlias;
    private Paint pDay;
    private Paint pFlip;
    private Paint pGoal;
    private Paint pMaskDay;
    private Paint pMoney;
    private Paint pMonth;
    private Paint pOrder;
    private Paint pRect[];
    private Paint pSelect;
    private Paint pTicket;
    private Paint pTitle;
    private Paint pTotal;
    private int pageH;
    private int phase;
    private int rectH;
    private int rectT;
    private int rectW;
    private int rectX;
    private int rectY;
    private float scale;
    private int selectCx;
    private int selectCy;
    private int selectH;
    private int selectPhase;
    private boolean selectVisible;
    private int selectW;
    private int selectX;
    private int selectY;
    private int ticketX[] = {
        368, 400, 374, 436, 374, 436, 452, 452, 450
    };
    private int ticketY[] = {
        64, 86, 114, 114, 136, 136, 160, 180, 206
    };
    private String title;
    private int titleX;
    private int titleY;
    private boolean trained;
    private Trainer trainer;
    private boolean velocityUsed;
    private float xVelocity;
    private float yVelocity;
    private int zoneH;
    private int zoneW;

    public LevelSelect()
    {
        int ai[] = new int[12];
        ai[0] = 3;
        ai[2] = 3;
        ai[3] = 2;
        ai[4] = 3;
        ai[5] = 2;
        ai[6] = 3;
        ai[7] = 3;
        ai[8] = 2;
        ai[9] = 3;
        ai[10] = 2;
        ai[11] = 3;
        DAYS_LEFT = ai;
    }

    private void changeStage()
    {
        switch(nextStage)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return;

        case 8: // '\b'
            Game.setStage(8);
            return;

        case 1: // '\001'
            Game.setStage(1);
            return;

        case 3: // '\003'
            Game.setStage(3);
            return;

        case 5: // '\005'
            Game.setStage(5);
            return;
        }
    }

    private void drawPage(int i, int j)
    {
        int k;
        boolean flag;
        int l;
        int j1;
        Game.drawRect(rectX, rectY, rectW, rectH, pRect[j]);
        if(i > 0)
        {
            if(selected[0] >= i)
            {
                Game.drawBitmap(BitmapManager.calendarLeft, arrowX1, arrowY);
            } else
            if(PrefManager.configs[3])
            {
                Game.drawBitmap(BitmapManager.calendarLeft2, mtxLeft, pAlias);
            } else
            if(selectVisible)
            {
                Game.drawBitmap(BitmapManager.calendarLeft2, arrowXS1, arrowYS);
            } else
            {
                Game.drawBitmap(BitmapManager.calendarLeft, arrowX1, arrowY);
            }
        }
        if(i < 11)
        {
            if(selected[0] <= i)
            {
                Game.drawBitmap(BitmapManager.calendarRight, arrowX2, arrowY);
            } else
            if(PrefManager.configs[3])
            {
                Game.drawBitmap(BitmapManager.calendarRight2, mtxRight, pAlias);
            } else
            if(selectVisible)
            {
                Game.drawBitmap(BitmapManager.calendarRight2, arrowXS2, arrowYS);
            } else
            {
                Game.drawBitmap(BitmapManager.calendarRight, arrowX2, arrowY);
            }
        }
        Game.drawText(month[j], monthX, monthY, pMonth);
        k = 0;
        while(k < 7) {
            Game.drawText(days[k], dayX[k], dayY, pDay);
            k++;
        }
        if(DAYS_LEFT[i] < 3) {
            j1 = 2;
            while (j1 >= DAYS_LEFT[i]) {
                Game.drawRect(maskX[j1], maskY, maskW, maskH, pMaskDay);
                j1--;
            }
        }
        flag = true;
        l = 0;
        while(l < 5) {
            int i1 = 0;
            while(i1 < 7)
            {
                if(i1 >= 6)
                {
                    Game.drawBitmap(BitmapManager.calendarLock, blocX[i1], blocY[l]);
                }else {
                    if (l == 4 && i1 >= DAYS_LEFT[i]) {
                        if(i == selected[0]) {
                            if (selected[0] != lastDay[0] || selected[1] != lastDay[1] || selected[2] != lastDay[2] || PrefManager.higherLevel >= 365) {
                                if (PrefManager.configs[3]) {
                                    Game.drawBitmap(BitmapManager.calendarCross2, mtx, pSelect);
                                    return;
                                }
                                if (selectVisible) {
                                    Game.drawBitmap(BitmapManager.calendarCross2, selectX, selectY);
                                    return;
                                }
                            } else if (!PrefManager.configs[3]) {
                                if (selectVisible) {
                                    Game.drawBitmap(BitmapManager.calendarRound2, selectX, selectY);
                                    return;
                                }
                            } else
                                Game.drawBitmap(BitmapManager.calendarRound2, mtx, pSelect);
                        }
                        return;
                    }
                    if (i <= lastDay[0]) {
                        boolean flag1;
                        if (i == lastDay[0] && i1 == lastDay[1] && l == lastDay[2]) {
                            flag1 = true;
                        } else {
                            flag1 = false;
                        }
                        if ((i != selected[0] || i1 != selected[1] || l != selected[2]) && flag) {
                            if (flag1 && PrefManager.higherLevel <= 364) {
                                Game.drawBitmap(BitmapManager.calendarRound, blocX[i1], blocY[l]);
                                flag = false;
                            } else {
                                Game.drawBitmap(BitmapManager.calendarCross, blocX[i1], blocY[l]);
                            }
                        } else if (flag1) {
                            flag = false;
                        }
                    }
                }
                i1++;
            }
            l++;
        }
        if(i == selected[0]) {
            if (selected[0] != lastDay[0] || selected[1] != lastDay[1] || selected[2] != lastDay[2] || PrefManager.higherLevel >= 365) {
                if (PrefManager.configs[3]) {
                    Game.drawBitmap(BitmapManager.calendarCross2, mtx, pSelect);
                    return;
                }
                if (selectVisible) {
                    Game.drawBitmap(BitmapManager.calendarCross2, selectX, selectY);
                    return;
                }
            } else if (!PrefManager.configs[3]) {
                if (selectVisible) {
                    Game.drawBitmap(BitmapManager.calendarRound2, selectX, selectY);
                    return;
                }
            } else
                Game.drawBitmap(BitmapManager.calendarRound2, mtx, pSelect);
        }
    }

    private void gridManager(int i, int j, boolean flag)
    {
        int k = i + j * 7 + UIManager.DAYS_TOTAL[currentMonth];
        if(k <= PrefManager.higherLevel && k < UIManager.DAYS_TOTAL[1 + currentMonth] && k < 365 && i != 6)
        {
            GameManager.currentLevel = k;
            selected[0] = currentMonth;
            selected[1] = i;
            selected[2] = j;
            GameManager.setGoalByDay(selected);
            setPointerCoord(i, j);
            updateDay();
            if(!flag)
            {
                SoundManager.playSound(29);
            }
        }
    }

    private void navigate(int i)
    {
label0:
        {
            nextStage = i;
            activated = false;
            UIManager.backButtonActive = false;
            if(i != 8)
            {
                if(i != 3)
                {
                    break label0;
                }
                App.pause.fadeOut();
            }
            return;
        }
        App.fader.fadeOut();
    }

    private void nextMonth()
    {
label0:
        {
            if(currentMonth < 11)
            {
                if(!PrefManager.configs[3])
                {
                    break label0;
                }
                activated = false;
                lastMonth = currentMonth;
                currentMonth = 1 + currentMonth;
                setMonth(currentMonth, 0);
                setMonth(lastMonth, 1);
                phase = 2;
                flip.moveOut();
            }
            return;
        }
        currentMonth = 1 + currentMonth;
        setMonth(currentMonth, 0);
        SoundManager.playSound(24);
    }

    private void prevMonth()
    {
label0:
        {
            if(currentMonth > 0)
            {
                if(!PrefManager.configs[3])
                {
                    break label0;
                }
                activated = false;
                lastMonth = currentMonth;
                currentMonth = -1 + currentMonth;
                setMonth(lastMonth, 0);
                setMonth(currentMonth, 1);
                phase = 1;
                flip.moveIn();
            }
            return;
        }
        currentMonth = -1 + currentMonth;
        setMonth(currentMonth, 0);
        SoundManager.playSound(23);
    }

    private void setMonth(int i, int j)
    {
        pRect[j].setColor(COLORS[i]);
        month[j] = Game.getResString(MONTHS[i]).toUpperCase();
    }

    private void setPointerCoord(int i, int j)
    {
        int k = rectX + i * zoneW;
        int l = gridY + j * zoneH;
        selectX = k - (BitmapManager.calendarRound2.getWidth() - zoneW) / 2;
        selectY = l - (BitmapManager.calendarRound2.getHeight() - zoneH) / 2;
        selectCx = k + zoneW / 2;
        selectCy = l + zoneH / 2;
    }

    private void updateDay()
    {
        boolean flag;
        String as[];
        String s1;
        String as1[];
        String s2;
        String as2[];
        String s3;
        String as3[];
        String s4;
        String as4[];
        String s5;
        String as5[];
        String s6;
        String as6[];
        String s7;
        String as7[];
        String s8;
        int i;
        if(GameManager.currentLevel == 0)
        {
            title = Game.getResString(R.string.res_training).toUpperCase();
            pTitle.setTextScaleX(1.0F);
            int j = Game.getTextWidth(title, pTitle);
            int k = Game.scalei(120F);
            if(j > k)
            {
                pTitle.setTextScaleX((float)k / (float)j);
            }
        } else
        {
            String s = Game.getResString(R.string.res_startday);
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(GameManager.currentLevel);
            title = String.format(s, aobj).toUpperCase();
            pTitle.setTextScaleX(1.0F);
        }
        flag = DataManager.loadDayData(GameManager.currentLevel);
        datas[0] = String.valueOf(GameManager.dayGoal);
        as = datas;
        if(flag)
        {
            s1 = (new StringBuilder("x")).append(DataManager.dayOrders).toString();
        } else
        {
            s1 = "";
        }
        as[1] = s1;
        as1 = datas;
        if(flag)
        {
            s2 = (new StringBuilder("x")).append(DataManager.dayBurgers).toString();
        } else
        {
            s2 = "";
        }
        as1[2] = s2;
        as2 = datas;
        if(flag)
        {
            s3 = (new StringBuilder("x")).append(DataManager.dayDishes).toString();
        } else
        {
            s3 = "";
        }
        as2[3] = s3;
        as3 = datas;
        if(flag)
        {
            s4 = (new StringBuilder("x")).append(DataManager.dayDrinks).toString();
        } else
        {
            s4 = "";
        }
        as3[4] = s4;
        as4 = datas;
        if(flag)
        {
            s5 = (new StringBuilder("x")).append(DataManager.dayDesserts).toString();
        } else
        {
            s5 = "";
        }
        as4[5] = s5;
        as5 = datas;
        if(flag)
        {
            s6 = String.valueOf(DataManager.dayIncomes);
        } else
        {
            s6 = "0";
        }
        as5[6] = s6;
        as6 = datas;
        if(flag)
        {
            s7 = String.valueOf(DataManager.dayTips);
        } else
        {
            s7 = "0";
        }
        as6[7] = s7;
        as7 = datas;
        if(flag)
        {
            s8 = String.valueOf(DataManager.dayTotal);
        } else
        {
            s8 = "0";
        }
        as7[8] = s8;
        i = BitmapManager.calendarFlag.getWidth() + Game.getTextWidth(datas[0], pGoal) + BitmapManager.calendarM.getWidth() + Game.scalei(4);
        flagX = titleX - i / 2;
        ticketX[0] = flagX + BitmapManager.calendarFlag.getWidth() + Game.scalei(2);
        mGoalX = ticketX[0] + Game.getTextWidth(datas[0], pGoal) + Game.scalei(2);
    }

    private void velocityManager()
    {
        if(Math.abs(xVelocity) < 100F || Math.abs(yVelocity) >= 100F){
            if(Math.abs(xVelocity) < 100F && Math.abs(yVelocity) >= 100F)
            {
                if(yVelocity > 0.0F)
                    prevMonth();
                else
                    nextMonth();
            }
            return;
        }
        if(xVelocity <= 0.0F)
            nextMonth();
        else
            prevMonth();
    }

    public boolean enterOnResume()
    {
        return false;
    }

    public void onAction()
    {
        float f1;
        float f2;
        boolean flag;
        if(activated)
        {
            if(TouchScreen.eventDown)
            {
                if(TouchScreen.isInRect(nextX, nextY, nextW, nextH))
                {
                    SoundManager.playSound(28);
                    SoundManager.fadding = true;
                    f = 15;
                    frame = 0;
                    navigate(8);
                    Common.analytics("level/button/next");
                    if(trained)
                    {
                        Game.setPrefInt("levelTrainerCount", 1 + Game.getPrefInt("levelTrainerCount", 0));
                    }
                } else if(TouchScreen.isInRect(rectX, rectY, rectW, pageH))
                {
                    App.tracker.addMovement(TouchScreen.x, TouchScreen.y);
                    yVelocity = 0.0F;
                    xVelocity = 0.0F;
                }
            } else if(TouchScreen.eventUp) {
                if(velocityUsed)
                {
                    App.tracker.addMovement(TouchScreen.x, TouchScreen.y);
                    App.tracker.computeCurrentVelocity(100);
                    xVelocity = App.tracker.getXVelocity();
                    yVelocity = App.tracker.getYVelocity();
                    velocityManager();
                    velocityUsed = false;
                } else if(TouchScreen.isInRect(rectX, rectY, rectH, rectH)) {
                    prevMonth();
                } else if(TouchScreen.isInRect(rectT, rectY, rectH, rectH)) {
                    nextMonth();
                } else if(TouchScreen.isInRect(rectX, gridY, rectW, gridH))
                {
                    int i = (TouchScreen.x - rectX) / zoneW;
                    int j = (TouchScreen.y - gridY) / zoneH;
                    if(currentMonth != selected[0] || i != selected[1] || j != selected[2])
                    {
                        gridManager(i, j, false);
                    }
                }
            } else if(TouchScreen.eventMoving && isInVelocity)
            {
                App.tracker.addMovement(TouchScreen.x, TouchScreen.y);
                velocityUsed = true;
            }
        }
        if(!PrefManager.configs[3])
        {
            fSelect = 0.075F + fSelect;
            if(fSelect >= 0.5F)
            {
                fSelect = 0.0F;
                if(selectVisible)
                    flag = false;
                else
                    flag = true;
                selectVisible = flag;
            }
        }else {
            switch (selectPhase) {
                default:
                    break;
                case 1:
                    fSelect = 0.075F + fSelect;
                    if (fSelect >= 1.0F) {
                        fSelect = 1.0F;
                        selectPhase = 2;
                    }
                    break;
                case 2:
                    fSelect = fSelect - 0.075F;
                    if (fSelect < 0.0F) {
                        fSelect = 0.0F;
                        selectPhase = 1;
                    }
                    break;
            }
            scale = MathUtils.lerpDecelerate(0.8F, 1.0F, fSelect);
            mtx.setTranslate(selectCx, selectCy);
            mtx.postScale(scale, scale, selectCx, selectCy);
            mtx.postTranslate(-scale * (float) selectW, -scale * (float) selectH);
            f1 = -scale * (float) arrowW;
            f2 = -scale * (float) arrowH;
            mtxLeft.setTranslate(arrowCx1, arrowCy);
            mtxLeft.postScale(scale, scale, arrowCx1, arrowCy);
            mtxLeft.postTranslate(f1, f2);
            mtxRight.setTranslate(arrowCx2, arrowCy);
            mtxRight.postScale(scale, scale, arrowCx2, arrowCy);
            mtxRight.postTranslate(f1, f2);
        }
        if(flip.isPlaying())
        {
            flip.animate();
            if(flip.isFinished())
            {
                flip.stop();
                setMonth(currentMonth);
                pSelect.setAlpha(255);
                activated = true;
            }
            if(selected[0] == lastMonth && flip.phase == 1 && flip.f <= 0.5F)
            {
                pSelect.setAlpha((int)(255F * (1.0F - 2.0F * flip.f)));
            } else
            if(selected[0] == currentMonth && flip.phase == 2)
            {
                if(flip.f >= 0.5F)
                {
                    pSelect.setAlpha((int)(510F * (flip.f - 0.5F)));
                } else
                {
                    pSelect.setAlpha(0);
                }
            }
        }
        if(nextStage != 8)
        {
            f = 1 + f;
            if(f >= 4)
            {
                f = 0;
                frame = 1 + frame;
                frame = frame % 3;
            }
        } else
        if(f > 0)
        {
            f = -1 + f;
            if(f % 3 == 0)
            {
                frame = 1 + frame;
                frame = frame % 2;
            }
        } else
        if(f == 0 && !App.fader.isPlaying())
        {
            App.fader.fadeOut();
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
        if(trained)
        {
            trainer.animate();
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
            navigate(5);
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
        int i1;
        pAlias = new Paint();
        pAlias.setAntiAlias(true);
        pRect = new Paint[2];
        pRect[0] = new Paint();
        pRect[1] = new Paint();
        rectX = Game.scalei(12);
        rectY = Game.scalei(10);
        rectW = Game.scalei(322);
        rectH = Game.scalei(50);
        rectT = (rectX + rectW) - rectH;
        month = new String[2];
        pageH = Game.scalei(234);
        gridY = rectY + rectH;
        gridH = Game.scalei(200);
        zoneW = rectW / 7;
        zoneH = gridH / 5;
        arrowX1 = rectX + Game.scalei(6);
        arrowX2 = rectX + Game.scalei(300);
        arrowY = rectY + Game.scalei(3);
        arrowCx1 = arrowX1 + BitmapManager.calendarLeft.getWidth() / 2;
        arrowCx2 = arrowX2 + BitmapManager.calendarLeft.getWidth() / 2;
        arrowCy = arrowY + BitmapManager.calendarLeft.getHeight() / 2;
        arrowW = BitmapManager.calendarLeft2.getWidth() / 2;
        arrowH = BitmapManager.calendarLeft2.getHeight() / 2;
        arrowXS1 = arrowCx1 - arrowW;
        arrowXS2 = arrowCx2 - arrowW;
        arrowYS = arrowCy - arrowH;
        mtxLeft = new Matrix();
        mtxRight = new Matrix();
        pMonth = new Paint();
        pMonth.setColor(-1);
        pMonth.setTextAlign(android.graphics.Paint.Align.CENTER);
        pMonth.setTextSize(Game.scalef(26F));
        pMonth.setTypeface(Typeface.DEFAULT_BOLD);
        monthX = rectX + rectW / 2;
        monthY = rectY + Game.scalei(28);
        lastDay = new int[3];
        pDay = new Paint(pMonth);
        pDay.setTextSize(Game.scalef(10F));
        dayX = new int[7];
        days = new String[7];
        i = 0;
        while(i < 7) {
            dayX[i] = rectX + (int)((float)(rectW / 7) * (0.5F + (float)i));
            days[i] = Game.getResString(DAYS[i]);
            i++;
        }
        dayY = rectY + Game.scalei(46);
        pSelect = new Paint(pAlias);
        pTitle = new Paint(pMonth);
        pTitle.setTextSize(Game.scalef(24F));
        pTitle.setColor(0xff606060);
        titleX = Game.scalei(408);
        titleY = Game.scalei(44);
        flagX = Game.scalei(350);
        flagY = Game.scalei(50);
        mGoalX = Game.scalei(400);
        mGoalY = Game.scalei(54);
        pTicket = new Paint();
        pTicket.setTypeface(Typeface.SERIF);
        pTicket.setTextSize(Game.scalef(13F));
        pTicket.setColor(0xff606060);
        pGoal = new Paint(pTicket);
        pGoal.setTypeface(Typeface.create(Typeface.SERIF, 1));
        pOrder = new Paint(pTicket);
        pOrder.setTextSize(Game.scalef(14F));
        pMoney = new Paint(pTicket);
        pMoney.setTypeface(Typeface.create(Typeface.SERIF, 1));
        pMoney.setTextSize(Game.scalef(14F));
        pMoney.setTextAlign(android.graphics.Paint.Align.RIGHT);
        pTotal = new Paint(pMoney);
        pTotal.setTextSize(Game.scalef(17F));
        datas = new String[9];
        j = 0;
        while(j < 9) {
            ticketX[j] = Game.scalei(ticketX[j]);
            ticketY[j] = Game.scalei(ticketY[j]);
            j++;
        }
        int k;
        blocX = new int[7];
        blocY = new int[5];
        k = 0;
        while(k < 7) {
            blocX[k] = rectX + k * zoneW;
            k++;
        }
        int l = 0;
        while(l < 5){
            blocY[l] = gridY + l * zoneH;
            l++;
        }
        mtx = new Matrix();
        selectW = BitmapManager.calendarRound2.getWidth() / 2;
        selectH = BitmapManager.calendarRound2.getHeight() / 2;
        pMaskDay = new Paint();
        pMaskDay.setColor(0xffd9d9d9);
        maskX = new int[3];
        i1 = 0;
        while(i1 < 3)
        {
            maskX[i1] = blocX[i1] + Game.scalei(30);
            i1++;

        }
        maskY = gridY + Game.scalei(186);
        maskW = Game.scalei(14);
        maskH = Game.scalei(12);
        logoX = Game.scalei(364);
        logoY = Game.scalei(216);
        nextX = Game.scalei(344);
        nextY = Game.scalei(212);
        nextW = Game.scalei(128);
        nextH = Game.scalei(50);
        flip = new PageFlip();
        flip.x = rectX;
        flip.y = rectY - Game.scalei(14);
        flip.width = rectW;
        flip.height = Game.scalei(264);
        flip.mode = 1;
        flip.init();
        pFlip = new Paint();
        pFlip.setColor(-1);
        pFlip.setAntiAlias(true);
        trainer = new Trainer();
        trainer.setDirection(3);
        trainer.offset(Game.scalei(324), Game.scalei(216));
    }

    public void onLateResume()
    {
        resetStage();
    }

    public void onRender()
    {
        Paint paint;
        App.fader.apply();
        Game.drawBitmap(BitmapManager.background);
        int i;
        if(flip.isPlaying())
        {
            int j;
            int k;
            if(phase == 1)
            {
                j = lastMonth;
            } else
            {
                j = currentMonth;
            }
            drawPage(j, 0);
            Game.mCanvas.save();
            Game.mCanvas.clipPath(flip.path1);
            Game.drawBitmapParcel(BitmapManager.background, rectX, gridY, rectW, gridH, rectX, gridY);
            if(phase == 1)
            {
                k = currentMonth;
            } else
            {
                k = lastMonth;
            }
            drawPage(k, 1);
            Game.mCanvas.restore();
            Game.mCanvas.drawPath(flip.path2, pFlip);
            Game.mCanvas.save();
            Game.mCanvas.clipPath(flip.path2);
            Game.drawBitmap(BitmapManager.calendarBack, flip.mtx, pAlias);
            Game.mCanvas.restore();
        } else
        {
            drawPage(currentMonth, 0);
        }
        Game.drawText(title, titleX, titleY, pTitle);
        Game.drawBitmap(BitmapManager.calendarFlag, flagX, flagY);
        Game.drawBitmap(BitmapManager.calendarM, mGoalX, mGoalY);
        i = 0;
        do
        {
            if(i >= 9)
            {
                if(nextStage != 8)
                {
                    if(frame != 0)
                        Game.drawBitmap(BitmapManager.calendarArrow[frame], logoX, logoY);
                } else if(frame == 1)
                    Game.drawBitmap(BitmapManager.calendarArrow[0], logoX, logoY);
                if(trained && activated)
                {
                    trainer.draw();
                }
                App.fader.restore();
                if(App.pause.isPlaying())
                {
                    App.pause.draw();
                }
                App.showTrophy();
                App.showDebug();
                return;
            }
            if(i >= 8)
            {
                paint = pTotal;
            } else
            if(i >= 6)
            {
                paint = pMoney;
            } else
            if(i == 1)
            {
                paint = pOrder;
            } else
            if(i == 0)
            {
                paint = pGoal;
            } else
            {
                paint = pTicket;
            }
            Game.drawText(datas[i], ticketX[i], ticketY[i], paint);
            i++;
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
            UIManager.configIsFrom = 6;
            SoundManager.playSound(29);
            Common.analytics("level/menu/settings");
            navigate(3);
            return;

        case 5: // '\005'
            SoundManager.playSound(29);
            break;
        }
        Common.analytics("level/menu/quit");
        navigate(1);
    }

    public void resetStage()
    {
        boolean flag = true;
        activated = false;
        selectVisible = flag;
        BitmapManager.setBackground(6);
        PrefManager.updateConfig();
        UIManager.backButtonActive = false;
        nextStage = -1;
        if(UIManager.configIsFrom != 6)
        {
            lastDay = UIManager.getDayByLevel(PrefManager.higherLevel);
            if(GameManager.currentLevel == -1)
            {
                GameManager.currentLevel = PrefManager.higherLevel;
            }
            if(GameManager.currentLevel >= 365)
            {
                GameManager.currentLevel = 364;
            }
            selected = UIManager.getDayByLevel(GameManager.currentLevel);
            currentMonth = selected[0];
            setMonth(currentMonth);
            setPointerCoord(selected[1], selected[2]);
            gridManager(selected[1], selected[2], flag);
            frame = 0;
            f = 0;
            fSelect = 1.0F;
            selectPhase = ((flag) ? 1 : 0);
            if(Game.getPrefInt("levelTrainerCount", 0) >= 3)
            {
                flag = false;
            }
            trained = flag;
        }
        if(UIManager.configIsFrom == 6)
        {
            App.pause.fadeIn();
        } else
        {
            App.fader.fadeIn();
        }
        UIManager.configIsFrom = 3;
        SoundManager.playBGMusic();
    }

    public void setMonth(int i)
    {
        setMonth(i, 0);
    }
}
