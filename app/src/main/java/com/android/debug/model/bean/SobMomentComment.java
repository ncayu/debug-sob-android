package com.android.debug.model.bean;

import java.util.List;

public class SobMomentComment {

    private int total;
    private int pageSize;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPre;
    private int totalPage;
    private List<ListMomentBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPre() {
        return hasPre;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListMomentBean> getList() {
        return list;
    }

    public void setList(List<ListMomentBean> list) {
        this.list = list;
    }

    public static class ListMomentBean {
        private String id;
        private String userId;
        private String company;
        private String position;
        private String avatar;
        private String nickname;
        private String createTime;
        private String content;
        private int thumbUp;
        private String momentId;
        private boolean vip;
        private List<?> thumbUpList;
        private List<SubComment> subComments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getThumbUp() {
            return thumbUp;
        }

        public void setThumbUp(int thumbUp) {
            this.thumbUp = thumbUp;
        }

        public String getMomentId() {
            return momentId;
        }

        public void setMomentId(String momentId) {
            this.momentId = momentId;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public List<?> getThumbUpList() {
            return thumbUpList;
        }

        public void setThumbUpList(List<?> thumbUpList) {
            this.thumbUpList = thumbUpList;
        }

        public List<SubComment> getSubComments() {
            return subComments;
        }

        public void setSubComments(List<SubComment> subComments) {
            this.subComments = subComments;
        }
    }
    public static class SubComment{

        private String id;
        private String userId;
        private String avatar;
        private String position;
        private String company;
        private String nickname;
        private Object targetUserId;
        private String targetUserNickname;
        private boolean targetUserIsVip;
        private String createTime;
        private String content;
        private String commentId;
        private boolean vip;
        private List<?> thumbUpList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getTargetUserId() {
            return targetUserId;
        }

        public void setTargetUserId(Object targetUserId) {
            this.targetUserId = targetUserId;
        }

        public String getTargetUserNickname() {
            return targetUserNickname;
        }

        public void setTargetUserNickname(String targetUserNickname) {
            this.targetUserNickname = targetUserNickname;
        }

        public boolean isTargetUserIsVip() {
            return targetUserIsVip;
        }

        public void setTargetUserIsVip(boolean targetUserIsVip) {
            this.targetUserIsVip = targetUserIsVip;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public List<?> getThumbUpList() {
            return thumbUpList;
        }

        public void setThumbUpList(List<?> thumbUpList) {
            this.thumbUpList = thumbUpList;
        }
    }
}
