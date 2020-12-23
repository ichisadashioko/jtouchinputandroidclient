package io.github.ichisadashioko.jtouchinputandroidclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TouchInputClient extends Thread{
    public OutputStream outputStream;
    public InputStream inputStream;
    public byte widthFactor;
    public byte heightFactor;
    public String serverHostname;
    public int serverPort;
    public Socket socket;

    public boolean isInitialized;
    public boolean isConnecting;

    public TouchInputClient(byte widthFactor, byte heightFactor, String serverHostname, int serverPort) {
        this.isInitialized = false;
        this.socket = null;
        this.outputStream = null;
        this.inputStream = null;

        this.widthFactor = widthFactor;
        this.heightFactor = heightFactor;
        this.serverHostname = serverHostname;
        this.serverPort = serverPort;
        this.isConnecting = false;
    }

    public void initialize() throws IOException{
        this.socket = new Socket(this.serverHostname, this.serverPort);
        this.outputStream = this.socket.getOutputStream();
        byte[] buffer = {this.widthFactor, this.heightFactor};
        this.outputStream.write(buffer);

        this.inputStream = this.socket.getInputStream();

        this.isConnecting = true;
        this.isInitialized = true;
    }

    public void cleanResources(){
        if(this.inputStream != null){
            try{
                this.inputStream.close();
            }catch(IOException ex){
                System.err.println("Failed to close inputStream!");
                ex.printStackTrace(System.err);
            }
        }

        if(this.outputStream != null){
            try{
                this.outputStream.close();
            }catch(IOException ex){
                System.err.println("Failed to close outputStream!");
                ex.printStackTrace(System.err);
            }
        }

        if(this.socket != null){
            if(!this.socket.isClosed()){
                this.socket.close();
            }
        }

        this.isConnecting = false;
    }
}
