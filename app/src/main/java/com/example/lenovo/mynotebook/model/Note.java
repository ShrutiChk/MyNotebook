package com.example.lenovo.mynotebook.model;

/**
 * Created by Lenovo on 4/23/2020.
 */

public class Note {

        private String title;
        private String content;

        public Note(){}
        public Note(String title,String content){
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

}
