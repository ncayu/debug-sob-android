package com.android.debug.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeRecommend {

    private int total;
    private int pageSize;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPre;
    private int totalPage;
    private List<RecommendArticle> list;

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

    public List<RecommendArticle> getList() {
        return list;
    }

    public void setList(List<RecommendArticle> list) {
        this.list = list;
    }

    public static class RecommendArticle implements MultiItemEntity {
        public static final int ITEM_STYLE_SINGLE = 0;
        public static final int ITEM_STYLE_MULTI = 1;
        private String title;
        private String createTime;
        private int thumbUp;
        private int viewCount;
        private int type;
        private String nickName;
        private String avatar;
        private String id;
        private String userId;
        private boolean vip;
        private List<String> covers;
        /**
         * 1:多图片
         * 0:单图片
         */
        private int rvItemType = 0;

        public int getRvItemType() {
            return rvItemType;
        }

        public void setRvItemType(int rvItemType) {
            this.rvItemType = rvItemType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getThumbUp() {
            return thumbUp;
        }

        public void setThumbUp(int thumbUp) {
            this.thumbUp = thumbUp;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

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

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public List<String> getCovers() {
            return covers;
        }

        public void setCovers(List<String> covers) {
            this.covers = covers;
        }

        @Override
        public int getItemType() {
            return rvItemType;
        }
    }
}
