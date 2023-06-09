package com.goit.hibernate.app.servlet;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.dto.CustomerDto;
import com.goit.hibernate.app.entity.CustomerEntity;
import com.goit.hibernate.app.mapper.CustomerMapper;
import com.goit.hibernate.app.repository.CustomerEntityRepository;
import com.goit.hibernate.app.servlet.exception.HibernateAppBadRequestException;
import com.goit.hibernate.app.servlet.exception.HibernateAppNotFoundException;
import com.goit.hibernate.app.servlet.exception.handler.ServletExceptionHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;

import static com.goit.hibernate.app.servlet.ServletUtils.resolveNumericPathVariable;
import static com.goit.hibernate.app.servlet.ServletUtils.sendJsonResponse;
import static com.goit.hibernate.app.util.Constants.APP_ENV;
import static java.util.Objects.isNull;

public class CustomerServlet extends HttpServlet {

    private CustomerEntityRepository customerEntityRepository;
    private CustomerMapper customerMapper;
    private Gson gson;

    @Override
    public void init(ServletConfig config) {
        gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
        Environment environment = (Environment) config.getServletContext().getAttribute(APP_ENV);
        customerEntityRepository = new CustomerEntityRepository(new Datasource(environment));
        customerMapper = CustomerMapper.instance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    List<CustomerDto> customerDtos = resolveNumericPathVariable(request.getRequestURI())
                            .map(id -> {
                                CustomerEntity entity = customerEntityRepository.findById(id);
                                validateIsCustomerExists(id, entity);
                                CustomerDto customerDto = customerMapper.mapEntityToDto(entity);
                                return List.of(Objects.requireNonNullElse(customerDto, new CustomerDto()));
                            }).orElse(customerMapper.mapEntityToDto(customerEntityRepository.findAll()));
                    sendJsonResponse(response, customerDtos);
                })
                .build()
                .doAction();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    String json = new String(request.getInputStream().readAllBytes());
                    CustomerDto customerDto = gson.fromJson(json, CustomerDto.class);
                    CustomerEntity entity = customerMapper.mapDtoToEntity(customerDto);
                    CustomerEntity saved = customerEntityRepository.save(entity);
                    CustomerDto mapped = customerMapper.mapEntityToDto(saved);
                    sendJsonResponse(response, mapped);
                })
                .build()
                .doAction();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    String json = new String(request.getInputStream().readAllBytes());
                    CustomerDto customerDto = gson.fromJson(json, CustomerDto.class);
                    validateCustomer(customerDto);
                    CustomerEntity entity = customerMapper.mapDtoToEntity(customerDto);
                    CustomerEntity saved = customerEntityRepository.save(entity);
                    CustomerDto mapped = customerMapper.mapEntityToDto(saved);
                    sendJsonResponse(response, mapped);
                })
                .build()
                .doAction();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        ServletExceptionHandler.builder()
                .servletResponse(response)
                .action(() -> {
                    resolveNumericPathVariable(request.getRequestURI())
                            .map(id -> customerEntityRepository.deleteById(id));
                    response.setStatus(HttpCode.OK);
                })
                .build()
                .doAction();
    }

    private static void validateCustomer(CustomerDto customerDto) {
        if (isNull(customerDto.getId())) {
            throw new HibernateAppBadRequestException("Customer id is required");
        }
    }

    private static void validateIsCustomerExists(Long id, CustomerEntity entity) {
        if (isNull(entity)) {
            throw new HibernateAppNotFoundException("customer with id:" + id + " not found");
        }
    }

}
