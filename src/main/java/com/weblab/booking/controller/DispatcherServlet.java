package com.weblab.booking.controller;

import com.weblab.booking.config.BookingAppConfig;
import com.weblab.booking.entity.Reservation;
import com.weblab.booking.service.ReservationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AbstractApplicationContext container = null;

    public DispatcherServlet() {
        super();
    }
    
 	@Override
	public void init() throws ServletException {
//    	container = new ClassPathXmlApplicationContext("classpath:BookingAppConfig.xml");
		container = new AnnotationConfigApplicationContext(BookingAppConfig.class);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// STEP #1. GET REQUEST PARAMETERS
		request.setCharacterEncoding("UTF-8");
		String path = request.getRequestURI();
		if (path.equals("/")) {
			path = "/index";
		}
		String viewName = null;

		// STEP #2. DATA PROCESSING
		HandlerMapping handlerMapper = container.getBean(HandlerMapping.class);
		Controller controller = handlerMapper.getController(path);
		if (controller != null) {
			viewName = controller.handleRequest(request, response);
		}

		// STEP #3. OUTPUT PROCESSING RESULTS
		if (viewName != null) {
			viewName = viewName.trim();
			
			viewName = "/WEB-INF/views/" + viewName;
			
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request, response);
		}
		else {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append("지원되지 않는 URL입니다...");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
