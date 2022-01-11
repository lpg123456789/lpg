package flower.common.communication.echo.manager;

import java.util.Scanner;

public class ConsoleUtil {

    /**
     * 启动控制台输入监听
     */
    public static void startConsoleListener() {
        Thread consoleThread = new Thread(() -> {
            try (Scanner sc = new Scanner(System.in)) {
                while (true) {
                    String cmd = sc.next();
                    String response = ConsoleServerManager.getInstance().handlerConsoleCmd(cmd);
                    System.out.println("exec cmd result : " + response);
                }
            } finally {
            }
        });
        consoleThread.setName("os_console_thread");
        consoleThread.setDaemon(true);
        consoleThread.start();
    }
}