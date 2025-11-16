package com.nopaper.work.earthquake.service;

import org.springframework.web.multipart.MultipartFile;

import com.nopaper.work.earthquake.dto.OllamaChatMsgResponse;

import reactor.core.publisher.Flux;

public interface OllamaChatService {

	OllamaChatMsgResponse chat(String inputMessage);

	OllamaChatMsgResponse chatWithImage(String inputMessage, MultipartFile imageFile);

	Flux<String> chatWithStream(String inputMessage);

}
