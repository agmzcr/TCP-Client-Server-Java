package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    protected ServerSocket server;
    protected Socket socket;
    ArrayList<Tarea> list= new ArrayList<>();

    public Server() throws IOException {
        int PORT = 9876;
        server = new ServerSocket(PORT);
        socket = new Socket();
    }

    public void startServer() throws IOException {

            while (true) {
                System.out.println("Esperando al cliente...");
                socket = server.accept();

                DataOutputStream outClient = new DataOutputStream(socket.getOutputStream());
                DataInputStream inClient = new DataInputStream(socket.getInputStream());

                outClient.writeUTF("Bienvenido, ¿Como te llamas?");
                String nombre = inClient.readUTF();
                System.out.println("Encantado de verte, "+nombre);

                outClient.writeUTF("¿Cuantas tareas has de realizar?");
                String mensaje = inClient.readUTF();

                System.out.println("Has recibido "+mensaje+" tareas.");
                int numTarea = Integer.parseInt(mensaje);

                // Bucle for para recoger la informacion de la descripcion y estado
                for (int i = 1; i <= numTarea; i++) {
                    outClient.writeUTF("Introducción de la tarea: "+i);

                    outClient.writeUTF("Introduce la descripción");

                    String descripcion = inClient.readUTF();

                    System.out.println("Descripción recibida: "+descripcion);

                    outClient.writeUTF("Introduce el estado:");

                    String estado = inClient.readUTF();

                    System.out.println("Estado recibido: "+estado);

                    Tarea j = new Tarea(i, descripcion, estado);
                    list.add(j);
                }

                outClient.writeUTF("Se enviaran las tareas");

                // Creamos el iterator

                // While que envia los mensajes 1 en 1
                for (Tarea tarea : list) {
                    outClient.writeUTF("Tarea " + tarea.numTarea + ": Descripcion: " + tarea.descripcion + ". Estado: " + tarea.estado);
                }

                outClient.close();
                inClient.close();
                System.out.println("Cliente desconectado");
                socket.close();
            }
        }
}

