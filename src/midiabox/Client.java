package midiabox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jess
 */
public class Client {

    Socket cliente;
    ObjectOutputStream saida;
    ObjectInputStream entrada;

    public Client() {
        try {
            cliente = new Socket("localhost", 12345);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean logar(String usuario, String senha) throws ClassNotFoundException {
        boolean autenticou = false;
        try {
            saida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            saida.writeObject("logar");
            saida.flush();
            saida.writeObject(usuario);
            saida.flush();
            saida.writeObject(senha);
            saida.flush();
            autenticou = (boolean) entrada.readObject();
        } catch (IOException e) {

        }
        return autenticou;
    }

    public String getUrlVideo(String codigoVideo) {
        String url = "";
        try {
            saida.writeObject("getVideo");
            saida.flush();
            saida.writeObject(codigoVideo);
            saida.flush();
            int tamanhoArquivo = (int) entrada.readObject();
            String nomeArquivo = (String) entrada.readObject();
            entrada.close();
            url = "c:\\temp\\" + nomeArquivo;
            receberArquivo(tamanhoArquivo, url);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

    public void receberArquivo(int tamanho, String path) throws Exception {
        DatagramSocket ds = new DatagramSocket(12345);
        byte[] msg = new byte[tamanho];
        DatagramPacket pkg = new DatagramPacket(msg, msg.length);
        ds.receive(pkg);
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(pkg.getData());
        ds.close();
    }

    public void close() {
        try {
            saida.close();
            entrada.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
