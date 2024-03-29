package tarea1tema6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Ricardo Núñez Hernández
 */
public class EncriptacionServidor {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket socServidor = new ServerSocket(8066);
        Socket socCliente;
        
        while(true) {
            
            socCliente = socServidor.accept();
            System.out.println("Atendiendo al cliente");
            procesarPeticion(socCliente);
            socCliente.close();
            
        }
        
    }
    
    private static void procesarPeticion(Socket socketCliente) throws IOException {
        
        PrivateKey clavePrivada = null;
        PublicKey clavePublica = null;
        
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair clavesRSA = keyGen.generateKeyPair();
            clavePrivada = clavesRSA.getPrivate();
            clavePublica = clavesRSA.getPublic();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("clave Publica: " + clavePublica);
        System.out.println("clave Privada: " + clavePrivada);
            
        //Flujo de entrada
        ObjectOutputStream inOO = new ObjectOutputStream(socketCliente.getOutputStream());
        inOO.writeObject(clavePublica);
            
        //Flujo de salida
        ObjectInputStream inOI = new ObjectInputStream(socketCliente.getInputStream());
        
        Cipher cifrador = null;
        
        try {
            cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
            
            System.out.println("Descifrar con clave privada");
            byte[] bufferCifrado = (byte[])inOI.readObject();
            byte[] bufferClaro;
            bufferClaro = cifrador.doFinal(bufferCifrado);
            System.out.println("Texto descifrado");
            mostrarBytes(bufferClaro);
            
        } catch (Exception e) {
            
        }
        
        
        System.out.println("\n_______________");
        
        
    }
    
    public static void mostrarBytes(byte[] buffer) throws IOException {
        
        System.out.write(buffer);
        
    } 
    
}
