package com.nab.challenge.currencyAnalyser.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nab.challenge.currencyAnalyser.dto.CryptoCurrTradeDetailsDto;
import com.nab.challenge.currencyAnalyser.dto.CryptoCurrencyResponseDto;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
import com.nab.challenge.currencyAnalyser.repository.CryptoCurrTradeDetailsRepo;
import com.nab.challenge.currencyAnalyser.repository.CryptoCurrencyRepo;

/**
 * This class tests CryptoCurrencyServiceImpl
 * 
 * @author kavitha
 *
 */

public class CryptoCurrencyServiceTest {

	@InjectMocks
	CryptoCurrencyServiceImpl cryptoCurrencyService;
	@Mock
	private CryptoCurrTradeDetailsRepo cryptoCurrTradeDetailsRepo;
	@Mock
	private CryptoCurrencyRepo cryptoCurrencyRepository;
	List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList = new ArrayList<>();
	List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsListOne = new ArrayList<>();
	List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsListforTimeSort = new ArrayList<>();

	@Before
	/**
	 * This method takes care of initial set up of data
	 * @throws ParseException
	 */
	public void setup() throws ParseException {
		MockitoAnnotations.initMocks(this);
		DateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyyMMdd");

		CryptoCurrency cryptoCurrency = new CryptoCurrency();
		cryptoCurrency.setId((long) 1);
		cryptoCurrency.setName("BTC");
		CryptoCurrTradeDetails cryptoCurrTradeDetails = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetails.setCryptoCurrency(cryptoCurrency);
		cryptoCurrTradeDetails.setId((long) 1);
		cryptoCurrTradeDetails.setPrice(34.98);
		cryptoCurrTradeDetails.setTradeDate(DATE_ONLY_FORMAT.parse("20180507"));
		cryptoCurrTradeDetails.setTradeTime("0915");
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails);
		CryptoCurrTradeDetails cryptoCurrTradeDetails2 = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetails2.setCryptoCurrency(cryptoCurrency);
		cryptoCurrTradeDetails2.setId((long) 2);
		cryptoCurrTradeDetails2.setPrice(36.13);
		cryptoCurrTradeDetails2.setTradeDate(DATE_ONLY_FORMAT.parse("20180507"));
		cryptoCurrTradeDetails2.setTradeTime("1045");
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails2);
		CryptoCurrTradeDetails cryptoCurrTradeDetails3 = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetails3.setCryptoCurrency(cryptoCurrency);
		cryptoCurrTradeDetails3.setId((long) 3);
		cryptoCurrTradeDetails3.setPrice(37.01);
		cryptoCurrTradeDetails3.setTradeDate(DATE_ONLY_FORMAT.parse("20180507"));
		cryptoCurrTradeDetails3.setTradeTime("1230");
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails3);
		CryptoCurrTradeDetails cryptoCurrTradeDetails4 = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetails4.setCryptoCurrency(cryptoCurrency);
		cryptoCurrTradeDetails4.setId((long) 4);
		cryptoCurrTradeDetails4.setPrice(35.98);
		cryptoCurrTradeDetails4.setTradeDate(DATE_ONLY_FORMAT.parse("20180507"));
		cryptoCurrTradeDetails4.setTradeTime("1400");
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails4);
		CryptoCurrTradeDetails cryptoCurrTradeDetails5 = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetails5.setCryptoCurrency(cryptoCurrency);
		cryptoCurrTradeDetails5.setId((long) 5);
		cryptoCurrTradeDetails5.setPrice(33.56);
		cryptoCurrTradeDetails5.setTradeDate(DATE_ONLY_FORMAT.parse("20180507"));
		cryptoCurrTradeDetails5.setTradeTime("1530");
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails5);
		cryptoCurrTradeDetailsListOne.add(cryptoCurrTradeDetails5);
		cryptoCurrTradeDetailsListforTimeSort.add(cryptoCurrTradeDetails3);
		cryptoCurrTradeDetailsListforTimeSort.add(cryptoCurrTradeDetails2);
		cryptoCurrTradeDetailsListforTimeSort.add(cryptoCurrTradeDetails4);
		cryptoCurrTradeDetailsListforTimeSort.add(cryptoCurrTradeDetails);

	}

	@Test
	/**
	 * this method test getInfoForGivenCurrency() method of CryptoCurrencyServiceImpl
	 */
	public void test_getInfoForGivenCurrency() {
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList = new ArrayList<>();
		CryptoCurrTradeDetails cryptoCurrTradeDetails = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails);
		when(cryptoCurrTradeDetailsRepo.getInfoForGivenCurrency((long) 1)).thenReturn(cryptoCurrTradeDetailsList);
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsTemp = cryptoCurrencyService
				.getInfoForGivenCurrency((long) 1);
		assertThat(cryptoCurrTradeDetailsList, is(cryptoCurrTradeDetailsTemp));
	}

	@Test
	/**
	 * this method test getProfitForGivenCurrency() method of CryptoCurrencyServiceImpl with list size is one.
	 *  It should not call profit calculation method.
	 * @throws ParseException
	 */
	public void test_getProfitForGivenCurrency_WhenListSizeLessThanOne() throws ParseException {
		CryptoCurrencyResponseDto cryptoCurrencyResponseObj = cryptoCurrencyService
				.getProfitForGivenCurrency(cryptoCurrTradeDetailsListOne);
		assertFalse(cryptoCurrencyResponseObj.isProfitCalculationAvailable());
	}

	@Test
	/**
	 * this method test getProfitForGivenCurrency() method of CryptoCurrencyServiceImpl with list size more one. 
	 * It should  call profit calculation method for creating advice
	 * @throws ParseException
	 */
	public void test_getProfitForGivenCurrency_WhenListSizeMoreThanOne() throws ParseException {
		CryptoCurrencyResponseDto cryptoCurrencyResponseObj = cryptoCurrencyService
				.getProfitForGivenCurrency(cryptoCurrTradeDetailsList);
		assertTrue(cryptoCurrencyResponseObj.isProfitCalculationAvailable());
		assertEquals("$34.98", cryptoCurrencyResponseObj.getBuyingRate());
		assertEquals(5, cryptoCurrencyResponseObj.getCryptoCurrTradeDetailsDto().size());
	}

	@Test
	/**
	 *  this method test getAllCryptoCurrency() method of CryptoCurrencyServiceImpl 
	 */
	public void test_getAllCryptoCurrency() {
		List<CryptoCurrency> cryptoCurrencyList = new ArrayList<>();
		CryptoCurrency currency1 = new CryptoCurrency();
		currency1.setName("BTC");
		currency1.setId((long) 1);
		CryptoCurrency currency2 = new CryptoCurrency();
		currency2.setName("ETH");
		currency2.setId((long) 2);
		CryptoCurrency currency3 = new CryptoCurrency();
		currency3.setName("LTC");
		currency3.setId((long) 3);
		cryptoCurrencyList.add(currency1);
		cryptoCurrencyList.add(currency2);
		cryptoCurrencyList.add(currency3);
		when(cryptoCurrencyRepository.findAll()).thenReturn(cryptoCurrencyList);
		List<CryptoCurrency> temp = cryptoCurrencyService.getAllCryptoCurrency();
		assertThat(cryptoCurrencyList, is(temp));
	}

	@Test
	/**
	 * this method test getAllListCryptoCurrTradeDetails() method of CryptoCurrencyServiceImpl 
	 */
	public void test_getAllListCryptoCurrTradeDetails() {
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsList = new ArrayList<>();
		CryptoCurrTradeDetails cryptoCurrTradeDetails = new CryptoCurrTradeDetails();
		cryptoCurrTradeDetailsList.add(cryptoCurrTradeDetails);
		when(cryptoCurrTradeDetailsRepo.findAll()).thenReturn(cryptoCurrTradeDetailsList);
		List<CryptoCurrTradeDetails> cryptoCurrTradeDetailsTemp = cryptoCurrencyService
				.getAllListCryptoCurrTradeDetails();
		assertThat(cryptoCurrTradeDetailsList, is(cryptoCurrTradeDetailsTemp));
	}

	@Test
	/**
	 * this method test getFormatedTradeTimemethod of CryptoCurrencyServiceImpl.
	 * Since this method is  private method, it is tested with reflection concept.
	 */
	public void getFormatedTradeTime() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		CryptoCurrencyResponseDto cryptoCurrencyResponseObj = new CryptoCurrencyResponseDto();
		Method method = CryptoCurrencyServiceImpl.class.getDeclaredMethod("calculMaxProfitSellingTime", List.class,
				CryptoCurrencyResponseDto.class);
		method.setAccessible(true);
		cryptoCurrencyResponseObj = (CryptoCurrencyResponseDto) method.invoke(cryptoCurrencyService,
				cryptoCurrTradeDetailsList, cryptoCurrencyResponseObj);
		assertEquals("$37.01", cryptoCurrencyResponseObj.getSellingRate());
		assertEquals("$2.03", cryptoCurrencyResponseObj.getProfit());
	}

	@Test
	/**
	 * this method test mapCryptoCurrTradeDetailsToDto of CryptoCurrencyServiceImpl.
	 * Since this method is  private method, it is tested with reflection concept.
	 */
	public void test_mapCryptoCurrTradeDetailsToDto() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = CryptoCurrencyServiceImpl.class.getDeclaredMethod("mapCryptoCurrTradeDetailsToDto", List.class);
		method.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<CryptoCurrTradeDetailsDto> temp = (List<CryptoCurrTradeDetailsDto>) method.invoke(cryptoCurrencyService,
				cryptoCurrTradeDetailsList);
		assertEquals(5, temp.size());
		assertEquals("BTC", temp.get(0).getCurrencyName());
	}

	@Test
	/**
	 * this method test getFormatedTradeTime of CryptoCurrencyServiceImpl.
	 * Since this method is  private method, it is tested with reflection concept.
	 */
	public void test_getFormatedTradeTime() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Method method = CryptoCurrencyServiceImpl.class.getDeclaredMethod("getFormatedTradeTime", String.class);
		method.setAccessible(true);
		String dateTemp = (String) method.invoke(cryptoCurrencyService, "2005");
		assertEquals("08:05 PM", dateTemp);
	}

	@Test
	/**
	 * this method test sortCryptoCurrTradeDetailsBasedOnPrice of CryptoCurrencyServiceImpl.
	 * Since this method is  private method, it is tested with reflection concept.
	 */
	public void test_sortCryptoCurrTradeDetailsBasedOnPrice() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = CryptoCurrencyServiceImpl.class.getDeclaredMethod("sortCryptoCurrTradeDetailsBasedOnPrice", List.class);
		method.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<CryptoCurrTradeDetails> temp = (List<CryptoCurrTradeDetails>) method.invoke(cryptoCurrencyService,
				cryptoCurrTradeDetailsList);
		assertEquals(37.01, temp.get(4).getPrice(), 0.001);
	}
	
	@Test
	/**
	 * this method test sortCryptoCurrTradeDetailsBasedOnPrice of CryptoCurrencyServiceImpl.
	 * Since this method is  private method, it is tested with reflection concept.
	 */
	public void sortCryptoCurrTradeDetailsBasedOnTime() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = CryptoCurrencyServiceImpl.class.getDeclaredMethod("sortCryptoCurrTradeDetailsBasedOnTime", List.class);
		method.setAccessible(true);
		@SuppressWarnings("unchecked")
		List<CryptoCurrTradeDetails> temp = (List<CryptoCurrTradeDetails>) method.invoke(cryptoCurrencyService,
				cryptoCurrTradeDetailsListforTimeSort);
		assertEquals("0915", temp.get(0).getTradeTime());
	}
}