package epis.utils;

/**
 * String Utils
 * @author Jean Poffo
 * @since  04/08/2020
 */
public class StringUtil {

    public static String removeStrangeCharacter(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", "");
    }
    
    /**
     * Validate CPF
     * @param cpf
     * @return boolean
     */
    public static boolean validateCpf(String cpf) {
        int iDigito1Aux = 0; 
        int iDigito2Aux = 0; 
        int iDigitoCPF;
        int iDigito1 = 0; 
        int iDigito2 = 0;
        int iRestoDivisao = 0;
        
        String strDigitoVerificador; 
        String strDigitoResultado;
        
        cpf = StringUtil.removeStrangeCharacter(cpf);
        
        try {
            for(int iCont = 1; iCont < cpf.length() -1; iCont++) {
                iDigitoCPF = Integer.valueOf(cpf.substring(iCont -1, iCont));
                iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
                iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
            }
            
            iRestoDivisao = (iDigito1Aux % 11);
            
            if(iRestoDivisao < 2) {
                iDigito1 = 0;
            } 
            else {
                iDigito1 = 11 - iRestoDivisao;
            }
            
            iDigito2Aux += 2 * iDigito1;
            iRestoDivisao = (iDigito2Aux % 11);
            
            if(iRestoDivisao < 2) {
                iDigito2 = 0;
            } 
            else {
                iDigito2 = 11 - iRestoDivisao;
            }
            
            strDigitoVerificador = cpf.substring(cpf.length() - 2, cpf.length());
            strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
            
            return strDigitoVerificador.equals(strDigitoResultado);
        } 
        catch (NumberFormatException e) {
            return false;
        }
    }
    
}
