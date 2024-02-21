package org.example.domains;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("tacos")
public class Taco {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    //@NotNull
    //@Size(min=5, message = "Name must be at least 5 characters long")
    private String nameTaco;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt;

    //@NotNull
    //@Size(min = 1, message = "You must choose atlease 1 ingredents")
    @Column("ingredients")
    private List<IngredientUTD> ingredientListTaco = new ArrayList<>();

    public void addIngredient(Ingredient taco) {
        this.ingredientListTaco.add(new IngredientUTD(taco.getName(), taco.getType()));
    }

}
