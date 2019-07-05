package com.nab.challange.currencyAnalyser.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import com.nab.challenge.currencyAnalyser.controller.CryptoCurrencyController;
import com.nab.challenge.currencyAnalyser.dto.CryptoCurrTradeDetailsDto;
import com.nab.challenge.currencyAnalyser.dto.CryptoCurrencyResponseDto;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
import com.nab.challenge.currencyAnalyser.service.CryptoCurrencyService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
public class CryptoCurrencyControllerTest {

	@InjectMocks
	private CryptoCurrencyController cryptoCurrencyController;

	@Mock
	CryptoCurrencyService cryptoCurrencyService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(cryptoCurrencyController).build();
	}

	@Test
	public void testGetAllCryptoCurrency_shouldReturnNotFoundWhenListEmpty() throws Exception {
		List<CryptoCurrency> cryptoCurrencyList =null;
		when(cryptoCurrencyService.getAllCryptoCurrency()).thenReturn(cryptoCurrencyList);		 
        mockMvc.perform(get("/cryptoCurrency/baseCurrencies"))
                .andExpect(status().isNotFound());
        verify(cryptoCurrencyService, times(1)).getAllCryptoCurrency();
        verifyNoMoreInteractions(cryptoCurrencyService);
	}
	
	@Test
	public void testGetAllCryptoCurrency_shouldReturnStatusOKWhenListNonEmpty() throws Exception {
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
		when(cryptoCurrencyService.getAllCryptoCurrency()).thenReturn(cryptoCurrencyList);		 
        mockMvc.perform(get("/cryptoCurrency/baseCurrencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("BTC")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("ETH")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("LTC")));
 
        verify(cryptoCurrencyService, times(1)).getAllCryptoCurrency();
        verifyNoMoreInteractions(cryptoCurrencyService);
	}
	
	@Test
	public void testGetCurrencyDetailsAndProfit_shouldReturnStatusNotFoundWhenListEmpty() throws Exception {
		CryptoCurrencyResponseDto cryptoCurrencyResponseDto= new CryptoCurrencyResponseDto();
		List<CryptoCurrTradeDetailsDto> temp =new ArrayList<>();
		CryptoCurrTradeDetailsDto cryptoCurrTradeDetailsDto= new CryptoCurrTradeDetailsDto();
		cryptoCurrTradeDetailsDto.setId((long)1);
		cryptoCurrTradeDetailsDto.setCurrencyName("BTC");
		cryptoCurrTradeDetailsDto.setTradeDate("2018/05/07");
		temp.add(cryptoCurrTradeDetailsDto);
		cryptoCurrencyResponseDto.setCryptoCurrTradeDetailsDto(temp);
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList= new ArrayList<>();
		when(cryptoCurrencyService.getInfoForGivenCurrency((long)1)).thenReturn(cryptoCurrTradeDetailsList);
		when(cryptoCurrencyService.getProfitForGivenCurrency(cryptoCurrTradeDetailsList)).thenReturn(cryptoCurrencyResponseDto);		 
        mockMvc.perform(get("/cryptoCurrency/currencyProfitDetails/1"))
                .andExpect(status().isNotFound());               
 
       verify(cryptoCurrencyService, times(1)).getInfoForGivenCurrency((long)1);
        verify(cryptoCurrencyService, times(0)).getProfitForGivenCurrency(cryptoCurrTradeDetailsList);
        verifyNoMoreInteractions(cryptoCurrencyService);
	}
	@Test
	public void testGetCurrencyDetailsAndProfit_shouldReturnStatusOKWhenListNonEmpty() throws Exception {
		CryptoCurrencyResponseDto cryptoCurrencyResponseDto= new CryptoCurrencyResponseDto();
		List<CryptoCurrTradeDetailsDto> temp =new ArrayList<>();
		CryptoCurrTradeDetailsDto cryptoCurrTradeDetailsDto= new CryptoCurrTradeDetailsDto();
		cryptoCurrTradeDetailsDto.setId((long)1);
		cryptoCurrTradeDetailsDto.setCurrencyName("BTC");
		cryptoCurrTradeDetailsDto.setTradeDate("2018/05/07");
		temp.add(cryptoCurrTradeDetailsDto);
		cryptoCurrencyResponseDto.setCryptoCurrTradeDetailsDto(temp);
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList= new ArrayList<>();
		CryptoCurrTradeDetails cryptoCurrTradeDetails=new CryptoCurrTradeDetails();
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails);
		when(cryptoCurrencyService.getInfoForGivenCurrency((long)1)).thenReturn(cryptoCurrTradeDetailsList);
		when(cryptoCurrencyService.getProfitForGivenCurrency(cryptoCurrTradeDetailsList)).thenReturn(cryptoCurrencyResponseDto);		 
        mockMvc.perform(get("/cryptoCurrency/currencyProfitDetails/1"))
                .andExpect(status().isOk());               
 
       verify(cryptoCurrencyService, times(1)).getInfoForGivenCurrency((long)1);
        verify(cryptoCurrencyService, times(1)).getProfitForGivenCurrency(cryptoCurrTradeDetailsList);
        verifyNoMoreInteractions(cryptoCurrencyService);
	}
}
