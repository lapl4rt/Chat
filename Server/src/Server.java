import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yuliya on 24.09.2014.
 */
public class Server {

    public static void main(String args[]) throws IOException {
        ServerSocket server = null;
        Socket socketClient = null;

        BufferedReader in = null;
        PrintWriter out = null;

        System.out.println("We are in the server");

        try {
            server = new ServerSocket(1234);
        }
        catch (IOException e) {
            System.out.println("Couldn't listen to port 1234");
            System.exit(-1);
        }

        try {
            socketClient = server.accept();
            System.out.println("Client connected");
        }
        catch (IOException e) {
            System.out.println("Couldn't accept");
        }

        in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        out = new PrintWriter(socketClient.getOutputStream(), true);
        String input, output;

        while ((input = in.readLine()) != null) {
            if (input.equalsIgnoreCase("exit")) break;
            out.println(">> " + input);
            System.out.println(input);
        }

        in.close();
        out.close();
        socketClient.close();
        server.close();
    }
}
