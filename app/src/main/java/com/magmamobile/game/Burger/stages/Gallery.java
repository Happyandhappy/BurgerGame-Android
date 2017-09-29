package com.magmamobile.game.Burger.stages;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.widget.Toast;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.*;
import com.magmamobile.game.Burger.game.Accomp;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.*;
import com.magmamobile.game.engine.*;

public final class Gallery extends GameStage
{

    private final int DECAL_PHOTO;
    private final int MENU_BACK = 2;
    private final int NEXT = 4;
    private final int NONE = -1;
    private final int OPTION_SAVE = 1;
    private final int OPTION_TRASH = 0;
    private final int PREVIOUS = 3;
    private final float SPEED = 0.075F;
    private final float SPEED_BTN = 0.05F;
    private final int TOAST_ERROR = 6;
    private final int TOAST_SAVEOK = 5;
    private final int TOTAL_OPTION = 2;
    private boolean activated;
    private int cx;
    private int cy;
    private int deletedIndex;
    private int dx;
    private float f;
    private float fBtn;
    private int index;
    private GalleryButton leftBtn;
    private int nextStage;
    private OptionButton options[];
    private Paint pCount1;
    private Paint pCount2;
    private Paint pTitle1;
    private Paint pTitle2;
    private int phase;
    private GalleryPhoto photos[];
    private GalleryButton rightBtn;
    private int selected;
    private String title;
    private int tx;
    private int ty;
    private int xFixed;

    public Gallery()
    {
        DECAL_PHOTO = (int)(0.8F * (float)GalleryManager.PHOTO_W) + Game.scalei(32);
    }

    private void buttonHandler(int i)
    {
        selected = i;
        activated = false;
        UIManager.backButtonActive = false;
        SoundManager.playSound(29);
        boolean flag;
        switch(i)
        {
        default:
            return;

        case 0: // '\0'
            deletePhoto();
            return;

        case 1: // '\001'
            flag = GalleryManager.savePhoto(index, BitmapManager.photos[index % 3]);
            break;
        }
        options[1].init();
        if(flag)
        {
            options[1].enabled = false;
            call(5);
        } else
        {
            call(6);
        }
        activated = true;
        UIManager.backButtonActive = true;
    }

    private void changePhoto()
    {
        boolean flag = true;
        SoundManager.playSound(23);
        OptionButton optionbutton = options[1];
        if(GalleryManager.getStateAt(index))
        {
            flag = false;
        }
        optionbutton.enabled = flag;
    }

    private void changeStage()
    {
        Game.setStage(nextStage);
    }

    private void deleteEnd()
    {
        deletedIndex = -1;
        reinitOptions();
        activated = true;
        UIManager.backButtonActive = true;
    }

    private void deletePhoto()
    {
        deletedIndex = index;
        SoundManager.playSound(16);
        photos[index % 3].fall();
    }

    private void deleteSuite()
    {
        int i = GalleryManager.delete(deletedIndex);
        if(i == 0)
        {
            photos[0].visible = false;
            selected = 2;
            index = -1;
            navigate(UIManager.galleryIsFrom);
            return;
        }
        if(deletedIndex <= 0)
        {
            dx = DECAL_PHOTO;
            index = -1;
            if(i >= 1)
            {
                photos[0].createPhoto(GalleryManager.getSnapAt(0));
            }
            if(i >= 2)
            {
                photos[1].createPhoto(GalleryManager.getSnapAt(1));
            }
            nextPhoto();
            return;
        }
        if(deletedIndex >= i)
        {
            dx = -DECAL_PHOTO;
            index = i;
            prevPhoto();
            return;
        }
        dx = DECAL_PHOTO;
        index = -1 + deletedIndex;
        int j = (1 + index) % 3;
        int k = (2 + index) % 3;
        if(1 + index < i)
        {
            photos[j].createPhoto(GalleryManager.getSnapAt(1 + index));
        }
        if(2 + index < i)
        {
            photos[k].createPhoto(GalleryManager.getSnapAt(2 + index));
        }
        nextPhoto();
    }

    private void navigate(int i)
    {
        nextStage = i;
        switch(i)
        {
        default:
            App.fader.fadeOut();
            return;

        case 3: // '\003'
        case 8: // '\b'
            App.pause.fadeOut();
            break;
        }
    }

    private void nextPhoto()
    {
        if(index < GalleryManager.getSize())
        {
            phase = 4;
            f = 1.0F;
            index = 1 + index;
            dx = DECAL_PHOTO;
            if(index == 1)
            {
                leftBtn.moveIn();
            }
            if(index == -1 + GalleryManager.getSize())
            {
                rightBtn.moveOut();
            }
            changePhoto();
        }
    }

    private void prevPhoto()
    {
        if(index > 0)
        {
            phase = 3;
            f = 1.0F;
            index = -1 + index;
            dx = -DECAL_PHOTO;
            if(index == 0)
            {
                leftBtn.moveOut();
            }
            if(index == -2 + GalleryManager.getSize())
            {
                rightBtn.moveIn();
            }
            changePhoto();
        }
    }

    private void refreshPos()
    {
        int i = index % 3;
        int j = 0;
        do
        {
            if(j >= 3)
            {
                return;
            }
            int k = (3 + (j - i)) % 3;
            byte byte0;
            int l;
            int i1;
            GalleryPhoto galleryphoto;
            boolean flag;
            int j1;
            if(k > 1)
            {
                byte0 = 3;
            } else
            {
                byte0 = 0;
            }
            l = k - byte0;
            i1 = (Game.mBufferCW - photos[j].getCW()) + (l * DECAL_PHOTO - dx);
            galleryphoto = photos[j];
            if(i1 > -photos[j].getWidth() && i1 < Game.mBufferWidth)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            galleryphoto.visible = flag;
            photos[j].setX(i1);
            j1 = l + index;
            if(j1 >= 0 && j1 < GalleryManager.getSize())
            {
                if(photos[j].getIndex() != j1)
                {
                    photos[j].setIndex(j1);
                    photos[j].createPhoto(GalleryManager.getSnapAt(j1));
                }
            } else
            {
                photos[j].init();
            }
            j++;
        } while(true);
    }

    private void reinitOptions()
    {
        OptionButton aoptionbutton[] = options;
        int i = aoptionbutton.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                return;
            }
            aoptionbutton[j].init();
            j++;
        } while(true);
    }

    public boolean enterOnResume()
    {
        return false;
    }

    public void onAction()
    {
        GalleryPhoto agalleryphoto[];
        int i;
        int j;
        int i1;
        GalleryPhoto galleryphoto;
        int l;
        App.scrollBG.scroll();
        fBtn = 0.05F + fBtn;
        if(fBtn > 1.0F)
        {
            fBtn = 0.0F;
        }
        leftBtn.animate(fBtn);
        rightBtn.animate(fBtn);
        agalleryphoto = photos;
        i = agalleryphoto.length;
        j = 0;
        while(j < i) {
            galleryphoto = agalleryphoto[j];
            galleryphoto.animate();
            if(galleryphoto.isFinished())
            {
                deleteSuite();
            }
            j++;
        }
        if(activated && TouchScreen.eventDown) {
            if (leftBtn.visible && leftBtn.isInRect()) {
                leftBtn.selected = true;
                prevPhoto();
            }
            if (rightBtn.visible && rightBtn.isInRect()) {
                rightBtn.selected = true;
                nextPhoto();
            }
            i1 = 0;
            while (i1 < 2) {
                options[i1].interact();
                if (options[i1].onPress()) {
                    buttonHandler(i1);
                }
                i1++;
            }
        }
        if(phase != -1)
        {
            f = f - 0.075F;
            if(f <= 0.0F)
            {
                f = 0.0F;
                phase = -1;
                leftBtn.selected = false;
                rightBtn.selected = false;
                if(deletedIndex != -1)
                {
                    deleteEnd();
                }
            }
            dx = (int)MathUtils.lerpAccelerate(0.0F, DECAL_PHOTO, f);
            int k = dx;
            if(phase == 3)
            {
                l = 1;
            } else
            {
                l = -1;
            }
            dx = l * k;
            refreshPos();
            if(phase == 4 && deletedIndex != -1)
            {
                photos[(2 + index) % 3].setX(xFixed);
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
        if(selected >= 0 && App.fader.isFinished())
        {
            App.fader.stop();
            changeStage();
        }
        if(App.fader.isPlaying())
        {
            App.fader.animate();
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            selected = 2;
            navigate(UIManager.galleryIsFrom);
        }
    }

    public void onCall(int i)
    {
        switch(i)
        {
        default:
            return;

        case 5: // '\005'
            Toast.makeText(Game.getContext(), Game.getResString(R.string.res_gallerysaveok), Toast.LENGTH_SHORT).show();
            return;

        case 6: // '\006'
            Toast.makeText(Game.getContext(), Game.getResString(R.string.res_gallerysaveno), Toast.LENGTH_SHORT).show();
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
        int k;
        tx = Game.mBufferCW;
        ty = Game.scalei(48);
        title = Game.getResString(R.string.res_gallery_title).toUpperCase();
        cx = Game.mBufferWidth - Game.scalei(12);
        cy = Game.mBufferHeight - Game.scalei(12);
        phase = -1;
        f = 0.0F;
        index = -1;
        pTitle1 = new Paint(1);
        pTitle1.setTypeface(App.defaultFont);
        pTitle1.setTextSize(App.scalefFont(46F));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle1.setColor(0xff985d00);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), -1);
        pCount1 = new Paint(pTitle1);
        pCount1.setTextSize(App.scalefFont(50F));
        pCount1.setTextAlign(android.graphics.Paint.Align.RIGHT);
        pCount2 = App.getStroked(pCount1, Game.scalef(5.5F), -1);
        photos = new GalleryPhoto[3];
        i = 0;
        while(i < 3) {
            photos[i] = new GalleryPhoto(i);
            photos[i].setY(Game.scalei(56));
            i++;
        }
        leftBtn = new GalleryButton(1);
        rightBtn = new GalleryButton(2);
        leftBtn.setX(Game.scalei(16));
        rightBtn.setX(Game.mBufferWidth - (leftBtn.getX() + BitmapManager.galleryButtons[0].getWidth()));
        int j = photos[0].getY() + (photos[0].getHeight() - BitmapManager.galleryButtons[0].getHeight()) / 2;
        leftBtn.setY(j);
        rightBtn.setY(j);
        options = new OptionButton[2];
        k = 0;
        while(k < 2)
        {
            int l = k * 3;
            options[k] = new OptionButton(Game.scalei(12 + k * 52), Game.scalei(264), BitmapManager.galleryOptions[l], BitmapManager.galleryOptions[l + 1], BitmapManager.galleryOptions[l + 2]);
            options[k].added = true;
            k++;
        }
        xFixed = Game.mBufferCW - (photos[0].getCW() + DECAL_PHOTO);
    }

    public void onLateResume()
    {
        resetStage();
    }

    public void onLeave()
    {
        super.onLeave();
        BitmapManager.recyclePhotos();
        Board.accomp.resetAllItems();
    }

    public void onRender()
    {
        int i;
        GalleryPhoto agalleryphoto[];
        int j;
        int k;
        i = 0;
        App.fader.apply();
        App.scrollBG.draw();
        Game.drawRect(0.0F, 0.0F, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawRect(0.0F, UIManager.barY, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawText(title, tx, ty, pTitle2);
        Game.drawText(title, tx, ty, pTitle1);
        agalleryphoto = photos;
        j = agalleryphoto.length;
        k = 0;
        while(k < j) {
            agalleryphoto[k].draw();
            k++;
        }
        OptionButton aoptionbutton[];
        int l;
        leftBtn.draw();
        rightBtn.draw();
        aoptionbutton = options;
        l = aoptionbutton.length;
        while(i < l)
        {
            aoptionbutton[i].draw();
            i++;
        }
        String s = (new StringBuilder(String.valueOf(1 + index))).append("/").append(GalleryManager.getSize()).toString();
        Game.drawText(s, cx, cy, pCount2);
        Game.drawText(s, cx, cy, pCount1);
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
        selected = 2;
        switch(i)
        {
        case 4: // '\004'
        default:
            return;

        case 3: // '\003'
            SoundManager.playSound(29);
            UIManager.configIsFrom = 4;
            Common.analytics("gallery/menu/settings");
            navigate(3);
            return;

        case 5: // '\005'
            SoundManager.playSound(29);
            break;
        }
        Common.analytics("gallery/menu/quit");
        UIManager.galleryIsFrom = 1;
        UIManager.fromPause = false;
        navigate(1);
    }

    public void resetStage()
    {
        boolean flag = true;
        activated = false;
        selected = -1;
        deletedIndex = -1;
        UIManager.backButtonActive = false;
        UIManager.barP.setColor(0xffcf9463);
        BitmapManager.setBackground(8);
        dx = 0;
        int i;
        GalleryPhoto agalleryphoto[];
        int j;
        int k;
        if(index < 0)
        {
            i = 0;
        } else
        if(index >= GalleryManager.getSize())
        {
            i = -1 + GalleryManager.getSize();
        } else
        {
            i = index;
        }
        index = i;
        agalleryphoto = photos;
        j = agalleryphoto.length;
        k = 0;
        do
        {
            if(k >= j)
            {
                BitmapManager.createPhotos();
                refreshPos();
                leftBtn.init();
                rightBtn.init();
                GalleryButton gallerybutton = leftBtn;
                boolean flag1;
                GalleryButton gallerybutton1;
                if(index > 0)
                {
                    flag1 = flag;
                } else
                {
                    flag1 = false;
                }
                gallerybutton.visible = flag1;
                gallerybutton1 = rightBtn;
                if(index >= -1 + GalleryManager.getSize())
                {
                    flag = false;
                }
                gallerybutton1.visible = flag;
                reinitOptions();
                App.scrollBG.setColor(6);
                if(UIManager.galleryIsFrom == 8 || UIManager.configIsFrom == 4)
                {
                    App.pause.fadeIn();
                } else
                {
                    App.fader.fadeIn();
                }
                GalleryManager.newPhoto = false;
                return;
            }
            agalleryphoto[k].init();
            k++;
        } while(true);
    }
}
