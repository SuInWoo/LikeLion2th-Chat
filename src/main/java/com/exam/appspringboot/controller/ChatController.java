package com.exam.appspringboot.controller;

import com.exam.appspringboot.RsData;
import com.exam.appspringboot.entity.ChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record WriteMessageRequest(String authorName, String content) {
    }

    public record WriteMessageResponse(long id) {
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName(), req.content());

        chatMessages.add(message);

        return new RsData<>(
                "S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId())
        );
    }

    public record MessageResponse(List<ChatMessage> messages, long count) {
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessageResponse> messages() {
        return new RsData<>(
                "S-1",
                "성공",
                new MessageResponse(chatMessages, chatMessages.size())
        );
    }

}
