import java.util.HashMap;
import java.util.List;

import com.ncia.mobilemessage.dto.TemplateCodeMngDTO;

public interface TemplateCodeMngService {
	List<Object> getTemplateCodeMngList(TemplateCodeMngDTO dto) throws Exception;

	TemplateCodeMngDTO getTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception;

	int countTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception;

	int updateTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception;

	/**
	 * ��û ���·� ����� ��û�ϵ� ���� ������Ʈ
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	int updateTemplateCodeStatusApply(TemplateCodeMngDTO dto) throws Exception;
	
	int updateTemplateCodeStatusEtc(TemplateCodeMngDTO dto) throws Exception;

	/**
	 * ��û�� �ʱ�ȭ �ݷ����� �ʱ�ȭ
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	int updateTemplateCodeMngReRegister(TemplateCodeMngDTO dto) throws Exception;

	/**
	 * ���� ���·� �����
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	int updateTemplateCodeStatusComplete(TemplateCodeMngDTO dto) throws Exception;

	/**
	 * �ݷ� ���·� �����
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	int updateTemplateCodeStatusReject(TemplateCodeMngDTO dto) throws Exception;

	boolean checkTemplateCodeExist(TemplateCodeMngDTO dto) throws Exception;

	int deleteTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception;

	int insertTemplateCodeMng(TemplateCodeMngDTO dto) throws Exception;

	List<Object> getTemplateCodeMngListWithClientInfo(TemplateCodeMngDTO dto) throws Exception;

	TemplateCodeMngDTO getTemplateCodeMngWithClientInfo(TemplateCodeMngDTO dto) throws Exception;

	int countTemplateCodeMngWithClientInfo(TemplateCodeMngDTO dto) throws Exception;

	List<Object> getExcelDownloadList(TemplateCodeMngDTO dto) throws Exception;

	/**
	 * ���� �����ٿ�ε�
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	List<Object> selectUserExcelDownloadList(HashMap<String, String> search) throws Exception;

	/**
	 * ù��° ī�װ� ��������
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Object> selectCategory1() throws Exception;

	/**
	 * �ι�° ī�װ� ��������
	 * 
	 * @param category
	 *            ù��° ī�װ�
	 * @return
	 * @throws Exception
	 */
	List<Object> selectCategory2(String category) throws Exception;

}
