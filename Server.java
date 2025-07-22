import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер начал работу, порт: " + port);

            // Ожидаем подключения клиента
            Socket clientSocket = serverSocket.accept();
            System.out.println("Новое соединение установлено");

            // Создаем потоки для чтения и записи
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Читаем строку от клиента
            String name = in.readLine();
            System.out.println("Имя клиента [порт " + clientSocket.getPort() + "]: " + name);

            // Отправляем ответ клиенту
            out.println(String.format("Привет %s, твой порт: %d", name, clientSocket.getPort()));

        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }
}