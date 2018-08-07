package kariyermimari.tr.com.broadcastkulaklik;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HEADSETLUG";
    private MusicIntentReceiver myReceiver;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MusicIntentReceiver();
        tv1 = (TextView)findViewById(R.id.tvResult);
        //yeni değişiklik yapıldı
    }

    public void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        super.onResume();
    }

    private class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Log.d(TAG, "Kulaklık Takılı Değil");
                        tv1.setText("Kulaklık Takılı Değil");
                        break;
                    case 1:
                        Log.d(TAG, "Kulaklık Takılı");
                        tv1.setText("Kulaklık Takılı");
                        break;
                    default:
                        Log.d(TAG, "xxx");
                        tv1.setText("xxx");
                }
            }
        }
    }

    @Override
    public void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }
}