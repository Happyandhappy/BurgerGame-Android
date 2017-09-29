package com.magmamobile.game.engine;

import android.graphics.*;
import java.nio.IntBuffer;

public class OpenningCrawl extends GameObject
{

    private static boolean _done;
    private static final Rect bnds = new Rect();
    private static float cos_vx;
    private static float dist;
    private static int height;
    private static Bitmap img;
    private static int pixels[];
    private static float scroll_offset;
    private static float scroll_speed;
    private static float sin_vx;
    private static int textheight;
    private static int textpixels[];
    private static int textwidth;
    private static float vx;
    private static int width;
    private static float zoom;

    public OpenningCrawl(String s, int i)
    {
        width = Game.mBufferWidth;
        height = Game.mBufferHeight;
        _done = false;
        zoom = 100F;
        vx = 1.3F;
        scroll_speed = 0.5F;
        cos_vx = (float)Math.cos(vx);
        sin_vx = (float)Math.sin(vx);
        Bitmap bitmap = createTextImage();
        textwidth = bitmap.getWidth();
        textheight = bitmap.getHeight();
        IntBuffer intbuffer = IntBuffer.allocate(textwidth * textheight);
        bitmap.copyPixelsToBuffer(intbuffer);
        textpixels = intbuffer.array();
        bitmap.recycle();
        intbuffer.clear();
        dist = ((cos_vx * zoom + (float)(height - height / 4) * sin_vx) * (float)(textwidth / 2)) / ((float)(width / 2) * cos_vx);
        scroll_offset = -((float)height - (float)height / 4F) * (dist / (cos_vx * zoom + ((float)height - (float)height / 4F) * sin_vx));
        pixels = new int[width * height];
        img = Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888);
    }

    private static final Bitmap createTextImage()
    {
        Paint paint;
        String as[];
        int i;
        int j;
        int ai[];
        int k;
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setColor(0xff00ffe4);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        as = (new String[] {
            "It is a period of civil war", "Rebel spaceship, striking", "from a hidden base, has won", "trop de ouai", "c'est cool.", "\347a marche bien mais aussi i"
        });
        i = 0;
        j = 0;
        ai = new int[6];
        k = 0;
        while(k < 6) {
            paint.getTextBounds(as[k], 0, as[k].length(), bnds);
            int l = bnds.right - bnds.left;
            int i1 = bnds.bottom - bnds.top;
            if(l > i)
            {
                i = l;
            }
            ai[k] = j - bnds.top;
            j += i1;
            k++;
        }
        Bitmap bitmap;
        Canvas canvas;
        int j1;
        bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        j1 = 0;
        while(j1 < 6)
        {
            drawJustifyText(canvas, paint, i, ai[j1], as[j1]);
            j1++;
        }
        return bitmap;
    }

    private static final void drawJustifyText(Canvas canvas, Paint paint, int i, int j, String s)
    {
        if(!s.endsWith(".")) {
            String as[] = s.split(" ");
            int k = as.length;
            int ai[] = new int[k];
            int l = 0;
            int i1 = 0;
            while(i1 < k)
            {
                paint.getTextBounds(as[i1], 0, as[i1].length(), bnds);
                ai[i1] = bnds.right - bnds.left;
                l += ai[i1];
                i1++;
            }
            int j1 = 0;
            int k1 = (-8 + (i - l)) / (k - 1);
            int l1 = 0;
            while(l1 < k)
            {
                canvas.drawText(as[l1], j1, j, paint);
                j1 += k1 + ai[l1];
                l1++;
            }
        }else
            canvas.drawText(s, 0.0F, j, paint);
    }

    private static final boolean next_scrollimage()
    {
        int j;
        int k;
        float f;
        int l;
        int k1;
        int l1;
        int i2;
        int k2;
        int l2;
        int j3;
        int k3;
        int i = (int)((-cos_vx * zoom) / sin_vx) + height / 4;
        if(i < 0)
        {
            j = 0;
        } else
        {
            j = i + 1;
        }
        k = j;
        while(k < height)
        {
            f = dist / (cos_vx * zoom + (float)(k - height / 4) * sin_vx);
            l = (int)(scroll_offset + f * (float)(k - height / 4));
            if(l >= textheight)
            {
                return true;
            }
            if(!(l < 0 || l >= textheight)) {
                int i1 = (int) (f * (float) ((-1 + height) - height / 4));
                int j1 = (int) (f * (float) (j - height / 4));
                k1 = 0xff000000 + 0x10100 * (int) ((255F * (f * (float) (k - height / 4) - (float) j1)) / (float) (i1 - j1));
                l1 = (int) (1048576F * ((float) (textwidth / 2) + f * ((float) (-width / 2) * cos_vx)));
                if (l1 < 0) {
                    l1 = 0;
                }
                i2 = (int) (1048576F * (f * cos_vx));
                int j2 = (int) ((float) (-textwidth / 2) / (f * cos_vx)) + width / 2;
                if (j2 < 0) {
                    j2 = 0;
                }
                k2 = width - j2;
                l2 = l * textwidth;
                int i3 = j2 + k * width;
                j3 = j2;
                k3 = i3;
                while (j3 < k2) {
                    int ai[] = pixels;
                    int l3 = k3 + 1;
                    ai[k3] = k1 & textpixels[l2 + (l1 >> 20)];
                    l1 += i2;
                    j3++;
                    k3 = l3;
                }
            }
            k++;
        }
        scroll_offset += scroll_speed;
        return false;
    }

    public boolean isDone()
    {
        return _done;
    }

    public void onAction()
    {
        _done = next_scrollimage();
    }

    public void onRender()
    {
        img.setPixels(pixels, 0, width, 0, 0, width, height);
        Game.drawBitmap(img, 0, 0, null);
    }

}
