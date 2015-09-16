package br.com.mano.batch.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.mano.batch.util.BatchUtils;


@Entity
@NamedQueries({
@NamedQuery(name = "diariobase.findAll", query = "SELECT d FROM DiarioBase d"),
@NamedQuery(name = "diariobase.findByidHead", query = "SELECT d FROM DiarioBase d WHERE d.idHead =:idHead"),
@NamedQuery(name = "diariobase.findBytipoRegistro", query = "SELECT d FROM DiarioBase d WHERE d.tipoRegistro =:tipoRegistro"),
@NamedQuery(name = "diariobase.findBydataPregao", query = "SELECT d FROM DiarioBase d WHERE d.dataPregao =:dataPregao"),
@NamedQuery(name = "diariobase.findBycodigoBDI", query = "SELECT d FROM DiarioBase d WHERE d.codigoBDI =:codigoBDI"),
@NamedQuery(name = "diariobase.findBycodigoNegociacaoPapel", query = "SELECT d FROM DiarioBase d WHERE d.codigoNegociacaoPapel =:codigoNegociacaoPapel"),
@NamedQuery(name = "diariobase.findBycodigoNegociacaoPapelEData", query = "SELECT d FROM DiarioBase d WHERE d.codigoNegociacaoPapel =:codigoNegociacaoPapel AND d.dataPregao =:dataPregao"),
@NamedQuery(name = "diariobase.findBytipoMercado", query = "SELECT d FROM DiarioBase d WHERE d.tipoMercado =:tipoMercado"),
@NamedQuery(name = "diariobase.findBynomeResumidoPapel", query = "SELECT d FROM DiarioBase d WHERE d.nomeResumidoPapel =:nomeResumidoPapel"),
@NamedQuery(name = "diariobase.findByespecificacaoPapel", query = "SELECT d FROM DiarioBase d WHERE d.especificacaoPapel =:especificacaoPapel"),
@NamedQuery(name = "diariobase.findByprazoMercadoTermo", query = "SELECT d FROM DiarioBase d WHERE d.prazoMercadoTermo =:prazoMercadoTermo"),
@NamedQuery(name = "diariobase.findBymoedaReferencia", query = "SELECT d FROM DiarioBase d WHERE d.moedaReferencia =:moedaReferencia"),
@NamedQuery(name = "diariobase.findBynumeroDistribuicao", query = "SELECT d FROM DiarioBase d WHERE d.numeroDistribuicao =:numeroDistribuicao")})
@Table(name="diariobase")
public class DiarioBase {
	private int idHead;							//head_idhead INT NOT NULL,
	private int id; 							//iddiariobase` INT NOT NULL,
	private Long tipoRegistro; 					//tipreg SMALLINT NULL,
	private Timestamp dataPregao; 				//datapreg TIMESTAMP NULL,
	private String codigoBDI; 					//codbdi VARCHAR(2) NULL,
	private String codigoNegociacaoPapel; 		//codneg VARCHAR(12) NULL,
	private Long tipoMercado; 					//tpmerc SMALLINT NULL,
	private String nomeResumidoPapel; 			//nomres VARCHAR(12) NULL,
	private String especificacaoPapel; 			//especi VARCHAR(10) NULL,
	private String prazoMercadoTermo; 			//prazot VARCHAR(3) NULL,
	private String moedaReferencia; 			//modref VARCHAR(4) NULL,
	private BigDecimal precoAbertura; 			//preabre DECIMAL(11,2) NULL,
	private BigDecimal precoMaximo;				//premax DECIMAL(11,2) NULL,
	private BigDecimal precoMinimo;				//premin DECIMAL(11,2) NULL,
	private BigDecimal precoMedio;				//premed DECIMAL(11,2) NULL,
	private BigDecimal precoFechamento;			//preult DECIMAL(11,2) NULL,
	private BigDecimal precoMelhorOfertaCompra;	//preofc DECIMAL(11,2) NULL,
	private BigDecimal precoMelhorOfertaVenda;	//preofv DECIMAL(11,2) NULL,
	private Integer negociosEfetuados; 			//totneg INT(11) NULL,
	private BigInteger titulosNegociados; 			//quatot BIGINT NULL,
	private BigDecimal volumeNegociado;			//voltot DECIMAL(16,2) NULL,
	private BigDecimal precoMercadoOpcoes;		//preexe DECIMAL(11,2) NULL,
	private Long indicadorCorrecao;				//indopc SMALLINT NULL,
	private Timestamp dataVencimentoOpcoes;		//datven TIMESTAMP NULL,
	private Integer fatorCotacao; 				//fatcot INT(11) NULL,
	private BigDecimal precoPontoRefDolar;		//ptoexe DECIMAL(7,6) NULL,
	private String codigoIsin;					//codisi VARCHAR(12) NULL,
	private int numeroDistribuicao;				//dimes SMALLINT NULL,
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	@Column(name="head_idhead")
	public int getIdHead() {
		return idHead;
	}
	public void setIdHead(int idHead) {
		this.idHead = idHead;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="iddiariobase")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name="tipreg")
	public Long getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(Long tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	
	@Column(name="datapreg")
	public Timestamp getDataPregao() {
		return dataPregao;
	}
	public void setDataPregao(Timestamp dataPregao){
		this.dataPregao = dataPregao;
	}
	public void setDataPregao(String datapre) {
		try {
			
			if(BatchUtils.verificaData(datapre))
				this.dataPregao = new Timestamp((sdf.parse(datapre)).getTime());
			else
				this.dataPregao = null;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Column(name="codbdi")
	public String getCodigoBDI() {
		return codigoBDI;
	}
	public void setCodigoBDI(String codigoBDI) {
		this.codigoBDI = codigoBDI;
	}
	
	@Column(name="codneg")
	public String getCodigoNegociacaoPapel() {
		return codigoNegociacaoPapel;
	}
	public void setCodigoNegociacaoPapel(String codigoNegociacaoPapel) {
		this.codigoNegociacaoPapel = codigoNegociacaoPapel;
	}
	
	@Column(name="tpmerc")
	public Long getTipoMercado() {
		return tipoMercado;
	}
	public void setTipoMercado(Long tipoMercado) {
		this.tipoMercado = tipoMercado;
	}
	
	@Column(name="nomres")
	public String getNomeResumidoPapel() {
		return nomeResumidoPapel;
	}
	public void setNomeResumidoPapel(String nomeResumidoPapel) {
		this.nomeResumidoPapel = nomeResumidoPapel;
	}
	
	@Column(name="especi")
	public String getEspecificacaoPapel() {
		return especificacaoPapel;
	}
	public void setEspecificacaoPapel(String especificacaoPapel) {
		this.especificacaoPapel = especificacaoPapel;
	}
	
	@Column(name="prazot")
	public String getPrazoMercadoTermo() {
		return prazoMercadoTermo;
	}
	public void setPrazoMercadoTermo(String prazoMercadoTermo) {
		this.prazoMercadoTermo = prazoMercadoTermo;
	}
	
	@Column(name="modref")
	public String getMoedaReferencia() {
		return moedaReferencia;
	}
	public void setMoedaReferencia(String moedaReferencia) {
		this.moedaReferencia = moedaReferencia;
	}
	
	@Column(name="preabre")
	public BigDecimal getPrecoAbertura() {
		return precoAbertura;
	}
	public void setPrecoAbertura(BigDecimal precoAbertura) {
		this.precoAbertura = precoAbertura;
	}
	
	@Column(name="premax")
	public BigDecimal getPrecoMaximo() {
		return precoMaximo;
	}
	public void setPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
	}
	
	@Column(name="premin")
	public BigDecimal getPrecoMinimo() {
		return precoMinimo;
	}
	public void setPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
	}
	
	@Column(name="premed")
	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}
	public void setPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
	}
	
	@Column(name="preult")
	public BigDecimal getPrecoFechamento() {
		return precoFechamento;
	}
	public void setPrecoFechamento(BigDecimal precoFechamento) {
		this.precoFechamento = precoFechamento;
	}
	
	@Column(name="preofc")
	public BigDecimal getPrecoMelhorOfertaCompra() {
		return precoMelhorOfertaCompra;
	}
	public void setPrecoMelhorOfertaCompra(BigDecimal precoMelhorOfertaCompra) {
		this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
	}
	
	@Column(name="preofv")
	public BigDecimal getPrecoMelhorOfertaVenda() {
		return precoMelhorOfertaVenda;
	}
	public void setPrecoMelhorOfertaVenda(BigDecimal precoMelhorOfertaVenda) {
		this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
	}
	
	@Column(name="totneg")
	public Integer getNegociosEfetuados() {
		return negociosEfetuados;
	}
	public void setNegociosEfetuados(Integer negociosEfetuados) {
		this.negociosEfetuados = negociosEfetuados;
	}
	
	@Column(name="quatot")
	public BigInteger getTitulosNegociados() {
		return titulosNegociados;
	}
	public void setTitulosNegociados(BigInteger titulosNegociados) {
		this.titulosNegociados = titulosNegociados;
	}
	
	@Column(name="voltot")
	public BigDecimal getVolumeNegociado() {
		return volumeNegociado;
	}
	public void setVolumeNegociado(BigDecimal volumeNegociado) {
		this.volumeNegociado = volumeNegociado;
	}
	
	@Column(name="preexe")
	public BigDecimal getPrecoMercadoOpcoes() {
		return precoMercadoOpcoes;
	}
	public void setPrecoMercadoOpcoes(BigDecimal precoMercadoOpcoes) {
		this.precoMercadoOpcoes = precoMercadoOpcoes;
	}
	
	@Column(name="indopc")
	public Long getIndicadorCorrecao() {
		return indicadorCorrecao;
	}
	public void setIndicadorCorrecao(Long indicadorCorrecao) {
		this.indicadorCorrecao = indicadorCorrecao;
	}
	
	@Column(name="datven")
	public Timestamp getDataVencimentoOpcoes() {
		return dataVencimentoOpcoes;
	}
	public void setDataVencimentoOpcoes(Timestamp dataVencimentoOpcoes){
		this.dataVencimentoOpcoes = dataVencimentoOpcoes;
	}
	public void setDataVencimentoOpcoes(String dataven) {
		try {

			if(BatchUtils.verificaData(dataven))
				this.dataVencimentoOpcoes = new Timestamp((sdf.parse(dataven)).getTime());
			else
				this.dataVencimentoOpcoes = null;	
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Column(name="fatcot")
	public Integer getFatorCotacao() {
		return fatorCotacao;
	}
	public void setFatorCotacao(Integer fatorCotacao) {
		this.fatorCotacao = fatorCotacao;
	}
	
	@Column(name="ptoexe")
	public BigDecimal getPrecoPontoRefDolar() {
		return precoPontoRefDolar;
	}
	public void setPrecoPontoRefDolar(BigDecimal precoPontoRefDolar) {
		this.precoPontoRefDolar = precoPontoRefDolar;
	}
	
	@Column(name="codisi")
	public String getCodigoIsin() {
		return codigoIsin;
	}
	public void setCodigoIsin(String codigoIsin) {
		this.codigoIsin = codigoIsin;
	}
	
	@Column(name="dimes")
	public int getNumeroDistribuicao() {
		return numeroDistribuicao;
	}
	public void setNumeroDistribuicao(int numeroDistribuicao) {
		this.numeroDistribuicao = numeroDistribuicao;
	}
	
	public DiarioBase carregaValores(String linha, int idHead){
		int i = 0;
		this.setIdHead(idHead);
        this.setTipoRegistro(Long.parseLong(linha.substring(i, i=i+2)));
        this.setDataPregao(linha.substring(i, i=i+8));
        this.setCodigoBDI(linha.substring(i, i=i+2));
        this.setCodigoNegociacaoPapel(linha.substring(i, i=i+12));
        this.setTipoMercado(Long.parseLong(linha.substring(i, i=i+3)));
        this.setNomeResumidoPapel(linha.substring(i, i=i+12));
        this.setEspecificacaoPapel(linha.substring(i, i=i+10));
        this.setPrazoMercadoTermo(linha.substring(i, i=i+3));
        this.setMoedaReferencia(linha.substring(i, i=i+4));
        this.setPrecoAbertura(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setPrecoMaximo(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setPrecoMinimo(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setPrecoMedio(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setPrecoFechamento(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setPrecoMelhorOfertaCompra(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setPrecoMelhorOfertaVenda(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setNegociosEfetuados(new Integer ((linha.substring(i, i=i+5))));
        this.setTitulosNegociados(new BigInteger(linha.substring(i, i=i+18)));
        this.setVolumeNegociado(new BigDecimal(linha.substring(i, i=i+16) + "." +linha.substring(i, i=i+2)));
        this.setPrecoMercadoOpcoes(new BigDecimal(linha.substring(i, i=i+11) + "." +linha.substring(i, i=i+2)));
        this.setIndicadorCorrecao(Long.parseLong(linha.substring(i, i=i+1)));
        this.setDataVencimentoOpcoes(linha.substring(i, i=i+8));
        this.setFatorCotacao(new Integer(linha.substring(i, i=i+7)));
        this.setPrecoPontoRefDolar(new BigDecimal(linha.substring(i, i=i+7) + "." +linha.substring(i, i=i+6)));
        this.setCodigoIsin(linha.substring(i, i=i+12));
        this.setNumeroDistribuicao(new Short(linha.substring(i, i=i+3)));
        return this;
	}
	
}
