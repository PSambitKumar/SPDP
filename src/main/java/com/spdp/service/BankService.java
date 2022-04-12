package com.spdp.service;

import java.util.List;

import com.spdp.bean.BankBean;
import com.spdp.bean.BankMasterBean;



public interface BankService {

	List<BankBean> getDetailsByBankId();
	List<BankMasterBean> getDetailsOfBank();
	
	
	//List<SubdivisionBean> getSubdivisionDetails();
	
	
	void deleteBankByBranchName(String bankbranchName);
	String saveBankDetails(BankBean bkBean) ;
	//List<DistSubdivisionBean> getDistrictAndSubdivisionDetails();
	BankBean getBankDetailsForUpdate(Long id);

	void updateBankDetails(BankBean rdcObj);
	List<BankBean> getBankMasterDetailsFilteredByDistrictNames(Long did);
	List<BankBean> getBankMasterDetailsFilteredByBankNames(Long bid);
	List<BankBean> getBankMasterDetailsFilteredByBankBranchNames(Long bbid);
	List<BankBean> bankMasterDetailsFilterByAllData(Long did, Long bid, Long bbid);
	List<BankBean> bankMasterDetailsFilterByDistAndBank(Long did, Long bid);
	List<BankBean> getBankBranchDetailsBybankId(Long id);

}
