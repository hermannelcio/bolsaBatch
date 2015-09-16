package br.com.mano.batch.business;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.mano.batch.dao.DiarioBaseDAO;
import br.com.mano.batch.util.BatchConstants;

public class DownloadCotacao {
	
	/**
	 * Método que realiza o download dos arquivos de cotações
	 * @return
	 */
	public static String[] downloadArquivo(Calendar ultimoRegistro, Calendar hoje){
		
		String[] nomes = DownloadCotacao.montaURL(ultimoRegistro, hoje);
			try{
				if(!ManipulacaoArquivos.verificaArquivoBaixados(nomes[1], nomes[0])){
					URL url = new URL(BatchConstants.linkDownload+nomes[0]); 
				 	
				 	ManipulacaoArquivos.criaVerificaDiretorio(BatchConstants.pathLocal, nomes[1] );
	            
		            InputStream is = url.openStream();
		            FileOutputStream fos = new FileOutputStream(BatchConstants.pathLocal + nomes[1] + BatchConstants.path +nomes[0]);  
		            int umByte = 0;
		            
		            while ((umByte = is.read()) != -1){  
		                fos.write(umByte);  
		            }  
		            
		            is.close();  
		            fos.close();
				}
            }catch (Exception e){
            	System.out.println(e);
            }
		
		return nomes;
	}
	
	/**
	 * M�todo que monta a URL para fazer o download
	 * @param ultimoRegistro
	 * @param hoje
	 * @return
	 */
	private static String[] montaURL(Calendar ultimoRegistro, Calendar hoje) {
		@SuppressWarnings("unused")
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

		String nomeArquivoLocal = "COTAHIST_";
		String nomePastaOrigem = "";
		String nomePastaDestino = "";
		
		int mes = ultimoRegistro.get(Calendar.MONTH);
		int dia = ultimoRegistro.get(Calendar.DAY_OF_MONTH);

		if ((hoje.get(Calendar.YEAR) - ultimoRegistro.get(Calendar.YEAR)) > 0) {

			ultimoRegistro.add(Calendar.YEAR, 1);
			nomeArquivoLocal += "A" + ultimoRegistro.get(Calendar.YEAR) + ".ZIP";
			nomePastaOrigem = "cotzip";
			nomePastaDestino = "cotbase";

		} else if ((hoje.get(Calendar.MONTH) - mes) > 0) {
			ultimoRegistro.add(Calendar.MONTH, 1);
			mes = ultimoRegistro.get(Calendar.MONTH);
			++mes;
			if (mes < 10)
				nomeArquivoLocal += "M0" + mes + ultimoRegistro.get(Calendar.YEAR) + ".ZIP";
			else
				nomeArquivoLocal += "M" + mes + ultimoRegistro.get(Calendar.YEAR) + ".ZIP";
			
			nomePastaOrigem = "cotzipmonth";
			nomePastaDestino = "cotbasemonth";

		} else if ((hoje.get(Calendar.DAY_OF_MONTH) - dia) > 0) {
			ultimoRegistro.add(Calendar.DAY_OF_MONTH, 1);
			String mesD;
			mes = ultimoRegistro.get(Calendar.MONTH);
			++mes;
			dia = ultimoRegistro.get(Calendar.DAY_OF_MONTH);
			if (mes < 10) {
				mesD = "0" + mes;
			} else {
				mesD = String.valueOf(mes);
			}
			if (dia < 10)
				nomeArquivoLocal += "D0" + dia + mesD + ultimoRegistro.get(Calendar.YEAR) + ".ZIP";
			else
				nomeArquivoLocal += "D" + dia + mesD + ultimoRegistro.get(Calendar.YEAR) + ".ZIP";
			
			nomePastaOrigem = "cotzipday";
			nomePastaDestino = "cotbaseday";

		} else {
			ultimoRegistro.add(Calendar.DAY_OF_MONTH, 1);
		}
		return new String[] {nomeArquivoLocal, nomePastaOrigem, nomePastaDestino};
	}

}
