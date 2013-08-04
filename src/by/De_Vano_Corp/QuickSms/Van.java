package by.De_Vano_Corp.QuickSms;

import java.io.File;

import android.widget.Toast;
import android.content.Context;

public class Van {
	

	public static void ToastBoxLong(Context context, CharSequence textmessage)
    {
		 Toast.makeText(context,textmessage, Toast.LENGTH_LONG).show();
    }
	public static  void ToastBox(Context context, CharSequence textmessage)
    {
    	 Toast.makeText(context,textmessage, Toast.LENGTH_SHORT).show();
    }
    public static  void ToastBox(Context context, CharSequence textmessage, int timems)
    {
    	 Toast.makeText(context,textmessage, timems).show();
    }
    public static  void createDir(String foldername) {

        File f1 = new File(foldername); //Создаем файловую переменную
        if (!f1.exists()) { //Если папка не существует
                f1.mkdirs();  //создаем её
        }
    }
    
	
	
	
	
	
	
	
	
	
	/*
	Simple replace Notification.Builder with NotificationCompat.Builder, and import android.support.v4.app.NotificationCompat.



    myNotification = new NotificationCompat.Builder(context)
          .setContentTitle("Exercise of Notification!")
          .setContentText("http://android-er.blogspot.com/")
          .setTicker("Notification!")
          .setWhen(System.currentTimeMillis())
          .setContentIntent(pendingIntent)
          .setDefaults(Notification.DEFAULT_SOUND)
          .setAutoCancel(true)
          .setSmallIcon(R.drawable.ic_launcher)
          .build();
	*/
}
