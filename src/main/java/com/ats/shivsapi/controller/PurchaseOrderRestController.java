package com.ats.shivsapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.shivsapi.common.DateConvertor;
import com.ats.shivsapi.model.ErrorMessage;
import com.ats.shivsapi.model.GetPODetail;
import com.ats.shivsapi.model.GetPoDetailList;
import com.ats.shivsapi.model.GetPoHeaderList;
import com.ats.shivsapi.model.PoDetail;
import com.ats.shivsapi.model.PoHeader;
import com.ats.shivsapi.model.getqueryitems.GetPoQueryItem;
import com.ats.shivsapi.model.indent.IndentTrans;
import com.ats.shivsapi.repository.GetPoDetailListRepository;
import com.ats.shivsapi.repository.GetPoHeaderListRepository;
import com.ats.shivsapi.repository.ItemRepository;
import com.ats.shivsapi.repository.PoDetailRepository;
import com.ats.shivsapi.repository.PoHeaderRepository;
import com.ats.shivsapi.repository.getpodetail.GetPODetailRepo;
import com.ats.shivsapi.repository.indent.IndentTransRepo;
import com.ats.shivsapi.repository.queryitems.GetPoQueryItemRepo;

@RestController
public class PurchaseOrderRestController {

	@Autowired
	PoHeaderRepository poHeaderRepository;

	@Autowired
	PoDetailRepository poDetailRepository;

	@Autowired
	GetPODetailRepo getPODetailRepo;

	@Autowired
	GetPoHeaderListRepository getPoHeaderListRepository;

	@Autowired
	GetPoDetailListRepository getPoDetailListRepository;

	@Autowired
	IndentTransRepo indentTransRepo;
	
	@Autowired
	GetPoQueryItemRepo getPoQueryItemRepo;
	
	@Autowired
	ItemRepository itemRepository;

	@RequestMapping(value = { "/getPODetailList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPODetail> getPODetails(@RequestParam("poIdList") List<Integer> poIdList) {

		System.err.println("Inside getPODetailList ");
		List<GetPODetail> poDetailList = new ArrayList<GetPODetail>();

		try {

			poDetailList = getPODetailRepo.getPoDetailsByPoIds(poIdList);
			System.err.println("poDetailList  " + poDetailList.toString());

		} catch (Exception e) {

			System.err.println("Exception /getPODetailList @PurchaseOrderRestControlle ");

			e.printStackTrace();

		}
		return poDetailList;

	}

	// Sachin 12-07-2018
	@RequestMapping(value = { "/getPOHeaderList" }, method = RequestMethod.POST)
	public @ResponseBody List<PoHeader> getPOHeaderList(@RequestParam("vendId") int vendId,
			@RequestParam("delStatus") int delStatus, @RequestParam("poType") int poType,
			@RequestParam("statusList") List<Integer> statusList) {

		List<PoHeader> poHeaderList = new ArrayList<PoHeader>();

		try {

			poHeaderList = poHeaderRepository.findByVendIdAndDelStatusAndPoTypeAndPoStatusIn(vendId, delStatus, poType,
					statusList);

		} catch (Exception e) {

			System.err.println("Exception /getPOHeaderList @PurchaseOrderRestControlle ");

			e.printStackTrace();

		}
		return poHeaderList;

	}

	@RequestMapping(value = { "/savePoHeaderAndDetail" }, method = RequestMethod.POST)
	public @ResponseBody PoHeader savePoHeaderAndDetail(@RequestBody PoHeader poHeader) {

		PoHeader save = new PoHeader();

		try {

			save = poHeaderRepository.save(poHeader);

			for (int i = 0; i < poHeader.getPoDetailList().size(); i++) {
				poHeader.getPoDetailList().get(i).setPoId(save.getPoId());
				poHeader.getPoDetailList().get(i).setVendId(save.getVendId());
			}

			List<PoDetail> poDetailList = poDetailRepository.saveAll(poHeader.getPoDetailList());
			save.setPoDetailList(poDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return save;

	}

	@RequestMapping(value = { "/deletePo" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deletePo(@RequestParam("poId") int poId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {

			int delete = poHeaderRepository.delete(poId);
			if (delete != 0) {
				errorMessage.setError(false);
				errorMessage.setMessage("deleted");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("failed");

		}
		return errorMessage;

	}

	@RequestMapping(value = { "/getPoHeaderListBetweenDate" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPoHeaderList> getPoHeaderListBetweenDate(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		List<GetPoHeaderList> list = new ArrayList<GetPoHeaderList>();

		try {

			list = getPoHeaderListRepository.getPoHeaderListBetweenDate(fromDate, toDate);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}
	
	@RequestMapping(value = { "/getPoHeaderListForApprove" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPoHeaderList> getPoHeaderListForApprove(@RequestParam("status") List<Integer> status) {

		List<GetPoHeaderList> list = new ArrayList<GetPoHeaderList>();

		try {

			list = getPoHeaderListRepository.getPoHeaderListForApprove(status);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}

	@RequestMapping(value = { "/getPoHeaderAndDetailByHeaderId" }, method = RequestMethod.POST)
	public @ResponseBody GetPoHeaderList getPoHeaderAndDetailByHeaderId(@RequestParam("poId") int poId) {

		GetPoHeaderList getPoHeaderList = new GetPoHeaderList();

		try {

			getPoHeaderList = getPoHeaderListRepository.getPoHeaderAndDetailByHeaderId(poId);

			List<GetPoDetailList> poDetailList = getPoDetailListRepository.getDetail(poId);
			getPoHeaderList.setPoDetailList(poDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return getPoHeaderList;

	}

	@RequestMapping(value = { "/getPoHeaderListReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPoHeaderList> getPoHeaderListReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("vendorIdList") List<Integer> vendorIdList,
			@RequestParam("poTypeList") List<Integer> poTypeList, @RequestParam("poStatus") List<Integer> poStatus) {

		List<GetPoHeaderList> list = new ArrayList<GetPoHeaderList>();

		try {
			if (vendorIdList.contains(0) && poTypeList.contains(0) && poStatus.contains(-1)) {

				list = getPoHeaderListRepository.getPoHeaderListBetweenDate(fromDate, toDate);
			} else if (vendorIdList.contains(0) && poTypeList.contains(0) && !poStatus.contains(-1)) {

				list = getPoHeaderListRepository.getPoHeaderByStatus(fromDate, toDate, poStatus);
			} else if (vendorIdList.contains(0) && !poTypeList.contains(0) && !poStatus.contains(-1)) {

				list = getPoHeaderListRepository.getPoHeaderByStatusAndPoTypeList(fromDate, toDate, poStatus,
						poTypeList);
			} else if (!vendorIdList.contains(0) && poTypeList.contains(0) && !poStatus.contains(-1)) {

				list = getPoHeaderListRepository.getPoHeaderByVendorAndPoType(fromDate, toDate, vendorIdList,
						poTypeList);
			}

			else if (!vendorIdList.contains(0) && poTypeList.contains(0) && poStatus.contains(-1)) {

				list = getPoHeaderListRepository.getPoHeaderByVendor(fromDate, toDate, vendorIdList);
			}

			else if (!vendorIdList.contains(0) && !poTypeList.contains(0) && poStatus.contains(-1)) {

				list = getPoHeaderListRepository.getPoHeaderByVendorAndPoTypeList(fromDate, toDate, vendorIdList,
						poTypeList);
			}

			else {

				list = getPoHeaderListRepository.getPoHeaderByStatusAndPoTypeAndVendorList(fromDate, toDate, poStatus,
						poTypeList, vendorIdList);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}

	@RequestMapping(value = { "/deletePoItem" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateIndendPendingQty(@RequestParam("poDetailId") int poDetailId) {

		ErrorMessage errorMessage = new ErrorMessage();
		try {

			poDetailRepository.delete(poDetailId);

			errorMessage.setError(false);
			errorMessage.setMessage("update");

		} catch (Exception e) {

			errorMessage.setError(true);
			errorMessage.setMessage("failed");

			e.printStackTrace();

		}
		return errorMessage;

	}
	
	@RequestMapping(value = { "/getPreviousRecordOfPoItem" }, method = RequestMethod.POST)
	public @ResponseBody List<GetPoQueryItem> getPreviousRecordOfPoItem(@RequestParam("itemId") int itemId) {
 
		List<GetPoQueryItem> getPreviousRecordOfPoItem = new ArrayList<GetPoQueryItem>();

		try {
 
			getPreviousRecordOfPoItem = getPoQueryItemRepo.getPreviousRecordOfPoItem(itemId);

			System.err.println(" getPoQueryItem  List " + getPreviousRecordOfPoItem.toString());
		} catch (Exception e) {
 
			e.printStackTrace();

		}

		return getPreviousRecordOfPoItem;

	}
	
	@RequestMapping(value = { "/updateStatusWhileApprov" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateStatusWhileApprov(@RequestParam("poId") int poId,
			@RequestParam("poDetalId") List<Integer> poDetalId,@RequestParam("status") int status) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {

			int update = poHeaderRepository.updateStatusWhileApprov(poId,status); 
			
			/*for(int i=0 ; i<poDetalId.size() : i++)
			{
				
			}*/
			int updateDetail = poDetailRepository.updateStatusWhileApprov(poDetalId,status);
			
			errorMessage.setError(false);
			errorMessage.setMessage("approved");
			 
		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("failed");

		}
		return errorMessage;

	}
	
	@RequestMapping(value = { "/updateRateOfItemAfterApproveRate" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateRateOfItemAfterApproveRate(@RequestParam("itemId") int itemId,
			@RequestParam("rate") float rate ) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = itemRepository.updateRate(itemId, rate);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage(" Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		
		return errorMessage;

	}

}
