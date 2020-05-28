package jp.truetech.testsupport;

import jp.truetech.testsupport.util.Arguments;

public class ProxyApp {
    public static void main(String[] args) {
        try {
            int localPort = 8880;
            String remoteHost = "127.0.0.1";
            int remotePort = 8881;
            int connectTime = 0;
            int responseTime = 0;

            if (args.length > 1) {
                Arguments arguments = new Arguments(args);
                if (arguments.contains("localPort")) {
                    localPort = stringToInt(arguments.get("localPort"), localPort);
                }
                if (arguments.contains("remoteHost")) {
                    remoteHost = arguments.get("remoteHost");
                }
                if (arguments.contains("remotePort")) {
                    remotePort = stringToInt(arguments.get("remotePort"), remotePort);
                }
                if (arguments.contains("connectTime")) {
                    connectTime = stringToInt(arguments.get("connectTime"), connectTime);
                }
                if (arguments.contains("responseTime")) {
                    responseTime = stringToInt(arguments.get("responseTime"), responseTime);
                }
            }
            System.out.println(
                    String.format("%d, %s, %d, %d, %d", localPort, remoteHost, remotePort, connectTime, responseTime));

            new Proxy(localPort, remoteHost, remotePort).start(connectTime, responseTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int stringToInt(String sValue, int defaultValue) {
        try {
            return Integer.parseInt(sValue);
        } catch (Exception e) {
            System.err.println(e);
        }
        return defaultValue;
    }

}
