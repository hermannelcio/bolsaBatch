package br.com.mano.batch.start;

import java.sql.Timestamp;
import java.util.Calendar;

import br.com.mano.batch.business.DownloadCotacao;
import br.com.mano.batch.business.ExtracaoArquivos;
import br.com.mano.batch.dao.DiarioBaseDAO;
import br.com.mano.batch.util.BatchConstants;


public class MainCarregaBase {
    
    
	 public static void main(String[] args) throws Exception {
		DiarioBaseDAO dao = new DiarioBaseDAO();
		Calendar hoje = Calendar.getInstance();
		Calendar ontem = Calendar.getInstance();
		ontem.add(Calendar.DAY_OF_YEAR, - 1);
		Calendar ultimoRegistro = Calendar.getInstance();
		String[] pastas = null;
		String[] arquivos = new String[6];
		boolean contem = false;
		int controle = 2;
		
		if(dao.dataUltimoRegistro() != null)
			ultimoRegistro.setTime(dao.dataUltimoRegistro());
		else 
			ultimoRegistro.setTime(new Timestamp(1985));
		 
		while(ultimoRegistro.before(ontem)){
			pastas = DownloadCotacao.downloadArquivo(ultimoRegistro, hoje);
			contem = false;
			if(arquivos[0] == null || arquivos[0] == ""){
				arquivos[0] = pastas[1]; 
				arquivos[1] = pastas[2];
			}
			if(arquivos[0] != null || arquivos[0] != ""){
				for(int i = 0; arquivos.length > i; i++){
					if(arquivos[i] != null && arquivos[i].equals(pastas[1])){
						contem = true;
						break;
					}
				}
				if(!contem){
					arquivos[controle] = pastas[1];
					arquivos[controle + 1] = pastas[2];
					controle = controle + 2;
				}
			}
			
		}
		
		ExtracaoArquivos.extrairEPersistir(BatchConstants.pathLocal, arquivos);
		
	 }
}
