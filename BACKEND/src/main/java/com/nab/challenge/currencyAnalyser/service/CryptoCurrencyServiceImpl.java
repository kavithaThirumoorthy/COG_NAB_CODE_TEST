package com.nab.challenge.currencyAnalyser.service;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.challenge.currencyAnalyser.dto.CryptoCurrTradeDetailsDto;
import com.nab.challenge.currencyAnalyser.dto.CryptoCurrencyResponseDto;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;
import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
import com.nab.challenge.currencyAnalyser.repository.CryptoCurrTradeDetailsRepo;
import com.nab.challenge.currencyAnalyser.repository.CryptoCurrencyRepo;
import com.nab.challenge.currencyAnalyser.util.CurrencyAnalyserConstant;

@Service
/**
 * This is service implementation
 * @author Kavitha
 *
 */
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

	Logger logger = LoggerFactory.getLogger(CryptoCurrencyServiceImpl.class);
	
	@Autowired
	private CryptoCurrencyRepo cryptoCurrencyRepository;
	@Autowired
	private CryptoCurrTradeDetailsRepo cryptoCurrTradeDetailsRepo;
	
	@Override
	/**
	 * This method provide list CryptoCurrTradeDetails 
	 * @param currencyId
	 * @return List<CryptoCurrTradeDetails> 
	 */
	public List<CryptoCurrTradeDetails> getInfoForGivenCurrency(long cryptoCurrencyID) {
		return cryptoCurrTradeDetailsRepo.getInfoForGivenCurrency(cryptoCurrencyID);
	}

	@Override
	/** This method calculates the profit advice information
	 * @param List<CryptoCurrTradeDetails>
	 * @return CryptoCurrencyResponseDto
	 */
	public CryptoCurrencyResponseDto getProfitForGivenCurrency(List<CryptoCurrTradeDetails> cryptoCurrencyList) {
		CryptoCurrencyResponseDto cryptoCurrencyResponseObj = new CryptoCurrencyResponseDto();	
		cryptoCurrencyResponseObj.setCryptoCurrTradeDetailsDto(mapCryptoCurrTradeDetailsToDto(cryptoCurrencyList));	
		if (cryptoCurrencyList.size() > 1) {
			cryptoCurrencyResponseObj.setProfitCalculationAvailable(true);
			List<CryptoCurrTradeDetails> sortedBasedOnTime=sortCryptoCurrTradeDetailsBasedOnTime(cryptoCurrencyList);
			cryptoCurrencyResponseObj.setBuyingRate(CurrencyAnalyserConstant.DOLLOR_SYMBOL + sortedBasedOnTime.get(0).getPrice());
			cryptoCurrencyResponseObj.setBuyingTime(getFormatedTradeTime(sortedBasedOnTime.get(0).getTradeTime()));
			cryptoCurrencyResponseObj.setTradeDate(cryptoCurrencyResponseObj.getCryptoCurrTradeDetailsDto().get(0).getTradeDate());
			calculMaxProfitSellingTime(cryptoCurrencyList, cryptoCurrencyResponseObj);
		}
		return cryptoCurrencyResponseObj;
	}
	@Override
	/** This method provides currency list basic information
	 * @return List<CryptoCurrency>
	 */
	public List<CryptoCurrency> getAllCryptoCurrency() {
		return cryptoCurrencyRepository.findAll();
	}

	@Override
	/** This method provides currency list transaction  information
	 * @return List<CryptoCurrTradeDetails>
	 */
	public List<CryptoCurrTradeDetails> getAllListCryptoCurrTradeDetails() {
		return cryptoCurrTradeDetailsRepo.findAll();
	}
	/**
	 * This method is called for calculating profit advice selling information
	 * @param List<CryptoCurrTradeDetails>
	 * @param cryptoCurrencyResponseObj
	 * @return CryptoCurrencyResponseDto
	 */
	private CryptoCurrencyResponseDto calculMaxProfitSellingTime(List<CryptoCurrTradeDetails> cryptoCurrencyList, CryptoCurrencyResponseDto cryptoCurrencyResponseObj) {
		double buyingRate=cryptoCurrencyList.get(0).getPrice();
		cryptoCurrencyList.remove(0);
		List<CryptoCurrTradeDetails> cryptoCurrencySortedList = sortCryptoCurrTradeDetailsBasedOnPrice(cryptoCurrencyList);	
		cryptoCurrencyResponseObj.setSellingRate(CurrencyAnalyserConstant.DOLLOR_SYMBOL + cryptoCurrencyList.get(cryptoCurrencySortedList.size()-1).getPrice());
		cryptoCurrencyResponseObj.setSellingTime(getFormatedTradeTime( cryptoCurrencySortedList.get(cryptoCurrencySortedList.size()-1).getTradeTime()));
		cryptoCurrencyResponseObj.setProfit(CurrencyAnalyserConstant.DOLLOR_SYMBOL+CurrencyAnalyserConstant.TWO_DECIMAL.format((cryptoCurrencyList.get(cryptoCurrencySortedList.size()-1).getPrice()-buyingRate)));
		return cryptoCurrencyResponseObj;
	}
/**
 * This method provide mapping between entity and DTO
 * @param List<CryptoCurrTradeDetails> 
 * @return List<CryptoCurrTradeDetailsDto>
 */
	private List<CryptoCurrTradeDetailsDto> mapCryptoCurrTradeDetailsToDto(List<CryptoCurrTradeDetails> cryptoCurrencyList) {
		List<CryptoCurrTradeDetailsDto> cryptoCurrTradeDetailsDtoList = cryptoCurrencyList.stream().map(currencyVal -> {			
			CryptoCurrTradeDetailsDto cryptoCurrTradeDetailsDto= new CryptoCurrTradeDetailsDto();
			cryptoCurrTradeDetailsDto.setId(currencyVal.getId());
			cryptoCurrTradeDetailsDto.setCurrencyName(currencyVal.getCryptoCurrency().getName());
			cryptoCurrTradeDetailsDto.setTradeDate(CurrencyAnalyserConstant.DATE_ONLY_FORMAT.format(currencyVal.getTradeDate()));
			cryptoCurrTradeDetailsDto.setTradeTime(getFormatedTradeTime(currencyVal.getTradeTime()));
			cryptoCurrTradeDetailsDto.setPrice(CurrencyAnalyserConstant.DOLLOR_SYMBOL +currencyVal.getPrice());
            return cryptoCurrTradeDetailsDto;
        }).collect(Collectors.toList());
		return cryptoCurrTradeDetailsDtoList;
	}

	/**
	 * This method formats the time for UI display
	 * @param tradeTime
	 * @return String
	 */
	private String getFormatedTradeTime(String tradeTime) {

		try {
			if (tradeTime.length() != CurrencyAnalyserConstant.MAX_TIME_DIGIT) {
				tradeTime = CurrencyAnalyserConstant.ZERO_APPEND + tradeTime;
			}
			Date timeOnly = CurrencyAnalyserConstant.TIME_ONLY_FORMAT.parse(tradeTime);
			return CurrencyAnalyserConstant.TIME_ONLY_FORMAT_AMPM.format(timeOnly);

		} catch (ParseException e) {
			logger.error("Error in parsing the given trade time => {}", tradeTime);
		}
		return tradeTime;
		
	}
	/**
	 * this method sorts List<CryptoCurrTradeDetails> based on price ascending order
	 * @param  List<CryptoCurrTradeDetails>
	 * @return List<CryptoCurrTradeDetails>
	 */

	private List<CryptoCurrTradeDetails> sortCryptoCurrTradeDetailsBasedOnPrice(List<CryptoCurrTradeDetails> cryptoCurrencyList) {
		cryptoCurrencyList.sort(new Comparator<CryptoCurrTradeDetails>() {
			@Override
			public int compare(CryptoCurrTradeDetails cur1, CryptoCurrTradeDetails cur2) {
				double delta = cur1.getPrice() - cur2.getPrice();
				if (delta > 0)
					return 1;
				if (delta < 0)
					return -1;
				return 0;
			}
		});

		return cryptoCurrencyList;

	}

	/**
	 * this method sorts List<CryptoCurrTradeDetails> based on time ascending order
	 * @param cryptoCurrencyList
	 * @return
	 */
	private static List<CryptoCurrTradeDetails> sortCryptoCurrTradeDetailsBasedOnTime(List<CryptoCurrTradeDetails> cryptoCurrencyList) {
		cryptoCurrencyList.sort(new Comparator<CryptoCurrTradeDetails>() {
			@Override
			public int compare(CryptoCurrTradeDetails cur1, CryptoCurrTradeDetails cur2) {
				int delta = Integer.parseInt(cur1.getTradeTime())  - Integer.parseInt(cur2.getTradeTime()) ;
				if (delta > 0)
					return 1;
				if (delta < 0)
					return -1;
				return 0;
			}
		});

		return cryptoCurrencyList;

	}


}
