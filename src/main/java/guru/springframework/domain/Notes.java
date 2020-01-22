package guru.springframework.domain;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Builder
public class Notes  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNote;
}
