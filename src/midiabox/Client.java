package midiabox;

import Model.Midia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
            saida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean logar(String usuario, String senha) throws ClassNotFoundException {
        boolean autenticou = false;
        try {
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
            saida.writeObject(codigoVideo);
            saida.flush();
            int tamanhoArquivo = entrada.readInt();
            String nomeArquivo = (String) entrada.readObject();
            byte[] arquivo = new byte[tamanhoArquivo];
            entrada.readFully(arquivo);
            url = "c:\\Temp\\" + nomeArquivo;
            FileOutputStream fos = new FileOutputStream(url);
            fos.write(arquivo);
            close();
            url = url.replace("\\", "/").replace("\\", "/");
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

    public void gravarArquivo(File arquivo) {
        try {
            FileInputStream fileInputStream;
            fileInputStream = new FileInputStream(arquivo);
            byte[] bArq = new byte[(int) arquivo.length()];
            fileInputStream.read(bArq);
            fileInputStream.close();
            saida.writeObject("salvarMidia");
            saida.writeInt(bArq.length);
            saida.writeObject(arquivo.getName());
            saida.write(bArq);
            saida.flush();
            saida.close();
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public List<Midia> getListaArquivos(){
        List<Midia> listaMidia = new ArrayList<Midia>();
        try {
            saida.writeObject("getLista");
            saida.flush();
            listaMidia = (List<Midia>) entrada.readObject();
            close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMidia;
    }
}
