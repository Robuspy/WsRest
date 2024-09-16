package com.pablosrl.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public abstract class WebSocketServlet extends HttpServlet {

	protected abstract StreamInbound createWebSocketInbound(String subProtocol,
            HttpServletRequest request);

}