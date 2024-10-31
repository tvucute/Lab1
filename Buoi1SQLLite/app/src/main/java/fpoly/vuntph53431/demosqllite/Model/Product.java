package fpoly.vuntph53431.demosqllite.Model;

public class Product {

    private int id;
    private String title;
    private String content;
    private String date;
    private String status;

    public Product(String status, String date, String content, String title) {
        this.status = status;
        this.date = date;
        this.content = content;
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
