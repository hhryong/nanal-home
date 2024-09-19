import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncia.mobilemessage.common.CommonDAO;
import com.ncia.mobilemessage.dto.TemplateCodeMngDTO;
import com.ncia.mobilemessage.service.TemplateCodeMngService;

@Service("TemplateCodeMngServiceImpl")
public class TemplateCodeMngServiceImpl implements TemplateCodeMngService {

	@Autowired
	CommonDAO con;

	@Override
	public List<Object> getTemplateCodeMngList(TemplateCodeMngDTO dto) throws Exception {
		return con.getListData("template.getTemplateCodeMngList", dto);
	}

	@Override
	public TemplateCodeMngDTO getTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception {
		return (TemplateCodeMngDTO) con.getReadData("template.getTemplateCodeMng", dto);
	}

	@Override
	public int countTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception {
		return con.getIntValue("template.countTemplateCodeMng", dto);
	}

	@Override
	public int updateTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception {
		return con.updateData("template.updateTemplateCodeMng", dto);
	}

	@Override
	public int updateTemplateCodeStatusApply(TemplateCodeMngDTO dto) throws Exception {
		return con.updateData("template.updateTemplateCodeStatusApply", dto);
	}
	
	@Override
	public int updateTemplateCodeStatusEtc(TemplateCodeMngDTO dto) throws Exception {
		return con.updateData("template.updateTemplateCodeStatusEtc", dto);
	}

	@Override
	public int deleteTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception {
		return con.deleteData("template.deleteTemplateCodeMng", dto);
	}

	@Override
	public int insertTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception {
		return con.insertData("template.insertTemplateCodeMng", dto);
	}

	@Override
	public int updateTemplateCodeMngReRegister(TemplateCodeMngDTO dto) throws Exception {
		return con.updateData("template.updateTemplateCodeMngReRegister", dto);
	}

	@Override
	public boolean checkTemplateCodeExist(TemplateCodeMngDTO dto) throws Exception {
		return 1 == con.getIntValue("template.checkTemplateCodeExist", dto);
	}

	@Override
	public List<Object> getTemplateCodeMngListWithClientInfo(TemplateCodeMngDTO dto) throws Exception {
		return con.getListData("template.getTemplateCodeMngListWithClientInfo", dto);
	}

	@Override
	public TemplateCodeMngDTO getTemplateCodeMngWithClientInfo(TemplateCodeMngDTO dto) throws Exception {
		return (TemplateCodeMngDTO) con.getReadData("template.getTemplateCodeMngWithClientInfo", dto);
	}

	@Override
	public int countTemplateCodeMngWithClientInfo(TemplateCodeMngDTO dto) throws Exception {
		return con.getIntValue("template.countTemplateCodeMngWithClientInfo", dto);
	}

	@Override
	public int updateTemplateCodeStatusComplete(TemplateCodeMngDTO dto) throws Exception {
		return con.updateData("template.updateTemplateCodeStatusComplete", dto);
	}

	@Override
	public List<Object> getExcelDownloadList(TemplateCodeMngDTO dto) throws Exception {
		return con.getListData("template.getTemplateCodeMngListWithClientInfo2", dto);
	}

	@Override
	public int updateTemplateCodeStatusReject(TemplateCodeMngDTO dto) throws Exception {
		return con.updateData("template.updateTemplateCodeStatusReject", dto);
	}

	@Override
	public List<Object> selectUserExcelDownloadList(HashMap<String, String> search) throws Exception {
		return con.getListData("template.selectUserExcelDownloadList", search);
	}

	@Override
	public List<Object> selectCategory1() throws Exception {
		return con.getListData("template.selectCategory1");
	}

	@Override
	public List<Object> selectCategory2(String category) throws Exception {
		return con.getListData("template.selectCategory2", category);
	}

}
