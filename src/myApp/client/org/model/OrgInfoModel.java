package myApp.client.org.model;

import java.util.Date;
import java.util.List;

import myApp.frame.ui.AbstractDataModel;

public class OrgInfoModel extends AbstractDataModel {
	private Long 	infoId;
	private Date	modDate ;
	private String 	modCode;
	private String 	modName ; 
	private String 	modDetail ;
	private Long 	parentId ;
	private Long 	codeId;
	private String 	korName;
	private String 	shortName;
	private String 	engName;
	private String 	levelCode;
	private String 	levelName;
	private String 	sortOrder;
	private String 	note;
	
	private OrgCodeModel orgCodeModel;

	private List<AbstractDataModel> childList;
	
	@Override
	public void setKeyId(Long id) {
		this.setInfoId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getInfoId();
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getModCode() {
		return modCode;
	}

	public void setModCode(String modCode) {
		this.modCode = modCode;
	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getModDetail() {
		return modDetail;
	}

	public void setModDetail(String modDetail) {
		this.modDetail = modDetail;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getKorName() {
		return korName;
	}

	public void setKorName(String korName) {
		this.korName = korName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrgCodeModel getOrgCodeModel() {
		return orgCodeModel;
	}

	public void setOrgCodeModel(OrgCodeModel orgCodeModel) {
		this.orgCodeModel = orgCodeModel;
	}

	public List<AbstractDataModel> getChildList() {
		return childList;
	}

	public void setChildList(List<AbstractDataModel> childList) {
		this.childList = childList;
	}
	
}
