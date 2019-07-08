package com.nab.challenge.currencyAnalyser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.nab.challenge.currencyAnalyser.model.CryptoCurrency;
@Repository
/**
 * This class is a Repository for CryptoCurrency
 * @author Kavitha
 *
 */
public interface CryptoCurrencyRepo extends JpaRepository<CryptoCurrency, Long>{
	


}
