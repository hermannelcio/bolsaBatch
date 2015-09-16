package br.com.mano.batch.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.mano.batch.business.ExtracaoArquivos;

public class BatchUtils {
	
	/**
	 * Método que verifica se a data não está errada com valor 9999
	 * 
	 * @param data
	 * @return
	 */
	public static boolean verificaData(String data){
		return !"9".equals(data.substring(0, 1));
	}
	
    /**
     * Método que exibe a quantidade de linhas apenas
     *
     * @param arquivo
     * @return
     * @throws Exception 
     */
    public static int getLinhas(String arquivo) throws Exception{
        int linhas = 0;
        try { 
            File arquivoLeitura = new File(arquivo); 

            // pega o tamanho 
            long tamanhoArquivo = arquivoLeitura .length(); 
            FileInputStream fs = new FileInputStream(arquivoLeitura); 
            DataInputStream in = new DataInputStream(fs); 

			@SuppressWarnings("resource")
			LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(in)); 
            lineRead.skip(tamanhoArquivo); 
            // conta o numero de linhas do arquivo, começa com zero, por isso adiciona 1 
            linhas = lineRead.getLineNumber() + 1; 
            System.out.println("O ARQUIVO CONTEM " + linhas + " LINHAS!!!!!!!"); 

        } catch (IOException ex) { 
            Logger.getLogger(ExtracaoArquivos.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
        return linhas;
    }
    
	
}
