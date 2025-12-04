package ca.hccis.t3;

import ca.hccis.t3.threads.Thread1Console;
import ca.hccis.t3.threads.Thread2GUI;

public class Controller {

    public static void main(String[] args) {
        Thread threadConsole = new Thread1Console();
        Thread threadGUI = new Thread(new Thread2GUI());

        threadConsole.start();
        threadGUI.start();

    }

}
