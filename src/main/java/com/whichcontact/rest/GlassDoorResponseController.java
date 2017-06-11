package com.whichcontact.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.whichcontact.core.entity.Employers;
import com.whichcontact.core.service.GlassDoorService;
@RequestMapping("/whichcontact")
@RestController
public class GlassDoorResponseController {
    @Inject
    private GlassDoorService glassDoorService;
 /**
 * @return
 */
@RequestMapping(value = "/viewEmployeeData", method = RequestMethod.GET)
    @ResponseBody
    public List < Employers > userdetail() {
       return glassDoorService.viewEmployee();
    }
}