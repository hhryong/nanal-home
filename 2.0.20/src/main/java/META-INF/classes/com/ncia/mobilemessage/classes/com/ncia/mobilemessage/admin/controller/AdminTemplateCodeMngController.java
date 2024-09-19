import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import au.com.bytecode.opencsv.CSVReader;

import com.ncia.mobilemessage.dto.TemplateCodeMngDTO;
import com.ncia.mobilemessage.service.TemplateCodeMngService;
import com.ncia.mobilemessage.util.ControllerErrorUtil;
import com.ncia.mobilemessage.util.ExcelUtil;

@Controller
@RequestMapping("admin/template")
public class AdminTemplateCodeMngController {
	private final int TEMPLATE_NUM_PER_PAGE = 20;

	@Autowired
	TemplateCodeMngService tcms;

	@Resource(name = "ControllerErrorUtil")
	ControllerErrorUtil eUtil;

	@Resource(name = "ExcelUtil")
	private ExcelUtil excelUtil;

	private void createExcelHeader(Row row, String[] header) {
		for (int i = 0; i < header.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(header[i]);
		}
	}

	@RequestMapping("/templateDownload.do")
	public void excelDownload(HttpServletRequest req, HttpServletResponse res, HttpSession session, @RequestParam Map<String, String> param) {
		TemplateCodeMngDTO dto = new TemplateCodeMngDTO();
		dto.setSeqList(param.get("seq").split(" "));
		try {
			List<Object> list = tcms.getExcelDownloadList(dto);

			Workbook wb = new HSSFWorkbook();
			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet("sheet1");
			Row row = null;
			Cell cell = null;

			int rowNum = 0;

			row = sheet.createRow(rowNum++);
			createExcelHeader(row, new String[] {
					"�����", "���̵�", "�����", "���ø��ڵ�",
					"�Ӹ���", "��ư��", "����", "�����",
					"�μ���", "����1", "����2", "����3",
					"����4", "����5", "īī�� ä�� ID", "īī�� KEY",
					"�ݷ� ����" });

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-m-d"));

			for (Object item : list) {
				TemplateCodeMngDTO t = (TemplateCodeMngDTO) item;
				row = sheet.createRow(rowNum++);

				cell = row.createCell(0);
				cell.setCellValue(t.getRegiDate());
				cell.setCellStyle(cellStyle);

				cell = row.createCell(1);
				cell.setCellValue(t.getClientId());

				cell = row.createCell(2);
				cell.setCellValue(t.getClientName());

				cell = row.createCell(3);
				cell.setCellValue(t.getTemplateCode());

				cell = row.createCell(4);
				cell.setCellValue(t.getHeader());

				cell = row.createCell(5);
				cell.setCellValue(t.getButtonName());

				cell = row.createCell(6);
				String status = t.getStatus();
				if (status.equals("1")) {
					cell.setCellValue("���");
				} else if (status.equals("2")) {
					cell.setCellValue("��û");
				} else if (status.equals("3")) {
					cell.setCellValue("�ݷ�");
				} else if (status.equals("4")) {
					cell.setCellValue("����");
				}

				cell = row.createCell(7);
				cell.setCellValue(t.getOrgName());

				cell = row.createCell(8);
				cell.setCellValue(t.getOrgDept());

				cell = row.createCell(9);
				cell.setCellValue(t.getExample1());

				cell = row.createCell(10);
				cell.setCellValue(t.getExample2());

				cell = row.createCell(11);
				cell.setCellValue(t.getExample3());

				cell = row.createCell(12);
				cell.setCellValue(t.getExample4());

				cell = row.createCell(13);
				cell.setCellValue(t.getExample5());

				cell = row.createCell(14);
				cell.setCellValue(t.getKakaoChannelId());

				cell = row.createCell(15);
				cell.setCellValue(t.getKakaoKey());

				cell = row.createCell(16);
				cell.setCellValue(t.getRejectReason());
			}

			sheet.autoSizeColumn(0); // �����
			sheet.autoSizeColumn(1); // ���̵�
			sheet.autoSizeColumn(2); // �����
			sheet.autoSizeColumn(3); // �۱���
			sheet.autoSizeColumn(4); // ���ø��ڵ�
			sheet.autoSizeColumn(5); // �Ӹ���
			sheet.setColumnWidth(6, 2000); // �Ӹ���
			sheet.setColumnWidth(7, 10000); // ����
			sheet.setColumnWidth(8, 10000); // �����
			sheet.setColumnWidth(9, 10000); // �μ���
			sheet.setColumnWidth(10, 10000); // ����1
			sheet.setColumnWidth(11, 10000); // ����2
			sheet.setColumnWidth(12, 10000); // ����3
			sheet.setColumnWidth(13, 10000); // ����4
			sheet.setColumnWidth(14, 10000); // ����5
			sheet.setColumnWidth(15, 10000); // KAKAO ä�� ID
			sheet.setColumnWidth(16, 10000); // KAKAO KEY

			res.setContentType("application/vnd.ms-excel");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			res.setHeader("Content-Disposition", "attachment;filename=templateCode_" + dateFormat.format(new Date()) + ".xls");

			OutputStream out = res.getOutputStream();

			wb.write(out);
			wb.close();

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("downloadBizmTemplate")
	public void downloadBizmTemplate(HttpServletRequest req, HttpServletResponse res) {
		TemplateCodeMngDTO dto = new TemplateCodeMngDTO();
		dto.setStatus("2");

		try {
			List<Object> list = tcms.getExcelDownloadList(dto);

			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("sheet1");
			Row row = null;
			Cell cell = null;

			int rowNum = 0;
			for (Object item : list) {
				TemplateCodeMngDTO t = (TemplateCodeMngDTO) item;
				row = sheet.createRow(rowNum++);

				cell = row.createCell(0);
				cell.setCellValue(t.getKakaoChannelId());
				cell = row.createCell(1);
				cell.setCellValue(t.getTemplateCode());
				cell = row.createCell(2);
				cell.setCellValue(t.getTemplateCode());
				cell = row.createCell(3);
				cell.setCellValue("�⺻��");
				cell = row.createCell(4);
				cell.setCellValue(String.format("%s\n\n#{%s}", t.getHeader(), t.getHeader().replaceAll(" ", "")));
				cell = row.createCell(7);
				cell.setCellValue("���þ���");
				cell = row.createCell(38);
				cell.setCellValue(false);
				cell = row.createCell(39);
				cell.setCellValue("��Ÿ");
				cell = row.createCell(40);
				cell.setCellValue("��Ÿ");
				if (t.getButtonName() != null) {
					cell = row.createCell(41);
					cell.setCellValue("����ũ");
					cell = row.createCell(42);
					cell.setCellValue(t.getButtonName());
					if (t.getUseHttps() != null && t.getUseHttps().equals("Y")) {
						cell = row.createCell(43);
						cell.setCellValue("https://#{url}");
						cell = row.createCell(44);
						cell.setCellValue("https://#{url}");
					} else {
						cell = row.createCell(43);
						cell.setCellValue("http://#{url}");
						cell = row.createCell(44);
						cell.setCellValue("http://#{url}");
					}
				}
			}

			res.setContentType("application/vnd.ms-excel");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			res.setHeader("Content-Disposition", "attachment;filename=BizmTemplateCode_" + dateFormat.format(new Date()) + ".xls");

			OutputStream out = res.getOutputStream();

			wb.write(out);
			wb.close();

			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("template.do")
	public ModelAndView getTemplateList(HttpServletRequest req, HttpServletResponse res, HttpSession session, TemplateCodeMngDTO dto) {

		ModelAndView mav = new ModelAndView("admin/account/template/list");
		dto.setNumPerPage(TEMPLATE_NUM_PER_PAGE);
		try {
			dto.setTotalCount(tcms.countTemplateCodeMngWithClientInfo(dto));
			mav.addObject("lists", tcms.getTemplateCodeMngListWithClientInfo(dto));
			mav.addObject("javascriptPageIndexList", dto.pageIndexList(TEMPLATE_NUM_PER_PAGE));
			if (dto.getStatusArray() != null) {
				String statusString = "";
				for (String s : dto.getStatusArray()) {
					statusString += s;
				}
				dto.setStatusString(statusString);
			}
			mav.addObject("search", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return eUtil.setErrorPage(req, e);
		}

		return mav;
	}

	@RequestMapping("templateView.do")
	public ModelAndView getTemplateView(HttpServletRequest req, HttpServletResponse res, HttpSession session, TemplateCodeMngDTO dto) {
		ModelAndView mav = new ModelAndView("admin/account/template/view");

		try {
			TemplateCodeMngDTO view = tcms.getTemplateCodeMngWithClientInfo(dto);
			mav.addObject("view", view);
		} catch (Exception e) {
			e.printStackTrace();
			return eUtil.setErrorPage(req, e);
		}

		return mav;
	}

	@RequestMapping("/templateRejectReasonList.do")
	public ModelAndView rejectList(HttpServletRequest req, HttpServletResponse res, HttpSession session, @RequestParam Map<String, String> params) {
		ModelAndView mav = new ModelAndView("admin/account/template/reject_list");

		try {
			String[] seqList = params.get("seq").split(" ");
			TemplateCodeMngDTO dto = new TemplateCodeMngDTO();
			dto.setSeqList(seqList);
			List<Object> list = tcms.getExcelDownloadList(dto);

			Iterator<Object> iter = list.iterator();

			StringBuilder sb = new StringBuilder();

			while (iter.hasNext()) {
				TemplateCodeMngDTO data = (TemplateCodeMngDTO) iter.next();
				if (!data.getStatus().equals("2")) {
					iter.remove();
				} else {
					sb.append(data.getSeq()).append(" ");
				}
			}

			mav.addObject("list", list);
			mav.addObject("seq", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			eUtil.setErrorPage(req, e);
		}

		return mav;
	}

	@RequestMapping("/templateRejectSelected.do")
	public ModelAndView rejectSelected(HttpServletRequest req, HttpServletResponse res, HttpSession session, @RequestParam Map<String, String> params) {
		ModelAndView mav = new ModelAndView("jsonView");

		try {
			if (params.containsKey("seq")) {
				String[] seqList = params.get("seq").split(" ");
				for (String seq : seqList) {
					if (params.containsKey(seq)) {
						TemplateCodeMngDTO dto = new TemplateCodeMngDTO();
						dto.setSeq(Long.parseLong(seq));
						dto.setRejectReason(params.get(seq));
						tcms.updateTemplateCodeStatusReject(dto);
					} else {
						throw new Exception("�ݷ� ������ �������� �ʴ� ���� �ֽ��ϴ�.");
					}
				}
			} else {
				throw new Exception("Seq �Ķ���Ͱ� �������� �ʽ��ϴ�.");
			}

			mav.addObject("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("result", false);
			mav.addObject("error", e.toString());
		}

		return mav;
	}

	@RequestMapping("templateUpdate.do")
	public ModelAndView updateTemplate(HttpServletRequest req, HttpServletResponse res, HttpSession session, TemplateCodeMngDTO dto) {
		ModelAndView mav = new ModelAndView("jsonView");

		try {
			String status = dto.getStatus();
			if (status.equals("2")) {
				tcms.updateTemplateCodeStatusApply(dto);
			} else {
				tcms.updateTemplateCodeMng(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("result", false);
		}

		mav.addObject("result", true);
		return mav;
	}
	
	@RequestMapping("templateDelete.do")
	public ModelAndView deleteTemplate(HttpServletRequest req, HttpServletResponse res, HttpSession session, TemplateCodeMngDTO dto) {
		ModelAndView mav = new ModelAndView("jsonView");

		try {
			tcms.updateTemplateCodeStatusEtc(dto);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("result", false);
		}

		mav.addObject("result", true);
		return mav;
	}

	@RequestMapping("templateUpdateAll.do")
	public ModelAndView updateTemplateAll(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			@RequestParam Map<String, String> param) {
		ModelAndView mav = new ModelAndView("jsonView");

		if (param.containsKey("seq") && param.containsKey("status")) {
			String[] seqList = param.get("seq").split(" ");
			String status = param.get("status");
			try {
				if (status.equals("3")) {
					for (String seq : seqList) {
						TemplateCodeMngDTO dto = new TemplateCodeMngDTO();
						dto.setSeq(Long.parseLong(seq));
						tcms.updateTemplateCodeStatusApply(dto);
					}
				} else {
					for (String seq : seqList) {
						TemplateCodeMngDTO dto = new TemplateCodeMngDTO();
						dto.setSeq(Long.parseLong(seq));
						tcms.updateTemplateCodeStatusComplete(dto);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				mav.addObject("result", false);
			}
		} else {
			mav.addObject("result", false);
			mav.addObject("error", "seq�Ķ���Ͱ� ����");
			return mav;
		}

		mav.addObject("result", true);
		return mav;
	}

	@RequestMapping("excelupload.do")
	public ModelAndView excelUpload(HttpServletRequest req, HttpServletResponse res, HttpSession session, @RequestParam MultipartFile excelFile) {
		ModelAndView body = new ModelAndView("jsonView");
		ArrayList<TemplateCodeMngDTO> templateDTOList = new ArrayList<TemplateCodeMngDTO>();
		if (excelFile.getOriginalFilename().endsWith(".csv")) {
			try {
				CSVReader csvReader = new CSVReader(new InputStreamReader(excelFile.getInputStream(), "EUC-KR"));
				String[] line;
				while ((line = csvReader.readNext()) != null) {
					TemplateCodeMngDTO tDTO = new TemplateCodeMngDTO();

					tDTO.setClientId(line[0]);
					tDTO.setTemplateCode(line[1]);
					tDTO.setHeader(line[2]);
					tDTO.setButtonName(line[3]);
					tDTO.setUseHttps(line[4]);
					tDTO.setExample1(line[5]);
					tDTO.setExample2(line[6]);
					tDTO.setExample3(line[7]);
					tDTO.setExample4(line[8]);
					tDTO.setExample5(line[9]);
					tDTO.setStatus(line[10]);

					if (tcms.checkTemplateCodeExist(tDTO)) {
						csvReader.close();
						body.addObject("success", false);
						body.addObject("msg", "���ø��ڵ� �ߺ�: " + tDTO.getTemplateCode());
						return body;
					} else {
						templateDTOList.add(tDTO);
					}
				}

				csvReader.close();
			} catch (Exception e) {
				e.printStackTrace();
				body.addObject("success", false);
				body.addObject("msg", "������ �߰� ����");
				return body;
			}
		} else {
			List<Map<String, String>> list = excelUtil.excelInsert(excelFile);

			for (Map<String, String> t : list) {
				TemplateCodeMngDTO tDTO = new TemplateCodeMngDTO();

				tDTO.setClientId(t.get("col0"));
				tDTO.setTemplateCode(t.get("col1"));
				tDTO.setHeader(t.get("col2"));
				tDTO.setButtonName(t.get("col3"));
				tDTO.setUseHttps(t.get("col4"));
				tDTO.setExample1(t.get("col5"));
				tDTO.setExample2(t.get("col6"));
				tDTO.setExample3(t.get("col7"));
				tDTO.setExample4(t.get("col8"));
				tDTO.setExample5(t.get("col9"));
				tDTO.setStatus(t.get("col10"));

				try {
					if (tcms.checkTemplateCodeExist(tDTO)) {
						body.addObject("success", false);
						body.addObject("msg", "���ø��ڵ� �ߺ�: " + tDTO.getTemplateCode());
						return body;
					} else {
						templateDTOList.add(tDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
					body.addObject("success", false);
					body.addObject("msg", "������ �߰� ����");
					return body;
				}
			}
		}

		for (TemplateCodeMngDTO t : templateDTOList) {
			try {
				tcms.insertTemplateCodeMng(t);
			} catch (Exception e) {
				e.printStackTrace();
				body.addObject("success", false);
				body.addObject("msg", "������ �߰� ����");
				return body;
			}
		}

		body.addObject("success", true);
		return body;
	}
}
