package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 * 关于我们
 */

public class AboutUsResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"article_title":"版权信息","article_content":"版权信息"},{"article_title":"软件许可使用协议","article_content":"软件许可使用协议"},{"article_title":"帮助中心","article_content":"帮助中心"}]
     */

    private int code;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * article_title : 版权信息
         * article_content : 版权信息
         */

        private String article_title;
        private String article_content;

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public String getArticle_content() {
            return article_content;
        }

        public void setArticle_content(String article_content) {
            this.article_content = article_content;
        }
    }
}
