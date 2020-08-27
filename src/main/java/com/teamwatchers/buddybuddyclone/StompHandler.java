package com.teamwatchers.buddybuddyclone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    // websocket을 통해 들어온 요청이 처리 되기전 실행된다.
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String jwtToken = accessor.getFirstNativeHeader("token");
            jwtTokenProvider.validateToken(jwtToken);
        } else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            String roomId = chatService.getRoomId(
                    Optional.ofNullable((String) message.getHeaders().get("simpDestination"))
                            .orElse("InvalidRoomId")
            );

            String sessionId = (String) message.getHeaders().get("simpSessionId");
            chatRoomRepository.setUserEnterInfo(sessionId, roomId);
            chatRoomRepository.plusUserCount(roomId);

            String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser"))
                    .map(Principal::getName)
                    .orElse("UnknownUser");

            chatService.sendChatMessage(
                    ChatMessage.builder().type(ChatMessage.MessageType.ENTER)
                    .roomId(roomId).sender(name).build());

        } else if (StompCommand.DISCONNECT == accessor.getCommand()) {
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            String roomId = chatRoomRepository.getUserEnterRoomId(sessionId);

            chatRoomRepository.minusUserCount(roomId);
            String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser"))
                    .map(Principal::getName)
                    .orElse("UnknownUser");

            chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.QUIT).roomId(roomId).sender(name).build());
            chatRoomRepository.removeUserEnterInfo(sessionId);

        }
        return message;
    }
}
