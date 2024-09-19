/**
 * @author Administrator
 * 
 */
public class AccountDTO extends PagingDTO {

	private String allow;
	private String auth;
	private String bankName;
	private String bankNo;
	private String bankOwner;
	private String bankRegNo;
	private String billAddr1;
	private String billAddr2;
	private String billCel01;
	private String billCel02;
	private String billCel03;
	private String billDept;
	private String billEmail01;
	private String billEmail02;
	private String billFax01;
	private String billFax02;
	private String billFax03;
	// LGû�� ������ ������� ���� �ʵ� �߰�
	private String billMethod;
	private String billName;
	private String billNo;
	private String billPost;
	private String billTel01;
	private String billTel02;
	private String billTel03;
	private String changeStatus;// û����� 0:û��Ϸ� 1:û���û
	private String clientAgree;
	private String clientCel;
	private String clientCode;
	private String clientId;
	private String clientLevel;
	private String clientLevelOld;
	private String clientMail;
	// ������ ����ȭ�� �޸��߰�
	private String clientMemo;
	private String clientName;
	private String clientOldPwd;
	private String clientPwd;
	private String clientPwdOri;
	private String clientPwdRe;
	private String clientSeq;
	private String clientStatus;// �������� 0:�̻�� 1:��� 2:���� 3:����
	private String clientTel;
	private String groupName;
	private String ipNo1;
	private String ipNo2;
	private String ipNo3;
	private String ipType;
	private String isAdmin;
	private String isOld;
	private String kakaoChannelId;
	private String kakaoKey;
	private String levelName;
	private String lgChangeStatus;
	private String lgClientId;
	private String lgCount;
	private String lgNStatus;
	private String lgReqDate;
	private String lgStatus;
	private String lgType;
	private String masterId;
	private String mngAddr1;
	private String mngAddr2;
	private String mngFax;
	private String mngOrgCeo;
	private String mngPost;
	private String moApproval;
	private String modifyDate;

	private String modifyId;
	private String moMng;
	private String monthSendLimite;
	private String monumber;
	private String moNumber;
	private String moPrefix;
	private String moPurpose;
	private String moSmsNoti;
	private String moTel;
	private String moTest;
	private String naverChannelId;

	private String naverKey;

	// �ű� status �߰�
	private String nStatus;	
	private String changeClientCel;

	private String orderBy;
	private String orderBySc;
	private String orgCeo;
	private String orgCode;
	private String orgDept;
	private String orgName;
	private String orgNo;
	private String orgTitle;
	private String outputFormat;
	private String outputFormatYN;
	private String payerNo;
	private String picAddr1;
	private String picAddr2;
	private String picCel01;
	private String picCel02;
	private String picCel03;
	private String picEmail01;
	private String picEmail02;
	private String picFax01;
	private String picFax02;
	private String picFax03;
	private String picName;
	private String picPost;
	private String picTel01;
	private String picTel02;
	private String picTel03;
	private String regDate;
	private String regId;
	/**
	 * MO��ȣ�߰�(MOINFO�� ���� �߰�(20160907)
	 */
	private String regperson;
	private String serviceDate;
	private String serviceName1;
	private String serviceName2;
	private String serviceStatus;
	private String serviceTel1;
	private String serviceTel2;
	// 20170223 û����� �߰�
	private String subscriptionStatus;
	// 20170223 û����� �߰� ��
	private String systemInfo;
	private String tossChannelId;

	// LG �����丮 ����

	private String tossKey;
	private String UlActionTime;
	private String UlReason;
	private String UlStatus;
    private String uniqueNo;
	private String upOrgId;
	private String upOrgName;

	// 2021-12-08
	// �� �޽��� ����
	private String useApp;
	private String useBilling;
	private String useMms;
	private String useMo;
	private String useMt;
	private String useOrg;
	private String userLogReason;

	private String useSms;

	private String useSmsOld;

	private String useWeb;

	public String changeMessage(AccountDTO target) {
		String str = "";

		// if (auth != null && target.getAuth() != null &&
		// !target.getAuth().equals(auth))
		// str += "������ȣ:'" + auth + "'->'" + target.getAuth() + "' <br>";
		// if (changeStatus != null && target.getChangeStatus() != null &&
		// !target.getChangeStatus().equals(changeStatus))
		// str += "û�����(Ȯ��):'" + changeStatus + "'->'" +
		// target.getChangeStatus() + "' <br>";
		if (clientCel != null && target.getClientCel() != null
				&& !target.getClientCel().equals(clientCel)) {
			str += "�ڵ�����ȣ:'" + clientCel + "'->'" + target.getClientCel()
					+ "' <br>";
		}
		if (clientId != null && target.getClientId() != null
				&& !target.getClientId().equals(clientId)) {
			str += "����:'" + clientId + "'->'" + target.getClientId() + "' <br>";
		}
		// if (clientLevel != null && target.getClientLevel() != null &&
		// !target.getClientLevel().equals(clientLevel))
		// str += "����:'" + clientLevel + "'->'" + target.getClientLevel() +
		// "' <br>";
		// if (clientLevelOld != null && target.getClientLevelOld() != null &&
		// !target.getClientLevelOld().equals(clientLevelOld))
		// str += "�� ����:'" + clientLevelOld + "'->'" +
		// target.getClientLevelOld() + "' <br>";
		if (clientName != null && target.getClientName() != null
				&& !target.getClientName().equals(clientName)) {
			str += "�̸�:'" + clientName + "'->'" + target.getClientName()
					+ "' <br>";
		}
		// if (clientOldPwd != null && target.getClientOldPwd() != null &&
		// !target.getClientOldPwd().equals(clientOldPwd))
		// str += "����й�ȣ:'" + clientOldPwd + "'->'" + target.getClientOldPwd() +
		// "' <br>";
		// if (clientPwd != null && target.getClientPwd() != null &&
		// !target.getClientPwd().equals(clientPwd))
		// str += "��й�ȣ:'" + clientPwd + "'->'" + target.getClientPwd() +
		// "' <br>";
		// if (clientSeq != null && target.getClientSeq() != null &&
		// !target.getClientSeq().equals(clientSeq))
		// str += "�Ϸù�ȣ:'" + clientSeq + "'->'" + target.getClientSeq() +
		// "' <br>";
		if (clientStatus != null && target.getClientStatus() != null
				&& !target.getClientStatus().equals(clientStatus)) {
			str += "��������:'" + clientStatus + "'->'" + target.getClientStatus()
					+ "' <br>";
		}
		if (clientTel != null && target.getClientTel() != null
				&& !target.getClientTel().equals(clientTel)) {
			str += "��ȭ��ȣ:'" + clientTel + "'->'" + target.getClientTel()
					+ "' <br>";
		}
		if (groupName != null && target.getGroupName() != null
				&& !target.getGroupName().equals(groupName)) {
			str += "�׷��:'" + groupName + "'->'" + target.getGroupName()
					+ "' <br>";
		}
		if (ipNo1 != null && target.getIpNo1() != null
				&& !target.getIpNo1().equals(ipNo1)) {
			str += "�ý���IP1:'" + ipNo1 + "'->'" + target.getIpNo1() + "' <br>";
		}
		if (ipNo2 != null && target.getIpNo2() != null
				&& !target.getIpNo2().equals(ipNo2)) {
			str += "�ý���IP2:'" + ipNo2 + "'->'" + target.getIpNo2() + "' <br>";
		}
		if (ipNo3 != null && target.getIpNo3() != null
				&& !target.getIpNo3().equals(ipNo3)) {
			str += "�ý���IP3:'" + ipNo3 + "'->'" + target.getIpNo3() + "' <br>";
		}
		if (isOld != null && target.getIsOld() != null
				&& !target.getIsOld().equals(isOld)) {
			str += "���ý��۵����Ϳ���:'" + isOld + "'->'" + target.getIsOld()
					+ "' <br>";
		}
		if (masterId != null && target.getMasterId() != null
				&& !target.getMasterId().equals(masterId)) {
			str += "��������:'" + masterId + "'->'" + target.getMasterId()
					+ "' <br>";
		}
		if (mngAddr1 != null && target.getMngAddr1() != null
				&& !target.getMngAddr1().equals(mngAddr1)) {
			str += "�ּ�:'" + mngAddr1 + "'->'" + target.getMngAddr1() + "' <br>";
		}
		if (mngAddr2 != null && target.getMngAddr2() != null
				&& !target.getMngAddr2().equals(mngAddr2)) {
			str += "���ּ�:'" + mngAddr2 + "'->'" + target.getMngAddr2()
					+ "' <br>";
		}
		if (mngFax != null && target.getMngFax() != null
				&& !target.getMngFax().equals(mngFax)) {
			str += "FAX:'" + mngFax + "'->'" + target.getMngFax() + "' <br>";
		}
		if (mngOrgCeo != null && target.getMngOrgCeo() != null
				&& !target.getMngOrgCeo().equals(mngOrgCeo)) {
			str += "��ǥ�ڸ�:'" + mngOrgCeo + "'->'" + target.getMngOrgCeo()
					+ "' <br>";
		}
		if (mngPost != null && target.getMngPost() != null
				&& !target.getMngPost().equals(mngPost)) {
			str += "�����ȣ:'" + mngPost + "'->'" + target.getMngPost() + "' <br>";
		}
		if (modifyDate != null && target.getModifyDate() != null
				&& !target.getModifyDate().equals(modifyDate)) {
			str += "MO������������:'" + modifyDate + "'->'" + target.getModifyDate()
					+ "' <br>";
		}
		if (modifyId != null && target.getModifyId() != null
				&& !target.getModifyId().equals(modifyId)) {
			str += "MO���ž˸�:'" + modifyId + "'->'" + target.getModifyId()
					+ "' <br>";
		}
		if (moApproval != null && target.getMoApproval() != null
				&& !target.getMoApproval().equals(moApproval)) {
			str += "������:'" + moApproval + "'->'" + target.getMoApproval()
					+ "' <br>";
		}
		if (moSmsNoti != null && target.getMoSmsNoti() != null
				&& !target.getMoSmsNoti().equals(moSmsNoti)) {
			str += "������:'" + moSmsNoti + "'->'" + target.getMoSmsNoti()
					+ "' <br>";
		}
		if (monthSendLimite != null && target.getMonthSendLimite() != null
				&& !target.getMonthSendLimite().equals(monthSendLimite)) {
			str += "�߼۰Ǽ�����:'" + monthSendLimite + "'->'"
					+ target.getMonthSendLimite() + "' <br>";
		}
		if (orgCode != null && target.getOrgCode() != null
				&& !target.getOrgCode().equals(orgCode)) {
			str += "����ڵ�:'" + orgCode + "'->'" + target.getOrgCode() + "' <br>";
		}
		if (orgDept != null && target.getOrgDept() != null
				&& !target.getOrgDept().equals(orgDept)) {
			str += "�ֱ����:'" + orgDept + "'->'" + target.getOrgDept() + "' <br>";
		}
		if (orgName != null && target.getOrgName() != null
				&& !target.getOrgName().equals(orgName)) {
			str += "�����:'" + orgName + "'->'" + target.getOrgName() + "' <br>";
		}
		if (orgTitle != null && target.getOrgTitle() != null
				&& !target.getOrgTitle().equals(orgTitle)) {
			str += "Ÿ��Ʋ(������Ī):'" + orgTitle + "'->'" + target.getOrgTitle()
					+ "' <br>";
		}
		if (regDate != null && target.getRegDate() != null
				&& !target.getRegDate().equals(regDate)) {
			str += "������:'" + regDate + "'->'" + target.getRegDate() + "' <br>";
		}
		if (regId != null && target.getRegId() != null
				&& !target.getRegId().equals(regId)) {
			str += "������:'" + regId + "'->'" + target.getRegId() + "' <br>";
		}
		if (serviceStatus != null && target.getServiceStatus() != null
				&& !target.getServiceStatus().equals(serviceStatus)) {
			str += "���񽺻���:'" + serviceStatus + "'->'"
					+ target.getServiceStatus() + "' <br>";
		}
		if (systemInfo != null && target.getSystemInfo() != null
				&& !target.getSystemInfo().equals(systemInfo)) {
			str += "�ý�������:'" + systemInfo + "'->'" + target.getSystemInfo()
					+ "' <br>";
		}
		if (uniqueNo != null && target.getUniqueNo() != null
				&& !target.getUniqueNo().equals(uniqueNo)) {
			str += "����ڹ�ȣ:'" + uniqueNo + "'->'" + target.getUniqueNo()
					+ "' <br>";
		}
		if (upOrgId != null && target.getUpOrgId() != null
				&& !target.getUpOrgId().equals(upOrgId)) {
			str += "�׷����:'" + upOrgId + "'->'" + target.getUpOrgId() + "' <br>";
		}
		if (useBilling != null && target.getUseBilling() != null
				&& !target.getUseBilling().equals(useBilling)) {
			str += "������������(����):'" + useBilling + "'->'"
					+ target.getUseBilling() + "' <br>";
		}
		if (useMms != null && target.getUseMms() != null
				&& !target.getUseMms().equals(useMms)) {
			str += "MMS��뿩��:'" + useMms + "'->'" + target.getUseMms()
					+ "' <br>";
		}
		if (useMo != null && target.getUseMo() != null
				&& !target.getUseMo().equals(useMo)) {
			str += "���Ż�뿩��:'" + useMo + "'->'" + target.getUseMo() + "' <br>";
		}
		if (useMt != null && target.getUseMt() != null
				&& !target.getUseMt().equals(useMt)) {
			str += "SMS/MMS MT:'" + useMt + "'->'" + target.getUseMt()
					+ "' <br>";
		}
		if (useOrg != null && target.getUseOrg() != null
				&& !target.getUseOrg().equals(useOrg)) {
			str += "�����������:'" + useOrg + "'->'" + target.getUseOrg() + "' <br>";
		}
		if (useSms != null && target.getUseSms() != null
				&& !target.getUseSms().equals(useSms)) {
			str += "SMS��뿩��:'" + useSms + "'->'" + target.getUseSms()
					+ "' <br>";
		}
		if (useSmsOld != null && target.getUseSmsOld() != null
				&& !target.getUseSmsOld().equals(useSmsOld)) {
			str += "SMS_��뿩��(����):'" + useSmsOld + "'->'"
					+ target.getUseSmsOld() + "' <br>";
		}
		if (useWeb != null && target.getUseWeb() != null
				&& !target.getUseWeb().equals(useWeb)) {
			str += "���߼ۻ�뿩��:'" + useWeb + "'->'" + target.getUseWeb()
					+ "' <br>";
		}
		if (ipType != null && target.getIpType() != null
				&& !target.getIpType().equals(ipType)) {
			str += "�缳�����ǿ���:'" + ipType + "'->'" + target.getIpType()
					+ "' <br>";
		}
		if (nStatus != null && target.getnStatus() != null
				&& !target.getnStatus().equals(nStatus)) {
			str += "û����½ű��߰�(Ȯ��):'" + nStatus + "'->'" + target.getnStatus()
					+ "' <br>";
		}
		if (clientMail != null && target.getClientMail() != null
				&& !target.getClientMail().equals(clientMail)) {
			str += "E-MAIL:'" + clientMail + "'->'" + target.getClientMail()
					+ "' <br>";
		}

		// if (kakaoChannelId != null && target.getKakaoChannelId() != null &&
		// !target.getKakaoChannelId().equals(kakaoChannelId))
		// str += "īī�� ä�� ID:'" + kakaoChannelId + "'->'" +
		// target.getKakaoChannelId() + "' <br>";
		// if (kakaoKey != null && target.getKakaoKey() != null &&
		// !target.getKakaoKey().equals(kakaoKey))
		// str += "īī�� ������ KEY:'" + kakaoKey + "'->'" + target.getKakaoKey() +
		// "' <br>";
		//
		// if (naverChannelId != null && target.getNaverChannelId() != null &&
		// !target.getNaverChannelId().equals(naverChannelId))
		// str += "���̹� ä�� ID:'" + naverChannelId + "'->'" +
		// target.getNaverChannelId() + "' <br>";
		// if (naverKey != null && target.getNaverKey() != null &&
		// !target.getNaverKey().equals(naverKey))
		// str += "���̹� ������ KEY:'" + naverKey + "'->'" + target.getNaverKey() +
		// "' <br>";
		//
		// if (tossChannelId != null && target.getTossChannelId() != null &&
		// !target.getTossChannelId().equals(tossChannelId))
		// str += "�佺 ä�� ID:'" + tossChannelId + "'->'" +
		// target.getTossChannelId() + "' <br>";
		// if (tossKey != null && target.getTossKey() != null &&
		// !target.getTossKey().equals(tossKey))
		// str += "�佺 ������ KEY:'" + tossKey + "'->'" + target.getTossKey() +
		// "' <br>";
		// if (useApp != null && target.getUseApp() != null &&
		// !target.getUseApp().equals(useApp))
		// str += String.format("�۸޽��� ��� ����: %s -> %s <br>", useApp,
		// target.getUseApp());
		return str;
	}

	public String getAllow() {
		return allow;
	}

	public String getAuth() {
		return auth;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public String getBankOwner() {
		return bankOwner;
	}

	public String getBankRegNo() {
		return bankRegNo;
	}

	public String getBillAddr1() {
		return billAddr1;
	}

	public String getBillAddr2() {
		return billAddr2;
	}

	public String getBillCel01() {
		return billCel01;
	}

	public String getBillCel02() {
		return billCel02;
	}

	public String getBillCel03() {
		return billCel03;
	}

	public String getBillDept() {
		return billDept;
	}

	public String getBillEmail01() {
		return billEmail01;
	}

	public String getBillEmail02() {
		return billEmail02;
	}

	public String getBillFax01() {
		return billFax01;
	}

	public String getBillFax02() {
		return billFax02;
	}

	public String getBillFax03() {
		return billFax03;
	}

	public String getBillMethod() {
		return billMethod;
	}

	public String getBillName() {
		return billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public String getBillPost() {
		return billPost;
	}

	public String getBillTel01() {
		return billTel01;
	}

	public String getBillTel02() {
		return billTel02;
	}

	public String getBillTel03() {
		return billTel03;
	}

	public String getChangeStatus() {
		return changeStatus;
	}

	public String getClientAgree() {
		return clientAgree;
	}

	public String getClientCel() {
		return clientCel;
	}

	public String getClientCode() {
		return clientCode;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientLevel() {
		return clientLevel;
	}

	public String getClientLevelOld() {
		return clientLevelOld;
	}

	public String getClientMail() {
		return clientMail;
	}

	public String getClientMemo() {
		return clientMemo;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientOldPwd() {
		return clientOldPwd;
	}

	public String getClientPwd() {
		return clientPwd;
	}

	public String getClientPwdOri() {
		return clientPwdOri;
	}

	public String getClientPwdRe() {
		return clientPwdRe;
	}

	public String getClientSeq() {
		return clientSeq;
	}

	public String getClientStatus() {
		return clientStatus;
	}

	public String getClientTel() {
		return clientTel;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getIpNo1() {
		return ipNo1;
	}

	public String getIpNo2() {
		return ipNo2;
	}

	public String getIpNo3() {
		return ipNo3;
	}

	public String getIpType() {
		return ipType;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public String getIsOld() {
		return isOld;
	}

	public String getKakaoChannelId() {
		return kakaoChannelId;
	}

	public String getKakaoKey() {
		return kakaoKey;
	}

	public String getLevelName() {
		return levelName;
	}

	public String getLgChangeStatus() {
		return lgChangeStatus;
	}

	public String getLgClientId() {
		return lgClientId;
	}

	public String getLgCount() {
		return lgCount;
	}

	public String getLgNStatus() {
		return lgNStatus;
	}

	public String getLgReqDate() {
		return lgReqDate;
	}

	public String getLgStatus() {
		return lgStatus;
	}

	public String getLgType() {
		return lgType;
	}

	public String getMasterId() {
		return masterId;
	}

	public String getMngAddr1() {
		return mngAddr1;
	}

	public String getMngAddr2() {
		return mngAddr2;
	}

	public String getMngFax() {
		return mngFax;
	}

	public String getMngOrgCeo() {
		return mngOrgCeo;
	}

	public String getMngPost() {
		return mngPost;
	}

	public String getMoApproval() {
		return moApproval;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getModifyId() {
		return modifyId;
	}

	public String getMoMng() {
		return moMng;
	}

	public String getMonthSendLimite() {
		return monthSendLimite;
	}

	public String getMonumber() {
		return monumber;
	}

	public String getMoNumber() {
		return moNumber;
	}

	public String getMoPrefix() {
		return moPrefix;
	}

	public String getMoPurpose() {
		return moPurpose;
	}

	public String getMoSmsNoti() {
		return moSmsNoti;
	}

	public String getMoTel() {
		return moTel;
	}

	public String getMoTest() {
		return moTest;
	}

	public String getNaverChannelId() {
		return naverChannelId;
	}

	public String getNaverKey() {
		return naverKey;
	}

	public String getnStatus() {
		return nStatus;
	}
	
	public String getChangeClientCel() {
	    return changeClientCel;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getOrderBySc() {
		return orderBySc;
	}

	public String getOrgCeo() {
		return orgCeo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public String getOrgDept() {
		return orgDept;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public String getOrgTitle() {
		return orgTitle;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public String getOutputFormatYN() {
		return outputFormatYN;
	}

	public String getPayerNo() {
		return payerNo;
	}

	public String getPicAddr1() {
		return picAddr1;
	}

	public String getPicAddr2() {
		return picAddr2;
	}

	public String getPicCel01() {
		return picCel01;
	}

	public String getPicCel02() {
		return picCel02;
	}

	public String getPicCel03() {
		return picCel03;
	}

	public String getPicEmail01() {
		return picEmail01;
	}

	public String getPicEmail02() {
		return picEmail02;
	}

	public String getPicFax01() {
		return picFax01;
	}

	public String getPicFax02() {
		return picFax02;
	}

	public String getPicFax03() {
		return picFax03;
	}

	public String getPicName() {
		return picName;
	}

	public String getPicPost() {
		return picPost;
	}

	public String getPicTel01() {
		return picTel01;
	}

	public String getPicTel02() {
		return picTel02;
	}

	public String getPicTel03() {
		return picTel03;
	}

	public String getRegDate() {
		return regDate;
	}

	public String getRegId() {
		return regId;
	}

	public String getRegperson() {
		return regperson;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public String getServiceName1() {
		return serviceName1;
	}

	public String getServiceName2() {
		return serviceName2;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public String getServiceTel1() {
		return serviceTel1;
	}

	public String getServiceTel2() {
		return serviceTel2;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public String getSystemInfo() {
		return systemInfo;
	}

	public String getTossChannelId() {
		return tossChannelId;
	}

	public String getTossKey() {
		return tossKey;
	}

	public String getUlActionTime() {
		return UlActionTime;
	}

	public String getUlReason() {
		return UlReason;
	}

	public String getUlStatus() {
		return UlStatus;
	}

	public String getUniqueNo() {
		return uniqueNo;
	}

	public String getUpOrgId() {
		return upOrgId;
	}

	public String getUpOrgName() {
		return upOrgName;
	}

	public String getUseApp() {
		return useApp;
	}

	public String getUseBilling() {
		return useBilling;
	}

	public String getUseMms() {
		return useMms;
	}

	public String getUseMo() {
		return useMo;
	}

	public String getUseMt() {
		return useMt;
	}

	public String getUseOrg() {
		return useOrg;
	}

	public String getUserLogReason() {
		return userLogReason;
	}

	public String getUseSms() {
		return useSms;
	}

	public String getUseSmsOld() {
		return useSmsOld;
	}

	public String getUseWeb() {
		return useWeb;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public void setBankOwner(String bankOwner) {
		this.bankOwner = bankOwner;
	}

	public void setBankRegNo(String bankRegNo) {
		this.bankRegNo = bankRegNo;
	}

	public void setBillAddr1(String billAddr1) {
		this.billAddr1 = billAddr1;
	}

	public void setBillAddr2(String billAddr2) {
		this.billAddr2 = billAddr2;
	}

	public void setBillCel01(String billCel01) {
		this.billCel01 = billCel01;
	}

	public void setBillCel02(String billCel02) {
		this.billCel02 = billCel02;
	}

	public void setBillCel03(String billCel03) {
		this.billCel03 = billCel03;
	}

	public void setBillDept(String billDept) {
		this.billDept = billDept;
	}

	public void setBillEmail01(String billEmail01) {
		this.billEmail01 = billEmail01;
	}

	public void setBillEmail02(String billEmail02) {
		this.billEmail02 = billEmail02;
	}

	public void setBillFax01(String billFax01) {
		this.billFax01 = billFax01;
	}

	public void setBillFax02(String billFax02) {
		this.billFax02 = billFax02;
	}

	public void setBillFax03(String billFax03) {
		this.billFax03 = billFax03;
	}

	public void setBillMethod(String billMethod) {
		this.billMethod = billMethod;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public void setBillPost(String billPost) {
		this.billPost = billPost;
	}

	public void setBillTel01(String billTel01) {
		this.billTel01 = billTel01;
	}

	public void setBillTel02(String billTel02) {
		this.billTel02 = billTel02;
	}

	public void setBillTel03(String billTel03) {
		this.billTel03 = billTel03;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}

	public void setClientAgree(String clientAgree) {
		this.clientAgree = clientAgree;
	}

	public void setClientCel(String clientCel) {
		this.clientCel = clientCel;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}

	public void setClientLevelOld(String clientLevelOld) {
		this.clientLevelOld = clientLevelOld;
	}

	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}

	public void setClientMemo(String clientMemo) {
		this.clientMemo = clientMemo;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setClientOldPwd(String clientOldPwd) {
		this.clientOldPwd = clientOldPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	public void setClientPwdOri(String clientPwdOri) {
		this.clientPwdOri = clientPwdOri;
	}

	public void setClientPwdRe(String clientPwdRe) {
		this.clientPwdRe = clientPwdRe;
	}

	public void setClientSeq(String clientSeq) {
		this.clientSeq = clientSeq;
	}

	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setIpNo1(String ipNo1) {
		this.ipNo1 = ipNo1;
	}

	public void setIpNo2(String ipNo2) {
		this.ipNo2 = ipNo2;
	}

	public void setIpNo3(String ipNo3) {
		this.ipNo3 = ipNo3;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setIsOld(String isOld) {
		this.isOld = isOld;
	}

	public void setKakaoChannelId(String kakaoChannelId) {
		this.kakaoChannelId = kakaoChannelId;
	}

	public void setKakaoKey(String kakaoKey) {
		this.kakaoKey = kakaoKey;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public void setLgChangeStatus(String lgChangeStatus) {
		this.lgChangeStatus = lgChangeStatus;
	}

	public void setLgClientId(String lgClientId) {
		this.lgClientId = lgClientId;
	}

	public void setLgCount(String lgCount) {
		this.lgCount = lgCount;
	}

	public void setLgNStatus(String lgNStatus) {
		this.lgNStatus = lgNStatus;
	}

	public void setLgReqDate(String lgReqDate) {
		this.lgReqDate = lgReqDate;
	}

	public void setLgStatus(String lgStatus) {
		this.lgStatus = lgStatus;
	}

	public void setLgType(String lgType) {
		this.lgType = lgType;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public void setMngAddr1(String mngAddr1) {
		this.mngAddr1 = mngAddr1;
	}

	public void setMngAddr2(String mngAddr2) {
		this.mngAddr2 = mngAddr2;
	}

	public void setMngFax(String mngFax) {
		this.mngFax = mngFax;
	}

	public void setMngOrgCeo(String mngOrgCeo) {
		this.mngOrgCeo = mngOrgCeo;
	}

	public void setMngPost(String mngPost) {
		this.mngPost = mngPost;
	}

	public void setMoApproval(String moApproval) {
		this.moApproval = moApproval;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public void setMoMng(String moMng) {
		this.moMng = moMng;
	}

	public void setMonthSendLimite(String monthSendLimite) {
		this.monthSendLimite = monthSendLimite;
	}

	public void setMonumber(String monumber) {
		this.monumber = monumber;
	}

	public void setMoNumber(String moNumber) {
		this.moNumber = moNumber;
	}

	public void setMoPrefix(String moPrefix) {
		this.moPrefix = moPrefix;
	}

	public void setMoPurpose(String moPurpose) {
		this.moPurpose = moPurpose;
	}

	public void setMoSmsNoti(String moSmsNoti) {
		this.moSmsNoti = moSmsNoti;
	}

	public void setMoTel(String moTel) {
		this.moTel = moTel;
	}

	public void setMoTest(String moTest) {
		this.moTest = moTest;
	}

	public void setNaverChannelId(String naverChannelId) {
		this.naverChannelId = naverChannelId;
	}

	public void setNaverKey(String naverKey) {
		this.naverKey = naverKey;
	}

	public void setnStatus(String nStatus) {
		this.nStatus = nStatus;
	}

	public void setChangeClientCel( String changeClientCel ) {
		this.changeClientCel = changeClientCel;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setOrderBySc(String orderBySc) {
		this.orderBySc = orderBySc;
	}

	public void setOrgCeo(String orgCeo) {
		this.orgCeo = orgCeo;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOrgDept(String orgDept) {
		this.orgDept = orgDept;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public void setOrgTitle(String orgTitle) {
		this.orgTitle = orgTitle;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public void setOutputFormatYN(String outputFormatYN) {
		this.outputFormatYN = outputFormatYN;
	}

	public void setPayerNo(String payerNo) {
		this.payerNo = payerNo;
	}

	public void setPicAddr1(String picAddr1) {
		this.picAddr1 = picAddr1;
	}

	public void setPicAddr2(String picAddr2) {
		this.picAddr2 = picAddr2;
	}

	public void setPicCel01(String picCel01) {
		this.picCel01 = picCel01;
	}

	public void setPicCel02(String picCel02) {
		this.picCel02 = picCel02;
	}

	public void setPicCel03(String picCel03) {
		this.picCel03 = picCel03;
	}

	public void setPicEmail01(String picEmail01) {
		this.picEmail01 = picEmail01;
	}

	public void setPicEmail02(String picEmail02) {
		this.picEmail02 = picEmail02;
	}

	public void setPicFax01(String picFax01) {
		this.picFax01 = picFax01;
	}

	public void setPicFax02(String picFax02) {
		this.picFax02 = picFax02;
	}

	public void setPicFax03(String picFax03) {
		this.picFax03 = picFax03;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public void setPicPost(String picPost) {
		this.picPost = picPost;
	}

	public void setPicTel01(String picTel01) {
		this.picTel01 = picTel01;
	}

	public void setPicTel02(String picTel02) {
		this.picTel02 = picTel02;
	}

	public void setPicTel03(String picTel03) {
		this.picTel03 = picTel03;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public void setRegperson(String regperson) {
		this.regperson = regperson;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public void setServiceName1(String serviceName1) {
		this.serviceName1 = serviceName1;
	}

	public void setServiceName2(String serviceName2) {
		this.serviceName2 = serviceName2;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public void setServiceTel1(String serviceTel1) {
		this.serviceTel1 = serviceTel1;
	}

	public void setServiceTel2(String serviceTel2) {
		this.serviceTel2 = serviceTel2;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

	public void setTossChannelId(String tossChannelId) {
		this.tossChannelId = tossChannelId;
	}

	public void setTossKey(String tossKey) {
		this.tossKey = tossKey;
	}

	public void setUlActionTime(String ulActionTime) {
		UlActionTime = ulActionTime;
	}

	public void setUlReason(String ulReason) {
		UlReason = ulReason;
	}

	public void setUlStatus(String ulStatus) {
		UlStatus = ulStatus;
	}

	public void setUniqueNo(String uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

	public void setUpOrgId(String upOrgId) {
		this.upOrgId = upOrgId;
	}

	public void setUpOrgName(String upOrgName) {
		this.upOrgName = upOrgName;
	}

	public void setUseApp(String useApp) {
		this.useApp = useApp;
	}

	public void setUseBilling(String useBilling) {
		this.useBilling = useBilling;
	}

	public void setUseMms(String useMms) {
		this.useMms = useMms;
	}

	public void setUseMo(String useMo) {
		this.useMo = useMo;
	}

	public void setUseMt(String useMt) {
		this.useMt = useMt;
	}

	public void setUseOrg(String useOrg) {
		this.useOrg = useOrg;
	}

	public void setUserLogReason(String userLogReason) {
		this.userLogReason = userLogReason;
	}

	public void setUseSms(String useSms) {
		this.useSms = useSms;
	}

	public void setUseSmsOld(String useSmsOld) {
		this.useSmsOld = useSmsOld;
	}

	public void setUseWeb(String useWeb) {
		this.useWeb = useWeb;
	}

	@Override
	public String toString() {
		return "AccountDTO [auth=" + auth + ", changeStatus=" + changeStatus
				+ ", clientCel=" + clientCel + ", clientId=" + clientId
				+ ", clientLevel=" + clientLevel + ", clientLevelOld="
				+ clientLevelOld + ", clientName=" + clientName
				+ ", clientOldPwd=" + clientOldPwd + ", clientPwd=" + clientPwd
				+ ", clientPwdOri=" + clientPwdOri + ", clientSeq=" + clientSeq
				+ ", clientStatus=" + clientStatus + ", clientTel=" + clientTel
				+ ", groupName=" + groupName + ", ipNo1=" + ipNo1 + ", ipNo2="
				+ ipNo2 + ", ipNo3=" + ipNo3 + ", isOld=" + isOld
				+ ", masterId=" + masterId + ", mngAddr1=" + mngAddr1
				+ ", mngAddr2=" + mngAddr2 + ", mngFax=" + mngFax
				+ ", mngOrgCeo=" + mngOrgCeo + ", mngPost=" + mngPost
				+ ", modifyDate=" + modifyDate + ", modifyId=" + modifyId
				+ ", moApproval=" + moApproval + ", moSmsNoti=" + moSmsNoti
				+ ", monthSendLimite=" + monthSendLimite + ", orgCode="
				+ orgCode + ", orgDept=" + orgDept + ", orgName=" + orgName
				+ ", orgTitle=" + orgTitle + ", regDate=" + regDate
				+ ", regId=" + regId + ", serviceStatus=" + serviceStatus
				+ ", systemInfo=" + systemInfo + ", uniqueNo=" + uniqueNo
				+ ", upOrgId=" + upOrgId + ", upOrgName=" + upOrgName
				+ ", useBilling=" + useBilling + ", useMms=" + useMms
				+ ", useMo=" + useMo + ", useMt=" + useMt + ", useOrg="
				+ useOrg + ", useSms=" + useSms + ", useSmsOld=" + useSmsOld
				+ ", useWeb=" + useWeb + ", clientPwdRe=" + clientPwdRe
				+ ", isAdmin=" + isAdmin + ", userLogReason=" + userLogReason
				+ ", ipType=" + ipType + ", levelName=" + levelName
				+ ", outputFormat=" + outputFormat + ", UlActionTime="
				+ UlActionTime + ", UlReason=" + UlReason + ", UlStatus="
				+ UlStatus + ", serviceDate=" + serviceDate + ", payerNo="
				+ payerNo + ", bankRegNo=" + bankRegNo + ", clientAgree="
				+ clientAgree + ", serviceName1=" + serviceName1
				+ ", serviceTel1=" + serviceTel1 + ", serviceName2="
				+ serviceName2 + ", serviceTel2=" + serviceTel2
				+ ", clientMail=" + clientMail + ", regperson=" + regperson
				+ ", clientCode=" + clientCode + ", monumber=" + monumber
				+ ", allow=" + allow + ", moPurpose=" + moPurpose + ", moMng="
				+ moMng + ", moTel=" + moTel + ", moPrefix=" + moPrefix
				+ ", moNumber=" + moNumber + ", outputFormatYN="
				+ outputFormatYN + ", moTest=" + moTest
				+ ", subscriptionStatus=" + subscriptionStatus + ", nStatus="
				+ nStatus + ", billMethod=" + billMethod + ", bankName="
				+ bankName + ", bankNo=" + bankNo + ", bankOwner=" + bankOwner
				+ ", billNo=" + billNo + ", billDept=" + billDept
				+ ", billName=" + billName + ", billCel01=" + billCel01
				+ ", billCel02=" + billCel02 + ", billCel03=" + billCel03
				+ ", billTel01=" + billTel01 + ", billTel02=" + billTel02
				+ ", billTel03=" + billTel03 + ", billFax01=" + billFax01
				+ ", billFax02=" + billFax02 + ", billFax03=" + billFax03
				+ ", billEmail01=" + billEmail01 + ", billEmail02="
				+ billEmail02 + ", billPost=" + billPost + ", billAddr1="
				+ billAddr1 + ", billAddr2=" + billAddr2 + ", picName="
				+ picName + ", orgCeo=" + orgCeo + ", orgNo=" + orgNo
				+ ", picCel01=" + picCel01 + ", picCel02=" + picCel02
				+ ", picCel03=" + picCel03 + ", picTel01=" + picTel01
				+ ", picTel02=" + picTel02 + ", picTel03=" + picTel03
				+ ", picFax01=" + picFax01 + ", picFax02=" + picFax02
				+ ", picFax03=" + picFax03 + ", picPost=" + picPost
				+ ", picAddr1=" + picAddr1 + ", picAddr2=" + picAddr2
				+ ", picEmail01=" + picEmail01 + ", picEmail02=" + picEmail02
				+ ", lgClientId=" + lgClientId + ", lgReqDate=" + lgReqDate
				+ ", lgStatus=" + lgStatus + ", lgChangeStatus="
				+ lgChangeStatus + ", lgNStatus=" + lgNStatus + ", lgCount="
				+ lgCount + ", lgType=" + lgType + ", kakaoChannelId="
				+ kakaoChannelId + ", kakaoProfileKey=" + kakaoKey
				+ ", naverChannelId=" + naverChannelId + ", naverProfileKey="
				+ naverKey + ", tossChannelId=" + tossChannelId
				+ ", tossProfileKey=" + tossKey + ", clientMemo=" + clientMemo
				+ ", orderBy=" + orderBy + ", orderBySc=" + orderBySc
				+ ", useApp=" + useApp + "]";
	}

}
