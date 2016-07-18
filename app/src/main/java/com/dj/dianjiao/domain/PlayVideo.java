package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/18.
 */
public class PlayVideo implements Serializable {

    private String action;
    private DataBean data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String serverIp;
        private String port;
        private String fileName;

        public String getServerIp() {
            return serverIp;
        }

        public void setServerIp(String serverIp) {
            this.serverIp = serverIp;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    @Override
    public String toString() {
        return "PlayVideo{" +
                "action='" + action + '\'' +
                ", data=" + data +
                '}';
    }
}
