package com.rdb_aws.rdbCheck;

import jakarta.persistence.*;

@Entity
@Table(name="Book")
public class Enty {
    @Id
    public String id;
    public String bookname;

    public String rown;

    public Enty(String id, String bookname, String rown) {
        this.id = id;
        this.bookname = bookname;
        this.rown = rown;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getRown() {
        return rown;
    }

    public void setRown(String rown) {
        this.rown = rown;
    }

    public Enty() {
    }
}
