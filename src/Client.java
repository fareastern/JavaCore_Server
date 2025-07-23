import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            // Создаем потоки для чтения и записи
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправляем строку серверу
            String message = "Иван";
            out.println(message);
            System.out.println("Отправлено серверу: " + message);

            // Получаем ответ от сервера и выводим его
            String response = in.readLine();
            System.out.println("Ответ сервера: " + response);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}