package com.tj.service;

import javax.servlet.http.*;

public interface Service { // ServiceªÎInterface
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
