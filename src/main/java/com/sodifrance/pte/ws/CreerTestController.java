package com.sodifrance.pte.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodifrance.pte.model.dto.CreateTestDto;
import com.sodifrance.pte.service.CreateTestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pte")
public class CreerTestController {
	
	@Autowired
	private CreateTestService createTestService;
	
	private static final String PATH__CREER_TEST = "/v1/creer/test";
	
	  @PostMapping(value = PATH__CREER_TEST)
	  public CreateTestDto createTest(@RequestBody CreateTestDto createTestDto) throws Exception {
		  log.debug("Creation d'un Test");
		  
		  return null;

	  }

}
