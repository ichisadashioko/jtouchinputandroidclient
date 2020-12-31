package io.github.ichisadashioko.jtouchinputandroidclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    public EditText editText_hostname;
    public TouchInputClient touchInputClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.editText_hostname = findViewById(R.id.server_hostname);
    }

    public void onConnectButtonClicked(View v) {
        try {
            String hostname = this.editText_hostname.getText().toString();
            int comma_index = hostname.lastIndexOf(':');
            String portStr = hostname.substring(comma_index + 1);
            hostname = hostname.substring(0, comma_index);
            System.out.println("hostname: " + hostname);
            System.out.println("portStr: " + portStr);

            int serverPort = Integer.parseInt(portStr);

            if (this.touchInputClient == null) {
                this.touchInputClient =
                        new TouchInputClient((byte) 4, (byte) 3, hostname, serverPort);
                MainActivity that = this;

                Thread t =
                        new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            that.touchInputClient.initialize();
                                            that.touchInputClient.start();

                                        } catch (Exception ex) {
                                            ex.printStackTrace(System.err);
                                        }
                                    }
                                });
                t.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
