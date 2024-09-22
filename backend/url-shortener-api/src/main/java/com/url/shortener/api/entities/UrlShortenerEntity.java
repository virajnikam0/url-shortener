package com.url.shortener.api.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "url_shortener")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlShortenerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "long_url", nullable = false)
    private String longUrl;
    @Column(name = "short_url", nullable = false)
    private String shortUrl;
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;
    @Column(name = "count", nullable = false)
    private int count;

}
