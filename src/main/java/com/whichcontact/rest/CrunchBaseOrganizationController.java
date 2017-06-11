package com.whichcontact.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.whichcontact.core.entity.CrunchBaseFunding;
import com.whichcontact.core.service.CrunchBaseOrganizationService;
import com.whichcontact.core.service.ResponseDto;
@RequestMapping("/whichcontact")
@RestController
public class CrunchBaseOrganizationController {
    @Inject
    private CrunchBaseOrganizationService crunchbaseService;
    
@RequestMapping(value = "/crunchBaseOrganizationResponse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto viewFunding() {
       return crunchbaseService.saveOrganization();
}
@RequestMapping(value = "/crunchBasefundingResponse", method = RequestMethod.GET)
@ResponseBody
public List<CrunchBaseFunding> viewFundingResponse() {
   return crunchbaseService.viewFundingData();
}
}