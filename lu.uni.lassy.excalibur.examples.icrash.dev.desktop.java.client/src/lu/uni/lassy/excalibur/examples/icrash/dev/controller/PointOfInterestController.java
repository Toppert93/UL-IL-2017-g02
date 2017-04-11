package lu.uni.lassy.excalibur.examples.icrash.dev.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerNotBoundException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerOfflineException;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtPointOfInterest;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCategory;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.Server;

/**
 * The Point of interest controller deals with any functions to do with PointOfInterest. This includes creation, retrieval,
 * handling and so on. It extends the AbstractController to handle checking of Dt information is correct
 */
public class PointOfInterestController {
	
	/** Parameter that allows the system to gain server access, the server function lives in the model of the client and  has RMI calls to access the server. */
	private Server server = Server.getInstance();
	
	/**
	 * Returns a list of all PointOfInterest in the system, without using a logged in user.
	 *
	 * @return Returns an ArrayList of type CtPoint of interest, which contains all PointOfInterest currently within the iCrashSystem
	 * @throws ServerOfflineException is an error that is thrown when the server is offline or not reachable
	 * @throws ServerNotBoundException is only thrown when attempting to access a server which has no current binding. This shouldn't happen, but you never know!
	 */
	public ArrayList<CtPointOfInterest> getListOfPointOfInterest() throws ServerOfflineException, ServerNotBoundException{
		try {
			return server.sys().getAllCtPointOfInterest();
		} catch (RemoteException e) {
			Log4JUtils.getInstance().getLogger().error(e);
			throw new ServerOfflineException();
		} catch (NotBoundException e) {
			Log4JUtils.getInstance().getLogger().error(e);
			throw new ServerNotBoundException();
		}
	}
	
	/**
	 * Returns a list of all PointOfInterest in the system that match the status type specified, without using a logged in user.
	 *
	 * @param statusOfPoint of interest the status of Point of interest
	 * @return Returns an ArrayList of type CtPoint of interest, which contains all PointOfInterest currently within the iCrashSystem
	 * @throws ServerOfflineException is an error that is thrown when the server is offline or not reachable
	 * @throws ServerNotBoundException is only thrown when attempting to access a server which has no current binding. This shouldn't happen, but you never know!
	 */
	public ArrayList<CtPointOfInterest> getListOfPointOfInterest(EtCategory Category) throws ServerOfflineException, ServerNotBoundException{
		return (ArrayList<CtPointOfInterest>)getListOfPointOfInterest().stream().filter(t -> t.Category == Category).collect(Collectors.toList());
	}	
	/**
	 * Closes the server connection that is open at the moment.
	 */
	public void closeServerConnection(){
		server.disconnectServer();
	}
}
