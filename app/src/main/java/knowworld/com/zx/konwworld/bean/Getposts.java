package knowworld.com.zx.konwworld.bean;

import java.util.List;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-17
 */
public class Getposts {
    private String error;
    private int count;
    /**
     * title : 如何看待阿里「月饼门」中最后一人（第五人）也被开除？
     * time : 2016-09-15 13:21:09
     * summary : 从此看出他老大已经全力保全他了。自古英雄惜英雄
     * questionid : 50659896
     * answerid : 122059081
     * authorname : luka
     * authorhash : 58e1aad55a13b58538cd920333f7a0bf
     * avatar : https://pic1.zhimg.com/c0521f14392243947068d5932a6b4350_l.jpg
     * vote : 1907
     */
    private List<AnswersBean> answers;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class AnswersBean {
        private String title;
        private String time;
        private String summary;
        private String questionid;
        private String answerid;
        private String authorname;
        private String authorhash;
        private String avatar;
        private String vote;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getQuestionid() {
            return questionid;
        }

        public void setQuestionid(String questionid) {
            this.questionid = questionid;
        }

        public String getAnswerid() {
            return answerid;
        }

        public void setAnswerid(String answerid) {
            this.answerid = answerid;
        }

        public String getAuthorname() {
            return authorname;
        }

        public void setAuthorname(String authorname) {
            this.authorname = authorname;
        }

        public String getAuthorhash() {
            return authorhash;
        }

        public void setAuthorhash(String authorhash) {
            this.authorhash = authorhash;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getVote() {
            return vote;
        }

        public void setVote(String vote) {
            this.vote = vote;
        }
    }
}
