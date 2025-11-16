package com.nopaper.work.earthquake.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nopaper.work.earthquake.dto.OllamaChatMsgRequest;
import com.nopaper.work.earthquake.dto.OllamaChatMsgResponse;
import com.nopaper.work.earthquake.service.OllamaChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/ollama")
@RequiredArgsConstructor
public class OllamaChatController {

	private final OllamaChatService ollamaChatService;

// PRE-DEFINED PROMPT / INSTRUCTION
	private static final String INSTRUCTION = "You are an expert Insurance Appraiser who deals in Earthquake Damage Assessments. As a primary assessor, Analyze the attached image in detail. "
			+ "Identify the main objects, understand the pattern " + "refer to the major assessment criteria - "
			+ "1. Emanating from coloumns/beams. "
			+ "2. Pattern - Usually diagonal / X-shaped / stair-step can be considered"
			+ "3. Width - Wide (> 3mm), often widening " + "4. Depth - Deep, through wall structure "
			+ "5. Progression - Sudden + keeps widening after quake "
			+ "6. Associated signs - Doors jamming, sloping floors, multiple cracks, adjacent beam or pillar"
			+ "7. Distinguish between False Positive and Positive for verification"
			+ "8. Identify the Negative Use Images for rejection; this is critical. "
			+ "9. Achieve 80% confidence Level" + "10. Provide the output in a structured bulleted list.";

	/*
	 * @PostMapping("/chat") public @ResponseBody OllamaChatMsgResponse
	 * chat(@RequestBody final OllamaChatMsgRequest request) {
	 * 
	 * return ollamaChatService.chat(request.getInputMessage()); }
	 * 
	 * @PostMapping("/chat-with-image") public @ResponseBody OllamaChatMsgResponse
	 * chatWithImage(@RequestPart final String inputMessage, final @RequestPart
	 * MultipartFile imageFile) {
	 * 
	 * return ollamaChatService.chatWithImage(inputMessage, imageFile); }
	 * 
	 * @GetMapping("/chat-with-stream") public Flux<String>
	 * chatWithStream(@RequestParam("inputMessage") final String inputMessage) {
	 * 
	 * return ollamaChatService.chatWithStream(inputMessage); }
	 */

	// Endpoint for standard text chat
	@PostMapping
	public OllamaChatMsgResponse chat(@RequestBody OllamaChatMsgRequest request) {
		return ollamaChatService.chat(request.getInputMessage());
	}

	// Endpoint for chat with image (multipart/form-data)
	@PostMapping(value = "/prompt-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public OllamaChatMsgResponse chatWithImage(@RequestParam("prompt") String inputMessage,
			@RequestParam("image") MultipartFile imageFile) {
		return ollamaChatService.chatWithImage(inputMessage, imageFile);
	}

	// Endpoint for chat with image (multipart/form-data)
	@PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public OllamaChatMsgResponse chatWithImageOnly(@RequestParam("image") MultipartFile imageFile) {

		return ollamaChatService.chatWithImage(INSTRUCTION, imageFile);
	}

	// Endpoint for streaming chat response
	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> chatWithStream(@RequestParam("prompt") String inputMessage) {
		return ollamaChatService.chatWithStream(inputMessage);
	}

}