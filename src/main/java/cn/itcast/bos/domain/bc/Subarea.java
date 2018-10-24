package cn.itcast.bos.domain.bc;



/**
 * Subarea entity. @author MyEclipse Persistence Tools
 */

public class Subarea  implements java.io.Serializable {


    // Fields    

     private String id;   //编码（assined)
     private DecidedZone decidedZone;   //关联定区
     private Region region;             //关联区域
     private String addresskey;         //关键字
     private String startnum;          
     private String endnum;
     private String single;
     private String position;


    // Constructors

    /** default constructor */
    public Subarea() {
    }

	/** minimal constructor */
    public Subarea(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public Subarea(String id, DecidedZone decidedZone, Region region, String addresskey, String startnum, String endnum, String single, String position) {
        this.id = id;
        this.decidedZone = decidedZone;
        this.region = region;
        this.addresskey = addresskey;
        this.startnum = startnum;
        this.endnum = endnum;
        this.single = single;
        this.position = position;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public DecidedZone getDecidedZone() {
        return this.decidedZone;
    }
    
    public void setDecidedZone(DecidedZone decidedZone) {
        this.decidedZone = decidedZone;
    }

    public Region getRegion() {
        return this.region;
    }
    
    public void setRegion(Region region) {
        this.region = region;
    }

    public String getAddresskey() {
        return this.addresskey;
    }
    
    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }

    public String getStartnum() {
        return this.startnum;
    }
    
    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    public String getEndnum() {
        return this.endnum;
    }
    
    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }

    public String getSingle() {
        return this.single;
    }
    
    public void setSingle(String single) {
        this.single = single;
    }

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
   
    
    //因为在定区信息保存的时候，定区的id和分区的id会冲突，因此将返回的分区的数据结果的id值改为subareaId  ,在这里提供getSubareaId()方法
  public String getSubareaId() {
	  return id;
  }





}