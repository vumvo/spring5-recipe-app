package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    @Lob
    private String direction;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    @Singular(value="ingredient")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "recipe_category",
                joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Singular(value="category")
    private Set<Category> categories = new HashSet<>();

    @Lob
    Byte[] image;

    @Enumerated(value = EnumType.STRING)
    Difficulty difficulty;
}
