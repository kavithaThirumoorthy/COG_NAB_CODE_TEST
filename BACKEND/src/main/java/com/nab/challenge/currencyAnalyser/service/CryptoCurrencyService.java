package com.nab.challenge.currencyAnalyser.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.nab.challenge.currencyAnalyser.dto.CryptoCurrencyResponseDto;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
@Validated
/**
 * This is the service interface 
 * @author kavitha
 *
 */
public interface CryptoCurrencyService {

	List<CryptoCurrTradeDetails> getInfoForGivenCurrency(long cryptoCurrencyID);

	CryptoCurrencyResponseDto getProfitForGivenCurrency(List<CryptoCurrTradeDetails> cryptoCurrencyList);
	

	
	List<CryptoCurrency> getAllCryptoCurrency();
	
	List<CryptoCurrTradeDetails> getAllListCryptoCurrTradeDetails();

}
