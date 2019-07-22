package jp.truetech.testsupport;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * クライアントとターゲットサーバの間に組み込んで、 クライアントとの接続処理に介入(タイムアウトを発生させる)、
 * データ送受信処理に介入(送信処理開始を遅らせる)
 */

public class Proxy {
    
    private int localPort;
    private String remoteHost;
    private int remotePort;

    public Proxy(int localPort, String remoteHost, int remotePort) {
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public void start() throws Exception {
        start(0, 0);
    }

    public void start(int connectTime) throws Exception {
        start(connectTime, 0);
    }

    public void start(int connectTime, int responseTime) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(localPort);) {
            while (true) {
                if (connectTime > 0) {
                    suspend(connectTime, "suspend accept for " + connectTime + "sec");
                }

                System.out.println(">> accept");
                Socket client = serverSocket.accept();
                System.out.println("<< accept (" + client.getLocalPort() + ")");
                InputStream clientIn = client.getInputStream();
                OutputStream clientOut = client.getOutputStream();

                System.out.println(">> connect");
                Socket targetSocket = connect();
                System.out.println("<< connect (" + targetSocket.getLocalPort() + ")");
                InputStream targetIn = targetSocket.getInputStream();
                OutputStream targetOut = targetSocket.getOutputStream();

                transfer("client->server", clientIn, targetOut);
                transfer("client<-server", targetIn, clientOut, responseTime);
            }
        }
    }

    Socket connect() throws Exception {
        return new Socket(remoteHost, remotePort);
    }

    void transfer(String label, InputStream in, OutputStream out) {
        transfer(label, in, out, 0);
    }

    void transfer(String label, InputStream in, OutputStream out, int responseTime) {
        new Thread(() -> {
            try {
                boolean suspend = true;
                while (true) {
                    int b = in.read();
                    if (b == -1) {
                        break;
                    }
                    if (responseTime > 0 && suspend) {
                        suspend(responseTime, "suspend write for " + responseTime + "sec");
                        suspend = false;
                    }
                    out.write(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, label).start();
    }

    private void suspend(int second, String message) {
        System.out.println(message);
        try {
            for (int i = 0; i < second; i++) {
                System.out.print(" " + i);
                Thread.sleep(1000);
            }
            System.out.println();
            System.out.println("[suspend end]");
        }
        catch (Exception e) {
            
        }
    }

}
