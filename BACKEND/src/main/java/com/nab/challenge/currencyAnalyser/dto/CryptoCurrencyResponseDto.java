package com.nab.challenge.currencyAnalyser.dto;

import java.util.List;
/**
 * This is the DTO which carries information from back end to front end
 * @author Kavitha
 *
 */
public class CryptoCurrencyResponseDto {

	String buyingRate;
	String sellingRate;
	String buyingTime;
	String sellingTime;
	String profit;
	String tradeDate;

	List<CryptoCurrTradeDetailsDto> cryptoCurrTradeDetailsDto;
	
	boolean profitCalculationAvailable;
	public boolean isProfitCalculationAvailable() {
		return profitCalculationAvailable;
	}

	public void setProfitCalculationAvailable(boolean profitCalculationAvailable) {
		this.profitCalculationAvailable = profitCalculationAvailable;
	}

	public String getBuyingRate() {
		return buyingRate;
	}

	public List<CryptoCurrTradeDetailsDto> getCryptoCurrTradeDetailsDto() {
		return cryptoCurrTradeDetailsDto;
	}

	public void setCryptoCurrTradeDetailsDto(List<CryptoCurrTradeDetailsDto> cryptoCurrTradeDetailsDto) {
		this.cryptoCurrTradeDetailsDto = cryptoCurrTradeDetailsDto;
	}

	public void setBuyingRate(String buyingRate) {
		this.buyingRate = buyingRate;
	}

	public String getSellingRate() {
		return sellingRate;
	}

	public void setSellingRate(String sellingRate) {
		this.sellingRate = sellingRate;
	}

	public String getBuyingTime() {
		return buyingTime;
	}

	public void setBuyingTime(String buyingTime) {
		this.buyingTime = buyingTime;
	}

	public String getSellingTime() {
		return sellingTime;
	}

	public void setSellingTime(String sellingTime) {
		this.sellingTime = sellingTime;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
}
