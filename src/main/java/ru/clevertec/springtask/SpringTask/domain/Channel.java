package ru.clevertec.springtask.SpringTask.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "author")
    private AppUser author;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "avatar")
    private byte[] avatar;

    @ManyToMany
    @JoinTable(
            name = "user_channel",
            joinColumns = @JoinColumn(name = "channelId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<AppUser> subscribers;

    public void addSubscriber(AppUser user){
        subscribers.add(user);
    }

//https://stackoverflow.com/questions/34485420/how-do-you-put-an-image-file-in-a-json-object

}
