package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import com.magmamobile.game.Burger.display.PageFlip;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.GameManager;
import com.magmamobile.game.engine.Game;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Menus
{

    private final int DECAL_X = 3;
    private final int DECAL_X_TIMED = 110;
    private final int DECAL_Y = 3;
    private final int HEIGHT = 160;
    private final int MAX_HEIGHT = 154;
    private final int MAX_SPACING = 12;
    private final int MAX_WIDTH = 108;
    private final int WIDTH = 114;
    private final int WIDTH_TIMED = 130;
    private boolean activated[];
    public PageFlip flip;
    public int length;
    public MenuItem menu[];
    private int menuTimedX[];
    private int menuX[];
    private int menuY;
    public int menus[][][];

    public Menus()
    {
        super();
        int i = 4;
        int ai[] = new int[i];
        ai[0] = 362;
        menuX = ai;
        int ai1[] = new int[i];
        ai1[0] = 346;
        menuTimedX = ai1;
        menuY = 0;
        activated = new boolean[i];
        if(GameManager.monoMenu)
        {
            i = 1;
        }
        length = i;
        menu = new MenuItem[length];
        menuY = Game.scalei(menuY);
        int j = length;
        byte byte0;
        int k;
        if(GameManager.monoMenu)
        {
            byte0 = 3;
        } else
        {
            byte0 = 2;
        }

        //little strange
        menus = (int[][][])Array.newInstance(int[].class, new int[] {
            j, byte0
        });
        k = 0;
        do
        {
            if(k >= length)
            {
                flip = new PageFlip();
                flip.y = menuY;
                flip.height = Game.scalei(160);
                MenuTimer.x = menuTimedX[0] + Game.scalei(114);
                MenuTimer.y = menu[0].y + Game.scalei(18);
                MenuTimer.width = BitmapManager.menuTimer.getWidth();
                MenuTimer.height = BitmapManager.menuTimer.getHeight();
                return;
            }
            if(BitmapManager.menus[k] == null)
            {
                BitmapManager.menus[k] = Game.createBitmap(Game.scalei(114), Game.scalei(160));
            }
            menuX[k] = Game.scalei(menuX[k]);
            menuTimedX[k] = Game.scalei(menuTimedX[k]);
            menu[k] = new MenuItem(k);
            menu[k].y = menuY;
            menu[k].height = Game.scalei(160);
            menu[k].menuX = Game.scalei(3);
            menu[k].menuY = Game.scalei(3);
            menu[k].menuWidth = Game.scalei(108);
            menu[k].menuHeight = Game.scalei(154);
            menu[k].maxSpacing = Game.scalei(12);
            if(GameManager.monoMenu)
            {
                menus[k][2] = new int[18];
            }
            k++;
        } while(true);
    }

    private void activate(int i)
    {
        activated[i] = true;
    }

    public void deactivate(int i)
    {
        activated[i] = false;
    }

    public int[] getAccomp(int i)
    {
        return menus[i][1];
    }

    public int[] getBurger(int i)
    {
        return menus[i][0];
    }

    public int[][] getMenu(int i)
    {
        return menus[i];
    }

    public void init()
    {
        MenuItem menuitem;
        int j;
        int k;
        PageFlip pageflip1;
        char c;
        removeMenu(0);
        int i = 0;
        do
        {
            if(i >= length)
            {
                PageFlip pageflip = flip;
                if(GameManager.menuTimed)
                {
                    k = menuTimedX[0];
                } else
                {
                    k = menuX[0];
                }
                pageflip.x = k;
                pageflip1 = flip;
                if(GameManager.menuTimed)
                {
                    c = '\202';
                } else
                {
                    c = 'r';
                }
                pageflip1.width = Game.scalei(c);
                flip.mode = 0;
                flip.init();
                flip.visible = false;
                return;
            }
            menuitem = menu[i];
            if(GameManager.menuTimed)
            {
                j = menuTimedX[i];
            } else
            {
                j = menuX[i];
            }
            menuitem.x = j;
            menu[i].width = Game.scalei(114);
            menu[i].timedX = menu[i].x + Game.scalei(110);
            i++;
        } while(true);
    }

    public boolean isActivated(int i)
    {
        return activated[i];
    }

    public boolean isEmpty()
    {
        return isEmpty(0);
    }

    public boolean isEmpty(int i)
    {
        int j;
        if(menus[i][0] != null && menus[i][1] != null) {
            if(menus[i][0].length > 0)
            {
                return true;
            }
            j = menus[i][1].length;
            if(j <= 0)
                return false;
            return true;
        }
        return true;
    }

    public void nextPart()
    {
        MenuInfo.nextPart(menu[0].burgerCoord[Burger.length]);
    }

    public void reinitInfo()
    {
        MenuInfo.reinit();
    }

    public void removeMenu(int i)
    {
        deactivate(i);
    }

    public void setMenu(int i, int ai[][])
    {
        int j;
        activate(i);
        menus[i][0] = ai[0];
        menus[i][1] = ai[1];
        menu[i].setMenu(menus[i][0], menus[i][1]);
        if(!GameManager.monoMenu || ai[1] == null)
            return;
        Arrays.fill(menus[i][2], 0);
        j = 0;
        while(j < ai[1].length) {
            int ai1[] = menus[i][2];
            int k = ai[1][j];
            ai1[k] = 1 + ai1[k];
            j++;
        }
    }

    public void validateAccomp(int i)
    {
        if(!GameManager.isFreeplay() || menus[0][1] == null)
            return;
        int j = 0;
        while(j < menus[0][1].length) {
            if(!MenuInfo.aInfo[j] && menus[0][1][j] == i)
            {
                MenuInfo.validateAccomp(j);
                return;
            }
            j++;
        }
    }

    public void validateBurger()
    {
        MenuInfo.validateBurger();
    }
}
