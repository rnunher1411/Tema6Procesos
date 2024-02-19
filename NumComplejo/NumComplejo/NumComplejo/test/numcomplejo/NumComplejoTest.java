/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numcomplejo;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;




/** @author portatil_profesorado */

public class NumComplejoTest {
    
    NumComplejo result;
    NumComplejo esperado;
    NumComplejo c1;
    NumComplejo c2; 
    
    @Before
    public void before() {
        
        System.out.println("-----------------------------------------");
        System.out.println("Inicializando todos los números complejos");
        result = new NumComplejo();
        esperado = new NumComplejo();
        c1 = new NumComplejo();
        c2 = new NumComplejo();
        
    }
    
    @After
    public void after() {
        
        System.out.println("---------------------------------------\n");
        //num.clear();
        
    } 
    
    @Test
    public void testSumar() {
        
        System.out.println("Suma: numcomplejo.sumarComplejos(3.0+2.0i, -1.0+2.0i) >>> (3.0+2.0i)+(-1.0+2.0i)");
        
        c1.setpReal(3);
        c1.setpImaginaria(2);
        c2.setpImaginaria(2);
        c2.setpReal(-1);
        
        NumComplejo resultado = result.sumarComplejos(c1, c2);
        
        esperado.setpReal(2);
        esperado.setpImaginaria(4);
        
        assertEquals(esperado.getpReal(), resultado.getpReal(), 1);
        assertEquals(esperado.getpImaginaria(), resultado.getpImaginaria(), 1);
        
    }
    
    @Test
    public void testRestar() {
        
        System.out.println("Resta: numcomplejo.restarComplejos(3.0+2.0i, -1.0+2.0i) >>> (3.0+2.0i)-(-1.0+2.0i)");
        
        c1.setpReal(3);
        c1.setpImaginaria(2);
        c2.setpImaginaria(2);
        c2.setpReal(-1);
        
        NumComplejo resultado = result.restarComplejos(c1, c2);
        
        esperado.setpReal(4);
        esperado.setpImaginaria(0);
        
        assertEquals(esperado.getpReal(), resultado.getpReal(), 1);
        assertEquals(esperado.getpImaginaria(), resultado.getpImaginaria(), 1);
        
    }
    
    @Test
    public void testMultiplicar() {
        
        System.out.println("Multiplicación: numcomplejo.multiplicarComplejos(3.0+2.0i, -1.0+2.0i) >>> (3.0+2.0i)*(-1.0+2.0i)");
        
        c1.setpReal(3);
	c1.setpImaginaria(2);
	c2.setpReal(-1);
	c2.setpImaginaria(2);
        
        NumComplejo resultado = result.multiplicarComplejos(c1, c2);
        esperado.setpReal(-7);
	esperado.setpImaginaria(4);
        
        assertEquals(esperado.getpReal(), resultado.getpReal(), 1);
        assertEquals(esperado.getpImaginaria(), resultado.getpImaginaria(), 1);
        
    }
    
    @Test
    public void testDividir() {
        
        System.out.println("División: numcomplejo.dividirComplejos(3.0+2.0i, -1.0+2.0i) >>> (3.0+2.0i)/(-1.0+2.0i)");
        
        c1.setpReal(3);
	c1.setpImaginaria(2);
	c2.setpReal(-1);
	c2.setpImaginaria(2);
        
        NumComplejo resultado = result.dividirComplejos(c1, c2);
        esperado.setpReal(0.2);
	esperado.setpImaginaria(-1.6);
        
        assertEquals(esperado.getpReal(), resultado.getpReal(), 1);
        assertEquals(esperado.getpImaginaria(), resultado.getpImaginaria(), 1);
        
    }
    
    @Test(expected = ArithmeticException.class)
    public void testIndeterminado() {
        
        System.out.println("División (Indeterminado): numcomplejo.dividirComplejos(3.0+2.0i, 0.0+0.0i) >>> (3.0+2.0i / 0.0+0.0i)");
        
        c1.setpReal(3);
	c1.setpImaginaria(2);
	c2.setpReal(0);
	c2.setpImaginaria(0);
        
        NumComplejo resultado = result.dividirComplejos(c1, c2);
        esperado.setpReal(0.2);
	esperado.setpImaginaria(-1.6);
        
        assertEquals(esperado.getpReal(), resultado.getpReal(), 1);
        assertEquals(esperado.getpImaginaria(), resultado.getpImaginaria(), 1);
        
    }
    
    
    
}
