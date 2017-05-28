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
package lu.uni.lassy.excalibur.examples.icrash.dev.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectFormatException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerNotBoundException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerOfflineException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.StringToNumberException;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActProxyAuthenticated.UserType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtPointOfInterest;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtDescription;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLatitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLongitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtMail;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPointOfInterestID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCategory;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtReal;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.actors.ActProxyAdministratorImpl;

/**
 * The Class AdminController, used to do functions that an admin can only do.
 */
public class AdminController extends AbstractUserController {
	
	/**
	 * Instantiates a new admin controller.
	 *
	 * @param aActAdmin the a act admin
	 * @throws RemoteException Thrown if the server is offline
	 * @throws NotBoundException Thrown if the server has not been bound in the RMI properties
	 */
	public AdminController(ActAdministrator aActAdmin) throws RemoteException, NotBoundException{	
		super(new ActProxyAdministratorImpl(aActAdmin));
	}
	/**
	 * If an administrator is logged in, will send an addCoordinator request to the server. If successful, it will return a PtBoolean of true
	 * @param coordinatorID The ID of the coordinator to create, the user specifies the ID, not the system in the creation process
	 * @param login The logon of the user to create. This is the username they will use at point of logon at the client front end
	 * @param password The password of the user to create. This is the password they will use at point of logon at the client front end
	 * @return Returns a PtBoolean true if the user was created, otherwise will return false
	 * @throws ServerOfflineException is an error that is thrown when the server is offline or not reachable
	 * @throws ServerNotBoundException is only thrown when attempting to access a server which has no current binding. This shouldn't happen, but you never know!
	 * @throws IncorrectFormatException is thrown when a Dt/Et information type does not match the is() method specified in the specification
	 */
	public PtBoolean oeAddCoordinator(String coordinatorID, String login, String password, String mail) throws ServerOfflineException, ServerNotBoundException, IncorrectFormatException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			DtCoordinatorID aDtCoordinatorID = new DtCoordinatorID(new PtString(coordinatorID));
			DtLogin aDtLogin = new DtLogin(new PtString(login));
			DtPassword aDtPassword = new DtPassword(new PtString(password));
			DtMail aDtMail = new DtMail(new PtString(mail));
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			ht.put(aDtCoordinatorID, aDtCoordinatorID.value.getValue());
			ht.put(aDtLogin, aDtLogin.value.getValue());
			ht.put(aDtPassword, aDtPassword.value.getValue());
			ht.put(aDtMail, aDtMail.value.getValue());
					try {
				return actorAdmin.oeAddCoordinator(aDtCoordinatorID, aDtLogin, aDtPassword, aDtMail);
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			}
		}
		return new PtBoolean(false);
	}
	/**
	 * If an administrator is logged in, will send a deleteCoordinator request to the server. If successful, it will return a PtBoolean of true
	 * @param coordinatorID The ID of the coordinator to delete
	 * @return Returns a PtBoolean true if the user was deleted, otherwise will return false
	 * @throws ServerOfflineException is an error that is thrown when the server is offline or not reachable
	 * @throws ServerNotBoundException is only thrown when attempting to access a server which has no current binding. This shouldn't happen, but you never know!
	 * @throws IncorrectFormatException is thrown when a Dt/Et information type does not match the is() method specified in the specification
	 */
	public PtBoolean oeDeleteCoordinator(String coordinatorID) throws ServerOfflineException, ServerNotBoundException, IncorrectFormatException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			DtCoordinatorID aDtCoordinatorID = new DtCoordinatorID(new PtString(coordinatorID));
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			ht.put(aDtCoordinatorID, aDtCoordinatorID.value.getValue());
			if (!aDtCoordinatorID.is().getValue())
				return new PtBoolean(false);
			try {
				return actorAdmin.oeDeleteCoordinator(aDtCoordinatorID);
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			}
		}
		return new PtBoolean(false);
	}

	
	/**
	 * If an administrator is logged in, will send an addPointOfInterest request to the server. If successful, it will return a PtBoolean of true
	 * @param aEtCategory the category chosen by the administrator for the point of interest.
	 * @param latitude is the latitude point of where the point of interest is situated
	 * @param longitude is the longitude point of where the point of interest is situated
	 * @return Returns a PtBoolean true if the point of interest was created, otherwise will return false
	 * @throws ServerOfflineException is an error that is thrown when the server is offline or not reachable
	 * @throws ServerNotBoundException is only thrown when attempting to access a server which has no current binding. This shouldn't happen, but you never know!
	 * @throws IncorrectFormatException is thrown when a Dt/Et information type does not match the is() method specified in the specification
 
	 */
	public PtBoolean oeAddPointOfInterest(EtCategory aEtCategory,DtGPSLocation location, DtDescription Description) throws  ServerNotBoundException,ServerOfflineException,IncorrectFormatException, StringToNumberException, RemoteException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			ht.put(location.latitude, Double.toString(location.latitude.value.getValue()));
			ht.put(location.longitude, Double.toString(location.longitude.value.getValue()));
			ht.put(aEtCategory, aEtCategory.name());
			ht.put(Description, Description.value.getValue());
			try {
				return actorAdmin.oeAddPointOfInterest(aEtCategory,location,Description);
			} catch (NumberFormatException e){
				Log4JUtils.getInstance().getLogger().error(e);
				throw new StringToNumberException("Longitude: " + location.longitude + " and latitude: " + location.latitude);
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			
	}
		}
		return new PtBoolean(false);
		
	}	
	
	public PtBoolean oeDeletePointOfInterest(DtPointOfInterestID adtPointOfInterestID) throws  ServerNotBoundException,ServerOfflineException,IncorrectFormatException, StringToNumberException, RemoteException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			ht.put(adtPointOfInterestID , adtPointOfInterestID.value.getValue());
			try {
				return actorAdmin.oeDeletePointOfInterest(adtPointOfInterestID);
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			
	}
		}
		return new PtBoolean(false);
		
	}	
	
	public PtBoolean oeEditPointOfInterest(DtPointOfInterestID adtPointOfInterestID,EtCategory aEtCategory,DtGPSLocation location, DtDescription Description) throws  ServerNotBoundException,ServerOfflineException,IncorrectFormatException, StringToNumberException, RemoteException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			ht.put(location.latitude, Double.toString(location.latitude.value.getValue()));
			ht.put(location.longitude, Double.toString(location.longitude.value.getValue()));
			ht.put(aEtCategory, aEtCategory.name());
			ht.put(Description, Description.value.getValue());
			ht.put(adtPointOfInterestID, adtPointOfInterestID.value.getValue());
			try {
				return actorAdmin.oeEditPointOfInterest(adtPointOfInterestID,aEtCategory,location,Description);
			} catch (NumberFormatException e){
				Log4JUtils.getInstance().getLogger().error(e);
				throw new StringToNumberException("Longitude: " + location.longitude + " and latitude: " + location.latitude);
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			
	}
		}
		return new PtBoolean(false);
		
	}	
	
	public ArrayList<CtPointOfInterest> oeSelectCategory(EtCategory aEtCategory) throws ServerOfflineException, ServerNotBoundException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			ht.put(aEtCategory, aEtCategory.name());
			try{
				return actorAdmin.oeSelectCategory(aEtCategory);
				
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			}
		}
		return null;
	}
	public ArrayList<CtPointOfInterest> oeSelectClosestTo(DtGPSLocation location) throws ServerOfflineException, ServerNotBoundException{
		if (getUserType() == UserType.Admin){
			ActProxyAdministratorImpl actorAdmin = (ActProxyAdministratorImpl)getAuth();
			Hashtable<JIntIs, String> ht = new Hashtable<JIntIs, String>();
			double dblLatitude = Double.parseDouble(location.latitude.toString());
			double dblLongitude = Double.parseDouble(location.longitude.toString());
			DtGPSLocation aDtGPSLocation = new DtGPSLocation(new DtLatitude(new PtReal(dblLatitude)), new DtLongitude(new PtReal(dblLongitude)));
			ht.put(aDtGPSLocation.latitude, Double.toString(aDtGPSLocation.latitude.value.getValue()));
			ht.put(aDtGPSLocation.longitude, Double.toString(aDtGPSLocation.longitude.value.getValue()));
			try{
				return actorAdmin.oeSelectClosestTo(location);
				
			} catch (RemoteException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerOfflineException();
			} catch (NotBoundException e) {
				Log4JUtils.getInstance().getLogger().error(e);
				throw new ServerNotBoundException();
			}
		}
		return null;
	}
}

