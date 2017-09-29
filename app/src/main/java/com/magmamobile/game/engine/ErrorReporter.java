package com.magmamobile.game.engine;

import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.*;
import android.widget.Toast;
import java.io.*;
import java.util.Date;

final class ErrorReporter
    implements Thread.UncaughtExceptionHandler
{

    private static final String EMAIL = "supportapp@magmamobile.com";
    private static final String TITLE = "[Furnace]";
    private String AndroidVersion;
    private String Board;
    private String Brand;
    private String CPU_ABI;
    private String Device;
    private String Display;
    private String FilePath;
    private String FingerPrint;
    private String Host;
    private String ID;
    private String Manufacturer;
    private String Model;
    private String PackageName;
    private String PhoneModel;
    private Thread.UncaughtExceptionHandler PreviousHandler;
    private String Product;
    private String Tags;
    private long Time;
    private String Type;
    private String User;
    private String VersionName;

    public ErrorReporter(Context context)
    {
        PreviousHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        RecoltInformations(context);
    }

    private final String CreateInformationString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Version : ").append(VersionName).append("\n");
        stringbuilder.append("Package : ").append(PackageName).append("\n");
        stringbuilder.append("FilePath : ").append(FilePath).append("\n");
        stringbuilder.append("Phone Model").append(PhoneModel).append("\n");
        stringbuilder.append("Android Version : ").append(AndroidVersion).append("\n");
        stringbuilder.append("Board : ").append(Board).append("\n");
        stringbuilder.append("Brand : ").append(Brand).append("\n");
        stringbuilder.append("Device : ").append(Device).append("\n");
        stringbuilder.append("Display : ").append(Display).append("\n");
        stringbuilder.append("Finger Print : ").append(FingerPrint).append("\n");
        stringbuilder.append("Host : ").append(Host).append("\n");
        stringbuilder.append("ID : ").append(ID).append("\n");
        stringbuilder.append("Model : ").append(Model).append("\n");
        stringbuilder.append("Product : ").append(Product).append("\n");
        stringbuilder.append("Manufacturer : ").append(Manufacturer).append("\n");
        stringbuilder.append("CPU ABI : ").append(CPU_ABI).append("\n");
        stringbuilder.append("Tags : ").append(Tags).append("\n");
        stringbuilder.append("Time : ").append(Time).append("\n");
        stringbuilder.append("Type : ").append(Type).append("\n");
        stringbuilder.append("User : ").append(User).append("\n");
        stringbuilder.append("Total Internal memory : ").append(getTotalInternalMemorySize()).append("\n");
        stringbuilder.append("Available Internal memory : ").append(getAvailableInternalMemorySize()).append("\n");
        return stringbuilder.toString();
    }

    private final String[] GetErrorFileList()
    {
        File file = new File(FilePath.concat("/"));
        file.mkdir();
        return file.list(new FilenameFilter() {
            public boolean accept(File file1, String s)
            {
                return s.endsWith(".stacktrace");
            }
        });
    }

    private final void RecoltInformations(Context context)
    {
        try
        {
            PackageInfo packageinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            VersionName = packageinfo.versionName;
            PackageName = packageinfo.packageName;
            FilePath = context.getFilesDir().getAbsolutePath();
            PhoneModel = Build.MODEL;
            AndroidVersion = android.os.Build.VERSION.RELEASE;
            Board = Build.BOARD;
            Brand = Build.BRAND;
            Device = Build.DEVICE;
            Display = Build.DISPLAY;
            FingerPrint = Build.FINGERPRINT;
            Host = Build.HOST;
            ID = Build.ID;
            Model = Build.MODEL;
            Product = Build.PRODUCT;
            Tags = Build.TAGS;
            Time = Build.TIME;
            Type = Build.TYPE;
            User = Build.USER;
            return;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
    }

    private final void SaveAsFile(String s)
    {
        try
        {
            String s1 = "stack-".concat(String.valueOf(System.currentTimeMillis())).concat(".stacktrace");
            FileOutputStream fileoutputstream = new FileOutputStream(FilePath.concat("/").concat(s1));
            fileoutputstream.write(s.getBytes());
            fileoutputstream.close();
            return;
        }
        catch(IOException ioexception)
        {
            return;
        }
    }

    private final void SendErrorMail(Context context, String s)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        String s1 = (new StringBuilder("[Furnace][Rapport d'erreur][")).append(VersionName).append("]").toString();
        String s2 = (new StringBuilder("[Furnace][Rapport d'erreur]\n\n")).append(s).append("\n\n").toString();
        intent.putExtra("android.intent.extra.EMAIL", new String[] {
            "supportapp@magmamobile.com"
        });
        intent.putExtra("android.intent.extra.TEXT", s2);
        intent.putExtra("android.intent.extra.SUBJECT", s1);
        intent.setType("message/rfc822");
        context.startActivity(Intent.createChooser(intent, "Title:"));
    }

    private final boolean bIsThereAnyErrorFile()
    {
        return GetErrorFileList().length > 0;
    }

    private static final long getAvailableInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
    }

    private static final long getTotalInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
    }

    public final void CheckErrorAndSendMail(final Context context)
    {
        if(!bIsThereAnyErrorFile())
            return;
        String s = "";
        String as[];
        String s1;
        int l;
        String s2;
        FileReader filereader;
        BufferedReader bufferedreader;
        String s3;
        String s4;
        int i;
        as = GetErrorFileList();
        i = as.length;
        int j;
        int k;
        j = 0;
        k = 0;
        try
        {
            while(j < i) {
                s1 = as[j];
                l = k + 1;
                if(k <= 5) {
                    s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s))).append("New Trace collected :\n").toString()))).append("=====================\n ").toString();
                    s2 = (new StringBuilder(String.valueOf(FilePath))).append("/").append(s1).toString();
                    filereader = new FileReader(s2);
                    bufferedreader = new BufferedReader(filereader);
                    while (true) {
                        s3 = bufferedreader.readLine();
                        if (s3 != null) {
                            break;
                        }
                        s4 = (new StringBuilder(String.valueOf(s))).append(s3).append("\n").toString();
                        s = s4;
                    }
                    bufferedreader.close();
                }
                (new File((new StringBuilder(String.valueOf(FilePath))).append("/").append(s1).toString())).delete();
                j++;
                k = l;
            }
            final String txtError = s;
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage("Application has recently crashed. Would you like to send us the bug report ?");
            builder.setTitle("Bug report");
            builder.setCancelable(false);
            android.content.DialogInterface.OnClickListener onclicklistener = new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    SendErrorMail(context, txtError);
                }
            };
            builder.setPositiveButton("Yes", onclicklistener);
            android.content.DialogInterface.OnClickListener onclicklistener1 = new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    Toast.makeText(context, "Action canceled", Toast.LENGTH_SHORT).show();
                }
            };
            builder.setNegativeButton("No", onclicklistener1);
            builder.show();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public final void uncaughtException(Thread thread, Throwable throwable)
    {
        Date date = new Date();
        String s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(""))).append("Error Report collected on : ").append(date.toString()).toString()))).append("\n").toString()))).append("\n").toString()))).append("Informations :").toString()))).append("\n").toString()))).append("==============").toString()))).append("\n").toString()))).append("\n").toString()))).append(CreateInformationString()).toString()))).append("\n\n").toString()))).append("Stack : \n").toString()))).append("======= \n").toString();
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        throwable.printStackTrace(printwriter);
        String s1 = stringwriter.toString();
        String s2 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s))).append(s1).toString()))).append("\n").toString()))).append("Cause : \n").toString()))).append("======= \n").toString();
        Throwable throwable1 = throwable.getCause();
        do
        {
            if(throwable1 == null)
            {
                printwriter.close();
                SaveAsFile((new StringBuilder(String.valueOf(s2))).append("****  End of current Report ***").toString());
                PreviousHandler.uncaughtException(thread, throwable);
                return;
            }
            throwable1.printStackTrace(printwriter);
            s2 = (new StringBuilder(String.valueOf(s2))).append(stringwriter.toString()).toString();
            throwable1 = throwable1.getCause();
        } while(true);
    }

}
