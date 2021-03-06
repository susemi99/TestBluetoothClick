package kr.mint.testbluetoothclick;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

public class BluetoothReceiver extends BroadcastReceiver
{
   @Override
   public void onReceive(final Context $context, Intent $intent)
   {
      ContextUtil.CONTEXT = $context;
      Log.w("BluetoothReceiver.java | onReceive", "|" + $intent.getAction() + "|");
      
      String action = $intent.getAction();
      if (Intent.ACTION_MEDIA_BUTTON.equals(action))
      {
         KeyEvent keyEvent = (KeyEvent) $intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
         int currentKey = keyEvent.getKeyCode();
         
         Log.w("BluetoothReceiver.java | onReceive", "|" + $intent.getAction() + " | " + keyEvent.getAction());
         
         if (KeyEvent.ACTION_UP == keyEvent.getAction())
         {
            
            Log.w("BluetoothReceiver.java | onReceive", "|" + currentKey + "|");
            
            // 원래는 KeyEvent.KEYCODE_MEDIA_RECORD
            if (currentKey == KeyEvent.KEYCODE_MEDIA_PREVIOUS)
            {
               Intent intent = new Intent($context.getPackageName() + "." + Consts.KEY_TOGGLE_RECORD_STATE);
               intent.setClass($context, MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               $context.startActivity(intent);
            }
            else
            {
               Log.w("BluetoothReceiver.java | onReceive", "|" + "forword" + "|");
               
               Intent intent = new Intent(Consts.FORWORD_BROADCAST);
               intent.putExtra(Consts.KEY_CODE, currentKey);
               $context.sendBroadcast(intent);
            }
         }
      }
      else if ("android.media.VOLUME_CHANGED_ACTION".equals(action))
      {
         int volume = (Integer) $intent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_VALUE");
         Log.w("BluetoothReceiver.java | onReceive", "|" + volume + "|");
         
         Intent intent = new Intent($context.getPackageName() + "." + Consts.KEY_TOGGLE_RECORD_STATE);
         intent.setClass($context, MainActivity.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         $context.startActivity(intent);
         
//         KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
//         Log.w("BluetoothReceiver.java | onReceive", "|" + keyEvent.getKeyCode() + "|");
      }
   }
}
