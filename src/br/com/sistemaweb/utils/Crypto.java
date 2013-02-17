/**
 * Este artefato de software foi desenvolvido por mim (root) em 02/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.security.rsa.RSAKeyFactory;


/**
 * @author root
 *
 */
public class Crypto {

	private String palavra;
	private String criptografada;
	
	/**
	 * 
	 */
	public Crypto(String pal) {
		setPalavra(pal);
	}
	
	/**
	 * 
	 */
	public Crypto() {

	}
	
	
	/**
	 * Utilizado na funcao Criptografar
	 * @param text
	 * @return
	 */
	private char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
        	hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2,
                                    hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }
	
	/**
	 * Funo de Criptografia
	 * @param pwd Palavra a criptografar
	 * @return Palavra criptografada
	 * @throws NoSuchAlgorithmException
	 */
	public  String criptografar(String pwd) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }


	public String getCriptografada() {
		try {
			criptografada=criptografar(palavra);
			return criptografada;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
		
	}

	public String getPalavra() {
		return palavra;
	}
	
	

}
