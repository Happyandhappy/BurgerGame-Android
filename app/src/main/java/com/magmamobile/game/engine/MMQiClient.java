package com.magmamobile.game.engine;

import android.os.Handler;
import android.os.Message;
import java.io.*;
import java.net.*;

public final class MMQiClient
{
    public static interface OnEventListener
    {

        public abstract void onRequest(MMQiClient mmqiclient, byte abyte0[]);
    }


    private byte data[];
    private Handler handler;
    private OnEventListener listener;
    private boolean once;

    public MMQiClient(OnEventListener oneventlistener)
    {
        listener = oneventlistener;
        handler = new Handler() {
            public void handleMessage(Message message)
            {
                once = false;
                if(listener != null)
                {
                    listener.onRequest(MMQiClient.this, data);
                }
            }
        };
    }

    private void sendRequest(String s, int i, String s1)
    {
        Socket socket;
        socket = new Socket();
        try
        {
            socket.connect(new InetSocketAddress(s, i));
            if(socket.isConnected()) {
                InputStream inputstream;
                ByteArrayOutputStream bytearrayoutputstream;
                byte abyte0[];
                socket.getOutputStream().write(s1.getBytes());
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
                data = bytearrayoutputstream.toByteArray();
            }
            socket.close();
        }
        catch(SocketException socketexception)
        {
            socketexception.printStackTrace();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
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
