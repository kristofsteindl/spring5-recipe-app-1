package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jt on 6/13/17.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Notes() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Notes;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", recipeNotes='" + recipeNotes + '\'' +
                '}';
    }
}
