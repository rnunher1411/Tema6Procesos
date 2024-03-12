package tarea2tema6;

import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tarea2tema6.EncriptaciónCliente.mostrarBytes;

/**
 *
 * @author RicardoNúñez Hernández
 */

public class EncriptacionServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        ServerSocket socServidor = new ServerSocket(8066);
        Socket socketCliente = socServidor.accept();
        SecretKey clave = null;
        
        ObjectOutputStream outOO = new ObjectOutputStream(socketCliente.getOutputStream());
        ObjectInputStream inOI = new ObjectInputStream(socketCliente.getInputStream());
        
        System.out.println("1. Genera clave DES");
        
        //Generamos la clave DES
        KeyGenerator keyGen;   
        keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
            
        clave = keyGen.generateKey();
        
        //2. Servidor envía al cliente clave
        outOO.writeObject(clave);
        
        try{
            
            
            //5. Servidor lo descifra con la clave única.
            
            byte[] bufferCifrado = (byte[])inOI.readObject();

            descifrarFichero(bufferCifrado, clave, inOI);
            
        }catch(Exception e) {
            
            e.printStackTrace();
            
        }
    }
    
    
    private static void descifrarFichero(byte[] bufferCifrado, SecretKey clave, ObjectInputStream inOI) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        
        FileInputStream fe = null;
        FileOutputStream fs = null;
        Cipher cifrador = Cipher.getInstance("DES");
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        byte[] bufferClaro;
        
        byte[] buffer = new byte[1000];
        int bytesLeidos = inOI.read();
        
        while (bytesLeidos != -1) {
            
            bufferClaro = cifrador.update(buffer, 0, bytesLeidos);
            fs.write(bufferClaro);
            bytesLeidos = fe.read(buffer, 0, 1000);
            
        }
        
        bufferClaro = cifrador.doFinal();
        fs.write(bufferClaro);
        
        fe.close();
        fs.close();        
        
    }
    
    
    public static void mostrarBytes(byte[] buffer) throws IOException {
        
        System.out.write(buffer);
        
    } 
    
}
