package com.nab.challenge.currencyAnalyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nab.challenge.currencyAnalyser.model.CryptoCurrTradeDetails;

/**
 * This class is a Repository for CryptoCurrTradeDetails
 * 
 * @author kavitha
 *
 */

@Repository
public interface CryptoCurrTradeDetailsRepo extends JpaRepository<CryptoCurrTradeDetails, Long>{
	
	/**
	 * This is the method used for getting transaction information for a particular currency.
	 * @param cryptoCurrencyId
	 * @return
	 */
	@Query(value = "select currency.* from  CRYPTO_CURRENCY_TRADE_DETAILS currency where currency.CURRENCY_ID= ?1",  nativeQuery = true)
	 List<CryptoCurrTradeDetails> getInfoForGivenCurrency(Long cryptoCurrencyId);
}