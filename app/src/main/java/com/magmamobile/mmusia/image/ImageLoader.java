package com.magmamobile.mmusia.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;
import com.magmamobile.mmusia.MCommon;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class ImageLoader
{
    private class DownloadThread extends Thread
    {

        private HttpURLConnection _conn;
        public Group group;
        final Runnable threadCallback = new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        };
        final Handler threadHandler = new Handler();

        public void disconnect()
        {
            if(_conn != null)
            {
                _conn.disconnect();
            }
        }

        public void run()
        {
            InputStream inputstream;
            inputstream = null;
            _conn = null;
            try
            {
                _conn = (HttpURLConnection)(new URL(group.url)).openConnection();
                _conn.setDoInput(true);
                _conn.connect();
                inputstream = _conn.getInputStream();
                group.bitmap = BitmapFactory.decodeStream(inputstream);
                inputstream.close();
                _conn.disconnect();
                inputstream = null;
                _conn = null;
            }
            catch(Exception exception) { }
            if(inputstream != null)
            {
                try
                {
                    inputstream.close();
                }
                catch(Exception exception1) { }
            }
            disconnect();
            _conn = null;
            threadHandler.post(threadCallback);
        }

        public DownloadThread(Group group1)
        {
            super();
            group = group1;
        }
    }

    private class Group
    {

        public Bitmap bitmap;
        public boolean cache;
        public ImageView image;
        public String url;

        public Group(ImageView imageview, String s, Bitmap bitmap1, boolean flag)
        {
            super();
            image = imageview;
            url = s;
            bitmap = bitmap1;
            cache = flag;
        }
    }


    private static ImageLoader _instance;
    private boolean _busy;
    private Bitmap _missing;
    private Queue _queue;
    private DownloadThread _thread;
    private HashMap _urlToBitmap;

    private ImageLoader()
    {
        _urlToBitmap = new HashMap();
        _queue = new LinkedList();
        _busy = false;
    }

    public static ImageLoader getInstance()
    {
        if(_instance == null)
        {
            _instance = new ImageLoader();
        }
        return _instance;
    }

    private void loadNext()
    {
        Group group;
        Iterator iterator = _queue.iterator();
        if(!_busy && iterator.hasNext())
        {
            _busy = true;
            group = (Group)iterator.next();
            iterator.remove();
            if(_urlToBitmap.get(group.url) == null)
            {
                _thread = new DownloadThread(group);
                _thread.start();
                return;
            }
            if(group.image != null)
            {
                if(MCommon.isSDKAPI4Mini())
                {
                    ImageSetterSDK4.setImage(new BitmapDrawable((Bitmap)_urlToBitmap.get(group.url)), group.image);
                } else
                {
                    ImageSetterSDK3.setImage(new BitmapDrawable((Bitmap)_urlToBitmap.get(group.url)), group.image);
                }
            }
            _busy = false;
            loadNext();
        }
    }

    private void onLoad()
    {
        if(_thread != null) {
            Group group = _thread.group;
            if (group.bitmap == null) {
                if (_missing != null && group.image != null) {
                    if (MCommon.isSDKAPI4Mini()) {
                        ImageSetterSDK4.setImage(new BitmapDrawable(_missing), group.image);
                    } else {
                        ImageSetterSDK3.setImage(new BitmapDrawable(_missing), group.image);
                    }
                }
            } else {
                if (group.cache) {
                    _urlToBitmap.put(group.url, group.bitmap);
                }
                if (group.image != null) {
                    if (MCommon.isSDKAPI4Mini()) {
                        ImageSetterSDK4.setImage(new BitmapDrawable(group.bitmap), group.image);
                    } else {
                        ImageSetterSDK3.setImage(new BitmapDrawable(group.bitmap), group.image);
                    }
                }
            }
        }
        _thread = null;
        _busy = false;
        loadNext();
    }

    public void cancel()
    {
        clearQueue();
        if(_thread != null)
        {
            _thread.disconnect();
            _thread = null;
        }
    }

    public void clearCache()
    {
        _urlToBitmap = new HashMap();
    }

    public void clearQueue()
    {
        _queue = new LinkedList();
    }

    public Bitmap get(String s)
    {
        return (Bitmap)_urlToBitmap.get(s);
    }

    public void load(ImageView imageview, String s)
    {
        load(imageview, s, false);
    }

    public void load(ImageView imageview, String s, boolean flag)
    {
label0:
        {
label1:
            {
                if(_urlToBitmap.get(s) == null)
                {
                    break label0;
                }
                if(imageview != null)
                {
                    if(!MCommon.isSDKAPI4Mini())
                    {
                        break label1;
                    }
                    ImageSetterSDK4.setImage(new BitmapDrawable((Bitmap)_urlToBitmap.get(s)), imageview);
                }
                return;
            }
            ImageSetterSDK3.setImage(new BitmapDrawable((Bitmap)_urlToBitmap.get(s)), imageview);
            return;
        }
        queue(imageview, s, flag);
    }

    public void queue(ImageView imageview, String s, boolean flag)
    {
        Iterator iterator = _queue.iterator();
        if(imageview == null){
            if(s != null) {
                while (iterator.hasNext()) {
                    if (((Group) iterator.next()).url.equals(s)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }else {
            while (iterator.hasNext()) {
                if (((Group) iterator.next()).image.equals(imageview)) {
                    iterator.remove();
                    break;
                }
            }
        }
        _queue.add(new Group(imageview, s, null, flag));
        loadNext();
    }

    public void setMissingBitmap(Bitmap bitmap)
    {
        _missing = bitmap;
    }
}
