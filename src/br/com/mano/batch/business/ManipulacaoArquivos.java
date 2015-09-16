package br.com.mano.batch.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import br.com.mano.batch.util.BatchConstants;

public class ManipulacaoArquivos {
	  
		/**
	    * M�todo que verifica os arquivos existentes em uma pasta e os lista em um ArrayList
	    * @param dir
	    * @return 
	    */
	   public ArrayList<String> verificaArquivosBaixados(String dir){
	        ArrayList<String> lista = new ArrayList<>();
	        File fList[] = new File(dir).listFiles();
	        if(fList != null){
		        for (int i = 0; fList.length > i; i++) {
		             lista.add(fList[i].getName());
		        }
	        }
	        return lista;   
	    }
	   
		/**
	    * M�todo que verifica se um arquivo j� foi baixado
	    * @param dir
	    * @return 
	    */
	   public static boolean verificaArquivoBaixados(String dir, String nome){
	        File fList[] = new File( BatchConstants.pathLocal + dir).listFiles();
	        boolean existe = false;
	        if(fList != null){
		        for (int i = 0; fList.length > i; i++) {
		             if(fList[i].getName().equals(nome))
		            		 existe = true;
		        }
	        }
	        return existe;   
	    }
	    
	    /**
	     * M�todo que baixa um arquivo da internet e grava em uma pasta determinada
	     * @param urlArq
	     * @param pasta 
	     */
	    public void downloadArquivo(String urlArq, String pasta){
	        try {
	            URL url = new URL(urlArq);
	            String nomeArquivoLocal = url.getPath().substring(url.getPath().lastIndexOf("/"));
	            FileOutputStream fos;
	            try (InputStream is = url.openStream()) {
	                fos = new FileOutputStream(pasta + nomeArquivoLocal);
	                int umByte;
	                while ((umByte = is.read()) != -1){
	                    fos.write(umByte);
	                }
	            }
	            fos.close();
	               
	        } catch (IOException ex) {
	            Logger.getLogger(ManipulacaoArquivos.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    /**
	     * M�todo que estrai arquivos zipados em uma pasta determinada
	     * @param diretorioArquivoZip
	     * @param diretorioDestino 
	     */
	    public void extraiZip(String diretorioArquivoZip, String diretorioDestino){
	        try {
	            final int BUFFER = 2048;
	          
	            try (FileInputStream fis = new FileInputStream(diretorioArquivoZip); BufferedInputStream bis = new BufferedInputStream(fis, BUFFER); ZipInputStream zis = new ZipInputStream(bis)) {
	                 ZipEntry entrada;
	                 
	                 while((entrada = zis.getNextEntry()) != null){
	                     int bytesLidos = 0;
	                     byte dados[] = new byte[BUFFER];
	                     
	                     String nomeArq;
	                     if(entrada.getName().substring(entrada.getName().lastIndexOf(".") + 1).equalsIgnoreCase("txt")){
	                         nomeArq = entrada.getName();
	                     }else{
	                         nomeArq = entrada.getName() + ".txt";
	                     }
	                     
	                     boolean exist = (new File(diretorioDestino + nomeArq).exists());
	                     if(!exist){
	                        try (FileOutputStream fos = new FileOutputStream(diretorioDestino + nomeArq); 
	                        		BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER)) {
	                            while((bytesLidos = zis.read(dados, 0, BUFFER)) != -1 ){
	                                dest.write(dados, 0, bytesLidos);
	                            }
	                            dest.flush();
	                        }
	                     }
	                     
	                 }
	             }
	             
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(ManipulacaoArquivos.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (IOException ex) {
	            Logger.getLogger(ManipulacaoArquivos.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    /**
	     * Cria diret�rio
	     * 
	     * @author Hermann
	     * @param stringDir
	     * @param nome 
	     */
	    public static void criaVerificaDiretorio (String stringDir, String nome){
	    
	        File dir = new File(stringDir + BatchConstants.path + nome);  
	            if (!dir.exists()) {
	            	if (dir.mkdirs()) {
	                	Logger.getLogger("Diretorio criado com sucesso!");
	                    System.out.println("Diretorio criado com sucesso!");
	                } else {
	                	Logger.getLogger("Erro ao criar diretorio!");
	                    System.out.println("Erro ao criar diretorio!");  
	                }
	            } else {
	            	Logger.getLogger("Diretório já existente");
	            	System.out.println("Diretório já existente"); 
	            }
	    }

}
