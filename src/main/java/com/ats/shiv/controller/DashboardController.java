package com.ats.shiv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.shiv.model.ConsumptionReportData;
import com.ats.shiv.model.GetCurrStockRol;
import com.ats.shiv.model.GetCurrentStock;
import com.ats.shiv.model.GetItem;
import com.ats.shiv.model.GetPoHeader;
import com.ats.shiv.model.PoHeader;
import com.ats.shiv.model.indent.DashIndentDetails;
import com.ats.shiv.model.indent.GetIndents;
import com.ats.shiv.repository.ConsumptionReportRepository;
import com.ats.shiv.repository.GetCurrentStockHeaderResRepository;
import com.ats.shiv.repository.GetItemRepository;
import com.ats.shiv.repository.GetPoHeaderRepository;
import com.ats.shiv.repository.PoHeaderRepository;
import com.ats.shiv.repository.indent.GetIndentRepository;
import com.ats.shiv.repository.indent.IndentTransRepository;
import com.ats.shiv.repository.stock.GetCurrentStockHeaderRepository;

@RestController
public class DashboardController {

	@Autowired
	GetIndentRepository getIndentRepository;
	
	@Autowired
	IndentTransRepository indentTransRepository;
	
	@Autowired
	GetPoHeaderRepository getPoHeaderRepository;
	
	@Autowired
	ConsumptionReportRepository consumptionReportRepository;
	
	@Autowired
	GetItemRepository getItemRepository;
	
	@Autowired
	GetCurrentStockHeaderRepository getCurrentStockHeaderRepository;
	
	@Autowired
	GetCurrentStockHeaderResRepository getCurrentStockHeaderResRepository;
	
	
	@RequestMapping(value = { "/getIndentList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetIndents> getIndentList(@RequestParam("status") List<Integer> status) {

		List<GetIndents> indentList = new ArrayList<GetIndents>();

		try {
            System.out.println(status);
			indentList = getIndentRepository.getIndentList(status);
			
			for(int i=0;i<indentList.size();i++)
			{System.err.println("indent List " + indentList.get(i).getIndMId()+"status "+status);
				List<DashIndentDetails> dashIndentDetailList =indentTransRepository.findByIndMIdAndIndDStatusIn(indentList.get(i).getIndMId(),status);
				System.err.println("indent List "+dashIndentDetailList.toString());
				indentList.get(i).setDashIndentDetailList(dashIndentDetailList);
			}

			System.err.println("indent List " + indentList.toString());
		} catch (Exception e) {

			System.err.println("Exception in getIndents Indent  " + e.getMessage());

			e.printStackTrace();

		}

		return indentList;

	}
	
	@RequestMapping(value = { "/getPoHeaderDashList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPoHeader> getPOHeaderList(@RequestParam("poType") int poType,@RequestParam("status") int status) {

		List<GetPoHeader> poHeaderList = new ArrayList<GetPoHeader>();

		try {

			 
			poHeaderList = getPoHeaderRepository.findByPoTypeAndPoStatusAndDelStatus(poType, status);
				

		} catch (Exception e) {
			
			System.err.println("Exception /getPOHeaderList @PurchaseOrderRestControlle ");

			e.printStackTrace();
			 
		}
		return poHeaderList;

	}
	/*@RequestMapping(value = { "/getConsumptionData" }, method = RequestMethod.POST)
	public @ResponseBody List<ConsumptionReportData> getConsumptionData(@RequestParam("poType") int poType,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate) {

		List<ConsumptionReportData> consumptionReportData = new ArrayList<ConsumptionReportData>();

		try {

			consumptionReportData = consumptionReportRepository.findByPoTypeAndDate(poType,fromDate,toDate);
				

		} catch (Exception e) {
			

			e.printStackTrace();
			 
		}
		return consumptionReportData;

	}*/
	@RequestMapping(value = { "/getItemsLessThanROLForDashB" }, method = RequestMethod.POST)
	public @ResponseBody List<GetCurrStockRol> getItemsLessThanROLForDashB(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		
		List<GetCurrStockRol> getCurrentStock = new ArrayList<GetCurrStockRol>();

		try {
			
			List<GetItem>  itemList = getItemRepository.getAllItems();

			getCurrentStock = getCurrentStockHeaderResRepository.getCurrentStockForDash(fromDate,toDate);
			
			for(int i = 0 ; i<itemList.size() ; i++)
			{
				for(int j = 0 ; j<getCurrentStock.size() ; j++)
				{ 
					if(itemList.get(i).getItemId()==getCurrentStock.get(j).getItemId())
					{
						getCurrentStock.get(j).setItemName(itemList.get(i).getItemDesc());
						getCurrentStock.get(j).setCatId(itemList.get(i).getCatId());
						getCurrentStock.get(j).setItemUom(itemList.get(i).getItemUom());
						getCurrentStock.get(j).setItemMaxLevel(itemList.get(i).getItemMaxLevel());
						getCurrentStock.get(j).setItemMinLevel(itemList.get(i).getItemMinLevel());
						if(itemList.get(i).getItemRodLevel()>(getCurrentStock.get(j).getOpeningStock()+getCurrentStock.get(j).getApproveQty()-
									getCurrentStock.get(j).getIssueQty()+getCurrentStock.get(j).getReturnIssueQty()-getCurrentStock.get(j).getDamageQty()-
									getCurrentStock.get(j).getGatepassQty()+getCurrentStock.get(j).getGatepassReturnQty()) && itemList.get(i).getItemRodLevel()>0)
							
						{
							getCurrentStock.get(j).setRolLevel(itemList.get(i).getItemRodLevel()); 
						}
						else
						{
							
							getCurrentStock.remove(j);
						}
						
						break;
					} 
				}
			}
 
		} catch (Exception e) {

			e.printStackTrace();

		}
		return getCurrentStock;

	}
	
}
