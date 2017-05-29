/*******************************************************************************
 * Copyright (c) 2014-2015 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 *     Christophe Kamphaus - Remote implementation of Actors
 *     Thomas Mortimer - Updated client to MVC and added new design patterns
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale.Category;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/*
 * This is the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.AdminController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.SystemStateController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectActorException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectFormatException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerNotBoundException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerOfflineException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.StringToNumberException;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbPointOfInterest;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIsActor;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtPointOfInterest;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCaptcha;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtDescription;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLatitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLongitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPointOfInterestID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCategory;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.PointOfInterest;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtReal;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.Message;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.coordinator.CreateICrashCoordGUI;

/*
 * This is the end of the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
/**
 * The Class ICrashGUIController, which deals with handling the GUI and it's functions for the Administrator.
 */
public class ICrashAdminGUIController extends AbstractAuthGUIController {
	
	int attempts = 0;
	
	/*
	* This section of controls and methods is to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
	* When replacing, remember to reassign the correct methods to the button event methods and set the correct types for the tableviews
	*/
	

    /** The pane containing the logon controls. */

	@FXML
	private TreeTableView<PointOfInterest> TTVPOI = new TreeTableView<PointOfInterest>();
	
	
	@FXML
	private Pane pnAdminLogon;

	@FXML
	private SplitPane pnAdminPointOfInterest;

	@FXML
	private ComboBox<String> ComboBoxPOI;

	@FXML
	private AnchorPane pnAdminLog;
	
	@FXML
	private AnchorPane PnAdminPOI;

	@FXML
	private AnchorPane PnAdminTreeView;

	/** The textfield that allows input of a username for logon. */
	@FXML
	private TextField txtfldAdminUserName;

	/** The textfield that allows input of a captcha for the captcha test. */
	@FXML
	private TextField txtfldAdminCaptcha;
	
	/** The label that contains the new captcha. */
	
    @FXML
    private Label lblCaptcha;


	/** The passwordfield that allows input of a password for logon. */
	@FXML
	private PasswordField psswrdfldAdminPassword;

	/** The button that initiates the login function. */
	@FXML
	private Button bttnAdminLogin;

	/** The button that initiates the login function. */
	@FXML
	private Button bttnAdminCaptcha;

	/** The button that initiates the reset password procedure */
	@FXML
	private Button bttnAdminResetPassword;

	/** The borderpane that contains the normal controls the user will use. */
	@FXML
	private BorderPane brdpnAdmin;
	

	/**
	 * The anchorpane that will have the add or delete coordinator controls
	 * added/removed from it
	 */
	@FXML
	private AnchorPane anchrpnCoordinatorDetails;

	/** The button that shows the controls for adding a coordinator */
	@FXML
	private Button bttnBottomAdminCoordinatorAddACoordinator;

	/** The button that shows the controls for deleting a coordinator */
	@FXML
	private Button bttnBottomAdminCoordinatorDeleteACoordinator;

	/** The button that shows the controls for deleting a coordinator */
	@FXML
	private Button bttnBottomAdminCoordinatorDemoteACoordinator;

	@FXML
	private Button bttnAdminAddpointofinterest;

	@FXML
	private Button bttnAdminEditpointofinterest;

	@FXML
	private Button bttnAdminDeletepointofinterest;
	
	@FXML 
	private Button bttnAdminCancel;
	

	@FXML
	private Button bttnAdminPointsOfInterestListPointsOfInterest;

	/** The tableview of the recieved messages from the system */
	@FXML
	private TableView<Message> tblvwAdminMessages;

	/** The button that allows a user to logoff */
	@FXML
	private Button bttnAdminLogoff;

	/**
	 * The button event that will show the controls for adding a coordinator
	 *
	 * @param event
	 *            The event type thrown, we do not need this, but it must be
	 *            specified
	 */
	@FXML
	void bttnBottomAdminCoordinatorAddACoordinator_OnClick(ActionEvent event) {
		showCoordinatorScreen(TypeOfEdit.Add);
	}

	/**
	 * The button event that will show the controls for deleting a coordinator
	 *
	 * @param event
	 *            The event type thrown, we do not need this, but it must be
	 *            specified
	 */
	@FXML
	void bttnBottomAdminCoordinatorDeleteACoordinator_OnClick(ActionEvent event) {
		showCoordinatorScreen(TypeOfEdit.Delete);
	}

	@FXML
	void bttnBottomAdminCoordinatorDemoteACoordinator_OnClick(ActionEvent event) {
		showCoordinatorScreen(TypeOfEdit.Demote);
	}

	@FXML
	void bttnAdminPointsOfInterestListPointsOfInterest_OnClick(ActionEvent event) throws IOException {
		pnAdminPointOfInterest.setVisible(true);
		pnAdminLog.setVisible(false);
		PnAdminPOI.setVisible(true);
		PnAdminTreeView.setVisible(true);
		

		ComboBoxPOI.getItems().removeAll(ComboBoxPOI.getItems());
		ComboBoxPOI.getItems().addAll("All","Hospital", "PoliceStation", "Garage", "Parking", "InsuranceOffice",
				"FireStation");
		ArrayList<CtPointOfInterest> Collection = DbPointOfInterest.getAllCtPointOfInterest();
		setupTableView(Collection);

	}
	
	@FXML
	void bttnAdminReturnToMain_OnClick(ActionEvent event) throws IOException{
		pnAdminPointOfInterest.setVisible(false);
		pnAdminLog.setVisible(true);
		PnAdminPOI.setVisible(false);
		PnAdminTreeView.setVisible(false);
	}

	public List<PointOfInterest> ChangeListToString(ArrayList<CtPointOfInterest> collection) {
		List<PointOfInterest> List = new ArrayList<PointOfInterest>();
		for (int i = 0; i < collection.size(); i++) {
			PointOfInterest Point =  new PointOfInterest(collection.get(i).id.toString(),collection.get(i).Category.name(),
			Double.toString(collection.get(i).location.latitude.value.getValue()),Double.toString(collection.get(i).location.longitude.value.getValue()),collection.get(i).Description.toString());
			
			List.add(Point);
		}

		return List;

	}

	@FXML
	void bttnAdminAddpointofinterest_OnClick(ActionEvent event) throws IOException {
		showPointOfInterestScreen(TypeOfEditPointOfInterest.Add);
		
	}
	
	@FXML
	void ComboBoxPOI_OnClick(ActionEvent event)throws IOException, ServerOfflineException, ServerNotBoundException{
		ArrayList<CtPointOfInterest> Collection = DbPointOfInterest.getAllCtPointOfInterest();
		if(ComboBoxPOI.getValue()!="All"){
		
		EtCategory category = EtCategory.valueOf(ComboBoxPOI.getValue());
		
		if(userController.oeSelectCategory(category) !=null){
		
		ArrayList<CtPointOfInterest> SortedCollection = SortedList(Collection);
		setupTableView(SortedCollection);
		}
		}else setupTableView(Collection);
		
	}
	
	public ArrayList<CtPointOfInterest> SortedList(ArrayList<CtPointOfInterest> Collection){
		
		ArrayList<CtPointOfInterest> SortedCollection =new ArrayList<CtPointOfInterest>();
			for(int i=0;i<Collection.size();i++){
				if(Collection.get(i).Category.name() == ComboBoxPOI.getValue() ){
					SortedCollection.add(Collection.get(i));
				}
			}
			
		
		return SortedCollection;
	}
	
	private void setupTableView(ArrayList<CtPointOfInterest> Collection){
		
	
		
		List<PointOfInterest> List = ChangeListToString(Collection);
		
		TTVPOI.getColumns().clear();
		
		TreeTableColumn<String, String> initial //
		= new TreeTableColumn<String, String>("");
		
		TreeTableColumn<PointOfInterest, String> colID //
				= new TreeTableColumn<PointOfInterest, String>("ID");

		
		TreeTableColumn<PointOfInterest, String> colCategory
				= new TreeTableColumn<PointOfInterest, String>("Category");

	
		TreeTableColumn<PointOfInterest, String> colLatitude //
				= new TreeTableColumn<PointOfInterest, String>("Latitude");

		TreeTableColumn<PointOfInterest, String> colLongitude //
				= new TreeTableColumn<PointOfInterest, String>("Longitude");

		TreeTableColumn<PointOfInterest, String> colDescription//
				= new TreeTableColumn<PointOfInterest, String>("Description");
	
		colID.setCellValueFactory(new TreeItemPropertyValueFactory<PointOfInterest, String>("id"));
		colID.setMinWidth(75);
        colCategory.setCellValueFactory(new TreeItemPropertyValueFactory<PointOfInterest, String>("category"));
        colCategory.setMinWidth(100);
        colLatitude.setCellValueFactory(new TreeItemPropertyValueFactory<PointOfInterest, String>("latitude"));
        colLongitude.setCellValueFactory(new TreeItemPropertyValueFactory<PointOfInterest, String>("longitude"));
        colDescription.setCellValueFactory(new TreeItemPropertyValueFactory<PointOfInterest, String>("description"));
        colDescription.setMinWidth(500);
        TTVPOI.getColumns().addAll(colID, colCategory,colLatitude, colLongitude, colDescription);
        PointOfInterest Initialrow = new PointOfInterest("","","","","");
        TreeItem<PointOfInterest> itemRoot = new TreeItem<PointOfInterest>(Initialrow);
        for(int i =0;i<List.size();i++){
        TreeItem<PointOfInterest> item = new TreeItem<PointOfInterest>(List.get(i));
        itemRoot.getChildren().add(item);
        }
       
        TTVPOI.setRoot(itemRoot);
        TTVPOI.setShowRoot(false);
	}
	@FXML 
	void bttnAdminCancel_OnClick(ActionEvent event) throws IOException{
		if(PnAdminPOI.getChildren().size()>=5 ){
			
		
		PnAdminPOI.getChildren().remove(PnAdminPOI.getChildren().size()-1);
		}
	}
	
	@FXML
	void bttnAdminEditpointofinterest_OnClick(ActionEvent event) throws IOException {
		showPointOfInterestScreen(TypeOfEditPointOfInterest.Edit);
	}

	@FXML
	void bttnAdminDeletepointofinterest_OnClick(ActionEvent event) throws IOException {
		showPointOfInterestScreen(TypeOfEditPointOfInterest.Delete);
	}

	/**
	 * The button event that will initiate the logging on of a user
	 *
	 * @param event
	 *            The event type thrown, we do not need this, but it must be
	 *            specified
	 */
	@FXML
	void bttnBottomLoginPaneLogin_OnClick(ActionEvent event) {
		logon();
	}

	/**
	 * The button event that will initiate a reset password of a user
	 * 
	 * @param event
	 */
	@FXML
	void bttnResetPassword_OnClick(ActionEvent event) {

		resetPassword();
	}

	/**
	 * The button event that will initiate a captcha validation of a user's
	 * captcha test
	 * 
	 * @param event
	 */
	@FXML
	void bttnBottomCaptchaPaneLogin_OnClick(ActionEvent event) {

		fillCaptcha();
	}

	/**
	 * The button event that will initiate the logging off of a user
	 *
	 * @param event
	 *            The event type thrown, we do not need this, but it must be
	 *            specified
	 */
	@FXML
	void bttnTopLogoff_OnClick(ActionEvent event) {
		logoff();
	}

	/*
	 * These are other classes accessed by this controller
	 */
	/**
	 * The user controller, for this GUI it's the admin controller and allows
	 * access to admin functions like adding a coordinator.
	 */
	private AdminController userController;

	/**
	 * Used to get the actor coordinator that was created by the admin, for
	 * creating the new window with.
	 */
	private SystemStateController systemstateController;

	/*
	 * Other things created for this controller
	 */
	/**
	 * The enumeration dictating the type of edit an admin is doing to a
	 * coordinator.
	 */
	private enum TypeOfEdit {

		/** Adding a coordinator. */
		Add,

		/** Deleting a coordinator. */
		Delete,

		Demote
	}

	private enum TypeOfEditPointOfInterest {

		Add,

		Edit,

		Delete,
		
		Cancel
	}

	/**
	 * The list of open windows in the system. We open a new window when a
	 * coordinator is created, so we also should close the window if the
	 * coordinator is deleted
	 */
	private ArrayList<CreateICrashCoordGUI> listOfOpenWindows = new ArrayList<CreateICrashCoordGUI>();
	/*
	 * Methods used within the GUI
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.
	 * AbstractAuthGUIController#logonShowPanes(boolean)
	 */
	protected void logonShowPanes(boolean loggedOn) {
		pnAdminLogon.setVisible(!loggedOn);
		brdpnAdmin.setVisible(loggedOn);
		bttnAdminLogoff.setDisable(!loggedOn);
		bttnAdminLogin.setDefaultButton(!loggedOn);
		

		if (!loggedOn) {
			txtfldAdminUserName.setText("");
			psswrdfldAdminPassword.setText("");
			txtfldAdminUserName.requestFocus();
			for (int i = anchrpnCoordinatorDetails.getChildren().size() - 1; i >= 0; i--)
				anchrpnCoordinatorDetails.getChildren().remove(i);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.
	 * AbstractAuthGUIController#captchaShowPanes(boolean)
	 */
	protected void captchaShowPanes(boolean captchaOn){
		
		String generatedCaptcha = "";
		DtCaptcha newCaptcha = new DtCaptcha(new PtString(generatedCaptcha));
		newCaptcha = newCaptcha.generateNewCaptcha();
		
		lblCaptcha.setText(newCaptcha.value.getValue());
		lblCaptcha.setVisible(captchaOn);
		pnAdminLogon.setVisible(!captchaOn);
		bttnAdminLogin.setDefaultButton(!captchaOn);
		bttnAdminResetPassword.setDisable(captchaOn);
		
		txtfldAdminCaptcha.setVisible(captchaOn);
		bttnAdminCaptcha.setVisible(captchaOn);
		bttnAdminCaptcha.setDefaultButton(captchaOn);
		if (!captchaOn){
			txtfldAdminUserName.setText("");
			psswrdfldAdminPassword.setText("");
			txtfldAdminCaptcha.setText("");
			txtfldAdminUserName.requestFocus();
			for (int i = anchrpnCoordinatorDetails.getChildren().size() -1; i >= 0; i--)
				anchrpnCoordinatorDetails.getChildren().remove(i);
		}
		
	}

	/**
	 * Server has gone down.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.AbstractGUIController
	 * #serverHasGoneDown()
	 */
	protected void serverHasGoneDown() {
		logoff();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.HasTables
	 * #setUpTables()
	 */
	public void setUpTables() {
		setUpMessageTables(tblvwAdminMessages);
	}
	
	private void showPointOfInterestScreen(TypeOfEditPointOfInterest type) {
		GridPane grdpn = new GridPane();
		TextField txtfldPOIID = new TextField();
		ComboBox<String> cmboboxCategory = new ComboBox<String>();
		TextField txtfldlongitude = new TextField();
		TextField txtfldlatitude = new TextField();
		TextField txtfldDescription = new TextField();
		txtfldPOIID.setPromptText("Point of interest ID");
		cmboboxCategory.getItems().removeAll(cmboboxCategory.getItems());
		cmboboxCategory.getItems().addAll("Hospital", "PoliceStation", "Garage", "Parking", "InsuranceOffice",
				"FireStation");
		txtfldlongitude.setPromptText("Longitude");
		txtfldlatitude.setPromptText("latitude");
		txtfldDescription.setPromptText("Description");
		Button bttntypeConfirm = new Button("Confirm");
		switch (type) {
		case Add:

			grdpn.add(cmboboxCategory, 1, 1);
			grdpn.add(txtfldlongitude, 1, 3);
			grdpn.add(txtfldlatitude, 1, 2);
			grdpn.add(txtfldDescription, 1, 4);
			grdpn.add(bttntypeConfirm, 1, 5);

			break;
		case Edit:
			grdpn.add(txtfldPOIID, 1, 0);
			grdpn.add(cmboboxCategory, 1, 1);
			grdpn.add(txtfldlongitude, 1, 3);
			grdpn.add(txtfldlatitude, 1, 2);
			grdpn.add(txtfldDescription, 1, 4);
			grdpn.add(bttntypeConfirm, 1, 5);
			break;
		case Delete:
			grdpn.add(txtfldPOIID, 1, 0);
			grdpn.add(bttntypeConfirm, 1, 1);
			break;
		
		}
		PnAdminPOI.getChildren().add(grdpn);
		AnchorPane.setTopAnchor(grdpn, 50.0);
		AnchorPane.setLeftAnchor(grdpn, 0.0);
		AnchorPane.setBottomAnchor(grdpn, 0.0);
		AnchorPane.setRightAnchor(grdpn, 0.0);
		bttntypeConfirm.setDefaultButton(true);
		bttntypeConfirm.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				PnAdminPOI.getChildren().remove(grdpn);
				if (!checkIfAllDialogHasBeenFilledIn(grdpn))
					showWarningNoDataEntered();
				else {
					try {
						switch (type) {
						case Add:

							DtDescription Description = new DtDescription(new PtString(txtfldDescription.getText()));

							DtLatitude latitude = new DtLatitude(new PtReal(Double.valueOf(txtfldlatitude.getText())));
							DtLongitude longitude = new DtLongitude(
									new PtReal(Double.valueOf(txtfldlongitude.getText())));
							DtGPSLocation location = new DtGPSLocation(latitude, longitude);

							EtCategory category = EtCategory.valueOf(cmboboxCategory.getValue());
						
							if (userController.oeAddPointOfInterest(category, location, Description) != null) {
								ArrayList<CtPointOfInterest> Collection = DbPointOfInterest.getAllCtPointOfInterest();
								setupTableView(Collection);

							} else
								showErrorMessage("Unable to add point of interest",
										"An error occured when addingg a point of interest");

							break;

						case Delete:
							DtPointOfInterestID ID = new DtPointOfInterestID(new PtString(txtfldPOIID.getText()));
							
							if (userController.oeDeletePointOfInterest(ID) != null) {
								ArrayList<CtPointOfInterest> Collection = DbPointOfInterest.getAllCtPointOfInterest();
								setupTableView(Collection);
							} else
								showErrorMessage("Unable to delete point of interest",
										"An error occured when deleting a point of interest");

							break;
						case Edit:
							DtDescription Description1 = new DtDescription(new PtString(txtfldDescription.getText()));
							DtPointOfInterestID ID1 = new DtPointOfInterestID(new PtString(txtfldPOIID.getText()));
							DtLatitude latitude1 = new DtLatitude(new PtReal(Double.valueOf(txtfldlatitude.getText())));
							DtLongitude longitude1 = new DtLongitude(
									new PtReal(Double.valueOf(txtfldlongitude.getText())));
							DtGPSLocation location1 = new DtGPSLocation(latitude1, longitude1);

							EtCategory category1 = EtCategory.valueOf(cmboboxCategory.getValue());
							
							if (userController.oeEditPointOfInterest(ID1, category1, location1, Description1) != null) {
								ArrayList<CtPointOfInterest> Collection = DbPointOfInterest.getAllCtPointOfInterest();
								setupTableView(Collection);
							} else
								showErrorMessage("Unable to edit point of interest",
										"An error occured when editing a point of interest");

							break;
						}
					} catch (RemoteException | ServerNotBoundException | ServerOfflineException
							| IncorrectFormatException | StringToNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

	}

	/**
	 * Shows the modify coordinator screen.
	 *
	 * @param type
	 *            The type of edit to be done, this could be add or delete
	 */
	private void showCoordinatorScreen(TypeOfEdit type) {
		for (int i = anchrpnCoordinatorDetails.getChildren().size() - 1; i >= 0; i--)
			anchrpnCoordinatorDetails.getChildren().remove(i);
		TextField txtfldUserID = new TextField();
		TextField txtfldUserName = new TextField();
		TextField txtfldMail = new TextField();
		PasswordField psswrdfldPassword = new PasswordField();
		txtfldUserID.setPromptText("User ID");
		txtfldMail.setPromptText("User Mail");
		Button bttntypOK = null;
		GridPane grdpn = new GridPane();
		grdpn.add(txtfldUserID, 1, 1);
		switch (type) {
		case Add:
			bttntypOK = new Button("Create");
			txtfldUserName.setPromptText("User name");
			psswrdfldPassword.setPromptText("Password");
			txtfldMail.setPromptText("User Mail");
			grdpn.add(txtfldUserName, 1, 2);
			grdpn.add(psswrdfldPassword, 1, 3);
			grdpn.add(txtfldMail, 1, 4);
			grdpn.add(bttntypOK, 1, 5);
			break;
		case Delete:
			bttntypOK = new Button("Delete");
			grdpn.add(bttntypOK, 1, 2);
			break;
		case Demote:
			// TODO
		}
		bttntypOK.setDefaultButton(true);
		bttntypOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!checkIfAllDialogHasBeenFilledIn(grdpn))
					showWarningNoDataEntered();
				else {
					try {
						DtCoordinatorID coordID = new DtCoordinatorID(new PtString(txtfldUserID.getText()));
						switch (type) {
						case Add:
							if (userController.oeAddCoordinator(txtfldUserID.getText(), txtfldUserName.getText(),
									psswrdfldPassword.getText(), txtfldMail.getText()) != null) {
								listOfOpenWindows.add(new CreateICrashCoordGUI(coordID,
										systemstateController.getActCoordinator(txtfldUserName.getText())));
								anchrpnCoordinatorDetails.getChildren().remove(grdpn);
							} else
								showErrorMessage("Unable to add coordinator",
										"An error occured when adding the coordinator");
							break;
						case Delete:
							if (userController.oeDeleteCoordinator(txtfldUserID.getText()).getValue()) {
								for (CreateICrashCoordGUI window : listOfOpenWindows) {
									if (window.getDtCoordinatorID().value.getValue().equals(coordID.value.getValue()))
										window.closeWindow();
								}
								anchrpnCoordinatorDetails.getChildren().remove(grdpn);
							} else
								showErrorMessage("Unable to delete coordinator",
										"An error occured when deleting the coordinator");
							break;
						}
					} catch (ServerOfflineException | ServerNotBoundException | IncorrectFormatException e) {
						showExceptionErrorMessage(e);
					}
				}
			}
		});
		anchrpnCoordinatorDetails.getChildren().add(grdpn);
		AnchorPane.setTopAnchor(grdpn, 0.0);
		AnchorPane.setLeftAnchor(grdpn, 0.0);
		AnchorPane.setBottomAnchor(grdpn, 0.0);
		AnchorPane.setRightAnchor(grdpn, 0.0);
		txtfldUserID.requestFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.
	 * AbstractAuthGUIController#logon()
	 */
	@Override
	public void logon() {
	
		if(txtfldAdminUserName.getText().length() > 0 && psswrdfldAdminPassword.getText().length() > 0){
			try {
				
				if (attempts < 3){
				if (userController.oeLogin(txtfldAdminUserName.getText(), psswrdfldAdminPassword.getText()).getValue())
					logonShowPanes(true);
				else attempts++;
				}
				else {
					captchaShowPanes(true);
				}
			}
			catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}	
    	}
    	else
    		showWarningNoDataEntered();
	
			try {
				if (userController.oeLogin(txtfldAdminUserName.getText(), psswrdfldAdminPassword.getText()).getValue())
					logonShowPanes(true);
			} catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}
		}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.
	 * AbstractAuthGUIController#logoff()
	 */
	@Override
	public void logoff() {
		try {
			if (userController.oeLogout().getValue()) {
				logonShowPanes(false);
			}
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.
	 * AbstractAuthGUIController#logon()
	 */
	
	@Override
	public void fillCaptcha() {
	
		if((txtfldAdminCaptcha.getText().length() > 0) && (txtfldAdminCaptcha.getText().equals(lblCaptcha.getText()))){
			try {
		
				if (userController.oeFillCaptcha(txtfldAdminCaptcha.getText()).getValue()){
					captchaShowPanes(false);
					attempts = 0;
				}
				else{captchaShowPanes(true);}
		}
	
			catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}	
    	}
			
		
    	else{
    		showWarningNoDataEntered();
			captchaShowPanes(true);
    	}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.
	 * AbstractGUIController#closeForm()
	 */
	@Override
	public void closeForm() {
		try {
			userController.removeAllListeners();
			systemstateController.closeServerConnection();
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		systemstateController = new SystemStateController();
		logonShowPanes(false);
		setUpTables();

	}

	@Override
	public PtBoolean setActor(JIntIsActor actor) {
		try {
			if (actor instanceof ActAdministrator)
				try {
					userController = new AdminController((ActAdministrator) actor);
					try {
						userController.getAuthImpl().listOfMessages.addListener(new ListChangeListener<Message>() {
							@Override
							public void onChanged(ListChangeListener.Change<? extends Message> c) {
								addMessageToTableView(tblvwAdminMessages, c.getList());
							}
						});
					} catch (Exception e) {
						showExceptionErrorMessage(e);
					}
				} catch (RemoteException e) {
					Log4JUtils.getInstance().getLogger().error(e);
					throw new ServerOfflineException();
				} catch (NotBoundException e) {
					Log4JUtils.getInstance().getLogger().error(e);
					throw new ServerNotBoundException();
				}
			else
				throw new IncorrectActorException(actor, ActAdministrator.class);
		} catch (ServerOfflineException | ServerNotBoundException | IncorrectActorException e) {
			showExceptionErrorMessage(e);
			return new PtBoolean(false);
		}
		return new PtBoolean(false);
	}

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logon()
	 */
	@Override
	public void resetPassword() {

		if(txtfldAdminUserName.getText().length() > 0){
			try {
				if (userController.oeResetPassword(txtfldAdminUserName.getText()).getValue())
					logonShowPanes(false);
			}
			catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}	
    	}
		
    	else
    		showWarningNoDataEntered();
	}

}
