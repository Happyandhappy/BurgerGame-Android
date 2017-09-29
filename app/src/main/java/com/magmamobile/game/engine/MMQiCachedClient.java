package com.magmamobile.game.engine;

import android.os.*;
import java.io.*;
import java.net.*;

public final class MMQiCachedClient
{
    public static interface OnEventListener
    {

        public abstract void onRequest(MMQiCachedClient mmqicachedclient, byte abyte0[]);
    }


    private byte data[];
    private long date;
    private Handler handler;
    private OnEventListener listener;
    private boolean once;

    public MMQiCachedClient(OnEventListener oneventlistener)
    {
        listener = oneventlistener;
        handler = new Handler() {
            public void handleMessage(Message message)
            {
                once = false;
                if(listener != null)
                {
                    listener.onRequest(MMQiCachedClient.this, data);
                }
            }
        };
    }

    private boolean getCache(String s)
        throws Exception
    {
        File file = getFile(s);
        if(file.exists() && file.canRead())
        {
            date = file.lastModified();
            FileInputStream fileinputstream = new FileInputStream(file);
            byte abyte0[] = new byte[(int)file.length()];
            fileinputstream.read(abyte0);
            fileinputstream.close();
            data = abyte0;
            return true;
        } else
        {
            return false;
        }
    }

    private File getFile(String s)
    {
        File file;
        if(Environment.getExternalStorageDirectory().canWrite())
        {
            file = Environment.getExternalStorageDirectory();
        } else
        {
            file = Game.getContext().getCacheDir();
        }
        return new File(file.getPath().concat("/").concat((new StringBuilder(String.valueOf(s))).append(".dat").toString()));
    }

    private void sendRequest(String s, int i, String s1)
    {
        Socket socket;
        try
        {
            getCache(s1);
            socket = new Socket();
            socket.connect(new InetSocketAddress(s, i));
            if(socket.isConnected()) {
                InputStream inputstream;
                ByteArrayOutputStream bytearrayoutputstream;
                byte abyte0[];
                socket.getOutputStream().write((new StringBuilder(String.valueOf(s1))).append("/").append(date).toString().getBytes());
                inputstream = socket.getInputStream();
                bytearrayoutputstream = new ByteArrayOutputStream();
                abyte0 = new byte[4096];
                while (true) {
                    int j = inputstream.read(abyte0);
                    if (j < 0)
                        break;
                    bytearrayoutputstream.write(abyte0, 0, j);
                }
                inputstream.close();
                bytearrayoutputstream.close();
                byte abyte1[] = bytearrayoutputstream.toByteArray();
                if (abyte1.length > 0) {
                    data = abyte1;
                    setCache(s1);
                }
            }
            socket.close();
        }
        catch(SocketException socketexception)
        {
            socketexception.printStackTrace();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return;
        }
    }

    private boolean setCache(String s)
        throws Exception
    {
        Debug.e("set cache");
        FileOutputStream fileoutputstream = new FileOutputStream(getFile(s));
        fileoutputstream.write(data);
        fileoutputstream.flush();
        fileoutputstream.close();
        return true;
    }

    public byte[] getData()
    {
        return data;
    }

    public void sendAsyncRequest(final String host, final int port, final String key)
    {
        if(once)
        {
            handler.sendEmptyMessage(0);
            return;
        } else
        {
            once = true;
            data = null;
            (new Thread() {
                public void run()
                {
                    sendRequest(host, port, key);
                    handler.sendEmptyMessage(0);
                }
            }).start();
            return;
        }
    }

    public void sendSyncRequest(String s, int i, String s1)
    {
        sendRequest(s, i, s1);
    }





}
