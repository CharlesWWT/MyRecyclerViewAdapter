package com.example.packagerecyclerview;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class AppBean {

    /**
     * count : 10
     * error : false
     * results : [{"desc":"11.3","ganhuo_id":"56cc6d1d421aa95caa7075b1","publishedAt":"2015-11-03T06:04:59.454000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1exng5dd728j20go0m877n.jpg","who":"张涵宇"},{"desc":"5.25","ganhuo_id":"56cc6d1d421aa95caa7075e0","publishedAt":"2015-05-25T03:37:08.537000","readability":"","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1esfbgw6vc3j20gy0pedic.jpg","who":"张涵宇"},{"desc":"6.23","ganhuo_id":"56cc6d1d421aa95caa7076b4","publishedAt":"2016-05-03T12:13:53.904000","readability":"","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1etdsksgctqj20hs0qowgy.jpg","who":"张涵宇"},{"desc":"6.30","ganhuo_id":"56cc6d1d421aa95caa7076e5","publishedAt":"2015-07-02T03:50:48.082000","readability":"","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1etlw75so1hj20qo0hsgpk.jpg","who":"张涵宇"},{"desc":"6.2","ganhuo_id":"56cc6d1d421aa95caa707715","publishedAt":"2015-06-02T03:44:53.404000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1esojpl5gmgj20qo0hstbs.jpg","who":"张涵宇"},{"desc":"8.13","ganhuo_id":"56cc6d1d421aa95caa707762","publishedAt":"2015-08-13T03:58:48.460000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1ev0qk6r38pj20hs0qoq58.jpg","who":"张涵宇"},{"desc":"8.24","ganhuo_id":"56cc6d1d421aa95caa7077d9","publishedAt":"2015-08-24T04:06:07.594000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1evdga4dimoj20qo0hsmzf.jpg","who":"张涵宇"},{"desc":"8.27","ganhuo_id":"56cc6d1d421aa95caa7077fa","publishedAt":"2015-08-27T03:52:53.834000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1evgwbi4o1oj20hq0qomzp.jpg","who":"张涵宇"},{"desc":"9.1","ganhuo_id":"56cc6d1d421aa95caa707827","publishedAt":"2015-09-01T03:57:44.180000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1evmsyc7thzj20hs0qon0n.jpg","who":"张涵宇"},{"desc":"8/25","ganhuo_id":"56cc6d1d421aa95caa7077ef","publishedAt":"2015-08-25T04:08:30.735000","readability":"","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034gw1eveq3prvojj20k00qetbj.jpg","who":"daimajia"}]
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc : 11.3
         * ganhuo_id : 56cc6d1d421aa95caa7075b1
         * publishedAt : 2015-11-03T06:04:59.454000
         * readability :
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1exng5dd728j20go0m877n.jpg
         * who : 张涵宇
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
