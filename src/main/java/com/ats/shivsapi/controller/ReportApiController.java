package com.ats.shivsapi.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.shivsapi.model.report.ApproveStatusMrnReport;
import com.ats.shivsapi.model.report.ApproveStatusMrnReportDetail;
import com.ats.shivsapi.model.report.GatepassReport;
import com.ats.shivsapi.model.report.GatepassReportDetail;
import com.ats.shivsapi.model.report.IndentReport;
import com.ats.shivsapi.model.report.IndentReportDetail;
import com.ats.shivsapi.model.report.IssueReport;
import com.ats.shivsapi.model.report.IssueReportDetail;
import com.ats.shivsapi.model.report.MrnReport;
import com.ats.shivsapi.model.report.MrnReportDetail;
import com.ats.shivsapi.model.report.POReport;
import com.ats.shivsapi.model.report.POReportDetail;
import com.ats.shivsapi.model.report.RejectionReport;
import com.ats.shivsapi.model.report.RejectionReportDetail;
import com.ats.shivsapi.repository.indent.IndentReportDetailRepository;
import com.ats.shivsapi.repository.report.ApproveMrnDetailRepository;
import com.ats.shivsapi.repository.report.ApproveStatusMrnReportRepo;
import com.ats.shivsapi.repository.report.GatepassReportDetailRepo;
import com.ats.shivsapi.repository.report.GatepassReportRepository;
import com.ats.shivsapi.repository.report.IndentReportRepo;
import com.ats.shivsapi.repository.report.IssueReportDetailRepository;
import com.ats.shivsapi.repository.report.IssueReportRepository;
import com.ats.shivsapi.repository.report.MrnReportDetailRepo;
import com.ats.shivsapi.repository.report.MrnReportrepo;
import com.ats.shivsapi.repository.report.POReportDetailRepo;
import com.ats.shivsapi.repository.report.POReportRepository;
import com.ats.shivsapi.repository.report.RejectionReportDetailRepo;
import com.ats.shivsapi.repository.report.RejectionReportRepository;

@RestController
public class ReportApiController {

	@Autowired
	IndentReportRepo indentReportRepo;

	@Autowired
	GatepassReportDetailRepo gatepassReportDetailRepo;

	@Autowired
	GatepassReportRepository gatepassReportRepository;

	@Autowired
	RejectionReportDetailRepo rejectionReportDetailRepo;

	@Autowired
	ApproveMrnDetailRepository approveMrnDetailRepository;

	@Autowired
	MrnReportDetailRepo mrnReportDetailRepo;

	@Autowired
	IndentReportDetailRepository indentReportDetailRepository;

	@Autowired
	RejectionReportRepository rejectionReportRepository;

	@Autowired
	POReportRepository pOReportRepository;

	@Autowired
	POReportDetailRepo pOReportDetailRepo;

	@Autowired
	MrnReportrepo mrnReportrepo;

	@Autowired
	ApproveStatusMrnReportRepo approveStatusMrnReportRepo;

	@Autowired
	IssueReportRepository issueReportRepository;
	@Autowired
	IssueReportDetailRepository issueReportDetailRepository;

	@RequestMapping(value = { "/getIndentListHeaderDetailReport" }, method = RequestMethod.POST)
	public @ResponseBody List<IndentReport> getIndentListHeaderDetailReport(

			@RequestParam("indentIdList") List<Integer> indentIdList) {

		List<IndentReport> indentList = new ArrayList<IndentReport>();

		try {

			indentList = indentReportRepo.getIndentHeaderReportList(indentIdList);

			List<IndentReportDetail> list = indentReportDetailRepository.getIndentReportDetailList(indentIdList);

			for (int i = 0; i < indentList.size(); i++) {
				List<IndentReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (indentList.get(i).getIndMId() == list.get(j).getIndMId()) {
						listRes.add(list.get(j));
					}
				}
				indentList.get(i).setIndentReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return indentList;

	}

	@RequestMapping(value = { "/getAllPoListHeaderDetailReport" }, method = RequestMethod.POST)
	public @ResponseBody List<POReport> getAllPoListHeaderDetailReport(

			@RequestParam("poIdList") List<Integer> poIdList) {

		List<POReport> poList = new ArrayList<POReport>();

		try {

			poList = pOReportRepository.getPOReportHeaderList(poIdList);

			List<POReportDetail> list = pOReportDetailRepo.getPOReportDetailList(poIdList);

			for (int i = 0; i < poList.size(); i++) {
				List<POReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (poList.get(i).getPoId() == list.get(j).getPoId()) {
						listRes.add(list.get(j));
					}
				}
				poList.get(i).setpOReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return poList;

	}

	@RequestMapping(value = { "/getAllMrnListHeaderDetailReport" }, method = RequestMethod.POST)
	public @ResponseBody List<MrnReport> getAllMrnListHeaderDetailReport(

			@RequestParam("mrnIdList") List<Integer> mrnIdList) {

		List<MrnReport> mrnList = new ArrayList<MrnReport>();

		try {

			mrnList = mrnReportrepo.getHeaderMrnReportList(mrnIdList);

			List<MrnReportDetail> list = mrnReportDetailRepo.getMrnDetailReportList(mrnIdList);

			for (int i = 0; i < mrnList.size(); i++) {
				List<MrnReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (mrnList.get(i).getMrnId() == list.get(j).getMrnId()) {
						listRes.add(list.get(j));
					}
				}
				mrnList.get(i).setMrnReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return mrnList;

	}

	@RequestMapping(value = { "/getIssueHeaderDetailReport" }, method = RequestMethod.POST)
	public @ResponseBody List<IssueReport> getIssueHeaderDetailReport(

			@RequestParam("issueIdList") List<Integer> issueIdList) {

		List<IssueReport> issList = new ArrayList<IssueReport>();

		try {

			issList = issueReportRepository.getIssueReportList(issueIdList);

			List<IssueReportDetail> list = issueReportDetailRepository.getIssueDetailReportList(issueIdList);

			for (int i = 0; i < issList.size(); i++) {
				List<IssueReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (issList.get(i).getIssueId() == list.get(j).getIssueId()) {
						listRes.add(list.get(j));
					}
				}
				issList.get(i).setIssueReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return issList;

	}

	@RequestMapping(value = { "/getApproveStatusReport" }, method = RequestMethod.POST)
	public @ResponseBody List<ApproveStatusMrnReport> getApproveStatusReport(

			@RequestParam("mrnIdList") List<Integer> mrnIdList) {

		List<ApproveStatusMrnReport> appList = new ArrayList<ApproveStatusMrnReport>();

		try {

			appList = approveStatusMrnReportRepo.getApproveStatusMrnHeaderReport(mrnIdList);

			List<ApproveStatusMrnReportDetail> list = approveMrnDetailRepository
					.getApproveStatusMrnDetailReport(mrnIdList);

			for (int i = 0; i < appList.size(); i++) {
				List<ApproveStatusMrnReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (appList.get(i).getMrnId() == list.get(j).getMrnId()) {
						listRes.add(list.get(j));
					}
				}
				appList.get(i).setApproveStatusMrnReportDetail(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return appList;

	}

	@RequestMapping(value = { "/getAllRejectionListReport" }, method = RequestMethod.POST)
	public @ResponseBody List<RejectionReport> getAllRejectionListReport(

			@RequestParam("rejectionIdList") List<Integer> rejectionIdList) {

		List<RejectionReport> rejList = new ArrayList<RejectionReport>();

		try {

			rejList = rejectionReportRepository.getRejHeaderReportList(rejectionIdList);

			List<RejectionReportDetail> list = rejectionReportDetailRepo.getRejDetailReportList(rejectionIdList);

			for (int i = 0; i < rejList.size(); i++) {
				List<RejectionReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (rejList.get(i).getRejectionId() == list.get(j).getRejectionId()) {
						listRes.add(list.get(j));
					}
				}
				rejList.get(i).setRejReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return rejList;

	}

	@RequestMapping(value = { "/getGatepassListReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GatepassReport> getGatepassListReport(

			@RequestParam("gpIdList") List<Integer> gpIdList) {

		List<GatepassReport> gpList = new ArrayList<GatepassReport>();

		try {

			gpList = gatepassReportRepository.gatepassReportHeader(gpIdList);

			List<GatepassReportDetail> list = gatepassReportDetailRepo.gatepassReportDetail(gpIdList);

			for (int i = 0; i < gpList.size(); i++) {
				List<GatepassReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (gpList.get(i).getGpId() == list.get(j).getGpId()) {
						listRes.add(list.get(j));
					}
				}
				gpList.get(i).setGatepassReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return gpList;

	}

	@RequestMapping(value = { "/getGatepassListReturnableReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GatepassReport> getGatepassListReturnableReport(

			@RequestParam("gpIdList") List<Integer> gpIdList) {

		List<GatepassReport> gpList = new ArrayList<GatepassReport>();

		try {

			gpList = gatepassReportRepository.gatepassReportHeaderReturnable(gpIdList);

			List<GatepassReportDetail> list = gatepassReportDetailRepo.gatepassReportDetailReturnable(gpIdList);

			for (int i = 0; i < gpList.size(); i++) {
				List<GatepassReportDetail> listRes = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if (gpList.get(i).getGpId() == list.get(j).getGpId()) {
						listRes.add(list.get(j));
					}
				}
				gpList.get(i).setGatepassReportDetailList(listRes);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return gpList;

	}

}
