package com.ihuxu.chatxserver.util.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	private ServerSocket serverSocket;

	public Server() {}

	public void start() {
		System.out.println("Server starting...");
		/** crontab **/
		ServerCrontab.checkClientSocket();

		/** listening **/
		try {
			serverSocket = new ServerSocket(1722);
			boolean go = true;
			while (go) {
				/** listening to the new socket **/
				Socket socket = serverSocket.accept();

				/** client thread **/
				System.out.println("the recieved the serverSocket.");
				try {
					ClientThread clientThread = new ClientThread(socket);
					ClientThreadManager.addClientThread(clientThread.getClientKey(), clientThread);
					clientThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
	}

	public void restart() {
	}

}
