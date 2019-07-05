package com.nab.challenge.currencyAnalyser.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CRYPTO_CURRENCY_TRADE_DETAILS")
public class CryptoCurrTradeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TRADE_DATE")
	private Date tradeDate;
	@Column(name="TRADE_TIME")
	private String tradeTime;
	@Column(name="PRICE")
	private double price;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CURRENCY_ID")
	private CryptoCurrency cryptoCurrency;

	public CryptoCurrTradeDetails( Date tradeDate, String tradeTime, double price,CryptoCurrency cryptoCurrency) {
		this.tradeDate = tradeDate;
		this.tradeTime = tradeTime;
		this.cryptoCurrency = cryptoCurrency;
		this.price = price;
	}

}
