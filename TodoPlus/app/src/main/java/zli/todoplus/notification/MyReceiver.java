package zli.todoplus.notification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class MyReceiver extends BroadcastReceiver implements ServiceConnection {
    Intent notifyIntent;
    Context context;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        notifyIntent = new Intent(context, MyNewIntentService.class);
        this.context = context;
        context.bindService(notifyIntent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        context.startService(notifyIntent);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}