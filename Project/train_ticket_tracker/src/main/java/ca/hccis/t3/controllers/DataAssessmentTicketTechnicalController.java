package ca.hccis.t3.controllers;

import ca.hccis.t3.repositories.DataAssessmentTicketTechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.hccis.t3.repositories.CodeValueRepository;

@Controller
@RequestMapping("/data_assessment_ticket_technical")
public class DataAssessmentTicketTechnicalController {
    
    public final DataAssessmentTicketTechnicalRepository _dattr;
    private final CodeValueRepository _cvr;

    @Autowired
    public DataAssessmentTicketTechnicalController(DataAssessmentTicketTechnicalRepository dattr, CodeValueRepository cvr) {
        _dattr = dattr;
        _cvr = cvr;
    }
}
