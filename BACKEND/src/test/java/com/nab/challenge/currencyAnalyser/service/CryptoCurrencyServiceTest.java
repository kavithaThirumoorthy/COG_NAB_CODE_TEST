package com.nab.challenge.currencyAnalyser.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
import com.nab.challenge.currencyAnalyser.repository.CryptoCurrTradeDetailsRepo;
import com.nab.challenge.currencyAnalyser.repository.CryptoCurrencyRepo;




public class CryptoCurrencyServiceTest {

	
	@InjectMocks
	CryptoCurrencyServiceImpl cryptoCurrencyService;
	@Mock
	private CryptoCurrTradeDetailsRepo cryptoCurrTradeDetailsRepo;
	@Mock
	private CryptoCurrencyRepo cryptoCurrencyRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}
	
	@Test
	public void test_getInfoForGivenCurrency() {
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList= new ArrayList<>();
		CryptoCurrTradeDetails cryptoCurrTradeDetails=new CryptoCurrTradeDetails();
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails);
		when(cryptoCurrTradeDetailsRepo.getInfoForGivenCurrency((long) 1)).thenReturn(cryptoCurrTradeDetailsList);
		List<CryptoCurrTradeDetails>  cryptoCurrTradeDetailsTemp = cryptoCurrencyService.getInfoForGivenCurrency((long) 1);
		assertThat(cryptoCurrTradeDetailsList, is(cryptoCurrTradeDetailsTemp));
	}
	@Test
	public void test_getAllCryptoCurrency() {
		List<CryptoCurrency> cryptoCurrencyList = new ArrayList<>();
		CryptoCurrency currency1= new CryptoCurrency();
		currency1.setName("BTC");
		currency1.setId((long) 1);
		CryptoCurrency currency2= new CryptoCurrency();
		currency2.setName("ETH");
		currency2.setId((long) 2);
		CryptoCurrency currency3= new CryptoCurrency();
		currency3.setName("LTC");
		currency3.setId((long) 3);
		cryptoCurrencyList.add(currency1);
		cryptoCurrencyList.add(currency2);
		cryptoCurrencyList.add(currency3);
		when(cryptoCurrencyRepository.findAll()).thenReturn(cryptoCurrencyList);
		List<CryptoCurrency>  temp = cryptoCurrencyService.getAllCryptoCurrency();
		assertThat(cryptoCurrencyList, is(temp));
	}
	@Test
	public void test_getAllListCryptoCurrTradeDetails() {
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList= new ArrayList<>();
		CryptoCurrTradeDetails cryptoCurrTradeDetails=new CryptoCurrTradeDetails();
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails);
		when(cryptoCurrTradeDetailsRepo.findAll()).thenReturn(cryptoCurrTradeDetailsList);
		List<CryptoCurrTradeDetails>  cryptoCurrTradeDetailsTemp = cryptoCurrencyService.getAllListCryptoCurrTradeDetails();
		assertThat(cryptoCurrTradeDetailsList, is(cryptoCurrTradeDetailsTemp));
	}
}
