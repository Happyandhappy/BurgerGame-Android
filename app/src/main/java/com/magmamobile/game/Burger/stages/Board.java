package com.magmamobile.game.Burger.stages;

import android.graphics.*;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.*;
import com.magmamobile.game.Burger.game.*;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.Trainer;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;
import java.util.Arrays;

public final class Board extends GameStage
{

    public static Accomp accomp;
    public static boolean activated;
    public static boolean animated;
    public static Burger burger;
    public static boolean finished;
    public static boolean freezed;
    public static boolean paused;
    public static Plate plate;
    public static Tray tray;
    private final int ADDED_ANIM = 160;
    private final float ADDED_SCALEMAX = 1.5F;
    private final int ADDED_STEP = 10;
    private final float SPEED_IN = 0.2F;
    private final float SPEED_OUT = 0.1F;
    private final int TEMPO_VALID = 15;
    private int addedCount;
    private int addedCx;
    private int addedCy;
    private int addedItem;
    private ServiceBell bell;
    private int boxesY;
    private ClockTimer clock;
    private int compareItem[];
    private int dX;
    private float f;
    private int flashCount;
    private int flashItem;
    private int flashX[];
    private int flashY1;
    private int flashY2;
    private IndicatorInfo gain;
    private int item;
    private int itemS;
    private int itemS2;
    private int itemX[] = {
        6, 50, 94, 136, 180, 222, 350, 394, 438
    };
    private int itemY1;
    private int itemY2;
    private Menus menus;
    private InfoMessage message;
    private Matrix mtx;
    private int nextStage;
    private int outX;
    private Paint p;
    private Paint pAlias;
    private int phase;
    private boolean quitting;
    private float scale;
    private ScoreDisplay score;
    private SnapButton snap;
    private int tempo;
    private IndicatorInfo timeAdded;
    private TipCoins tipCoins;
    private Trainer trainer;
    private UndoButton undo;

    public Board()
    {
    }

    private void acceptMenu()
    {
        acceptMenu(0);
    }

    private void acceptMenu(int i)
    {
        activated = false;
        moveTrayOut();
        if(!GameManager.isFreeplay())
        {
            int ai[][] = menus.menus[i];
            SoundManager.playSound(19);
            int j;
            int k;
            int l;
            int i1;
            int j1;
            if(ai[0] != null)
            {
                j = 1 * ai[0].length;
            } else
            {
                j = 0;
            }
            k = 3 + j;
            if(ai[1] != null)
            {
                l = 2 * ai[1].length;
            } else
            {
                l = 0;
            }
            i1 = k + l;
            if(GameManager.menuTimed)
            {
                if(MenuTimer.percent <= 0.5F)
                {
                    j1 = (int)((float)i1 * (2.0F * (0.5F - MenuTimer.percent)));
                } else
                {
                    j1 = 0;
                }
                if(j1 > 0)
                {
                    tipCoins.addCoins((int)Math.ceil((float)j1 / 2.0F));
                }
            } else
            {
                j1 = 0;
            }
            gain.showValue(i1 + j1);
            GameManager.dayIncome = i1 + GameManager.dayIncome;
            GameManager.dayTips = j1 + GameManager.dayTips;
            GameManager.dayTotal += i1 + j1;
            score.update();
            if(!GameManager.isFreeplay())
            {
                countTrayItems(menus.menus[0]);
            }
            if(GameManager.gameMode == 1)
            {
                addAttackTime();
                GameManager.setHarnessByOrders();
            }
            menus.menu[i].validate();
            if(GameManager.menuTimed)
            {
                MenuTimer.stop();
            }
        }
    }

    public static void activate()
    {
        if(!finished)
        {
            activated = true;
        }
    }

    private void addAttackTime()
    {
        int i = GameManager.getTimeToAdd(Burger.length + accomp.length);
        clock.addTime(i);
        timeAdded.showValue(i / 1000);
    }

    private void addItem(int i)
    {
        if(GameManager.trainingMode && trainer.activated)
        {
            if(i == trainer.itemTargeted)
            {
                placeItem(i);
                trainer.nextItem();
                offsetTrainer();
                return;
            } else
            {
                SoundManager.playSound(18);
                flashItem(trainer.itemTargeted);
                return;
            }
        } else
        {
            placeItem(i);
            return;
        }
    }

    private void addPlate()
    {
        plate.moveIn();
    }

    private void animate()
    {
        switch(phase)
        {
        default:
            tempo = 0;
            phase = 0;
            return;

        case 1: // '\001'
            f = 0.2F + f;
            if(f > 1.0F)
            {
                tray.setScale(1.0F);
                boolean flag = GameManager.isFreeplay();
                int i = 0;
                if(flag)
                {
                    i = 5;
                }
                phase = i;
                return;
            } else
            {
                tray.setScale(MathUtils.lerpOvershoot(0.0F, 1.0F, f));
                return;
            }

        case 2: // '\002'
            break;
        }
        if(tempo > 0)
        {
            tempo = -1 + tempo;
            return;
        }
        if(!PrefManager.configs[3])
        {
            tray.visible = false;
            plate.visible = false;
            burger.visible = false;
            accomp.visible = false;
        }
        if(f == 0.0F)
        {
            if(!GameManager.isFreeplay())
            {
                menus.flip.moveOut();
            }
            SoundManager.playSound(21);
        }
        f = 0.1F + f;
        if(f > 1.0F)
        {
            clearTray();
            moveTrayIn();
            return;
        } else
        {
            dX = (int)MathUtils.lerpAccelerate(0.0F, outX, f);
            return;
        }
    }

    private void changeStage()
    {
        if(nextStage == 1)
        {
            quit();
        }
        Game.setStage(nextStage);
    }

    private void changeStage(int i)
    {
        nextStage = i;
        changeStage();
    }

    private void clearTray()
    {
        f = 0.0F;
        dX = 0;
        burger.clear();
        plate.clear();
        accomp.clear();
    }

    private void compare()
    {
        int ai[];
        int ai1[];
        if(GameManager.isFreeplay())
            return;
        ai = menus.menus[0][0];
        ai1 = menus.menus[0][1];
        if((ai != null || Burger.length != 0) && (ai == null || Burger.length != ai.length) || (ai1 != null || accomp.length != 0) && (ai1 == null || accomp.length != ai1.length)) {
            if(!compareMenu(true))
                rejectMenu();
            return;
        }
        if(!compareMenu(0))
            rejectMenu();
        else
            acceptMenu(0);
    }

    private boolean compareMenu(int i)
    {
        int ai[];
        int ai1[];
        int k1;
        int l1;
        int j2;
        int k2;
        int i1;
        int j1;
        ai = menus.menus[i][0];
        ai1 = menus.menus[i][1];
        if(ai != null) {
            k1 = Burger.length;
            l1 = ai.length;
            if (k1 != l1)
                return false;
            int i2 = 0;
            while (i2 < ai.length) {
                j2 = ai[i2];
                k2 = burger.burger[i2];
                if (j2 != k2)
                    return false;
                i2++;
            }
        }
        if(ai1 == null)
            return true;
        int j;
        int k;
        j = accomp.length;
        k = ai1.length;
        if(j != k)
            return false;
        int l = 0;
        while(l < ai1.length) {
            i1 = ai1[l];
            j1 = accomp.accomp[l];
            if(i1 != j1)
                return false;
            l++;
        }
        return true;
    }

    private boolean compareMenu(boolean flag)
    {
        int ai[];
        int ai1[];
        Arrays.fill(compareItem, 0);
        ai = menus.menus[0][0];
        ai1 = menus.menus[0][1];
        if(ai == null){
            if(Burger.length != 0)
                return false;
        }else{
            int k = -1 + Burger.length;
            if(!(Burger.length == 0 || burger.burger[k] == ai[k]))
                return false;
        }
        if(ai1 == null) {
            if(accomp.length == 0)
                return true;
            return false;
        }
        int i = 0;
        while(i < accomp.length) {
            int ai2[] = compareItem;
            int j = accomp.accomp[i];
            ai2[j] = 1 + ai2[j];
            if(compareItem[accomp.accomp[i]] > menus.menus[0][2][accomp.accomp[i]])
                return false;
            i++;
        }
        return true;
    }

    private void countTrayItems(int ai[][])
    {
        int i;
        int ai1[];
        int j;
        i = 0;
        GameManager.orderSend = 1 + GameManager.orderSend;
        PrefManager.totalMenu = 1 + PrefManager.totalMenu;
        if(ai[0] != null)
        {
            GameManager.burgerSend = 1 + GameManager.burgerSend;
            PrefManager.totalBurger = 1 + PrefManager.totalBurger;
        }
        if(ai[1] != null) {
            ai1 = ai[1];
            j = ai1.length;
            while (i < j) {
                int k;
                k = ai1[i];
                if (k <= 13) {
                    GameManager.dessertSend = 1 + GameManager.dessertSend;
                    PrefManager.totalDessert = 1 + PrefManager.totalDessert;
                } else if (k <= 15) {
                    GameManager.drinkSend = 1 + GameManager.drinkSend;
                    PrefManager.totalDrink = 1 + PrefManager.totalDrink;
                } else {
                    GameManager.dishSend = 1 + GameManager.dishSend;
                    PrefManager.totalDish = 1 + PrefManager.totalDish;
                }

                i++;
            }
        }
        AchievementManager.compareTray();
        PrefManager.saveTrayData();
    }

    private void drawClock(int i, int j, int k)
    {
        try
        {
            Game.drawBitmap(BitmapManager.clockNum[i], j, k);
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void drawItem(int i, int j)
    {
        int k;
        int l;
        Bitmap bitmap;
        int i1;
        if(j == 0)
        {
            k = itemY1;
        } else
        {
            k = itemY2;
        }
        l = i / 2;
        if(flashCount <= 0 || (flashCount / 5) % 2 != 1 || i != flashItem) {
            if(addedCount <= 0 || i != addedItem)
            {
                if(GameManager.isActive(i))
                    Game.drawBitmap(BitmapManager.boardItems[i], itemX[l], k);
                return;
            }
            if(animated || (addedCount / 5) % 2 != 1)
                return;
            Game.drawBitmap(BitmapManager.boardItems[i], itemX[l], k);
            return;
        }
        bitmap = BitmapManager.flashItems[i];
        i1 = flashX[l];
        int j1;
        if(j == 0)
        {
            j1 = flashY1;
        } else
        {
            j1 = flashY2;
        }
        Game.drawBitmap(bitmap, i1, j1);
    }

    private void endMenuTimer()
    {
        MenuTimer.clear();
        rejectMenu();
        menus.flip.moveOut();
    }

    private void flashItem(int i)
    {
        flashItem = i;
        flashCount = 30;
    }

    private void freeze()
    {
        pauseElements();
        freezed = true;
    }

    private void gotoTrash()
    {
        if(Burger.length > 0 || accomp.length > 0)
        {
            activated = false;
            if(Burger.length > 0)
            {
                burger.moveOut();
            }
            if(plate.added)
            {
                removePlate();
            }
            if(accomp.length > 0)
            {
                accomp.removeAllItems();
            }
            if(GameManager.menuHelp)
            {
                menus.reinitInfo();
            }
            SoundManager.playSound(16);
        }
    }

    private void moveTrayIn()
    {
        f = 0.0F;
        tray.setScale(0.0F);
        SoundManager.playSound(22);
        phase = 1;
    }

    private void moveTrayOut()
    {
        f = 0.0F;
        tempo = 15;
        phase = 2;
    }

    private void offsetTrainer()
    {
        Trainer trainer1 = trainer;
        int i = itemX[trainer.itemTargeted / 2];
        int j;
        if(trainer.itemTargeted % 2 == 1)
        {
            j = itemY1;
        } else
        {
            j = itemY2;
        }
        trainer1.offset(i, j);
    }

    private void pauseElements()
    {
        MenuTimer.pause();
        ClockTimer.pause();
    }

    private void placeItem(int i)
    {
        if(i >= 12) {
            if(accomp.length < 6)
            {
                accomp.addItem(i);
                UndoManager.addItem(i);
                menus.validateAccomp(i);
                compare();
            } else {
                SoundManager.playSound(17);
            }
            return;
        }
        if(!startStop(i))
            return;
        burger.addItem(i);
        UndoManager.addItem(i);
        if(Burger.length != 1) {
            try
            {
                if(!GameManager.isFreeplay() && Burger.length == menus.menus[0][0].length)
                {
                    menus.validateBurger();
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }else
            addPlate();
        if(GameManager.menuHelp && Burger.length < 15)
            menus.nextPart();
        compare();
    }

    private void quit()
    {
        clock.reset();
        MenuTimer.stop();
        MenuTimer.clear();
        SoundManager.stopClock();
    }

    private void rejectMenu()
    {
        activated = false;
        SoundManager.playSound(20);
        gotoTrash();
    }

    private void removePlate()
    {
        plate.moveOut();
    }

    private void restoreTray()
    {
        boolean flag = true;
        burger.restore(GalleryManager.savedTray[0], false);
        accomp.restore(GalleryManager.savedTray[1], GalleryManager.savedTray[2], false);
        Plate plate1 = plate;
        if(Burger.length <= 0)
        {
            flag = false;
        }
        plate1.added = flag;
    }

    private void resume()
    {
        UIManager.fromPause = false;
        App.pause.fadeIn();
    }

    private void resumeElements()
    {
        MenuTimer.resume();
        ClockTimer.resume();
    }

    private void snap()
    {
        if(Burger.length == 0 && accomp.length == 0)
        {
            SoundManager.playSound(29);
            return;
        }
        if(!GalleryManager.addSnap(burger.burger, accomp.accomp, accomp.places))
        {
            SoundManager.playSound(29);
            return;
        } else
        {
            PhotoFlash.flash();
            return;
        }
    }

    private boolean startStop(int i)
    {
        if(Burger.length > 0 && burger.getLastItem() == 1)
        {
            SoundManager.playSound(17);
            return false;
        }
        if(Burger.length == 0 && i != 0)
        {
            SoundManager.playSound(18);
            flashItem(0);
            return false;
        }
        if(Burger.length == 14 && i != 1)
        {
            SoundManager.playSound(18);
            flashItem(1);
            return false;
        } else
        {
            return true;
        }
    }

    private void undo()
    {
        if(UndoManager.restore)
        {
            UndoManager.restore = false;
            burger.restore(UndoManager.getBurger());
            if(Burger.length > 0)
            {
                plate.moveIn();
            }
            accomp.restore(UndoManager.getAccomp(), UndoManager.getPlaces());
        } else
        if(UndoManager.length > 0)
        {
            if(UndoManager.getLastItem() < 12)
            {
                burger.removeLastItem();
                if(Burger.length == 0)
                {
                    plate.moveOut();
                }
            } else
            {
                accomp.removeLastItem();
            }
            UndoManager.undo();
            return;
        }
    }

    private void unfreeze()
    {
        resumeElements();
        freezed = false;
        activated = true;
        UIManager.backButtonActive = true;
    }

    public void changeMenu()
    {
        changeMenu(0);
    }

    public void changeMenu(int i)
    {
        Accomp accomp1;
        int j;
        if(GameManager.trainingMode)
        {
            menus.setMenu(0, trainer.getMenu());
            if(trainer.activated)
            {
                offsetTrainer();
            }
        } else
        {
            menus.setMenu(0, MenuGenerator.getMenu(GameManager.hardness, GameManager.pMenu1, GameManager.pMenu2));
        }
        accomp1 = accomp;
        if(menus.menus[0][1] != null)
        {
            j = menus.menus[0][1].length;
        } else
        {
            j = 0;
        }
        accomp1.menuLength = j;
        if(GameManager.menuTimed)
        {
            int k;
            int l;
            if(menus.menus[0][0] != null)
            {
                k = menus.menus[0][0].length * GameManager.itemTime;
            } else
            {
                k = 0;
            }
            if(menus.menus[0][1] != null)
            {
                l = menus.menus[0][1].length * GameManager.itemTime;
            } else
            {
                l = 0;
            }
            MenuTimer.setTimer(l + (k + GameManager.orderTime));
        }
    }

    public void endLevel()
    {
        activated = false;
        finished = true;
        PrefManager.addPlayCount();
        nextStage = 9;
        UIManager.backButtonActive = false;
        switch(GameManager.gameMode) {
            default:
                break;
            case 0:
                if (GameManager.trainingMode) {
                    menus.deactivate(0);
                    message.setText(Game.getResString(R.string.res_trainingend));
                } else {
                    message.setText(Game.getResString(R.string.res_dayend).toUpperCase());
                }
                if (GameManager.dayTotal >= GameManager.dayGoal) {
                    Common.analytics((new StringBuilder("game/endlevel/")).append(GameManager.getFormatedCurrentStage()).append("/win").toString());
                    if (PrefManager.higherLevel <= GameManager.currentLevel) {
                        PrefManager.higherLevel = UIManager.nextLevel(GameManager.currentLevel);
                        PrefManager.saveHigherLevel();
                        AchievementManager.compareDay();
                    }
                } else {
                    Common.analytics((new StringBuilder("game/endlevel/")).append(GameManager.getFormatedCurrentStage()).append("/loose").toString());
                }
                DataManager.updateDayData();
                DataManager.saveDayData(GameManager.currentLevel);
                PrefManager.totalTips += GameManager.dayTips;
                PrefManager.totalIncome += GameManager.dayIncome;
                PrefManager.saveMoneyData();
                break;
            case 1:
                message.setText(Game.getResString(R.string.res_timeout).toUpperCase());
                if (GameManager.dayTotal > PrefManager.scoreAttack) {
                    PrefManager.scoreAttack = GameManager.dayTotal;
                }
                PrefManager.totalAttack += GameManager.dayTotal;
                PrefManager.saveTimeAttack();
                break;
        }
        if(GameManager.trainingMode)
        {
            message.moveIn();
            App.fader.wait(32, 2);
        } else
        {
            message.wait(32, 1);
            App.fader.wait(64, 2);
        }
        if(GameManager.menuTimed)
        {
            MenuTimer.stop();
        }
    }

    public void onAction()
    {
        int i;
        int j;
        if(paused)
        {
            if(App.pause.isFinished())
            {
                App.pause.stop();
                if(App.pause.bgAlpha == 0)
                {
                    paused = false;
                    unfreeze();
                } else
                {
                    changeStage();
                }
            } else
            if(App.pause.isPlaying())
            {
                App.pause.animate();
            }
        } else {
            if(GameManager.gameTimed && !ClockTimer.isPaused() && !GameManager.trainingMode)
            {
                if(ClockTimer.isRunning())
                {
                    clock.animate();
                    if(clock.isOver())
                    {
                        clock.stop();
                        clock.ring();
                        endLevel();
                    }
                } else
                if(clock.isRinging())
                {
                    clock.animate();
                }
            }
            if(phase == 5)
            {
                phase = 0;
                activated = true;
            } else
            if(phase != 0)
            {
                animate();
            }
            if(addedCount > 0)
            {
                addedCount = -1 + addedCount;
            }
            if(animated)
            {
                if(addedCount > 0)
                {
                    int k = addedCount / 10;
                    float f1 = (float)(addedCount % 10) / 10F;
                    if(k % 2 == 1)
                    {
                        scale = MathUtils.lerpAccelerate(1.5F, 1.0F, f1);
                    } else
                    {
                        scale = MathUtils.lerpDecelerate(1.0F, 1.5F, f1);
                    }
                    mtx.setTranslate(addedCx, addedCy);
                    mtx.postScale(scale, scale, addedCx, addedCy);
                    mtx.postTranslate(-scale * (float)itemS2, -scale * (float)itemS2);
                }
                if(GameManager.menuHelp && activated)
                {
                    MenuInfo.animate();
                }
            }
            if(plate.isPlaying())
            {
                plate.animate();
                plate.mtx.postTranslate(-dX, 0.0F);
            }
            if(burger.isPlaying())
            {
                burger.animate();
                burger.mtx.postTranslate(-dX, 0.0F);
            }
            AccompItem accompitem;
            i = 0;
            while(i < 6) {
                accompitem = accomp.items[i];
                if(accompitem.added && accompitem.isPlaying())
                {
                    accompitem.animate();
                    accompitem.mtx.postTranslate(-dX, 0.0F);
                }
                i++;
            }
            if(flashCount > 0)
            {
                flashCount = -1 + flashCount;
            }
            if(menus.flip.isPlaying())
            {
                if(menus.flip.isFinished())
                {
                    if(GameManager.trainingMode && trainer.complete)
                    {
                        menus.flip.stop();
                        endLevel();
                    } else
                    if(!finished)
                    {
                        changeMenu();
                        menus.flip.moveIn();
                    }
                } else
                {
                    menus.flip.animate();
                }
            }
            if(MenuTimer.isRunning() && GameManager.menuTimed)
            {
                if(MenuTimer.isFinished())
                {
                    endMenuTimer();
                } else
                if(MenuTimer.isRunning() && !MenuTimer.isPaused())
                {
                    MenuTimer.onTimer();
                }
            }
            if(tipCoins.hasCoins())
            {
                tipCoins.animate();
            }
            if(GameManager.trainingMode && trainer != null)
            {
                trainer.animate();
            }
            if(TouchScreen.eventDown && activated) {
                j = 0;
                while (j < 9) {
                    if (GameManager.isActive(j * 2) && TouchScreen.isInRect(itemX[j], itemY2, itemS, itemS)) {
                        addItem(j * 2);
                    } else {
                        if (!GameManager.isActive(1 + j * 2) || !TouchScreen.isInRect(itemX[j], itemY1, itemS, itemS)) {
                            j++;
                            continue;
                        }
                        addItem(1 + j * 2);
                    }
                    break;
                }
            }
            score.update();
            timeAdded.animate();
            gain.animate();
            if(message.isRunning() && !freezed)
            {
                message.animate();
                if(message.isFinished())
                {
                    message.stop();
                    if(App.fader.opaque)
                    {
                        changeStage();
                    } else
                    {
                        startGame();
                    }
                }
            }
            if(App.fader.isPlaying())
            {
                if(App.fader.isFinished())
                {
                    App.fader.stop();
                    if(!App.fader.opaque)
                    {
                        if(!message.isRunning())
                        {
                            message.moveOut();
                        }
                        if(freezed)
                        {
                            unfreeze();
                        }
                    } else
                    if(App.fader.opaque)
                    {
                        if(nextStage == 3 || quitting)
                        {
                            changeStage();
                        } else
                        {
                            message.moveOut(true);
                        }
                    }
                } else
                {
                    App.fader.animate();
                }
            }
            if(GameManager.isFreeplay())
            {
                bell.animate();
                undo.animate();
                snap.animate();
                PhotoFlash.animate();
                if(TouchScreen.eventDown && activated)
                {
                    if(bell.onAction())
                    {
                        bell.ring();
                        if(!burger.isEmpty() || !accomp.isEmpty())
                        {
                            acceptMenu();
                            UndoManager.saveMenu(burger.burger, accomp.accomp, accomp.places);
                        }
                    }
                    if(undo.onAction())
                    {
                        undo();
                    }
                    if(snap.onAction())
                    {
                        snap();
                    }
                }
            }
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            nextStage = 10;
            pause();
        }
    }

    public void onEnter()
    {
        super.onEnter();
        activated = false;
        finished = false;
        PrefManager.updateConfig();
        animated = PrefManager.configs[3];
        quitting = false;
        BitmapManager.setBackground(8);
        if(UIManager.configIsFrom == 8 || UIManager.galleryIsFrom == 8)
        {
            if(UIManager.galleryIsFrom == 8)
            {
                restoreTray();
            }
            UIManager.configIsFrom = 3;
            UIManager.galleryIsFrom = 4;
            App.pause.fadeIn();
            if(!animated)
            {
                MenuInfo.noAnim();
            }
        } else
        if(UIManager.fromPause)
        {
            resume();
        } else
        {
            addedItem = -1;
            addedCount = 0;
            if(GameManager.gameMode == 0)
            {
                int i = UIManager.getNewItemByLevel(GameManager.currentLevel);
                if(!UIManager.fromNewItem)
                {
                    if(i != -1)
                    {
                        changeStage(7);
                        return;
                    }
                } else
                {
                    addedItem = GameManager.MONTH_ITEM[i];
                    addedCx = itemX[addedItem / 2] + itemS2;
                    int j;
                    if(addedItem % 2 == 1)
                    {
                        j = itemY1;
                    } else
                    {
                        j = itemY2;
                    }
                    addedCy = j + itemS2;
                    addedCount = 160;
                    UIManager.fromNewItem = false;
                }
            }
            if(!GameManager.isFreeplay())
            {
                GameManager.initDayStat();
                GameManager.setHarnessByLevel(GameManager.currentLevel);
                DataManager.loadDayData(GameManager.currentLevel);
                MenuGenerator.reinit();
                score.init();
                MenuTimer.stop();
                MenuTimer.clear();
                if(GameManager.trainingMode)
                {
                    if(trainer == null)
                    {
                        trainer = new Trainer();
                    }
                    trainer.init();
                } else
                if(!GameManager.trainingMode && trainer != null)
                {
                    trainer = null;
                }
            } else
            {
                bell.init();
                undo.init();
                snap.init();
                UndoManager.reset();
                PhotoFlash.init();
            }
            dX = 0;
            paused = false;
            freezed = false;
            clearTray();
            menus.init();
            clock.reset();
            startLevel();
        }
        UIManager.backButtonActive = false;
        UIManager.configIsFrom = 3;
        UIManager.galleryIsFrom = 4;
        UIManager.fromPause = false;
        SoundManager.stopBGMusic();
    }

    public void onInitialize()
    {
        MenuInfo.init();
        tipCoins = new TipCoins();
        message = new InfoMessage();
        score = new ScoreDisplay();
        boxesY = Game.mBufferHeight - BitmapManager.boxes.getHeight();
        flashX = new int[9];
        int i = 0;
        do
        {
            if(i >= itemX.length)
            {
                itemY1 = Game.scalei(238);
                itemY2 = Game.scalei(280);
                itemS = BitmapManager.boardItems[0].getWidth();
                itemS2 = itemS / 2;
                pAlias = new Paint();
                pAlias.setAntiAlias(true);
                flashY1 = itemY1 - Game.scalei(4);
                flashY2 = itemY2 - Game.scalei(4);
                burger = new Burger();
                Burger burger1 = burger;
                burger.getClass();
                burger1.dx = Game.scalei(138) / 2;
                burger.dy = Game.scalei(272);
                burger.setCenter(Game.scalei(16) + burger.dx, Game.scalei(203));
                plate = new Plate();
                plate.dx = BitmapManager.plate.getWidth() / 2;
                plate.dy = BitmapManager.plate.getHeight() / 2;
                plate.setCenter(burger.cx, burger.cy);
                accomp = new Accomp();
                menus = new Menus();
                compareItem = new int[18];
                p = new Paint();
                p.setColor(-1);
                p.setAntiAlias(true);
                tray = new Tray();
                tray.setCenter(Game.scalei(196), Game.scalei(200));
                outX = Game.scalei(480);
                clock = new ClockTimer();
                clock.offset(Game.scalei(122), 0);
                mtx = new Matrix();
                timeAdded = new IndicatorInfo();
                timeAdded.x = Game.mBufferCW;
                timeAdded.y = Game.scalei(56);
                timeAdded.decalY = Game.scalei(-24);
                timeAdded.stroked = true;
                gain = new IndicatorInfo();
                gain.x = Game.scalei(305);
                gain.y = Game.scalei(256);
                gain.decalY = Game.scalei(-24);
                gain.stroked = true;
                gain.setColor(-1, 0xffb06000);
                snap = new SnapButton(Game.scalei(422), Game.scalei(20));
                undo = new UndoButton(Game.scalei(422), Game.scalei(92));
                bell = new ServiceBell(Game.scalei(420), Game.scalei(170));
                return;
            }
            itemX[i] = Game.scalei(itemX[i]);
            flashX[i] = itemX[i] - Game.scalei(4);
            i++;
        } while(true);
    }

    public void onLeave()
    {
        super.onLeave();
    }

    public void onRender()
    {
        AccompItem aaccompitem[];
        int j;
        int k;
        int l;
        int j1;
        int i;
        AccompItem accompitem;
        if(App.fader.isPlaying() && !App.fader.isWaiting() || !App.fader.opaque) {
            App.fader.apply();
            try
            {
                Game.drawBitmap(BitmapManager.background);
                Game.drawBitmap(BitmapManager.boxes, 0, boxesY, null);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
            if(GameManager.gameTimed)
            {
                if(GameManager.gameMode == 0)
                {
                    Game.drawBitmap(BitmapManager.clockBG1, clock.x, clock.y);
                    if(clock.hammerRing)
                    {
                        Game.drawBitmap(BitmapManager.clockHammer, clock.mtxHammer, null);
                    } else
                    {
                        Game.drawBitmap(BitmapManager.clockHammer, clock.xHammer, clock.yHammer);
                    }
                    Game.drawBitmap(BitmapManager.clockHour, clock.mtxShadHour, clock.paint);
                    Game.drawBitmap(BitmapManager.clockMin, clock.mtxShadMin, clock.paint);
                    Game.drawBitmap(BitmapManager.clockHour, clock.mtxHour, null);
                    Game.drawBitmap(BitmapManager.clockMin, clock.mtxMin, null);
                }
                if(!GameManager.isFreeplay())
                {
                    Game.drawBitmap(BitmapManager.clockBG2, clock.nx, clock.ny);
                    if(clock.isVisible() && !GameManager.trainingMode)
                    {
                        drawClock(clock.numbers[0], clock.xNum1, clock.yNum1);
                        if(clock.isDotVisible())
                        {
                            Game.drawBitmap(BitmapManager.clockDots, clock.xDots, clock.yDots);
                        }
                        drawClock(clock.numbers[1], clock.xNum2, clock.yNum2);
                        drawClock(clock.numbers[2], clock.xNum3, clock.yNum3);
                    }
                }
            }
            if(GameManager.isFreeplay())
            {
                snap.draw();
                undo.draw();
                bell.draw();
            }
            if(tray.visible)
            {
                Game.drawBitmap(BitmapManager.tray, tray.x - dX, tray.y, tray.width, tray.height, pAlias);
            }
            aaccompitem = accomp.items;
            i = aaccompitem.length;
            j = 0;
            while(j < i) {
                accompitem = aaccompitem[j];
                if(accompitem.added)
                {
                    if(accompitem.isPlaying() && animated)
                    {
                        Game.drawBitmap(BitmapManager.accompItems[accompitem.type], accompitem.mtx, pAlias);
                    } else
                    if(accomp.visible)
                    {
                        Game.drawBitmap(BitmapManager.accompItems[accompitem.type], accompitem.x - dX, accompitem.y);
                    }
                }
                j++;
            }
            if(plate.added)
            {
                if(plate.isPlaying() && animated)
                {
                    Game.drawBitmap(BitmapManager.plate, plate.mtx, pAlias);
                } else
                if(plate.visible)
                {
                    Game.drawBitmap(BitmapManager.plate, plate.x - dX, plate.y);
                }
            }
            if(Burger.length > 0 && burger.visible)
            {
                if(burger.isPlaying() && animated)
                {
                    Game.drawBitmap(BitmapManager.burger, burger.mtx, pAlias);
                } else
                if(burger.visible)
                {
                    Game.drawBitmap(BitmapManager.burger, burger.x - dX, burger.y);
                }
            }
            if(!GameManager.isFreeplay())
            {
                score.draw();
            }
            k = 0;
            while(k < 9) {
                item = k * 2;
                drawItem(item, 1);
                drawItem(1 + item, 0);
                k++;
            }
            if(addedCount > 0 && animated)
            {
                Game.drawBitmap(BitmapManager.boardItems[addedItem], mtx, pAlias);
            }
            l = 0;
            while(l < menus.length) {
                if(menus.flip.visible) {
                    if (!menus.flip.isPlaying() || !animated) {
                        if (menus.isActivated(l)) {
                            Game.drawBitmap(BitmapManager.menus[l], menus.menu[l].x, menus.menu[l].y);
                            if (GameManager.menuTimed) {
                                Game.drawBitmap(BitmapManager.menuR, menus.menu[l].timedX, 0, null);
                            }
                            if (!(!GameManager.menuHelp || menus.menu[l].validated)) {
                                int i1;
                                if (menus.menus[l][0] != null && activated) {
                                    if (Burger.length < menus.menus[l][0].length) {
                                        Game.drawBitmap(BitmapManager.arrowR, MenuInfo.x, MenuInfo.y, MenuInfo.width, MenuInfo.height);
                                    } else if (menus.menus[l][1] != null) {
                                        if (animated && MenuInfo.bPlaying) {
                                            Game.drawBitmap(BitmapManager.menuCoche, MenuInfo.bMtx, MenuInfo.bPaint);
                                        } else {
                                            Game.drawBitmap(BitmapManager.menuCoche, MenuInfo.bx, MenuInfo.by, MenuInfo.bw, MenuInfo.bh);
                                        }
                                    }
                                }
                                if (menus.menus[l][1] != null) {
                                    i1 = 0;
                                    while (i1 < 6) {
                                        if (MenuInfo.aInfo[i1]) {
                                            if (animated && MenuInfo.aPlaying[i1]) {
                                                Game.drawBitmap(BitmapManager.menuCoche, MenuInfo.aMtx[i1], MenuInfo.aPaint[i1]);
                                            } else {
                                                int ai[] = menus.menu[l].accompCoord[i1];
                                                Game.drawBitmap(BitmapManager.menuCoche, ai[0], ai[1], ai[2], ai[3]);
                                            }
                                        }
                                        i1++;
                                    }
                                }
                            }
                            if (GameManager.menuTimed) {
                                if (menus.menu[l].validated) {
                                    Game.drawBitmap(BitmapManager.menuTimer, MenuTimer.x, MenuTimer.y);
                                } else {
                                    Game.drawBitmap(BitmapManager.menuTimer, 0, 0, MenuTimer.width, MenuTimer.sh, MenuTimer.x, MenuTimer.y, MenuTimer.width, MenuTimer.sh, null);
                                }
                            }
                        }
                    } else {
                        Game.mCanvas.save();
                        Game.mCanvas.clipPath(menus.flip.path1);
                        Game.drawBitmap(BitmapManager.menus[l], menus.menu[l].x, menus.menu[l].y);
                        if (GameManager.menuTimed) {
                            Game.drawBitmap(BitmapManager.menuR, menus.menu[l].timedX, 0, null);
                            if (menus.flip.phase == 2) {
                                Game.drawBitmap(BitmapManager.menuTimer, MenuTimer.x, MenuTimer.y);
                            }
                        }
                        Game.mCanvas.restore();
                        Game.mCanvas.drawPath(menus.flip.path2, p);
                        Game.mCanvas.save();
                        Game.mCanvas.clipPath(menus.flip.path2);
                        Game.drawBitmap(BitmapManager.menuBack, menus.flip.mtx, null);
                        Game.mCanvas.restore();
                    }
                }
                l++;
            }
            PhotoFlash.draw();
            if(activated && GameManager.trainingMode && trainer.activated)
            {
                trainer.draw();
            }
            if(tipCoins.hasCoins()) {
                j1 = 0;
                tipCoins.getClass();
                while (j1 < 32) {
                    int k1 = tipCoins.depth[j1].intValue();
                    if (tipCoins.active[k1]) {
                        Game.drawBitmap(BitmapManager.coin[tipCoins.face[k1]], (int) tipCoins.x[k1], (int) tipCoins.y[k1], tipCoins.width[k1], tipCoins.height[k1], null);
                    }
                    j1++;
                }
            }
            timeAdded.draw();
            gain.draw();
            if(message.visible && (quitting || nextStage == 3))
            {
                Game.drawText(message.label, message.x, message.y, message.pLabel2);
                Game.drawText(message.label, message.x, message.y, message.pLabel1);
                if(message.goalAdded)
                {
                    Game.drawText(message.goal, message.gx, message.gy, message.pGoal2);
                    Game.drawText(message.goal, message.gx, message.gy, message.pGoal1);
                    Game.drawBitmap(BitmapManager.mIntro, message.mx, message.my);
                }
            }
            App.fader.restore();
        }else
            Game.drawColor(App.fader.color);
        if(message.visible && !quitting && nextStage != 3)
        {
            Game.drawText(message.label, message.x, message.y, message.pLabel2);
            Game.drawText(message.label, message.x, message.y, message.pLabel1);
            if(message.goalAdded)
            {
                Game.drawText(message.goal, message.gx, message.gy, message.pGoal2);
                Game.drawText(message.goal, message.gx, message.gy, message.pGoal1);
                Game.drawBitmap(BitmapManager.mIntro, message.mx, message.my);
            }
        }
        if(paused)
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
            freeze();
            SoundManager.playSound(29);
            nextStage = 3;
            UIManager.configIsFrom = 8;
            pause();
            return;

        case 5: // '\005'
            quitting = true;
            SoundManager.playSound(29);
            nextStage = 1;
            App.fader.fadeOut();
            return;

        case 2: // '\002'
            freeze();
            SoundManager.playSound(29);
            nextStage = 4;
            UIManager.galleryIsFrom = 8;
            GalleryManager.saveTray(burger.burger, accomp.accomp, accomp.places);
            pause();
            return;
        }
    }

    public void pause()
    {
        pause(false);
    }

    public void pause(boolean flag)
    {
        paused = true;
        activated = false;
        UIManager.backButtonActive = false;
        UIManager.fromPause = true;
        pauseElements();
        if(!flag)
        {
            App.pause.fadeOut();
        }
    }

    public void startGame()
    {
        activated = true;
        UIManager.backButtonActive = true;
        if(!GameManager.isFreeplay())
        {
            changeMenu();
            menus.flip.moveIn();
        }
        if(GameManager.gameTimed)
        {
            clock.start();
        }
    }

    public void startLevel()
    {
        switch(GameManager.gameMode) {
            default:
                 break;
            case 0:
                if(PrefManager.higherLevel <= GameManager.currentLevel)
                {
                    Common.analytics((new StringBuilder("game/start/career/")).append(GameManager.getFormatedCurrentStage()).append("/new").toString());
                } else
                {
                    Common.analytics((new StringBuilder("game/start/career/")).append(GameManager.getFormatedCurrentStage()).append("/replay").toString());
                }
                if(GameManager.trainingMode)
                {
                    message.setText(Game.getResString(R.string.res_training));
                    clock.setTimer(0);
                } else
                {
                    InfoMessage infomessage = message;
                    String s = Game.getResString(R.string.res_startday);
                    Object aobj[] = new Object[1];
                    aobj[0] = Integer.valueOf(GameManager.currentLevel);
                    infomessage.setText(String.format(s, aobj).toUpperCase(), GameManager.dayGoal);
                    clock.setTimer(GameManager.gameTime);
                }
                break;
            case 1:
                Common.analytics("game/start/timeAttack");
                message.setText(Game.getResString(R.string.res_ready), GameManager.dayGoal);
                clock.setTimer(45);
                break;
            case 2:
                Common.analytics("game/start/freeplay");
                message.setText(Game.getResString(R.string.res_freeplay));
                break;
        }
        message.moveIn();
        App.fader.wait(32, 1);
    }
}
