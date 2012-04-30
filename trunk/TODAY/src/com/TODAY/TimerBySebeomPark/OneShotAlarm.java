package com.TODAY.TimerBySebeomPark;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.
//import com.example.android.apis.R;

/**
 * This is an example of implement an {@link BroadcastReceiver} for an alarm that
 * should occur once.
 * <p>
 * When the alarm goes off, we show a <i>Toast</i>, a quick message.
 */
public class OneShotAlarm extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "One shot alarm", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(context,showDialogActivity.class);
        Intent newIntent = new Intent(context,ShowDialogActivity.class);
        
        // here has the flag problem
        
        
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);			// Activiy NEw Task
        context.startActivity(newIntent);
        
        // Broadcast cannot open the AlertDialog.. why? I don't know
        
        
    }
}