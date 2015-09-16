package br.com.mano.batch.entities;

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
@NamedQuery(name = "head.findAll", query = "SELECT h FROM Head h"),
@NamedQuery(name = "head.findBynomeArquivoh", query = "SELECT h FROM Head h WHERE h.nomeArquivoh =:nomeArquivoh")})
@Table(name="head")
public class Head {
	private int id; 					//`idhead` INT NOT NULL,
	private Long tipoRegistroh;  		//`tipreghead` SMALLINT NULL,
	private String nomeArquivoh;  		//`nomarqhead` VARCHAR(13) NULL,
	private String codigoOrigemH;  		//`codorihead` VARCHAR(8) NULL,
	private Timestamp dataGeracaoArqH;  //`datgerhead` TIMESTAMP NULL,
	private Long totalRegistros; 		//`totregtrailer` DECIMAL(10,0) NULL,
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idhead")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="tipreghead")
	public Long getTipoRegistroh() {
		return tipoRegistroh;
	}
	public void setTipoRegistroh(Long tipoRegistroh) {
		this.tipoRegistroh = tipoRegistroh;
	}
	
	@Column(name="nomarqhead")
	public String getNomeArquivoh() {
		return nomeArquivoh;
	}
	public void setNomeArquivoh(String nomeArquivoh) {
		this.nomeArquivoh = nomeArquivoh;
	}
	
	@Column(name="codorihead")
	public String getCodigoOrigemH() {
		return codigoOrigemH;
	}
	public void setCodigoOrigemH(String codigoOrigemH) {
		this.codigoOrigemH = codigoOrigemH;
	}
	
	@Column(name="datgerhead")
	public Timestamp getDataGeracaoArqH() {
		return dataGeracaoArqH;
	}
	public void setDataGeracaoArqH(Timestamp dataGeracaoArqH){
		this.dataGeracaoArqH = dataGeracaoArqH;
	}
	public void setDataGeracaoArqH(String dataHead) {
		try {
			
			if(BatchUtils.verificaData(dataHead))
				this.dataGeracaoArqH = new Timestamp((sdf.parse(dataHead)).getTime());
			else
				this.dataGeracaoArqH = null;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Column(name="totregtrailer")
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public void setTotalRegistros(String linha) {
		this.totalRegistros = Long.parseLong(linha.substring(31, 42));
	}
	
	public Head carregaValores(String linha){
		this.setTipoRegistroh(Long.parseLong(linha.substring(0, 2)));
        this.setNomeArquivoh(linha.substring(2, 15));
        this.setCodigoOrigemH(linha.substring(15, 23));
        this.setDataGeracaoArqH(linha.substring(23, 31));
		return this;
	}
	
}
