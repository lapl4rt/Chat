import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 1234;

    public static void main(String args[]) throws IOException {
        ServerSocket server = null;
        Socket socketClient = null;

        System.out.println("We are in the server");

        try {
            server = new ServerSocket(PORT);
        }
        catch (IOException e) {
            System.out.println("Couldn't listen to port " + PORT);
            System.exit(-1);
        }

        while (true) {
            try {
                socketClient = server.accept();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println("Couldn't accept");
            }

            new ServerThread(socketClient).start();
        }

    }
}

class ServerThread extends Thread {
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String input, output;

        try {
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) break;
                out.println(">> " + input);
                System.out.println(input);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

    }
}
