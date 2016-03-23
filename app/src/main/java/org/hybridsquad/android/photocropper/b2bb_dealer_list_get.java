package org.hybridsquad.android.photocropper;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17 0017.
 */
public class b2bb_dealer_list_get {


    /**
     * pid : 商品id
     * title : 商品名称
     * code : 商品编号
     * url : 对应的url链接
     * bclass : [{"bid":"品牌id","title":"品牌标题","ischeck":"是否为选中的状态(1为选中，0为未选中)"}]
     * pclass : [{"cid":"商品分类id","title":"标题","ischeck":"是否为选中的状态(1为选中，0为未选中)"}]
     * aclass : [{"aid":"属性id","title":"标题名称","ischeck":"是否为选中的状态(1为选中，0为未选中)"}]
     * rclass : [{"rid":"认证标示id","title":"标题","ischeck":"是否为选中的状态(1为选中，0为未选中)"}]
     * dclass : [{"did":"显示标识id","title":"显示标题","ischeck":"是否为选中的状态(1为选中，0为未选中)"}]
     * sale_statue : 销售状态（0未销售，1销售中）
     * cover : 封面图片url
     * imgs : ["图片通用相册url"]
     * description : 商品通用说明
     * list : [{"pid":"推荐商品id","pcode":"推荐商品编号","title":"推荐商品名称"}]
     */

    private String pid;
    private String title;
    private String code;
    private String url;
    private String sale_statue;
    private String cover;
    private String description;
    /**
     * bid : 品牌id
     * title : 品牌标题
     * ischeck : 是否为选中的状态(1为选中，0为未选中)
     */

    private List<BclassEntity> bclass;
    /**
     * cid : 商品分类id
     * title : 标题
     * ischeck : 是否为选中的状态(1为选中，0为未选中)
     */

    private List<PclassEntity> pclass;
    /**
     * aid : 属性id
     * title : 标题名称
     * ischeck : 是否为选中的状态(1为选中，0为未选中)
     */

    private List<AclassEntity> aclass;
    /**
     * rid : 认证标示id
     * title : 标题
     * ischeck : 是否为选中的状态(1为选中，0为未选中)
     */

    private List<RclassEntity> rclass;
    /**
     * did : 显示标识id
     * title : 显示标题
     * ischeck : 是否为选中的状态(1为选中，0为未选中)
     */

    private List<DclassEntity> dclass;
    private List<String> imgs;
    /**
     * pid : 推荐商品id
     * pcode : 推荐商品编号
     * title : 推荐商品名称
     */

    private List<ListEntity> list;

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSale_statue(String sale_statue) {
        this.sale_statue = sale_statue;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBclass(List<BclassEntity> bclass) {
        this.bclass = bclass;
    }

    public void setPclass(List<PclassEntity> pclass) {
        this.pclass = pclass;
    }

    public void setAclass(List<AclassEntity> aclass) {
        this.aclass = aclass;
    }

    public void setRclass(List<RclassEntity> rclass) {
        this.rclass = rclass;
    }

    public void setDclass(List<DclassEntity> dclass) {
        this.dclass = dclass;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public String getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }

    public String getSale_statue() {
        return sale_statue;
    }

    public String getCover() {
        return cover;
    }

    public String getDescription() {
        return description;
    }

    public List<BclassEntity> getBclass() {
        return bclass;
    }

    public List<PclassEntity> getPclass() {
        return pclass;
    }

    public List<AclassEntity> getAclass() {
        return aclass;
    }

    public List<RclassEntity> getRclass() {
        return rclass;
    }

    public List<DclassEntity> getDclass() {
        return dclass;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class BclassEntity {
        private String bid;
        private String title;
        private String ischeck;

        public void setBid(String bid) {
            this.bid = bid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getBid() {
            return bid;
        }

        public String getTitle() {
            return title;
        }

        public String getIscheck() {
            return ischeck;
        }
    }

    public static class PclassEntity {
        private String cid;
        private String title;
        private String ischeck;

        public void setCid(String cid) {
            this.cid = cid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCid() {
            return cid;
        }

        public String getTitle() {
            return title;
        }

        public String getIscheck() {
            return ischeck;
        }
    }

    public static class AclassEntity {
        private String aid;
        private String title;
        private String ischeck;

        public void setAid(String aid) {
            this.aid = aid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getAid() {
            return aid;
        }

        public String getTitle() {
            return title;
        }

        public String getIscheck() {
            return ischeck;
        }
    }

    public static class RclassEntity {
        private String rid;
        private String title;
        private String ischeck;

        public void setRid(String rid) {
            this.rid = rid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getRid() {
            return rid;
        }

        public String getTitle() {
            return title;
        }

        public String getIscheck() {
            return ischeck;
        }
    }

    public static class DclassEntity {
        private String did;
        private String title;
        private String ischeck;

        public void setDid(String did) {
            this.did = did;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getDid() {
            return did;
        }

        public String getTitle() {
            return title;
        }

        public String getIscheck() {
            return ischeck;
        }
    }

    public static class ListEntity {
        private String pid;
        private String pcode;
        private String title;

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPid() {
            return pid;
        }

        public String getPcode() {
            return pcode;
        }

        public String getTitle() {
            return title;
        }
    }
}
