package com.nopaper.work.earthquake.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nopaper.work.earthquake.dto.OllamaChatMsgResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class OllamaChatServiceImpl implements OllamaChatService {
	private final ChatClient chatClient;

	@Override
	public OllamaChatMsgResponse chat(final String inputMessage) {

		try {
			final String response = chatClient.prompt().user(inputMessage).call().content();
			return OllamaChatMsgResponse.builder().message(response).build();
		} catch (Exception e) {
			log.error("Error occurred while chatting with Ollama", e);
			return OllamaChatMsgResponse.builder().message("Service is currently unavailable").build();
		}
	}

	@Override
	public OllamaChatMsgResponse chatWithImage(final String inputMessage, final MultipartFile imageFile) {

		try {

			final String response = chatClient.prompt().user(promptUserSpec -> {
				promptUserSpec.text(inputMessage);
				promptUserSpec.media(MimeTypeUtils.IMAGE_PNG, new InputStreamResource(imageFile));
			}).call().content();

			return OllamaChatMsgResponse.builder().message(response).build();

		} catch (Exception e) {
			log.error("Error occurred while chatWithImage with Ollama", e);
			return OllamaChatMsgResponse.builder().message("Service is currently unavailable").build();
		}
	}

	@Override
	public Flux<String> chatWithStream(final String inputMessage) {
		try {

			return chatClient.prompt().user(inputMessage).stream().content().doOnNext(s -> {
				System.out.print("Received: " + s);
			});

		} catch (Exception e) {
			log.error("Error occurred while chatWithStream with Ollama", e);
		}
		return null;
	}
}