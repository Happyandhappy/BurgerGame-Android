package com.magmamobile.game.Burger.game;

import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.engine.Game;
import java.util.Arrays;
import java.util.Random;

public class Accomp
{

    public static int deltaX[];
    public static int deltaY[];
    private static Random random = new Random();
    private final int CENTER_X[] = (new int[] {
            272, 194, 342, 294, 220, 370
    });;
    private final int CENTER_Y[] = (new int[] {
            183, 190, 185, 209, 217, 215
    });;
    private boolean LIMIT_PLACE[];
    public int accomp[];
    public AccompItem items[];
    public int length;
    public int menuLength;
    public int places[];
    public boolean visible;

    public Accomp()
    {
        int i;
        boolean aflag[] = new boolean[6];
        aflag[0] = true;
        aflag[1] = true;
        aflag[3] = true;
        aflag[4] = true;
        LIMIT_PLACE = aflag;
        visible = true;
        deltaX = (new int[] {
            34, 38, 36, 34, 42, 38
        });
        deltaY = (new int[] {
            106, 56, 118, 106, 92, 96
        });
        accomp = new int[6];
        places = new int[6];
        i = 0;
        while(i < 6){
            deltaX[i] = Game.scalei(deltaX[i]);
            deltaY[i] = Game.scalei(deltaY[i]);
            i++;
        }
        int j;
        items = new AccompItem[6];
        j = 0;
        while(j < 6)
        {
            items[j] = new AccompItem();
            items[j].setCenter(Game.scalei(CENTER_X[j]), Game.scalei(CENTER_Y[j]));
            j++;
        }
        Arrays.fill(accomp, 18);
        Arrays.fill(places, -1);
    }

    public void addItem(int i)
    {
        visible = true;
        int j;
        do
        {
            j = random.nextInt(6);
        } while(items[j].added || menuLength <= 4 && !LIMIT_PLACE[j] && !GameManager.isFreeplay());
        items[j].setType(i);
        items[j].moveIn();
        accomp[length] = i;
        places[length] = j;
        length = 1 + length;
        if(GameManager.gameMode != 2)
        {
            Arrays.sort(accomp);
        }
        SoundManager.playSound(10 + (i - 12));
        VibrationManager.vibrateItem(i);
    }

    public void clear()
    {
        int i = 0;
        do
        {
            if(i >= 6)
            {
                length = 0;
                visible = true;
                return;
            }
            items[i].clear();
            accomp[i] = 18;
            i++;
        } while(true);
    }

    public int getType(int i)
    {
        return items[i].type;
    }

    public void init()
    {
        visible = true;
        removeAllItems();
        menuLength = 6;
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    public void removeAllItems()
    {
        AccompItem aaccompitem[] = items;
        int i = aaccompitem.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                Arrays.fill(accomp, 18);
                Arrays.fill(places, -1);
                length = 0;
                return;
            }
            AccompItem accompitem = aaccompitem[j];
            if(accompitem.added)
            {
                accompitem.moveOut();
            }
            j++;
        } while(true);
    }

    public void removeLastItem()
    {
        if(length <= 0 || places[-1 + length] < 0)
        {
            return;
        } else
        {
            items[places[-1 + length]].moveOut();
            length = -1 + length;
            accomp[length] = 18;
            places[length] = -1;
            return;
        }
    }

    public void resetAllItems()
    {
        AccompItem aaccompitem[] = items;
        int i = aaccompitem.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                length = 0;
                return;
            }
            aaccompitem[j].added = false;
            j++;
        } while(true);
    }

    public void restore(int ai[], int ai1[])
    {
        restore(ai, ai1, true);
    }

    public void restore(int ai[], int ai1[], boolean flag)
    {
        resetAllItems();
        do
        {
            int i;
            for(i = 0; i >= ai.length || ai[i] == 18;)
            {
                return;
            }

            accomp[i] = ai[i];
            length = 1 + length;
            places[i] = ai1[i];
            AccompItem accompitem = items[ai1[i]];
            accompitem.setType(accomp[i]);
            if(flag)
            {
                accompitem.moveIn();
            } else
            {
                accompitem.show();
            }
            i++;
        } while(true);
    }

}
