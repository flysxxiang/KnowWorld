package knowworld.com.zx.konwworld.bean;

import java.util.List;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-20
 */
public class NewsBean {

    private int ret_code;
    private PagebeanBean pagebean;

    public int getRet_code () {
        return ret_code;
    }

    public void setRet_code (int ret_code) {
        this.ret_code = ret_code;
    }

    public PagebeanBean getPagebean () {
        return pagebean;
    }

    public void setPagebean (PagebeanBean pagebean) {
        this.pagebean = pagebean;
    }

    public static class PagebeanBean {
        private int allPages;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<ContentlistBean> contentlist;

        public int getAllPages () {
            return allPages;
        }

        public void setAllPages (int allPages) {
            this.allPages = allPages;
        }

        public int getCurrentPage () {
            return currentPage;
        }

        public void setCurrentPage (int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum () {
            return allNum;
        }

        public void setAllNum (int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult () {
            return maxResult;
        }

        public void setMaxResult (int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist () {
            return contentlist;
        }

        public void setContentlist (List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public static class ContentlistBean {
            private String pubDate;
            private boolean havePic;
            private String title;
            private String channelName;
            private String source;
            private String channelId;
            private String nid;
            private String link;
            private String desc;

            public String getDesc () {
                return desc;
            }

            public void setDesc (String desc) {
                this.desc = desc;
            }

            private List<ImageurlsBean> imageurls;

            public String getPubDate () {
                return pubDate;
            }

            public void setPubDate (String pubDate) {
                this.pubDate = pubDate;
            }

            public boolean isHavePic () {
                return havePic;
            }

            public void setHavePic (boolean havePic) {
                this.havePic = havePic;
            }

            public String getTitle () {
                return title;
            }

            public void setTitle (String title) {
                this.title = title;
            }

            public String getChannelName () {
                return channelName;
            }

            public void setChannelName (String channelName) {
                this.channelName = channelName;
            }

            public String getSource () {
                return source;
            }

            public void setSource (String source) {
                this.source = source;
            }

            public String getChannelId () {
                return channelId;
            }

            public void setChannelId (String channelId) {
                this.channelId = channelId;
            }

            public String getNid () {
                return nid;
            }

            public void setNid (String nid) {
                this.nid = nid;
            }

            public String getLink () {
                return link;
            }

            public void setLink (String link) {
                this.link = link;
            }

            public List<ImageurlsBean> getImageurls () {
                return imageurls;
            }

            public void setImageurls (List<ImageurlsBean> imageurls) {
                this.imageurls = imageurls;
            }

            public static class ImageurlsBean {
                private int height;
                private int width;
                private String url;

                public int getHeight () {
                    return height;
                }

                public void setHeight (int height) {
                    this.height = height;
                }

                public int getWidth () {
                    return width;
                }

                public void setWidth (int width) {
                    this.width = width;
                }

                public String getUrl () {
                    return url;
                }

                public void setUrl (String url) {
                    this.url = url;
                }
            }
        }
    }
}
