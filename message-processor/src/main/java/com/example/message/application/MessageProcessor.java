package com.example.message.application;

import com.example.common.domain.Chat;
import com.example.message.domain.BannedWordJudge;
import com.example.message.domain.BannedWordSupplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProcessor {
    private final BannedWordSupplier bannedWordSupplier;
    private BannedWordJudge bannedWordJudge;
    private final ObjectMapper objectMapper;
    private final Serde<String> stringSerde = Serdes.String();

    @PostConstruct
    public void postConstruct(){
        bannedWordJudge = new BannedWordJudge(bannedWordSupplier.findAll());
    }

    @Autowired
    public void process(StreamsBuilder streamsBuilder){
        handleChatMessage(streamsBuilder);
        handleRefreshBannedWordJudgeEvent(streamsBuilder);
    }


    private void handleChatMessage(StreamsBuilder streamsBuilder){
        KStream<String, String> inputStream = streamsBuilder.stream("topic-in-chat", Consumed.with(stringSerde, stringSerde));
        KStream<String, String> outStream = inputStream.mapValues(e -> processMessage(e));
        outStream.to("topic-out-chat", Produced.with(stringSerde, stringSerde));
    }

    private String processMessage(String message){
        System.out.println("message: "+message);
        try {
            Chat chat = objectMapper.readValue(message, Chat.class);
            Chat processChat = Chat.builder()
                    .roomId(chat.getRoomId())
                    .message(bannedWordJudge.judge(chat.getMessage()))
                    .build();
            System.out.println("chat: "+processChat);
            return objectMapper.writeValueAsString(processChat);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRefreshBannedWordJudgeEvent(StreamsBuilder streamsBuilder){
        KStream<String, String> inputStream = streamsBuilder.stream("topic-refresh-banned-word", Consumed.with(stringSerde, stringSerde));
        inputStream.foreach((key, value)-> {
            refreshBannedWordJudge();
        });
    }

    private void refreshBannedWordJudge(){
        log.info("start refresh");
        bannedWordJudge = new BannedWordJudge(bannedWordSupplier.findAll());
        log.info("end refresh");
    }
}
