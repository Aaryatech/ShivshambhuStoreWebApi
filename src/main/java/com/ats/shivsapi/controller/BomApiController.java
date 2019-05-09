package com.ats.shivsapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.shivsapi.model.bom.GetReqBomDetail;
import com.ats.shivsapi.model.bom.GetReqBomHeader;
import com.ats.shivsapi.model.bom.ReqBomDetail;
import com.ats.shivsapi.model.bom.ReqBomHeader;
import com.ats.shivsapi.model.mrn.MrnDetail;
import com.ats.shivsapi.repository.bom.GetReqBomDetailRepo;
import com.ats.shivsapi.repository.bom.GetReqBomHeaderRepo;
import com.ats.shivsapi.repository.bom.ReqBomDetailRepo;
import com.ats.shivsapi.repository.bom.ReqBomHeaderRepo;


@RestController
public class BomApiController {
	
	@Autowired GetReqBomHeaderRepo getGetReqBomHeaderRepo;
	
	@Autowired GetReqBomDetailRepo getGetReqBomDetailRepo;
	@Autowired ReqBomDetailRepo reqBomDetailRepo;
	@Autowired ReqBomHeaderRepo reqBomHeaderRepo;
	
	@RequestMapping(value = { "/getReqBomHeaders" }, method = RequestMethod.POST)
	public @ResponseBody List<GetReqBomHeader> getReqBomHeaders(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		System.err.println("inside web api /getReqBomHeaders BomApiController");

		List<GetReqBomHeader> reqBomHeadList = new ArrayList<GetReqBomHeader>();

		try {

			reqBomHeadList = getGetReqBomHeaderRepo.getGetReqBomHeader(fromDate, toDate);

			System.err.println("reqBomHeadList List " + reqBomHeadList.toString());
		} catch (Exception e) {

			System.err.println("Exception in getReqBomHeaders BomApiController  " + e.getMessage());

			e.printStackTrace();

		}

		return reqBomHeadList;

	}
	
	@RequestMapping(value = { "/getReqBomDetails" }, method = RequestMethod.POST)
	public @ResponseBody List<GetReqBomDetail> getReqBomDetails(@RequestParam("bomReqId") int bomReqId) {

		System.err.println("inside web api /getReqBomDetails BomApiController");

		List<GetReqBomDetail> reqBomDetailList = new ArrayList<GetReqBomDetail>();

		try {

			reqBomDetailList = getGetReqBomDetailRepo.getGetReqBomDetail(bomReqId);

			System.err.println("reqBomDetailList List " + reqBomDetailList.toString());
		} catch (Exception e) {

			System.err.println("Exception in getReqBomDetails BomApiController  " + e.getMessage());

			e.printStackTrace();

		}

		return reqBomDetailList;

	}
	
	@RequestMapping(value = { "/getReqBomHeaderByReqId" }, method = RequestMethod.POST)
	public @ResponseBody GetReqBomHeader getReqBomHeaderByReqId(@RequestParam("bomReqId") int bomReqId) {

		System.err.println("inside web api /getReqBomHeaderByReqId BomApiController");

		GetReqBomHeader reqBomHead = new GetReqBomHeader();

		try {

			reqBomHead = getGetReqBomHeaderRepo.getOneGetReqBomHeader(bomReqId);

			System.err.println("getReqBomHeaderByReqId  " + reqBomHead.toString());
		} catch (Exception e) {

			System.err.println("Exception in getReqBomHeaderByReqId BomApiController  " + e.getMessage());

			e.printStackTrace();

		}

		return reqBomHead;

	}
	
	@RequestMapping(value = { "/getBomDetListByBomDIdList" }, method = RequestMethod.POST)
	public @ResponseBody List<ReqBomDetail> getMrnDetailListByMrnDetailId(@RequestParam("bomDIdList") List<Integer> bomDIdList) {
		 
		List<ReqBomDetail> bomDetailList = new ArrayList<>();

		try {
 
			bomDetailList = reqBomDetailRepo.findByBomReqDetailIdIn(bomDIdList);

		} catch (Exception e) {
  
			e.printStackTrace();

		}

		return bomDetailList;

	}
	
	@RequestMapping(value = { "/saveBomHeaderDetail" }, method = RequestMethod.POST)
	public @ResponseBody ReqBomHeader saveOrder(@RequestBody ReqBomHeader bomHeader) {

		System.err.println("ReqBomHeader body " + bomHeader.toString());

		ReqBomHeader bomRes = null;

		try {

			bomRes = reqBomHeaderRepo.save(bomHeader);

			for (int i = 0; i < bomHeader.getReqBomDetailsList().size(); i++) {

				bomHeader.getReqBomDetailsList().get(i).setBomReqId(bomRes.getBomReqId());

			}

			List<ReqBomDetail> bomDetList = reqBomDetailRepo.saveAll(bomHeader.getReqBomDetailsList());

			bomRes.setReqBomDetailsList(bomDetList);
			
			

		} catch (Exception e) {

			System.err.println("exce in saving order head and detail " + e.getMessage());

			e.printStackTrace();

		}
		System.err.println("saveBomHeaderDetail Res{} body " + bomRes.toString());

		return bomRes;

	}
	

}
