package br.com.mano.batch.util;


public class BatchConstants {
    
	public static final String nomePastaOrigemAnual = "cotzip";
    public static final String nomePastaDestinoAnual = "cotbase";

    public final static String URL_EMPRESAS = "http://www.bmfbovespa.com.br/pregao-online/ExecutaAcaoCotRapXSL.asp?intIdiomaXsl=3";
    public final static String URL_COTACAO = "http://www.bmfbovespa.com.br/Pregao-Online/ExecutaAcaoCarregarDadosPapeis.asp?CodDado=";
    public final static String codDado = "PP  C03";
	
    public final static String linkDownload = "http://www.bmfbovespa.com.br/InstDados/SerHist/" ;
  
    public final static String path = System.getProperty("file.separator");
    public final static String pathLocal = path + "Volumes" + path + "HHD"  + path + "cotacoes" + path;
    
}
