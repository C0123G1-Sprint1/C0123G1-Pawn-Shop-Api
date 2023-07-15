package com.example.back_end.controller;

import com.example.back_end.dto.IPageCustomerDto;
import com.example.back_end.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by: DinhHD
 * Date created: 13/07/2023
 * Function: do about customer selection interface
 * <p>
 * // * @param Customer
 *
 * @return getAllCustomer(), searchCustomer()
 */

@RestController
@RequestMapping("/api/employee/contract/customer")
@CrossOrigin("*")
public class PageCustomerRestController {
    @Autowired
    private ICustomerService iCustomerService;


    @GetMapping("")
    public ResponseEntity<Page<IPageCustomerDto>> getAllCustomer(@PageableDefault(size = 3) Pageable pageable) {
        Page<IPageCustomerDto> iCustomerDtoPage = iCustomerService.findByCustomer(pageable);
        if (iCustomerDtoPage.isEmpty()) {
            return new ResponseEntity<>(iCustomerDtoPage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iCustomerDtoPage, HttpStatus.OK);
    }

    @GetMapping("/contract/search")
    public ResponseEntity<Page<IPageCustomerDto>> searchCustomer(@PageableDefault(size = 3) Pageable pageable, @RequestParam("name") String name) {
        Page<IPageCustomerDto> iCustomerDtoPage = iCustomerService.searchCustomer(pageable, name);
        if (iCustomerDtoPage.isEmpty()) {
            return new ResponseEntity<>(iCustomerDtoPage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iCustomerDtoPage, HttpStatus.OK);
    }

    @GetMapping("/contract/{id}")
    public ResponseEntity<IPageCustomerDto> getByIdCustomer(@PathVariable("id") String id) {
      IPageCustomerDto iCustomerDto=  iCustomerService.findByIdCustomer(id);
      if (iCustomerDto.equals(id)){
          return new ResponseEntity<>(iCustomerDto,HttpStatus.BAD_REQUEST);
      }
        return new ResponseEntity<>(iCustomerDto,HttpStatus.OK);
    }
}