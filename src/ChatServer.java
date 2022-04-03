import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        this.serverSocket = new ServerSocket(1234);
    }

    public void sendAll(String massage){
        for (Client c: clients){
            c.receive(massage);
        }
    }

    public void run(){
        while(true) {
            System.out.println("Waiting...");

            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента на своей стороне
                clients.add(new Client(socket, this));

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}