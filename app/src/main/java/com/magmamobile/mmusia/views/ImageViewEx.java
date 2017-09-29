package com.magmamobile.mmusia.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.magmamobile.mmusia.MCommon;
import com.magmamobile.mmusia.image.ImageSetterSDK3;
import com.magmamobile.mmusia.image.ImageSetterSDK4;
import com.magmamobile.mmusia.image.cache.Images;
import java.io.*;
import java.net.*;
import java.util.Map;

public final class ImageViewEx extends ImageView
{

    private static final String TAG = "MMUSIA";
    final Handler handler;
    private String mRemote;
    private boolean save2Disk;
    private boolean verboseLog;

    public ImageViewEx(Context context)
    {
        this(context, null, 0);
    }

    public ImageViewEx(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ImageViewEx(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        verboseLog = false;
        save2Disk = true;
        handler = new Handler() {
            public void handleMessage(Message message)
            {
                setFromLocal();
            }
        };
    }

    private static Drawable ImageOperations(String s)
    {
        HttpURLConnection httpurlconnection;
        InputStream inputstream;
        byte abyte0[];
        Drawable drawable;
        Drawable drawable1;
        try
        {
            httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpurlconnection.connect();
            inputstream = httpurlconnection.getInputStream();
            abyte0 = readBytes(inputstream);
        }
        catch(Exception exception)
        {
            MCommon.Log_e("MMUSIA", (new StringBuilder("ImageViewEx ImageOperations Exception Image :: ")).append(exception.getMessage()).toString());
            MCommon.Log_e("MMUSIA", s);
            return null;
        }
        try {
            drawable1 = loadResizedBitmap(abyte0);
            drawable = drawable1;
        }catch(Exception e){
            drawable = null;
        }catch(OutOfMemoryError error) {
            drawable = null;
        }
        if(inputstream == null)
        {
            MCommon.Log_e("MMUSIA", (new StringBuilder("ImageViewEx ImageOperations Malformed :: ")).append(s).toString());
            return null;
        }
        try {
            inputstream.close();
            httpurlconnection.disconnect();
            return drawable;
        }catch (Exception e) {
            MCommon.Log_e("MMUSIA", (new StringBuilder("ImageViewEx ImageOperations IO :: ")).append(e.getMessage()).toString());
            MCommon.Log_e("MMUSIA", s);
            return null;
        }
    }

    public static final void arthur_fetchDrawable(Context context, String s, boolean flag)
    {
        fetchDrawable(context, s, flag);
    }

    private final void fetchDrawable(Context context, String s)
    {
        fetchDrawable(context, s, true, save2Disk);
    }

    private static final void fetchDrawable(Context context, String s, boolean flag)
    {
        fetchDrawable(context, s, true, flag);
    }

    private static final void fetchDrawable(Context context, String s, boolean flag, boolean flag1)
    {
        Drawable drawable = ImageOperations(s);
        if(drawable != null)
        {
            if(flag1)
            {
                Images.saveImage(context, drawable, MCommon.alphaNum(s, ""));
            }
            MCommon.drawableMap.put(s, drawable);
            return;
        }
        if(flag)
        {
            MCommon.Log_e("MMUSIA", (new StringBuilder("ImageViewEx :: drawable == null !!!! Second Try ! :: ")).append(s).toString());
            fetchDrawable(context, s, false, flag1);
            return;
        } else
        {
            MCommon.Log_e("MMUSIA", (new StringBuilder("ImageViewEx :: drawable == null !!!! Last Try :( ")).append(s).toString());
            return;
        }
    }

    public static Drawable loadResizedBitmap(byte abyte0[])
    {
        android.graphics.BitmapFactory.Options options;
        int i;
        Bitmap bitmap;
        options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        i = options.outHeight;
        bitmap = null;
        if(i > 0) {
            int j;
            j = options.outWidth;
            bitmap = null;
            if (j > 0) {
                options.inJustDecodeBounds = false;
                options.inSampleSize = 1;
                while (options.outWidth / options.inSampleSize > 320 && options.outHeight / options.inSampleSize > 240) {
                    options.inSampleSize = 1 + options.inSampleSize;
                }
                bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
            }
        }
        return new BitmapDrawable(bitmap);
    }

    public static byte[] readBytes(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        do
        {
            int i = inputstream.read(abyte0);
            if(i == -1)
            {
                return bytearrayoutputstream.toByteArray();
            }
            bytearrayoutputstream.write(abyte0, 0, i);
        } while(true);
    }

    public static Drawable resizeDrawable(Drawable drawable, int i, int j)
    {
        if(drawable == null)
        {
            return null;
        } else
        {
            return new BitmapDrawable(Bitmap.createScaledBitmap(((BitmapDrawable)drawable).getBitmap(), i, j, false));
        }
    }

    private void setFromLocal()
    {
        Drawable drawable;
label0:
        {
            if(MCommon.drawableMap.containsKey(mRemote))
            {
                drawable = (Drawable)MCommon.drawableMap.get(mRemote);
                if(drawable != null)
                {
                    if(!MCommon.isSDKAPI4Mini())
                    {
                        break label0;
                    }
                    ImageSetterSDK4.setImage(drawable, this);
                }
            }
            return;
        }
        ImageSetterSDK3.setImage(drawable, this);
    }

    public void loadImage(final Context context)
    {
        while(mRemote == null || mRemote.equals("")) 
        {
            return;
        }
        if(!MCommon.drawableMap.containsKey(mRemote) && Images.isExist(context, MCommon.alphaNum(mRemote, "")) && save2Disk)
        {
            MCommon.drawableMap.put(mRemote, Images.loadImage(context, MCommon.alphaNum(mRemote, "")));
            if(verboseLog)
            {
                MCommon.Log_i("MMUSIA", "LoadImage : Exists in Cache, Added in memory cache");
            }
            (new Thread() {
                public void run()
                {
                    Message message = handler.obtainMessage(1);
                    handler.sendMessage(message);
                }
            }).start();
            return;
        }
        if(MCommon.drawableMap.containsKey(mRemote))
        {
            if(verboseLog)
            {
                MCommon.Log_i("MMUSIA", "LoadImage : Exists");
            }
            (new Thread() {
                public void run()
                {
                    Message message = handler.obtainMessage(1);
                    handler.sendMessage(message);
                }
            }).start();
            return;
        }
        if(verboseLog)
        {
            MCommon.Log_i("MMUSIA", "LoadImage : NOT Exists");
        }
        (new Thread() {
            public void run()
            {
                fetchDrawable(context, mRemote);
                Message message = handler.obtainMessage(1);
                handler.sendMessage(message);
            }
        }).start();
    }

    public void loadImage(Context context, boolean flag)
    {
        save2Disk = flag;
        loadImage(context);
    }

    public void setRemoteURI(String s)
    {
        while(s == null || !s.startsWith("http")) 
        {
            return;
        }
        mRemote = s;
    }
}
