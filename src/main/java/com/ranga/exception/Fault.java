package com.ranga.exception;

//~--- non-JDK imports --------------------------------------------------------

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Vishnu <vishnu.n@essilor.com.sg>
 * @since 05-04-2015
 */public class Fault {
    public Fault() {
    }

    @JsonProperty
    String tid;

    @JsonProperty
    List<Message> messages;

    public Fault(
            @JsonProperty("tid") String tid,
            @JsonProperty("messages") List<Message> messages) {
        this.tid = tid;
        this.messages = messages;
    }

    public Fault(String message, String code) {
        messages = new ArrayList<>(1);
        messages.add(new Message(message,code,Collections.emptyMap()));
    }

    public Fault(String tid,String message, String code) {
        this.tid = tid;
        messages = new ArrayList<>(1);
        messages.add(new Message(message,code,Collections.emptyMap()));
    }

    public Fault(String message, String code,Map<String,Object> argsMaps) {
        messages = new ArrayList<>(1);
        messages.add(new Message(message,code,argsMaps));
    }

    public Fault(String tid,String message, String code,Map<String,Object> argsMaps) {
        this.tid = tid;
        messages = new ArrayList<>(1);
        messages.add(new Message(message,code,argsMaps));
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getTid() {
        return tid;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("tid", tid)
                .add("messages", messages)
                .toString();
    }

    public static class Message {
        private String message;
        private String code;
        private Map<String,Object> argsMap = Collections.emptyMap();

        public Message(
                @JsonProperty("message") String message,
                @JsonProperty("code") String code,
                @JsonProperty("argsMap") Map<String,Object> argsMap
        ) {
            this.message = message;
            this.code = code;
            this.argsMap = argsMap;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        public Map<String, Object> getArgsMap() {
            return argsMap;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("message", message)
                    .add("code", code)
                    .add("argsMap", argsMap)
                    .toString();
        }
    }
}