package pro.sky.adsonlineapp.model;


import lombok.*;

import javax.persistence.*;

/**
 * Сущность комментария
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;


}

