package zli.todoplus.notification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class MyReceiver extends BroadcastReceiver {
    Intent notifyIntent;
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        notifyIntent = new Intent(context, MyNewIntentService.class);

        String todoName = notifyIntent.getStringExtra("todoName");
        notifyIntent.putExtra("todoName2", todoName);

        context.startService(notifyIntent);
    }
}