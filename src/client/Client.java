package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    private final Socket socket;
    Scanner scanner = new Scanner(System.in);

    public Client() throws IOException {
        String HOST = "localhost";
        // Preparamos la conexion
        int PORT = 9876;
        socket = new Socket(HOST, PORT);
    }

    public void startClient() throws IOException {
        DataInputStream inServer = new DataInputStream(socket.getInputStream());
        DataOutputStream outServer = new DataOutputStream(socket.getOutputStream());

        System.out.println("Recibiendo mensaje del servidor");
        System.out.println(inServer.readUTF());
        String nombre = scanner.nextLine();
        outServer.writeUTF(nombre);

        System.out.println("Enviado mensaje al servidor: {"+nombre+"}");

        System.out.println("Recibiendo mensaje del servidor");
        System.out.println(inServer.readUTF());
        String mensaje = scanner.nextLine();
        outServer.writeUTF(mensaje);
        // No se porque pero pasando el mensaje asi a Int me funciona,
        // Si no cuando me pide la descripcion se traga un mensaje y envia uno sin informaicon
        int numTarea = Integer.parseInt(mensaje);
        System.out.println("Enviado mensaje al servidor: {"+numTarea+"}");

        // Bucle para poder enviar la informacion de las descripciones y estados
            for (int i = 1; i <= numTarea; i++) {
                System.out.println("Leyendo mensaje del servidor");
                System.out.println(inServer.readUTF());

                System.out.println("Leyendo mensaje del servidor");
                System.out.println(inServer.readUTF());
                String descripcion = scanner.nextLine();
                outServer.writeUTF(descripcion);
                System.out.println("Enviado mensaje al servidor: {"+descripcion+"}");

                System.out.println("Leyendo mensaje del servidor");
                System.out.println(inServer.readUTF());
                String estado = scanner.nextLine();
                outServer.writeUTF(estado);
                System.out.println("Enviado mensaje al servidor: {"+estado+"}");

            }

            System.out.println("Leyendo mensaje del servidor");
            System.out.println(inServer.readUTF());

            // Bucle para recibir la informacion de las tareas
            for (int j = 1; j <= numTarea; j++) {
                System.out.println("Leyendo mensaje del servidor");
                System.out.println(inServer.readUTF());
              }

            outServer.close();
            inServer.close();

            socket.close();
        }

    }