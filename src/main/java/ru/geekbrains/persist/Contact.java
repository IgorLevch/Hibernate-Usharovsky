package ru.geekbrains.persist;

import jakarta.persistence.*;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;

    @Column
    private String data;
    @ManyToOne(optional = false) // поле user_id стало not null и невозможно добавить точно такого пользователя
     @JoinColumn(name="user_id") // название колонки, по которой будет происходить соединение.
    private User user;

    public Contact() {
    }

    public Contact(String type, String data, User user) {
        this.type = type;
        this.data = data;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
