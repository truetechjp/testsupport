package jp.truetech.testsupport;

public class ProxyApp {
    public static void main(String[] args) {
        try {
            new Proxy(8888, "192.168.0.101", 3128).start(10, 5);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
