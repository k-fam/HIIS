package myApp.client.org.model;

import java.util.Date;

import myApp.frame.ui.AbstractDataModel;

public class OrgCodeModel extends AbstractDataModel {
	private Long 	codeId;
	private Long 	companyId;
	private String 	orgCode;
	private Date 	openDate; 
	private Date 	closeDate;
	private String 	openCode;
	private String 	closeCode;
	private String 	note;
		
	@Override
	public void setKeyId(Long id) {
		this.setCodeId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getCodeId();
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getOpenCode() {
		return openCode;
	}

	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}

	public String getCloseCode() {
		return closeCode;
	}

	public void setCloseCode(String closeCode) {
		this.closeCode = closeCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
