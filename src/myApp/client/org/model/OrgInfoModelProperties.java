package myApp.client.org.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface OrgInfoModelProperties extends PropertyAccess<OrgInfoModel> {
	
	ModelKeyProvider<OrgInfoModel> keyId();	
	ValueProvider<OrgInfoModel, Long> 	infoId();
	ValueProvider<OrgInfoModel, Date> 	modDate();
	ValueProvider<OrgInfoModel, String> modCode();
	ValueProvider<OrgInfoModel, String> modName();
	ValueProvider<OrgInfoModel, String> modDetail();
	ValueProvider<OrgInfoModel, Long> 	parentId();
	ValueProvider<OrgInfoModel, Long> 	codeId();
	ValueProvider<OrgInfoModel, String> korName();
	ValueProvider<OrgInfoModel, String> shortName();
	ValueProvider<OrgInfoModel, String> engName();
	ValueProvider<OrgInfoModel, String> levelCode();
	ValueProvider<OrgInfoModel, String> levelName();
	ValueProvider<OrgInfoModel, String> sortOrder();
	ValueProvider<OrgInfoModel, String> note();
	
}

