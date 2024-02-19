/*
 * class NumComplejo                   version 1.0
 * Descripción:  
 * Clase que representa un número complejo y que realiza las siguientes operaciones de los números complejos: 
 *          Suma
 *          Resta
 *          Multiplicacion
 *          Division
 *          Modulo (no lo vamos a estudiar en esta tarea)
 * 
 * Módulo: DISEÑO DE INTERFACES        Tarea06
 */





package numcomplejo;

public class NumComplejo {
    
        /* Para representar esta clase utilizamos dos atributos
        *  Estos atributos representan la parte real y la parte imaginaria
        *  de un número complejo
        */
	
	private double pReal;
	private double pImaginaria;
	
	
	public NumComplejo(){
		
	}
	
	public NumComplejo(double pReal, double pImaginaria){
		this.pReal=pReal;
		this.pImaginaria=pImaginaria;
	}
	
	public double getpReal() {
		return pReal;
	}
	
	public void setpReal(double pReal) {
		this.pReal = pReal;
	}
	
	public double getpImaginaria() {
		return pImaginaria;
	}
	
	public void setpImaginaria(double pImaginaria) {
		this.pImaginaria = pImaginaria;
	}
	
	/**
	 * Suma dos numeros complejos
	 * @param c1
	 * @param c2
	 * @return suma
	 */
	public NumComplejo sumarComplejos (NumComplejo c1, NumComplejo c2){
		NumComplejo result = new NumComplejo();
		
		result.setpReal(c1.getpReal()+c2.getpReal());
		result.setpImaginaria(c1.getpImaginaria()+c2.getpImaginaria());
		
		return result;
	}
	
	/**
	 * Resta dos numeros complejos
	 * @param c1
	 * @param c2
	 * @return resta
	 */
	public NumComplejo restarComplejos (NumComplejo c1, NumComplejo c2){
		NumComplejo result = new NumComplejo();
		
		result.setpReal(c1.getpReal()-c2.getpReal());
		result.setpImaginaria(c1.getpImaginaria()-c2.getpImaginaria());
		
		return result;
	}
	
	/**
	 * Multiplica dos numeros complejos
	 * @param c1
	 * @param c2
	 * @return multiplicacion
	 */
	public NumComplejo multiplicarComplejos (NumComplejo c1, NumComplejo c2){
		NumComplejo result = new NumComplejo();
		
		double a=c1.getpReal();
		double b=c1.getpImaginaria();
		double c=c2.getpReal();
		double d=c2.getpImaginaria();
		
		result.setpReal((a*c)-(b*d));
		result.setpImaginaria((a*d)+(b*c));
		
		return result;
	}
	
	/**
	 * Divide dos numeros complejos
	 * @param c1
	 * @param c2
	 * @return division
	 */
	public NumComplejo dividirComplejos (NumComplejo c1, NumComplejo c2){
		NumComplejo result = new NumComplejo();
		
                // obtenemos los valores de cada número complejo
		double a=c1.getpReal();
		double b=c1.getpImaginaria();
		double c=c2.getpReal();
		double d=c2.getpImaginaria();
                
                
                // calculamos división de los dos números complejos
		
		double dividendoReal = (a*c)+(b*d);
		double dividendoImaginario = (b*c)-(a*d);
		double divisor = (Math.pow(c, 2)+Math.pow(d, 2));
                
                
                //Genera la excepción por número Indeterminado
                
                ////////////////////////////////////////////////////////////
                //Genera la excepción por número Indeterminado //
                //dividendoReal o dividendoImaginario es 0 y divisor es 0 //
                /////////////////////////////////////////////////
                //(Completa el código) //
                ////////////////////////////////////////////////////////////
                
                
  
		
		result.setpReal(dividendoReal/divisor);
		result.setpImaginaria(dividendoImaginario/divisor);
		
		return result;
	}
	
        
        /*
	/**
	 * Calcula el modulo de un numero complejo
	 * @param numComplejo
	 * @return modulo
	 */
	/*public double modulo (NumComplejo numComplejo){
		double real =  numComplejo.getpReal();
		double imaginaria = numComplejo.getpImaginaria();
		
		return Math.sqrt(Math.pow(real, 2)+Math.pow(imaginaria, 2));
	}*/
        
	
	@Override
	public String toString(){
		if (pImaginaria>=0){
			return pReal+"+"+pImaginaria+"i";
		}else
			return pReal+"-"+(pImaginaria*(-1))+"i";
	}
	
	
	
}
