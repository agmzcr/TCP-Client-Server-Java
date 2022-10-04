package client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Client cli = new Client();
        System.out.println("Iniciando cliente");
        System.out.println("******");

        cli.startClient();
    }
}