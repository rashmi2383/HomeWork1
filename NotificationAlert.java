package com.example.rr.notificationalert;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


public class NotificationAlert extends Activity {

    private static final int NOTIFY_ME_ID=1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_alert);

        /*********** Create notification ***********/

        final NotificationManager mgr=
                (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification note=new Notification(R.drawable.stat_notify_chat,
                "Android Example Status message!",
                System.currentTimeMillis());

        // This pending intent will open after notification click
        PendingIntent i=PendingIntent.getActivity(this, 0,
                new Intent(this, NotifyMessage.class),
                0);

        note.setLatestEventInfo(this, "Android Example Notification Title",
                "This is the android example notification message", i);

        //After uncomment this line you will see number of notification arrived
        //note.number=2;
        mgr.notify(NOTIFY_ME_ID, note);


    }
}
