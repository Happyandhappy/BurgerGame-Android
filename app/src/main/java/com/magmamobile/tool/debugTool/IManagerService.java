package com.magmamobile.tool.debugTool;

import android.os.*;

public interface IManagerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IManagerService
    {

        private static final String DESCRIPTOR = "com.magmamobile.tool.debugTool.IManagerService";
        static final int TRANSACTION_add = 1;

        public static IManagerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.magmamobile.tool.debugTool.IManagerService");
            if(iinterface != null && (iinterface instanceof IManagerService))
            {
                return (IManagerService)iinterface;
            } else
            {
                return new Proxy(ibinder);
            }
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.magmamobile.tool.debugTool.IManagerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.magmamobile.tool.debugTool.IManagerService");
                int k = add(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(k);
                return true;
            }
        }

        public Stub()
        {
            attachInterface(this, "com.magmamobile.tool.debugTool.IManagerService");
        }

        private static class Proxy implements IManagerService
        {

            private IBinder mRemote;

            public int add(int i, int j) throws RemoteException
            {
                try {
                    Parcel parcel;
                    Parcel parcel1;
                    parcel = Parcel.obtain();
                    parcel1 = Parcel.obtain();
                    int k;
                    parcel.writeInterfaceToken("com.magmamobile.tool.debugTool.IManagerService");
                    parcel.writeInt(i);
                    parcel.writeInt(j);
                    mRemote.transact(1, parcel, parcel1, 0);
                    parcel1.readException();
                    k = parcel1.readInt();
                    parcel1.recycle();
                    parcel.recycle();
                    return k;
                }catch(RemoteException e) {
                    throw e;
                }
            }

            public IBinder asBinder()
            {
                return mRemote;
            }

            public String getInterfaceDescriptor()
            {
                return "com.magmamobile.tool.debugTool.IManagerService";
            }

            Proxy(IBinder ibinder)
            {
                mRemote = ibinder;
            }
        }
    }

    public abstract int add(int i, int j)
        throws RemoteException;
}
