package zli.todoplus.notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import zli.todoplus.R;
import zli.todoplus.TodoActivity;

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;
    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);

        String todoName = intent.getStringExtra("todoName2");

        builder.setContentTitle("Todo: " + todoName);
        builder.setContentText("You have an open Todo!");
        builder.setSmallIcon(R.drawable.ic_todo_reminder);
        Intent notifyIntent = new Intent(this, TodoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}