package com.torontodjango.reminder;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.torontodjango.reminder.Ringing;
import com.torontodjango.reminder.Task;

public class TaskReceiver extends BroadcastReceiver
{
    private final String TAG = "TaskReceiver";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "onReceive");

        Intent newIntent = new Intent(context, Ringing.class);
        Task task = new Task();
        task.fromIntent(intent);
        task.toIntent(newIntent);

        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(newIntent);
        context.startForegroundService(newIntent);
    }
}
