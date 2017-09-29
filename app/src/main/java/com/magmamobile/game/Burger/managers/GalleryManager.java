package com.magmamobile.game.Burger.managers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.engine.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class GalleryManager
{

    public static final int DATA_ACCOMP = 1;
    public static final int DATA_BURGER = 0;
    public static final int DATA_PLACES = 2;
    public static final int DATA_STATE = 3;
    public static int PHOTO_H = 0;
    public static final float PHOTO_SCALE = 0.8F;
    public static final int PHOTO_W = Game.scalei(420);
    private static ArrayList album;
    public static int currentSnap;
    public static boolean newPhoto;
    private static ArrayList saveState;
    public static int savedTray[][];

    public GalleryManager()
    {
    }

    public static boolean addSnap(int ai[], int ai1[], int ai2[])
    {
        if(!contains(ai, ai1, ai2))
        {
            saveState.add(Boolean.valueOf(false));
            ArrayList arraylist = album;
            int ai3[][] = new int[3][];
            ai3[0] = (int[])ai.clone();
            ai3[1] = (int[])ai1.clone();
            ai3[2] = (int[])ai2.clone();
            arraylist.add(ai3);
            newPhoto = true;
            save();
            return true;
        } else
        {
            return false;
        }
    }

    private static boolean contains(int ai[], int ai1[], int ai2[])
    {
        if(album.size() == 0)
            return false;
        Iterator iterator = album.iterator();
        while(iterator.hasNext()) {
            int ai3[][];
            int i;
            ai3 = (int[][]) iterator.next();
            i = 0;
            int ai4[];
            while (i < ai3.length) {
                ai4 = ai3[i];
                int ai5[];
                switch (i) {
                    default:
                        ai5 = ai;
                        break;
                    case 1:
                        ai5 = ai1;
                        break;
                    case 2:
                        ai5 = ai2;
                        break;
                }
                int j = 0;
                while (j < ai5.length) {
                    if (ai4[j] != ai5[j])
                        continue;
                    j++;
                }
                i++;
            }
            return true;
        }
        return false;
    }

    public static int delete(int i)
    {
        if(i >= album.size() || i < 0)
        {
            return -1;
        } else
        {
            album.remove(i);
            saveState.remove(i);
            save();
            return album.size();
        }
    }

    public static int getSize()
    {
        return album.size();
    }

    public static int[][] getSnapAt(int i)
    {
        if(i >= album.size())
        {
            return null;
        } else
        {
            return (int[][])album.get(i);
        }
    }

    public static boolean getStateAt(int i)
    {
        if(i >= saveState.size())
        {
            return true;
        } else
        {
            return ((Boolean)saveState.get(i)).booleanValue();
        }
    }

    public static void init()
    {
        savedTray = new int[3][];
        saveState = new ArrayList();
        album = new ArrayList();
        reset();
        boolean flag;
        if(load())
        {
            flag = false;
        } else
        {
            flag = true;
        }
        newPhoto = flag;
    }

    public static boolean isEmpty()
    {
        return album.isEmpty();
    }

    public static boolean isFirst()
    {
        return !album.isEmpty() && newPhoto;
    }

    public static final boolean load()
    {
        File file;
        album = new ArrayList();
        file = Game.getFile("gallery");
        if(!file.exists())
        {
            return false;
        }
        InputStreamEx inputstreamex;
        int i;
        try {
            inputstreamex = new InputStreamEx(file);
            i = inputstreamex.readInt();
            int j = 0;
            while(j < i)
            {
                int ai[][];
                ai = new int[3][];
                ai[0] = new int[15];
                int k = 0;
                while(k < 15) {
                    ai[0][k] = inputstreamex.readInt();
                    k++;
                }
                ai[1] = new int[6];
                int l = 0;
                while(l < 6) {
                    ai[1][l] = inputstreamex.readInt();
                    l++;
                }
                ai[2] = new int[6];
                int i1 = 0;
                while(i1 < 6) {
                    ai[2][i1] = inputstreamex.readInt();
                    i1++;
                }
                album.add(ai);
                saveState.add(Boolean.valueOf(false));
                j++;
            }
            inputstreamex.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static void reinit()
    {
        currentSnap = 0;
    }

    public static void reset()
    {
        saveState.clear();
        album.clear();
        reinit();
    }

    public static final void save()
    {
        int i;
        int j1;
        int k1;
        OutputStreamEx outputstreamex;
        int ai[][];
        int ai1[];
        int j;
        int k;
        int ai2[];
        int l;
        int i1;
        int ai3[];
        try
        {
            outputstreamex = new OutputStreamEx(Game.getFile("gallery"));
            outputstreamex.writeInt(album.size());
            i = 0;
            while(i < album.size())
            {
                ai = (int[][])album.get(i);
                ai1 = ai[0];
                j = ai1.length;
                k = 0;
                while(k < j) {
                    outputstreamex.writeInt(ai1[k]);
                    k++;
                }
                ai2 = ai[1];
                l = ai2.length;
                i1 = 0;
                while(i1 < l) {
                    outputstreamex.writeInt(ai2[i1]);
                    i1++;
                }
                ai3 = ai[2];
                j1 = ai3.length;
                k1 = 0;
                while(k1 < j1) {
                    outputstreamex.writeInt(ai3[k1]);
                    k1++;
                }
                i++;
            }
            App.trace(outputstreamex.size());
            outputstreamex.close();
            App.trace("sauvegarde r\351ussie");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static boolean savePhoto(int i, Bitmap bitmap)
    {
        File file = new File("/sdcard/Burger");
        file.mkdirs();
        File file1 = new File(file, (new StringBuilder("img")).append((new Date()).getTime()).append(".jpg").toString());
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(file1);
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileoutputstream);
            Game.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse((new StringBuilder("file://")).append(Environment.getExternalStorageDirectory()).toString())));
            fileoutputstream.close();
            saveState.set(i, Boolean.valueOf(true));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public static void saveTray(int ai[], int ai1[], int ai2[])
    {
        savedTray[0] = (int[])ai.clone();
        savedTray[1] = (int[])ai1.clone();
        savedTray[2] = (int[])ai2.clone();
    }

    static 
    {
        PHOTO_H = Game.scalei(300);
    }
}
