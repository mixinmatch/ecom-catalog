package com.github.mixinmatch.appointments.models;

    public class Message
    {
        public Header header;
        public Content content;

        public Message() { }

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }
    }


