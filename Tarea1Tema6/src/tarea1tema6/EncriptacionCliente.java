package tarea1tema6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author Ricardo Núñez Hernández
 */
public class EncriptacionCliente {
    
    public static void main(String[] args) throws Exception {
        
        Socket s = new Socket();
        
        InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 8066);
        s.connect(direccionServidor);
        
        
        //Flujo de entrada
        ObjectInputStream inSR = new ObjectInputStream(s.getInputStream());
        PublicKey clavePublica = (PublicKey)inSR.readObject();
        
        //espacio en memoria para la entrada de peticiones
        
        byte[] bufferClaro = "Hola, soy Ricardo\n".getBytes();
        
        Cipher cifrador = Cipher.getInstance("RSA");
        cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
        System.out.println("Cifrar con clave publica el texto:");
        mostrarBytes(bufferClaro);
        
        
        byte[] bufferCifrado = cifrador.doFinal(bufferClaro);
        System.out.println("Texto cifrado");
        mostrarBytes(bufferCifrado);
        System.out.println("\n__________________________________________");
        
        PrintWriter salidaSocket = new PrintWriter(s.getOutputStream(), true);
        salidaSocket.println(bufferCifrado);
        
        /*
        cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
        System.out.println("Descifrar con clave privada");
        
        bufferClaro = cifrador.doFinal(bufferCifrado);
        System.out.println("Texto descifrado");
        mostrarBytes(bufferClaro);
        System.out.println("\n___________________________________");*/
        
    }
    
    public static void mostrarBytes(byte[] buffer) throws IOException {
        
        System.out.write(buffer);
        
    } 
    
}
