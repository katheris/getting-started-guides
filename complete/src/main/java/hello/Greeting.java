package hello;

public class Greeting {

    /** 
     * Revision of the document in the cloudant database. 
     * Cloudant will create this value for new documents.
     */
    private String _rev;

    /** 
     * ID of the document in the cloudant database  
     * Cloudant will create this value for new documents.
     */
    private String _id;

    private String content;

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String rev) {
        this._rev = rev;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}