package com.spring.boot.curd.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.curd.app.exception.EMSException;
import com.spring.boot.curd.app.pojo.Employee;
import com.spring.boot.curd.app.pojo.Response;
import com.spring.boot.curd.app.service.EMSService;

@RestController
@RequestMapping(value = "/EMS")
@EnableAutoConfiguration
public class EMSController {
	@Autowired(required = true)
	private EMSService service;

	private Logger logger = LoggerFactory.getLogger(EMSController.class);
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Response addEmployee(@RequestBody Employee e) {
		Response response;
		try {
			logger.debug("input request for addEmployee() method is {}",
					mapper.writeValueAsString(e));
			response = service.add(e);
			logger.info("response of addEmployee() method is {}",
					mapper.writeValueAsString(response));
		} catch (EMSException | JsonProcessingException exception) {
			response = new Response(false, exception.getLocalizedMessage());
			logger.error("Error while Insertion : " + response);

		}
		return response;
	}

	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET)
	public Response getAllEmployee() {
		Response response = new Response();
		List<Employee> employees = null;

		try {
			employees = service.getAllEmployee();
			logger.debug("response  for getAllEmployee() method is {}",
					mapper.writeValueAsString(employees));
			if (!employees.isEmpty()) {
				response.setEmployees(employees);
				response.setSuccess(true);
				response.setStatus("success");
			} else {
				response.setSuccess(false);
				response.setStatus("Failure");
			}
		} catch (JsonProcessingException e) {
			logger.error("Error while getAll : " + e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public Response getEmployee(@PathVariable("id") int id) throws EMSException {
		Response response = new Response();
		Employee employee = null;

		try {
			employee = service.findEmployeeById(id);
			logger.debug("input request for getEmployee() method is {}", id);
			if (employee != null) {
				response.setEmployee(employee);
				response.setSuccess(true);
				response.setStatus("success");
			} else {
				response.setSuccess(false);
				response.setStatus("Failure");
			}
			logger.info("response  for getEmployee() method is {}",
					mapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			logger.error("error while getEmployee {}", e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Response deleteEmployee(@PathVariable("id") int id) {
		Response response;
		try {
			logger.debug("input request for deleteEmployee() method is {}", id);
			response = service.deleteEmployee(id);

			logger.info("response of deleteEmployee() method is {}",
					mapper.writeValueAsString(response));
		} catch (EMSException | JsonProcessingException exception) {
			response = new Response(false, exception.getLocalizedMessage());
			logger.info("Error while Delet : " + response);

		}
		return response;
	}
}
