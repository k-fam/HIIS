package myApp.client.org;

import java.util.Date;
import java.util.List;

import myApp.client.org.model.OrgInfoModel;
import myApp.client.org.model.OrgInfoModelProperties;
import myApp.frame.LoginUser;
import myApp.frame.service.InterfaceServiceCall;
import myApp.frame.service.ServiceCall;
import myApp.frame.service.ServiceRequest;
import myApp.frame.service.ServiceResult;
import myApp.frame.service.TreeGridDeleteRow;
import myApp.frame.service.TreeGridInsertRow;
import myApp.frame.service.TreeGridUpdate;
import myApp.frame.ui.AbstractDataModel;
import myApp.frame.ui.SimpleMessage;
import myApp.frame.ui.builder.GridBuilder;
import myApp.frame.ui.builder.SearchBarBuilder;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class Tab_OrgInfo extends VerticalLayoutContainer implements InterfaceServiceCall {
	
	private TreeGrid<OrgInfoModel> treeGrid = this.buildTreeGrid();
	private DateField dateField = new DateField();
	
	public Tab_OrgInfo(){

		ButtonBar buttonBar = new ButtonBar();
		
		dateField.setValue(new Date()); //오늘일자로 설정한다. 

		FieldLabel dateFiledLabel = new FieldLabel(dateField, "기준일");
		dateFiledLabel.setWidth(160);
		dateFiledLabel.setLabelWidth(50);
		
		buttonBar.add(dateFiledLabel);
		
		//dateField.set
		TextButton retrieveButton = new TextButton("조회"); 
		retrieveButton.setWidth(70);
		retrieveButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				retrieve(); 
			}
		}); 
		buttonBar.add(retrieveButton);

/*		
		TextButton expandAll = new TextButton("펼치기"); 
		expandAll.setWidth(60);
		expandAll.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				 treeGrid.expandAll();
			}
		}); 
		buttonBar.add(expandAll);
		
		TextButton collapseAll = new TextButton("감추기");
		collapseAll.setWidth(60);
		collapseAll.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				 treeGrid.collapseAll();
			}
		}); 
		buttonBar.add(collapseAll);
*/ 
		
		TextButton createRoot = new TextButton("회사등록"); 
		createRoot.setWidth(70);
		createRoot.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				
				// open popup
				addRootMenu(); 
			}
		}); 
		buttonBar.add(createRoot);

		TextButton addSubMenu = new TextButton("신규 조직등록");
		addSubMenu.setWidth(100);
		addSubMenu.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				addSubMenu(); 
			}
		}); 
		buttonBar.add(addSubMenu);

		TextButton updateButton = new TextButton("조직코드 종료");
		updateButton.setWidth(100);
		updateButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				update();  
			}
		}); 
		buttonBar.add(updateButton);

//		TextButton deleteButton = new TextButton("삭제");  
//		deleteButton.setWidth(60);
//		deleteButton.addSelectHandler(new SelectHandler(){
//			@Override
//			public void onSelect(SelectEvent event) {
//				deleteRow();  
//			}
//		}); 
//		buttonBar.add(deleteButton);  
		
		this.add(buttonBar, new VerticalLayoutData(1, 40));
		this.add(this.treeGrid, new VerticalLayoutData(1, 1));
	}
	
	public TreeGrid<OrgInfoModel> buildTreeGrid(){
		
		OrgInfoModelProperties properties = GWT.create(OrgInfoModelProperties.class);
		final GridBuilder<OrgInfoModel> gridBuilder = new GridBuilder<OrgInfoModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.korName(), 300, "조직명", new TextField());
		gridBuilder.addText(properties.sortOrder(), 50, "순서", new TextField()) ;
		gridBuilder.addText(properties.levelName(), 200, "조직구분", new TextField()) ;
		
//		gridBuilder.addBoolean(properties.hiddenYnFlag(), 50, "숨김") ;
		gridBuilder.addText(properties.note(), 300, "상세설명", new TextField());
	
		return gridBuilder.getTreeGrid(2);  
	}

	public void retrieve(){
		// 서버에서 전체 트리를 한번에 가져온다.  
		ServiceRequest request = new ServiceRequest("org.OrgInfo.selectByCompanyId");
		request.add("companyId", LoginUser.getLoginUser().getCompanyId());
		request.add("baseDate", dateField.getValue());
		
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}

	private void addChild(OrgInfoModel parentMenu) {
		if(parentMenu.getChildList() != null){
			List<AbstractDataModel> childList = parentMenu.getChildList(); 
			for(AbstractDataModel child : childList){
				OrgInfoModel childObject = (OrgInfoModel)child;
				this.treeGrid.getTreeStore().add(parentMenu, childObject);
				this.addChild(childObject);
			}
		}
	}
	
	private void addRootMenu(){
	
		OrgInfoModel roleObject = new OrgInfoModel(); 
		roleObject.setParentId(Long.parseLong("0"));
		TreeGridInsertRow<OrgInfoModel> treeGridInsert = new TreeGridInsertRow<OrgInfoModel>(); 
		treeGridInsert.addRoot(treeGrid.getTreeStore(), roleObject);  
	}

	private void addSubMenu(){
		OrgInfoModel parentModel = treeGrid.getSelectionModel().getSelectedItem(); // 선택된 Menu를 가져온다.
		
		if(parentModel == null){
			new SimpleMessage("선택된 상위 조직정보가 없어 하위조직을 등록할 수 없습니다. "); 
			return ; 
		}
		
		treeGrid.setExpanded(parentModel, true);
		OrgInfoModel orgInforModel = new OrgInfoModel();
		orgInforModel.setParentId(parentModel.getInfoId());
		
		TreeGridInsertRow<OrgInfoModel> service = new TreeGridInsertRow<OrgInfoModel>(); 
		service.addItem(treeGrid.getTreeStore(), parentModel, orgInforModel);  
	}
	
	private void update(){
		TreeGridUpdate<OrgInfoModel> service = new TreeGridUpdate<OrgInfoModel>(); 
		service.update(treeGrid.getTreeStore(), "sys.Menu.update"); 
	}
	
//	private void deleteRow(){
//		TreeGridDeleteRow<OrgInfoModel> service = new TreeGridDeleteRow<OrgInfoModel>();
//		List<OrgInfoModel> checkedList = treeGrid.getSelectionModel().getSelectedItems() ; 
//		service.deleteRow(treeGrid.getTreeStore(), checkedList, "sys.Menu.delete");
//	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
		}
		else { 
			this.treeGrid.getTreeStore().clear(); // 깨끗이 비운다. 
			
			for (AbstractDataModel model: result.getResult()) {
				// 서버에서 전체 트리를 한번에 가져온 후 트리를 구성한다.  
				OrgInfoModel roleObject = (OrgInfoModel)model;   
				this.treeGrid.getTreeStore().add(roleObject);
				this.addChild(roleObject); // child menu & object setting  
			}
		} 
		
	}
}
