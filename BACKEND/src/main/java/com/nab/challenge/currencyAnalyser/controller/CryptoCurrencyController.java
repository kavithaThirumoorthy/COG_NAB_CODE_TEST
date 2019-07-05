package com.nab.challenge.currencyAnalyser.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nab.challenge.currencyAnalyser.dto.CryptoCurrTradeDetailsDto;
import com.nab.challenge.currencyAnalyser.dto.CryptoCurrencyResponseDto;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
import com.nab.challenge.currencyAnalyser.service.CryptoCurrencyService;
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class CryptoCurrencyController {
	Logger logger = LoggerFactory.getLogger(CryptoCurrencyController.class);
	@Autowired
	private CryptoCurrencyService cryptoCurrencyService;

	@GetMapping("/cryptoCurrency/baseCurrencies")
	public List<CryptoCurrency> getAllCryptoCurrency() {	
		List<CryptoCurrency> cryptoCurrencyList = cryptoCurrencyService.getAllCryptoCurrency();
		if (CollectionUtils.isNotEmpty(cryptoCurrencyList)) {
			logger.info("getAllCryptoCurrency::cryptoCurrency list Size =>{}", cryptoCurrencyList.size());
			return cryptoCurrencyList;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency Information not found in DataBase ");
		}
	}
	
	@GetMapping("/cryptoCurrency/currencyProfitDetails/{currencyId}")
	public CryptoCurrencyResponseDto getCurrencyDetailsAndProfit(@PathVariable long currencyId) {
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList= cryptoCurrencyService.getInfoForGivenCurrency(currencyId);
		if (CollectionUtils.isNotEmpty(cryptoCurrTradeDetailsList)) {
			logger.info("getCurrencyDetailsAndProfit::cryptoCurrTradeDetailsList list Size =>{}", cryptoCurrTradeDetailsList.size());
			return cryptoCurrencyService.getProfitForGivenCurrency(cryptoCurrTradeDetailsList);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency Information not found in DataBase ");
		}		
	}
	
	@GetMapping("/cryptoCurrency/currencyDetails/{currencyId}")
	public List<CryptoCurrTradeDetailsDto> getCurrencyDetails(@PathVariable long currencyId) {
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList= cryptoCurrencyService.getInfoForGivenCurrency(currencyId);
		if (CollectionUtils.isNotEmpty(cryptoCurrTradeDetailsList)) {
			logger.info("getCurrencyDetailsAndProfit::cryptoCurrTradeDetailsList list Size =>{}", cryptoCurrTradeDetailsList.size());
			return cryptoCurrencyService.getDetailsForGivenCurrency(cryptoCurrTradeDetailsList);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency Information not found in DataBase ");
		}		
	}
	
}
