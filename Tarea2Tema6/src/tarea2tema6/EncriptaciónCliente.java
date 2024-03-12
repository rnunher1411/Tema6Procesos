package tarea2tema6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import static tarea2tema6.EncriptacionServidor.mostrarBytes;

/**
 *
 * @author rnunher1411
 */
public class EncriptaciónCliente {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, ClassNotFoundException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {
        
        Socket s = new Socket();
        
        InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 8066);
        s.connect(direccionServidor);
        
        ObjectInputStream inSR = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream outOO = new ObjectOutputStream(s.getOutputStream());
        SecretKey clave;
        
      
            
        clave = (SecretKey)inSR.readObject();
        
        //3. Cliente utiliza la clave para codificar un fichero.
        byte[] bufferCifrado = cifrarFichero("fichero", clave);
        
        //4. Cliente envía a servidor fichero cifrado por un socket.
        outOO.writeObject(bufferCifrado);

        
    }
    
    
    private static byte[] cifrarFichero(String file, SecretKey clave) throws NoSuchAlgorithmException, NoSuchPaddingException,
            FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        
        FileInputStream fe = null;
        FileOutputStream fs = null;
        
        int bytesLeidos;
        
        System.out.println("Clave");
        mostrarBytes(clave.getEncoded());
        System.out.println();
        
        Cipher cifrador = Cipher.getInstance("DES");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        System.out.println("2. Cifrar con DES el fichero: " + file 
                + ", y dejar resultado en " + file + ".cifrado");
        
        //declarar objeto
        byte[] buffer = new byte[1000];
        byte[] bufferCifrado;
        
        fe = new FileInputStream(file);
        fs = new FileOutputStream(file + ".cifrado");
        
        bytesLeidos = fe.read(buffer, 0, 1000);
        
        while (bytesLeidos != -1) {
            
            bufferCifrado = cifrador.update(buffer, 0, bytesLeidos);
            fs.write(bufferCifrado);
            bytesLeidos = fe.read(buffer, 0, 1000);
            
        }
        
        bufferCifrado = cifrador.doFinal();
        
        fs.write(bufferCifrado);
        
        fe.close();
        fs.close();
        
        return bufferCifrado;
        
    }
    
    public static void mostrarBytes(byte[] buffer) throws IOException {
        
        System.out.write(buffer);
        
    } 
    
}
