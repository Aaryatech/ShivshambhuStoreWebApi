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

import com.ats.shivsapi.model.ErrorMessage;
import com.ats.shivsapi.model.GetIssueDetail;
import com.ats.shivsapi.model.GetIssueHeader;
import com.ats.shivsapi.model.IssueDetail;
import com.ats.shivsapi.model.IssueHeader;
import com.ats.shivsapi.model.indent.Indent;
import com.ats.shivsapi.model.indent.IndentTrans;
import com.ats.shivsapi.model.mrn.MrnDetail;
import com.ats.shivsapi.repository.GetIssueDetailRepository;
import com.ats.shivsapi.repository.GetIssueHeaderRepository;
import com.ats.shivsapi.repository.IssueDetailRepository;
import com.ats.shivsapi.repository.IssueHeaderRepository;
import com.ats.shivsapi.repository.indent.IndentTransRepo;
import com.ats.shivsapi.repository.mrn.MrnDetailRepo;

@RestController
public class IssueRestController {

	@Autowired
	IssueHeaderRepository issueHeaderRepository;

	@Autowired
	IssueDetailRepository issueDetailRepository;

	@Autowired
	GetIssueHeaderRepository getIssueHeaderRepository;

	@Autowired
	GetIssueDetailRepository getIssueDetailRepository;

	@Autowired
	MrnDetailRepo mrnDetailRepo;

	@RequestMapping(value = { "/saveIssueHeaderAndDetail" }, method = RequestMethod.POST)
	public @ResponseBody IssueHeader saveIssueHeaderAndDetail(@RequestBody IssueHeader issueHeader) {

		IssueHeader res = new IssueHeader();

		try {

			res = issueHeaderRepository.save(issueHeader);
			for (int i = 0; i < issueHeader.getIssueDetailList().size(); i++)
				issueHeader.getIssueDetailList().get(i).setIssueId(res.getIssueId());

			List<IssueDetail> issueDetailList = issueDetailRepository.saveAll(issueHeader.getIssueDetailList());
			res.setIssueDetailList(issueDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/deleteHeaderAndDetail" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteHeaderAndDetail(@RequestParam("issueId") int issueId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {

			int delete = issueHeaderRepository.delete(issueId);

			if (delete == 0) {
				errorMessage.setError(true);
				errorMessage.setMessage("failed ");
			} else {
				errorMessage.setError(false);
				errorMessage.setMessage("deleted ");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("failed");
		}
		return errorMessage;

	}

	@RequestMapping(value = { "/getIssueHeaderList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetIssueHeader> getIssueHeaderList(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		List<GetIssueHeader> list = new ArrayList<GetIssueHeader>();

		try {

			list = getIssueHeaderRepository.findByDeleteStatus(fromDate, toDate);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}

	@RequestMapping(value = { "/getIssueHeaderAndDetailById" }, method = RequestMethod.POST)
	public @ResponseBody GetIssueHeader getIssueHeaderAndDetailById(@RequestParam("issueId") int issueId) {

		GetIssueHeader getIssueHeader = new GetIssueHeader();

		try {

			getIssueHeader = getIssueHeaderRepository.findByIssueId(issueId);

			List<GetIssueDetail> getIssueDetailList = getIssueDetailRepository.findByIssueId(issueId);

			getIssueHeader.setIssueDetailList(getIssueDetailList);
		} catch (Exception e) {

			e.printStackTrace();

		}
		return getIssueHeader;

	}

	@RequestMapping(value = { "/getBatchByItemId" }, method = RequestMethod.POST)
	public @ResponseBody List<MrnDetail> getBatchByItemId(@RequestParam("itemId") int itemId,
			@RequestParam("type") int type, @RequestParam("date") String date) {

		List<MrnDetail> indTransList = new ArrayList<>();

		try {

			indTransList = mrnDetailRepo.findByItemIdAndDelStatusAndMrnDetailStatus(itemId, type, date);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return indTransList;

	}

	@RequestMapping(value = { "/getMrnDetailListByMrnDetailId" }, method = RequestMethod.POST)
	public @ResponseBody List<MrnDetail> getMrnDetailListByMrnDetailId(
			@RequestParam("mrnDetailList") List<Integer> mrnDetailList) {

		List<MrnDetail> mrnDetails = new ArrayList<>();

		try {

			mrnDetails = mrnDetailRepo.getMrnDetailListByMrnDetailId(mrnDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return mrnDetails;

	}

	@RequestMapping(value = { "/updateMrnDetailList" }, method = RequestMethod.POST)
	public @ResponseBody List<MrnDetail> updateMrnDetailList(@RequestBody List<MrnDetail> mrnDetailList) {

		List<MrnDetail> mrnDetails = new ArrayList<>();

		try {

			mrnDetails = mrnDetailRepo.saveAll(mrnDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return mrnDetails;

	}

	@RequestMapping(value = { "/getIssueHeaderByStatus" }, method = RequestMethod.POST)
	public @ResponseBody List<GetIssueHeader> getIssueHeaderByStatus(@RequestParam("status") List<String> status) {

		List<GetIssueHeader> list = new ArrayList<GetIssueHeader>();

		try {

			list = getIssueHeaderRepository.getIssueHeaderByStatus(status);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}

	@RequestMapping(value = { "/updateStatusWhileIssueApprov" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateStatusWhileMrnApprov(@RequestParam("issueId") int issueId,
			@RequestParam("issueDetalId") List<Integer> issueDetalId, @RequestParam("status") int status) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {

			int update = issueHeaderRepository.updateStatusWhileApprov(issueId, status);

			/*
			 * for(int i=0 ; i<poDetalId.size() : i++) {
			 * 
			 * }
			 */
			int updateDetail = issueDetailRepository.updateStatusWhileApprov(issueDetalId, status);

			errorMessage.setError(false);
			errorMessage.setMessage("approved");

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("failed");

		}
		return errorMessage;

	}

	@RequestMapping(value = { "/generateIssueNoAndMrnNo" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage generateIssueNoAndMrnNo(@RequestParam("docType") int docType,
			@RequestParam("date") String date) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			String recptNo = new String();

			if (docType == 1) {

				recptNo = issueHeaderRepository.generateMrnNo(date);
			} else {

				recptNo = issueHeaderRepository.generateIssueNo(date);
			}

			String[] monthAndYear = date.split("-");
			if (recptNo == null) {

				if (docType == 1) {
					recptNo = "MRN-" + monthAndYear[0].substring(2, monthAndYear[0].length()) + "-" + monthAndYear[1]
							+ "-" + monthAndYear[2] + "-0001";
				} else {
					recptNo = "IS-" + monthAndYear[0].substring(2, monthAndYear[0].length()) + "-" + monthAndYear[1]
							+ "-" + monthAndYear[2] + "-0001";
				}
			} else {
				String[] splNo = recptNo.split("-");
				int maxNo = Integer.parseInt(splNo[splNo.length - 1]);

				maxNo = maxNo + 1;

				String finalNo = new String();
				for (int i = String.valueOf(maxNo).length(); i < 4; i++) {

					finalNo = finalNo + "0";
				}
				finalNo = finalNo + String.valueOf(maxNo);

				if (docType == 1) {
					recptNo = "MRN-" + monthAndYear[0].substring(2, monthAndYear[0].length()) + "-" + monthAndYear[1]
							+ "-" + monthAndYear[2] + "-" + finalNo;
				} else {
					recptNo = "IS-" + monthAndYear[0].substring(2, monthAndYear[0].length()) + "-" + monthAndYear[1]
							+ "-" + monthAndYear[2] + "-" + finalNo;
				}
			}
			System.out.println("recptNo " + recptNo);
			errorMessage.setError(false);
			errorMessage.setMessage(recptNo);

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("failed");

		}
		return errorMessage;

	}

}
