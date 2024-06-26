package com.example.sse.service.impl;

import com.example.sse.service.EventService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Long.MAX_VALUE;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@Component
public class SseService implements EventService {

  private final Collection<SseEmitter> emitters = new CopyOnWriteArrayList<>();

  @Override
  public SseEmitter create() {

    SseEmitter emitter = new SseEmitter(MAX_VALUE);

    emitter.onCompletion(() -> emitters.remove(emitter));
    emitter.onTimeout(() -> emitters.remove(emitter));
    emitter.onError(throwable -> {
      emitters.remove(emitter);
      emitter.completeWithError(throwable);
    });

    emitters.add(emitter);

    return emitter;
  }

  @Override
  public <T> void sendEvent(T payload, String eventName) {

    var event = event().data(payload).name(eventName).build();

    for (SseEmitter emitter : emitters) {
      try {
        emitter.send(event);
      } catch (IOException exception) {
        emitter.completeWithError(exception);
      }
    }
  }
}
