package com.tj.service;

import javax.servlet.http.*;

public interface Service { // Service��Interface
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
