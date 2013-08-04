package by.De_Vano_Corp.QuickSms;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	String phoneNumber, message;
	ImageButton editBtn;
	SharedPreferences set;
	Editor seteditor;
	EditText ph;
	boolean oneTimeShoot=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitButtons();
//		LoadSettings();
	}

	protected void onResume()
	{
		super.onResume();
		LoadSettings();
	}
	
	void LoadSettings() {
		set = getSharedPreferences("app_settings", MODE_PRIVATE);
		phoneNumber = set.getString("PhoneNumber", "");
		ph.setText(phoneNumber);
		// phoneNumber="+375256431028";
	}

	void WriteSettings() {
		set = getSharedPreferences("app_settings", MODE_PRIVATE);
		seteditor = set.edit();
		phoneNumber = ph.getText().toString();
		seteditor.putString("PhoneNumber", phoneNumber);
		seteditor.commit();
	}

	void InitButtons() {
		editBtn = (ImageButton) findViewById(R.id.imageButton1);
		
		ph = (EditText) findViewById(R.id.editTextPhone);
		
		((Button) findViewById(R.id.cancelButton))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						MainActivity.this.finish();
					}
				});/* allowEdit */
		editBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (ph.isEnabled()) {
					//выключаем поле, сохраняем номер телефона
					ph.setEnabled(false);
					editBtn.setImageResource(R.drawable.editicon);
					WriteSettings();
				} else {
					//включаем поле
					ph.setEnabled(true);
					//запрашиваем фокус
					ph.requestFocus();
					editBtn.setImageResource(R.drawable.saveicon);
					
				}
			}
		});
		((Button) findViewById(R.id.sendButtton))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						message = ((EditText) findViewById(R.id.editTextMessage))
								.getText().toString();
						if (!phoneNumber.equals("") && !message.equals(""))
							SendSMS();
						else Van.ToastBox(getApplicationContext(), "Заполните все поля!");
					}
				});

	}
	boolean isSended=false;
	void SendSMS() {
		/*
		 * ///////////////SIMPLE/////////////////////// PendingIntent pi =
		 * PendingIntent.getActivity(this, 0, new Intent(this,
		 * MainActivity.class), 0); SmsManager sms = SmsManager.getDefault();
		 * sms.sendTextMessage(phoneNumber, null, message, pi, null);
		 */// ////////////////HARD///////////////////////////
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
/*
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
				SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		// ---when the SMS has been sent---
		registerReceiver(new BroadcastReceiver() {
			@SuppressWarnings("deprecation")
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.Sended),
							Toast.LENGTH_SHORT).show();
					MainActivity.this.finish();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.GenericFailure),
							Toast.LENGTH_SHORT).show();
					
					isSended=false;
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.NoService),
							Toast.LENGTH_SHORT).show();
					isSended=false;
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU",
							Toast.LENGTH_SHORT).show();
					isSended=false;
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.RadioOff),
							Toast.LENGTH_SHORT).show();
					isSended=false;
					break;
				}
			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.SMSDelivered),
							Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.SMSNotDelivered),
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));
*/
		//sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
		
		///////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
		
		
		BroadcastReceiver messageSentReceiver = new BroadcastReceiver() {      
		    @Override
		    public void onReceive(Context context, Intent intent) {
		    	switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.Sended),
							Toast.LENGTH_SHORT).show();
					
					/*
					ContentValues values = new ContentValues();
				    values.put("address", phoneNumber);
				    values.put("body", message);
				    getBaseContext().getContentResolver().insert(Uri.parse("content://sms/sent"), values);
					*/
				    
					//MainActivity.this.finish();
					
					isSended=true;
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.GenericFailure),
							Toast.LENGTH_SHORT).show();
					isSended=false; oneTimeShoot=false;
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.NoService),
							Toast.LENGTH_SHORT).show();
					isSended=false; oneTimeShoot=false;
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU",
							Toast.LENGTH_SHORT).show();
					isSended=false; oneTimeShoot=false;
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(),
							getResources().getString(R.string.RadioOff),
							Toast.LENGTH_SHORT).show();
					isSended=false; oneTimeShoot=false;
					break;
				}
		    	if (isSended && oneTimeShoot)
		    	{
		    		
		    		ContentValues values = new ContentValues();
		    	    values.put("address", phoneNumber);
		    	    values.put("body", message);
		    		//values.put("date", String.valueOf(System.currentTimeMillis())); 
		    		getBaseContext().getContentResolver().insert(Uri.parse("content://sms/sent"), values);
					isSended=false; oneTimeShoot=false;
				}
				//надо добавлять в черновик
				
		    	else 
		    	{
		    		if (!isSended && oneTimeShoot)
		    		{
		    		ContentValues values = new ContentValues();
		    	    values.put("address", phoneNumber);
		    	    values.put("body", message);
		    		//values.put("date", String.valueOf(System.currentTimeMillis())); 
		    		getBaseContext().getContentResolver().insert(Uri.parse("content://sms/draft"), values);
		    		}
		    	}
			  }
		    };
		   BroadcastReceiver messageDeliveredReceiver = new BroadcastReceiver() {      
			    @Override
			    public void onReceive(Context context, Intent intent) {
			    	switch (getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(),
								getResources().getString(R.string.SMSDelivered),
								Toast.LENGTH_SHORT).show();
						break;
					case Activity.RESULT_CANCELED:
						Toast.makeText(getBaseContext(),
								getResources().getString(R.string.SMSNotDelivered),
								Toast.LENGTH_SHORT).show();
						break;
					}
			      }
			    };
			    
		/////////////////////////////////////////////////////////
		SmsManager sms = SmsManager.getDefault();
		ArrayList<String> messages = sms.divideMessage(message);
		int messageCount = messages.size();
		ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>(messageCount);
		ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(messageCount);


		for (int j = 0; j < messageCount; j++) {
		   sentIntents.add(PendingIntent.getBroadcast(getApplicationContext(), 0, 
		                                              new Intent(SENT),
		                                              0));
		   deliveryIntents.add(PendingIntent.getBroadcast(getApplicationContext(), 0, 
                   new Intent(DELIVERED),
                   0));

		   }
		
		//PendingIntent sentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT), 0);
		//PendingIntent deliveredIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(DELIVERED), 0);


		getApplicationContext().registerReceiver(messageSentReceiver, new IntentFilter(SENT));
		getApplicationContext().registerReceiver(messageDeliveredReceiver, new IntentFilter(DELIVERED));
		
		
		sms.sendMultipartTextMessage( phoneNumber, null, messages, sentIntents, deliveryIntents);
		//getBaseContext().getContentResolver();
		//Van.ToastBox(getApplicationContext(), getContentResolver().getCurrentSync().account.name.toString());
		
		MainActivity.this.finish();
	}

}
