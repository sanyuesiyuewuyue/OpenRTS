package brainless.openrts.app.example;

import groovy.transform.CompileStatic

import java.util.logging.Logger

import brainless.openrts.event.EventManager
import brainless.openrts.event.client.ClientIsConnectedEvent

import com.google.inject.Inject
import com.jme3.network.Client
import com.jme3.network.ClientStateListener.DisconnectInfo

@CompileStatic
public class ClientStateListener implements com.jme3.network.ClientStateListener {

	private static final Logger logger = Logger.getLogger(ClientStateListener.class.getName());

	@Inject
	private EventManager eventManager;

	@Override
	public void clientConnected(Client c) {
		logger.info("connection to Server successfully. There is a game name:" + c.getGameName());
		ClientIsConnectedEvent evt = new ClientIsConnectedEvent(c.getVersion(), c.getGameName(), c.getId());
		eventManager.post(evt);
	}

	@Override
	public void clientDisconnected(Client c, DisconnectInfo info) {
		logger.warning("lost connection" + c.getGameName() + " because of " + info.reason);
	}


}